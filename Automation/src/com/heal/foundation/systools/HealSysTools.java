package com.heal.foundation.systools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.FromStringTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.math.BigDecimal;
import java.net.InetAddress;

/**
 * Main class for application-agnostic system tools through the Green Dot Automation Framework.
 * Contains primarily static methods for interactions with system information and other useful
 * tools for the Automation Framework Foundation.
 *
 */
public class SysTools
{
    private static Logger logger = LoggerFactory.getLogger(SysTools.class);
    private static String propPath = "C:/QA/ATF/conf";

    /**
     * Default constructor for the SysTools class. Initialization allows access to all public methods
     * within the class, regardless of whether or not they are static.
     */
    public SysTools()
    {
    }

    ////////////////////////////
    // File and I/O           //
    ////////////////////////////

    /**
     * Private method used for copying entire directory structures to a destination location. Utilizes
     * a recursive mechanism in order to accomplish the copy of all contents of a given directory
     * tree to a new location. Does not destroy destination directories if they exist. Also can be used
     * for file copying and renaming (though if the file exists in the destination that version of the
     * file is overwritten by this process)
     *
     * @param source
     * (String) - Fully resolved path to the source folder or file location (ex. C:/QA/source)
     *
     * @param destination
     * (String) - Fully resolved path to the source folder or file location (ex. C:/QA/destination)
     *
     * @throws IOException
     * Passes exceptions to the calling application level.
     */
    private static void copyFolder(File source, File destination) throws IOException
    {
//		logger.trace("Beginning File Copy");

        // If the source location is a directory
        if (source.isDirectory())
        {
//			logger.trace("Beginning Folder Walk And Copy");

            // check if the destination directory exists, and create it if it does not
            if (!destination.exists())
                destination.mkdir();

            //list all the directory contents
            String files[] = source.list();

            // cycle through the folders/files within the directory
            for (String file : files)
            {
                //construct the source and destination file structure
                File srcFile = new File(source, file);
                File destFile = new File(destination, file);
                //recursive copy in case a directory folder is found
                copyFolder(srcFile,destFile);
            }
        }
        else
        {
//			logger.trace("Copying File To Location");

            // construct the source and destination file streams
            InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(destination);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes
            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }

            // close the source and destination file streams
            in.close();
            out.close();
        }
    }

    /**
     * Allows for copying of a directory structure with files or a single file from one location
     * to another. Does not create a backup of the destination should the destination already exist. Also
     * does not destroy existing directories should those directories already exist. Also allows the file
     * name to be changed should the method be used to move a single file.
     *
     * @param source
     * (String) - Fully resolved path to the source folder or file location (ex. C:/QA/source)
     *
     * @param destination
     * (String) - Fully resolved path to the source folder or file location (ex. C:/QA/destination)
     *
     * @throws IOException
     * Passes exceptions to the calling application level.
     */
    public static void copyFile(String source, String destination) throws IOException
    {
        logger.trace("Attempting File/Directory Copy");

        // construct the source and destination file/folder locations
        File sourceFile = new File(source);
        File destinationFile = new File(destination);

        // if the source exists
        if (sourceFile.exists())
        {
            // call the recursive copy method copyFolder
            copyFolder(sourceFile, destinationFile);
        }
    }

    /**
     * Allows for copying of a file from one location to another. This method retains the file name during
     * copy while also creating a backup of the file at the destination should the file already exist at the
     * destination. Cannot be used to copy directory structures.
     *
     * @param source
     * (String) - Fully resolved path to the source file location (ex. C:/QA/source)
     *
     * @param destination
     * (String) - Fully resolved path to the destination file location (ex. C:/QA/source)
     *
     * @param fileName
     * (String) - Name of the file being copied
     *
     * @throws IOException
     * Passes exceptions to the calling application level.
     */
    public static void copyFile(String source, String destination, String fileName) throws IOException
    {
//		logger.trace("Attempting File Copy And Backup");

        // make sure that the source path is usable for the file name addition
        if (!source.endsWith("/") || !source.endsWith("\\"))
        {
            if (source.contains("/"))
            {
                source += "/";
            }
            else if (source.contains("\\"))
            {
                source += "\\";
            }
        }

        // make sure that the destination path is usable for the file name addition
        if (!destination.endsWith("/") || !destination.endsWith("\\"))
        {
            if (destination.contains("/"))
            {
                destination += "/";
            }
            else if (destination.contains("\\"))
            {
                destination += "\\";
            }
        }

        // create the source and destination paths, and set the copy options
        File sourcePath = new File(source + fileName);
        File destinationPath = new File(destination + fileName);

        // if the source exists
        if (destinationPath.exists())
        {
            // create a backup of the file
            copyFolder(destinationPath, new File(destinationPath + ".BAK"));
        }

        // call the recursive copy method copyFolder
        copyFolder(sourcePath, destinationPath);
    }

    /**
     * This is method is used to capturing a fullscreen screenshot to a specified folder.
     * The file name is constructed using timestamp in the form of "MMddyyyy-HHmmss".
     *
     * @param sFolderPath
     * (String) - The target folder path to save screen shot.  Do not include ending "/".
     *
     * @return
     * (String) - Full path of file saved.
     */
    public static String getScreenshot(String sFolderPath)
    {
        try
        {
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage capture = new Robot().createScreenCapture(screenRect);

            String fullFilePath = sFolderPath + "/" + getTimestamp() + ".bmp";

            if (!(new File(sFolderPath).exists()))
                (new File(sFolderPath)).mkdir();

            ImageIO.write(capture, "bmp", new File(fullFilePath));

            return fullFilePath;
        }
        catch (Exception e)
        {
            ErrorManagement.HandleError(e);
        }

        return null;
    }

    /**
     * This method is the same as getScreenshot(String sFolderPath) with target folder defaulting
     * to "C:/QA/ATF/out/screenshots"
     *
     * @return
     * (String) - Full path of file saved.
     */
    public static String getScreenshot()
    {
        return getScreenshot("C:/QA/ATF/out/screenshots");
    }

    //////////////////////////////////////
    // Data Generation and Manipulation //
    //////////////////////////////////////

    /**
     * Returns the current date/time value in the specified string format
     *
     * @param sFormat
     * (String) Date/Time display format
     *
     * @return
     * (String) Date/Time value displayed in specified format
     */
    public static String getTimestamp(String sFormat)
    {
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat(sFormat).format(timeStamp);
    }

    /**
     * Returns the current date/time value in the format MMddyyyy-HHmmss
     *
     * @return
     * (String) Date/Time value with format MMddyyyy-HHmmss
     */
    public static String getTimestamp()
    {
        return getTimestamp("MMddyyyy-HHmmss");
    }

    /**
     * Returns a numeric string representation of a randomly generated positive long.
     *
     * @param iLength
     * (int) - Length of return string.
     *
     * @return
     * (String) - numeric string.
     */
    public static String getRandomNumeric(int iLength)
    {
        java.util.Random oRandom = new java.util.Random();
        String oStr = Long.toString(oRandom.nextLong(), 10);
        return oStr.substring(1, 1+iLength);
    }

    /**
     * Get a random character string of input length.
     *
     * @param iLength
     * (int) - Length of string.
     *
     * @return
     * (String)
     */
    public static String getRandomChars(int iLength)
    {
        return RandomStringUtils.random(iLength, true, false);
    }

    /**
     * Convenient method to add/subtract number of days to a given Date object
     *
     * @param oldDate
     * (Date) - The source Date object to add to.
     *
     * @param daysToAdd
     * (int) - Number of days to add or substract (using negative number).
     *
     * @return
     * (Date) - New Date object.
     */
    public static Date addDate(Date oldDate, int daysToAdd)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(oldDate);
        cal.add(Calendar.DATE, daysToAdd);
        return cal.getTime();
    }

    /**
     * Convenient method to compute next business day from today
     *
     * @param idaysToAdd
     * (int) - Number of days to add or subtract (using negative number).
     *
     * @param dFromDate
     * (Date) - Date object to which add/subtract days.
     *
     * @return
     * (Date) - New Date object after computation
     *
     */
    public static Date getBusinessDayFrom(Date dFromDate,int idaysToAdd)
    {
        Calendar cCalendar = Calendar.getInstance();
        cCalendar.setTime(dFromDate);
        if (idaysToAdd == 0)
        {
            return cCalendar.getTime();
        }
        cCalendar.add(Calendar.DATE, 1);
        if (checkIfHoliday(cCalendar.getTime()))
        {
            // Adding one more day if computed date is a non working day //
            return getBusinessDayFrom(cCalendar.getTime(), idaysToAdd);
        }
        else
        {
            return getBusinessDayFrom(cCalendar.getTime(), idaysToAdd - 1);
        }

    }

    /**
     * Convenient method to compute the number of days between two given dates
     *
     * @param startDate
     * (Date) - The starting date to compute the number of days
     *
     * @param endDate
     * (Date) - The ending date till where the days need to be calculated
     *
     * @return
     * (int) - number of days between the two dates
     */
    public static int calculateNumberOfDaysBetween(Date startDate, Date endDate){
		/*if(startDate.after(endDate))
			throw new IllegalArgumentException("Staring date cannot be after ending date");*/

        long startDateTime = startDate.getTime();
        long endDateTime = endDate.getTime();
        long millisPerDay = 1000*60*60*24; //calculate the number of milliseconds in a day which is 86400000
        int numOfDays = (int)((endDateTime - startDateTime) / millisPerDay); //get the difference between dates & convert it into days
        return (numOfDays + 1); //add one day to include start date in the interval
    }

    /**
     * Return the during between two Date object in the string form of "hh:mm:ss"
     *
     * @param start
     * (Date) - Start date.
     *
     * @param end
     * (Date) - End date.
     *
     * @return
     * (String) - Elapsed time in string format.
     */
    public static String getElapsedTimeString(Date start, Date end)
    {
        long millies = end.getTime() - start.getTime();
        return String.format("%1$02d:%2$02d:%3$02d", TimeUnit.MILLISECONDS.toHours(millies), TimeUnit.MILLISECONDS.toMinutes(millies) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millies)), TimeUnit.MILLISECONDS.toSeconds(millies) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millies)));
    }

    /**
     * Return the during between two Date object in the string form of "hh:mm:ss"
     *
     * @param start
     * (long) - Elapsed time in milliseconds.
     *
     * @return
     * (String) - Elapsed time in string format.
     */
    public static String getElapsedTimeString(long millies)
    {
        return String.format("%1$02d:%2$02d:%3$02d", TimeUnit.MILLISECONDS.toHours(millies), TimeUnit.MILLISECONDS.toMinutes(millies) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millies)), TimeUnit.MILLISECONDS.toSeconds(millies) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millies)));
    }

    ////////////////////////////
    // Misc                   //
    ////////////////////////////

    /**
     * Convenience method that wraps around Thread.Sleep()
     *
     * @param lSecondsToWait
     * (long) - Seconds to sleep.
     */
    public static void sleepFor(long lSecondsToWait)
    {
        try
        {
            logger.trace("Sleeping for " + lSecondsToWait + " seconds");
            Thread.sleep(lSecondsToWait * 1000);
        }
        catch (Exception e)
        {
            //ErrorManagement.HandleError(e);
        }
    }

    /**
     * Parse XML from a file into a Document object.
     *
     * @param sXMLFilePath
     * (String) - File path.
     *
     * @return
     * (Document) - Document object.
     */
    public static Document parseXMLFile(String sXMLFilePath)
    {
        try
        {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return docBuilder.parse(sXMLFilePath);
        }
        catch(Exception ex)
        {
            logger.error("Failed to parse XML file:  {}", sXMLFilePath);
            System.exit(1);
        }

        return null;
    }
}
