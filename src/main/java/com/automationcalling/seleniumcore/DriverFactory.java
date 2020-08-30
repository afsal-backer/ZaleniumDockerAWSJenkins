package com.automationcalling.seleniumcore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;

import static com.automationcalling.commonutil.Utils.returnProperties;

public class DriverFactory {
    WebDriver driver;
    DesiredCapabilities caps;

    public WebDriver getBrowserInit(String browserType, String executionType) throws IOException 
    {
        if (executionType.equalsIgnoreCase("remote")) {
            initializeRemoteDriver(browserType);
        } else if (executionType.equalsIgnoreCase("local")) {
            initializeLocalDriver(browserType);
        } else if (executionType.equalsIgnoreCase("cloud")) {
            initializeCloudDriver(browserType);
        }
        return driver;
    }

    public void initializeRemoteDriver(String browserType) throws IOException {
        if (browserType.equalsIgnoreCase("chrome")) {
            caps = DesiredCapabilities.chrome();
        } else if (browserType.equalsIgnoreCase("firefox")) {
            caps = DesiredCapabilities.firefox();
        }

        driver = new RemoteWebDriver(new URL("http://" + returnProperties("HubName") + ":4444/wd/hub"), caps);
     
    }

    public void initializeCloudDriver(String browserType) throws IOException 
    {
        if (browserType.equalsIgnoreCase("chrome")) 
        {
            caps = DesiredCapabilities.chrome();
            caps.setCapability("platform", "WINDOWS");
            caps.setCapability("browserName", "chrome");
            caps.setCapability("version", "79.0.3945.130");
        } 
        else if (browserType.equalsIgnoreCase("firefox")) 
        {
            caps = DesiredCapabilities.firefox();
            caps.setCapability("platformName", "MAC");
            caps.setCapability("browserName", "firefox");
            caps.setCapability("version", "latest");
        } 
        else if (browserType.equalsIgnoreCase("safari")) 
        {
            caps = DesiredCapabilities.safari();
            caps.setCapability("platformName", "MAC");
            caps.setCapability("browserName", "safari");
            caps.setCapability("version", "latest");
        }
        driver = new RemoteWebDriver(new URL("http://" + returnProperties("HubName") + ":4444/wd/hub"), caps);
 
    }


    public void initializeLocalDriver(String browserType) throws IOException {
        if (browserType.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
       
    }

}
