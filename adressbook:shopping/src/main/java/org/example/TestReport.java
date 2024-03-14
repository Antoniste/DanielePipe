package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class TestReport {
    static WebDriver driver;
    private ExtentReports extentReports;
    private ExtentTest extentTest;

    public void setupReport() { //ATTENZIONE, QUESTO NON FUNZIONA BENE SPESSO ExtentHtmlReporter USARE QUESTO ExtentSparkReporter DALLA VERSIONE 5 IN POI
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-report.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Host Name", "Nome del tuo host");
        extentReports.setSystemInfo("Environment", "Ambiente di test");
    }

    public void startTest(String testName, String description) {
        extentTest = extentReports.createTest(testName, description);
    }

    public void pass(String message) {
        extentTest.log(Status.PASS, message);
    }


    public void fail(WebDriver driver, String messageFail) {
        try {
            String base64String = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            extentTest.fail(messageFail, MediaEntityBuilder.createScreenCaptureFromBase64String(base64String).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public void tearDown() {
        extentReports.flush();
    }
}

