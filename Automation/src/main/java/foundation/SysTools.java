package foundation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;


/**
 * Contains primarily static methods for interactions with system information and other useful
 * tools for the Automation Framework.
 *
 */
public class SysTools
{
//    private static Logger logger = LoggerFactory.getLogger(SysTools.class);

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
     * This is method is used to capture a fullscreen screenshot to a specified folder.
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
//            CommonException.HandleError(e);
        }

        return null;
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
 //           logger.trace("Sleeping for " + lSecondsToWait + " seconds");
            Reporter.log("Sleeping for " + lSecondsToWait + " seconds");
            Thread.sleep(lSecondsToWait * 1000);
        }
        catch (Exception e)
        {
            //ErrorManagement.HandleError(e);
        }
    }
}
