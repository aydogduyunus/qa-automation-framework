package com.qaautomation.base;

import com.qaautomation.pages.TextBoxPage;
import com.qaautomation.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected WebDriver driver;
    protected TextBoxPage textBoxPage;
    protected String browserName; // ✅ Bu satır kritik

    @Parameters("browser")
    @BeforeClass
    public void setup(@Optional("chrome") String browserParam) throws MalformedURLException {
        browserName = browserParam; // ✅ Gelen parametreyi field'a aktar
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browserName.equalsIgnoreCase("firefox")) {
            capabilities.setBrowserName("firefox");
        } else {
            capabilities.setBrowserName("chrome");
        }

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        textBoxPage = new TextBoxPage(driver, browserName); // ✅ Sorun buradaydı, şimdi düzgün
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
        if (!result.isSuccess()) {
            ScreenshotUtil.takeScreenshot(driver, "Failure - " + result.getMethod().getMethodName());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}