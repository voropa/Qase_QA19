import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class BaseTest {

    @BeforeClass
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        setWebDriver(driver);
        Configuration.webdriverLogsEnabled = true;
        Configuration.timeout = 10000;
        Configuration.headless = false;
        Configuration.baseUrl = "https://teachmeskills.by/";
        Configuration.browser = "chrome";
        Configuration.reportsFolder = "target";
        getWebDriver().manage().window().maximize();
    }

    @AfterClass
    public void tearDown () {
        WebDriver driver = getWebDriver();
        driver.navigate().refresh();
        driver.quit();
    }
}
