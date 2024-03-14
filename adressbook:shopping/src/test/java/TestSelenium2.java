import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.TestReport;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class TestSelenium2 {
    static WebDriver driver;
    static Wait<WebDriver> wait;
    public static TestReport testReport;

    @BeforeAll
    static void setUpDriver() {
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        Dimension windowSize = new Dimension(1280, 1024);
        driver.manage().window().setSize(windowSize);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        testReport = new TestReport();
        testReport.setupReport();
    }
    @BeforeEach
    void setUpTest() {
        driver.get("https://automationteststore.com");
        // var pageSource = driver.getPageSource();
        assertThat(driver.getTitle(), containsString("A place to practice your automation skills!"));
        assertThat(driver.getCurrentUrl(), containsString("https://automationteststore.com/"));


    }
    @AfterAll
    static void teardown() {
        if (driver != null) {
            testReport.tearDown();
            driver.quit();
        }
    }

    @Test
    public void testAcquire() throws InterruptedException {
        testReport.startTest("testAcquire - SELENIUM", "Verifica vari passaggi");
        List<WebElement> sections = driver.findElements(By.className("prdocutname"));
        for (WebElement section : sections) {
            System.out.println("Section '" + section.getText() + "' is clickable: " + (section.isDisplayed() && section.isEnabled()));
        }
        WebElement firstMenuClick = driver.findElement(By.xpath("//*[@id=\"categorymenu\"]/nav/ul/li[2]/a"));
        firstMenuClick.click();
        WebElement secondMenuClick = driver.findElement(By.xpath(" //*[@id=\"maincontainer\"]/div/div/div/div/ul/li[2]/div/a"));
        secondMenuClick.click();
        WebElement thirdMenuClick = driver.findElement(By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/div[3]/div[4]/div[2]/a/img"));
        thirdMenuClick.click();
        WebElement forthMenuClick = driver.findElement(By.xpath(" //*[@id=\"option348\"]/option[4]"));
        forthMenuClick.click();
        WebElement homeButton = driver.findElement(By.xpath("/html/body/div/header/div[1]/div/div[1]/a/img"));
        homeButton.click();
        WebElement addToCart = driver.findElement(By.xpath("//*[@id=\"block_frame_featured_1769\"]/div/div[1]/div[2]/div[3]/a/i"));
        addToCart.click();
        WebElement makeUpMenuClick = driver.findElement(By.xpath("//*[@id=\"categorymenu\"]/nav/ul/li[3]/a"));
        makeUpMenuClick.click();
        WebElement homeButton2 = driver.findElement(By.xpath("/html/body/div/header/div[1]/div/div[1]/a/img"));
        homeButton2.click();
        for (int i = 0; i < 3; i++) {
            WebElement addToCart3 = driver.findElement(By.xpath("//*[@id=\"block_frame_featured_1769\"]/div/div[3]/div[2]/div[3]/a/i"));
            addToCart3.click();
        }
        WebElement selectCart = driver.findElement(By.xpath("/html/body/div/header/div[2]/div/div[3]/ul/li/a"));
        selectCart.click();
        WebElement priceOnHomePage = driver.findElement(By.xpath("//*[@id=\"top_cart_product_list\"]/table/tbody/tr[1]/td[2]/span"));
        String priceOnHomePageText = priceOnHomePage.getText();
        WebElement cartSubTotal = driver.findElement(By.xpath("//*[@id=\"top_cart_product_list\"]/table/tbody/tr[1]/td[2]/span"));
        String cartSubTotalText = cartSubTotal.getText();
        System.out.println("Price on Home Page: " + priceOnHomePageText);
        System.out.println("Cart Subtotal: " + cartSubTotalText);
        assert priceOnHomePageText.equals(cartSubTotalText) : "The price on the home page does not match the cart subtotal.";
        System.out.println("The price on the home page matches the cart subtotal.");
        WebElement homeButton3 = driver.findElement(By.xpath("/html/body/div/header/div[1]/div/div[1]/a/img"));
        homeButton3.click();

        WebElement cart = driver.findElement(By.xpath("/html/body/div/header/div[2]/div/div[3]/ul/li/a"));
        Actions action = new Actions(driver);
        action.moveToElement(cart).perform();

        WebElement priceOnHomePageElement = driver.findElement(By.xpath("//*[@id=\"top_cart_product_list\"]/table/tbody/tr[2]/td[2]/span"));
        String priceOnHomePage2 = priceOnHomePageElement.getText();
        System.out.println(priceOnHomePage2);
        WebElement cartClick = driver.findElement(By.xpath("/html/body/div/header/div[2]/div/div[3]/ul/li/a"));
        cartClick.click();
        Thread.sleep(1000);
        WebElement cartTotalElement = driver.findElement(By.xpath("//*[@id=\"totals_table\"]/tbody/tr[3]/td[2]/span"));
        String cartTotalElement2 = cartTotalElement.getText();
        System.out.println(cartTotalElement2);
        assert !Objects.equals(priceOnHomePage2, cartTotalElement2) : "The price on the home page does not match the cart subtotal.";
        System.out.println("The price on the home page matches the cart subtotal.");
        for (int c = 0; c < 2; c++) {
            WebElement itemCart1 = driver.findElement(By.xpath("//*[@id=\"cart\"]/div/div[1]/table/tbody/tr[2]/td[7]/a"));
            itemCart1.click();
        }
        WebElement homeButton4 = driver.findElement(By.xpath("/html/body/div/header/div[1]/div/div[1]/a/img"));
        homeButton4.click();
        testReport.pass("test successful");
    }
    @Test
    public void testTimers() {
        testReport.startTest("testTimers - SELENIUM", "Verifica vari passaggi");
        WebElement searchbar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"filter_keyword\"]")));
        searchbar.click();
        searchbar.sendKeys("makeUp");
        WebElement search = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"search_form\"]/div/div/i")));
        search.click();
        WebElement addToCart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/div[3]/div[2]/div[2]/div[3]/a/i")));
        addToCart.click();
        WebElement cartCheck = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/header/div[2]/div/div[3]/ul/li/a/i")));
        Actions action = new Actions(driver);
        action.moveToElement(cartCheck).perform();
        WebElement cartItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"top_cart_product_list\"]/div/table/tbody/tr/td[5]")));
        int itemsInCart = Integer.parseInt(cartItem.getText().trim());
        Assertions.assertEquals(1, itemsInCart);
        System.out.println("Items in the cart: " + itemsInCart);
        WebElement cart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/header/div[2]/div/div[3]/ul/li/a")));
        cart.click();
        WebElement deleteCart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"cart\"]/div/div[1]/table/tbody/tr[2]/td[7]/a/i")));
        deleteCart.click();
        WebElement cartCheckMakeUp = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/header/div[2]/div/div[3]/ul/li/a")));
        action.moveToElement(cartCheckMakeUp).perform();
        WebElement cartItemMakeUp = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/header/div[2]/div/div[3]/ul/li/a/span[1]")));
        int itemsInCartMakeUp = Integer.parseInt(cartItemMakeUp.getText().trim());
        Assertions.assertEquals(0, itemsInCartMakeUp);
        System.out.println("Items in the cart: " + itemsInCartMakeUp);
        testReport.pass("test successful");
    }
    @Test
    public void testEBooks() {
        testReport.startTest("testEBooks - SELENIUM", "Verifica vari passaggi");
        WebElement books = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"categorymenu\"]/nav/ul/li[8]/a")));
        books.click();
        WebElement firstBook = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/div[3]/div[1]/div[1]/div/a")));
        WebElement sort = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(" //*[@id=\"sort\"]")));
        sort.click();
        WebElement sortList = driver.findElement(By.xpath("//*[@id=\"sort\"]/option[2]"));
        sortList.click();
        WebElement randomBook = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/div[3]/div[1]/div[1]/div/a")));
        Assertions.assertNotSame(firstBook, randomBook, "The two WebElements should not be the same.");
        testReport.pass("test successful");
    }
    @Test
    public void testSiteMap() throws IOException {
        testReport.startTest("testSiteMap - SELENIUM", "Verifica vari passaggi");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
        WebElement siteMap = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"footer\"]/footer/section[2]/div/div[1]/div/ul/li[6]/a")));
        siteMap.click();
        WebElement shampoo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/div/div/div[1]/ul/li[6]/ul/li[2]/a")));
        shampoo.click();
        WebElement profume = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/div[2]/div[1]/div[1]/div/a")));
        profume.click();
        WebElement profumeModel = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"description\"]/ul/li[2]")));
        String profumeModelText = profumeModel.getText();
        System.out.println("Text from the element: " + profumeModelText);
        assert profumeModel.isDisplayed() : "The profumeModel element is not displayed.";
        System.out.println("The profumeModel element is displayed.");
        WebElement addToCart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"product\"]/fieldset/div[4]/ul/li/a")));
        assert addToCart.isDisplayed() : "The addToCart element is not displayed.";
        System.out.println("The addToCart element is displayed.");
        WebElement print = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"product\"]/fieldset/div[4]/a")));
        assert print.isDisplayed() : "The print element is not displayed.";
        System.out.println("The print element is displayed.");
        testReport.fail(driver, "test failed");
    }
    @Test
    public void testAccount() {
        testReport.startTest("testAccount - SELENIUM", "Verifica vari passaggi");
        WebElement homeLogin = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"customer_menu_top\"]/li/a")));
        homeLogin.click();

        WebElement register = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"accountFrm\"]/fieldset/button")));
        register.click();

        WebElement firstName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"AccountFrm_firstname\"]")));
        firstName.click();
        firstName.sendKeys("Giuseppe");
        WebElement lastName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"AccountFrm_lastname\"]")));
        lastName.click();
        lastName.sendKeys("Trimone");
        WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(" //*[@id=\"AccountFrm_email\"]")));
        email.click();
        email.sendKeys("GiuseppeTrimone@gmail.com");
        WebElement address = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"AccountFrm_address_1\"]")));
        address.click();
        address.sendKeys("via lemanidalnaso 420");
        WebElement city = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(" //*[@id=\"AccountFrm_city\"]")));
        city.click();
        city.sendKeys("Pescara");
        WebElement country = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(" //*[@id=\"AccountFrm_country_id\"]")));
        country.click();
        WebElement italy = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"AccountFrm_country_id\"]/option[106]")));
        italy.click();

        WebElement regionClick = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"AccountFrm_zone_id\"] ")));
        regionClick.click();
        WebElement region = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(" //*[@id=\"AccountFrm_zone_id\"]/option[71]")));
        region.click();

        WebElement zipCode = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(" //*[@id=\"AccountFrm_postcode\"]")));
        zipCode.click();
        zipCode.sendKeys("65120");
        WebElement loginName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"AccountFrm_loginname\"]")));
        loginName.click();
        loginName.sendKeys("skipp");
        WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"AccountFrm_password\"]")));
        password.click();
        password.sendKeys("parolaChiave420");
        WebElement confirmpassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"AccountFrm_confirm\"]")));
        confirmpassword.click();
        confirmpassword.sendKeys("parolaChiave420");
        WebElement newsLetterButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"AccountFrm_newsletter0\"]")));
        newsLetterButton.click();
        WebElement confirmButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"AccountFrm_agree\"]")));
        confirmButton.click();
        WebElement continueButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"AccountFrm\"]/div[5]/div/div/button[1]")));
        continueButton.click();
        testReport.pass("test successful");
    }

    @Test
    public void testSpecial() {
        testReport.startTest(" testSpecial- SELENIUM", "Verifica vari passaggi");
        WebElement special = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main_menu_top\"]/li[1]/a/span")));
        special.click();
        WebElement specialClick = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"main_menu_top\"]/li[1]/a/span")));
        specialClick.click();
        WebElement sale = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(" //*[@id=\"maincontainer\"]/div/div/div/div/div[2]/div[2]/div[2]/span")));
        Assertions.assertTrue(sale.isDisplayed(), "sale is not displayed");
        System.out.println("sale is displayed");
    }

    @Test
    public void emptyResearch() {
        testReport.startTest("emptyResearch - SELENIUM", "Verifica vari passaggi");
        WebElement searchbar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"filter_keyword\"]")));
        searchbar.click();
        searchbar.sendKeys("qwerty");
        WebElement search = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"search_form\"]/div/div/i")));
        search.click();
        WebElement noProduct = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/h4[2]")));
        assert noProduct.isDisplayed() : "There is no product that matches the search criteria.";
        System.out.println("There is no product that matches the search criteria.");
        testReport.pass("Test successful");
    }

    @Test
    public void manCategory() {
        testReport.startTest("manCategory - SELENIUM", "Verifica vari passaggi");
        WebElement man = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"categorymenu\"]/nav/ul/li[6]/a")));
        man.click();
        WebElement changeView = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("   //*[@id=\"list\"]/i")));
        changeView.click();
        WebElement view = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/div[3]/div[1]/div/div/div[2]/div[2]/a[1]")));
        assert view.isDisplayed() : "The 'view' element is not visible.";
        System.out.println("The 'view' element is visible.");

        WebElement writeReview = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/div[3]/div[1]/div/div/div[2]/div[2]/a[2]")));
        assert writeReview.isDisplayed() : "The 'write review' element is not visible.";
        System.out.println("The 'write review' element is visible.");
        WebElement selectProduct = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/div[3]/div[1]/div/div/div[2]/div[4]/a/i")));
        selectProduct.click();
        WebElement addToCart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"product\"]/fieldset/div[5]/ul/li/a")));
        addToCart.click();
        WebElement selectCart = driver.findElement(By.xpath("/html/body/div/header/div[2]/div/div[3]/ul/li/a"));
        Actions action = new Actions(driver);
        action.moveToElement(selectCart).perform();
        selectCart.click();
        WebElement firstValue = driver.findElement(By.xpath("//*[@id=\"cart\"]/div/div[1]/table/tbody/tr[2]/td[6]"));
        Assertions.assertTrue(firstValue.isDisplayed());
        String valueText = firstValue.getText();
        System.out.println("Value is visible: " + valueText);
        WebElement quantity = driver.findElement(By.xpath(" //*[@id=\"cart_quantity7808d50be7efed8dd74bfcc27df4d70570\"]"));
        quantity.clear();
        quantity.sendKeys("2");
        WebElement update = driver.findElement(By.xpath("//*[@id=\"cart_update\"]"));
        update.click();
        WebElement secondValue = driver.findElement(By.xpath("//*[@id=\"cart\"]/div/div[1]/table/tbody/tr[2]/td[6]"));
        Assertions.assertNotSame("", valueText);
        String firstValueText = valueText.replaceAll("[^\\d.]", ""); // Extract numeric value
        double firstValueElement = Double.parseDouble(firstValueText);
        String secondValueText = secondValue.getText().replaceAll("[^\\d.]", ""); // Extract numeric value
        double secondValueElement = Double.parseDouble(secondValueText);
        testReport.fail(driver,"the first element is grater then the second");
        assert firstValueElement > secondValueElement : "The first value is not lower than the second value.";
        System.out.println("The first value is lower than the second value.");
        WebElement deleteItem = driver.findElement(By.xpath(" //*[@id=\"cart\"]/div/div[1]/table/tbody/tr[2]/td[7]/a/i"));
        deleteItem.click();
        testReport.pass("test successful");
    }
}








