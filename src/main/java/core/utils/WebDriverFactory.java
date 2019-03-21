package core.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {

    private WebDriverFactory() {
    }

    public static WebDriver getWebDriverImpl(final String driverName) {
        switch (driverName) {
            case "chrome" :
                System.getProperty("webdriver.chrome.driver", "chromedriver.exe");
                return new ChromeDriver();
            case "firefox":
                System.getProperty("webdriver.firefox.driver", "geckodriver.exe");
                return new FirefoxDriver();
            case "ie":
                return new InternetExplorerDriver();
            case "safari":
                return new SafariDriver();
            default:
                throw new IllegalStateException("Unsupportable driver type found!");
        }
    }
}
