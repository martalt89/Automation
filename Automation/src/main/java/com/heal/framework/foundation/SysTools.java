package com.heal.framework.foundation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.heal.framework.test.RunTestSuite;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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
     * This method is used to capture a fullscreen screenshot to a specified folder.
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
     * (String) - Date/Time display format
     *
     * @return
     * (String) - Date/Time value displayed in specified format
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
     * (String) - Date/Time value with format MMddyyyy-HHmmss
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
     * Convenient method that wraps around Thread.Sleep()
     *
     * @param lSecondsToWait
     * (long) - Seconds to sleep.
     */
    public static void sleepFor(long lSecondsToWait)
    {
        try
        {
 //           logger.trace("Sleeping for " + lSecondsToWait + " seconds");
            Reporter.log("Sleeping for " + lSecondsToWait + " seconds <br>");
            Thread.sleep(lSecondsToWait * 1000);
        }
        catch (Exception e)
        {
//            ErrorManagement.HandleError(e);
            Reporter.log(e.toString() + " <br>");
        }
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
     * @param millies
     * (long) - Elapsed time in milliseconds.
     *
     * @return
     * (String) - Elapsed time in string format.
     */
    public static String getElapsedTimeString(long millies)
    {
        return String.format("%1$02d:%2$02d:%3$02d", TimeUnit.MILLISECONDS.toHours(millies), TimeUnit.MILLISECONDS.toMinutes(millies) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millies)), TimeUnit.MILLISECONDS.toSeconds(millies) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millies)));
    }

    /**
     * Convenient method to get the goTo code from the URL
     *
     * @param dr
     * (WebDriver) - Drive from which to get the URL
     *
     * @return
     * (String)
     */
    public static String getVisitCodeFromURL(WebDriver dr){
        String visitCode;
        String currentURL = dr.getCurrentUrl();
        int slashIndex;
        slashIndex = StringUtils.ordinalIndexOf(currentURL, "/", 4) + 1;
        if (slashIndex > 0) {
            visitCode = currentURL.substring(slashIndex);
        } else {
            visitCode = currentURL;
        }
        return visitCode;
    }



    public static String healTime10MinAhead(){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone("PST"));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 10);
        return formatter.format(cal.getTime());
    }



    public static String getMD5(String msg, String keyString, String algo) {
        String digest = null;
        try {
            SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), algo);
            Mac mac = Mac.getInstance(algo);
            mac.init(key);

            byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

            StringBuffer hash = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            digest = hash.toString();
        } catch (UnsupportedEncodingException e) {
        } catch (InvalidKeyException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        return digest;
    }

    public static String HMAC(String values, String myKeyString){
        String output = "";
        try {

            PBEKeySpec keySpec = new PBEKeySpec(myKeyString.toCharArray());
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey key = skf.generateSecret(keySpec);

            //I use "hmacMD5" here becuase when I use
            //key.getAlgorithm() it returns nothing
            Mac mac = Mac.getInstance("hmacMD5");
            mac.init(key);


            // Encode the string into bytes using utf-8 and digest it
            byte[] utf8 = values.getBytes("utf8");
            byte[] digest = mac.doFinal(utf8);

            //convert the digest into a string
            String digestB64 = new sun.misc.BASE64Encoder().encode(digest);
            output += digestB64;

        } catch (InvalidKeyException e) {
        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (InvalidKeySpecException e) {
        }

        return output;
    }

    /**
     * This method will check weather the driver is remote safari driver.
     *
     * @param webDriver
     * @return Boolean
     */
    public static Boolean isRemoteSafari(WebDriver webDriver){
        if (webDriver instanceof RemoteWebDriver){
            if (((RemoteWebDriver) webDriver).getCapabilities().getBrowserName().equalsIgnoreCase("safari")){
                return true;
            }
            return false;
        }
        return false;
    }


}
