package com.fanaticaltest.test_factory_demo.lib;

import cucumber.api.Scenario;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;


public class BasePage {

  private DesiredCapabilities capabilities;
  private RemoteWebDriver driver;
  private final Logger logger = LoggerFactory.getLogger(BasePage.class);
  private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
  private Property prop = new Property();
  private long timeout_in_second = Long.parseLong(prop.read("selenium_timeout_in_second"), 10);
  private int window_width = Integer.parseInt(prop.read("selenium_window_width"));
  private int window_height = Integer.parseInt(prop.read("selenium_window_height"));
  private String screenshot_path = prop.read("selenium_screenshot_path");
  private String remoteDriverUrl = prop.read("selenium_url");
  private Stats stats = new Stats();

  public enum browserNameOS {CHROME_PC, FIREFOX_PC, CHROME_MAC, FIREFOX_MAC, CHROME_LINUX, FIREFOX_LINUX, IEXPLORER_PC}

  public void beforeScenario(browserNameOS browser) throws MalformedURLException {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    stats.setStartTime(sdf.format(timestamp));
    logger.info("============================Scenario starts=========================================");

    switch (browser) {
      case CHROME_PC:
        capabilities = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL(remoteDriverUrl), capabilities);
        driver.manage().window().setSize(new Dimension(window_width, window_height));
        break;
      case FIREFOX_PC:
        capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        driver = new RemoteWebDriver(new URL(remoteDriverUrl), capabilities);
        driver.manage().window().setSize(new Dimension(window_width, window_height));
        break;
      case CHROME_LINUX:
        capabilities = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL(remoteDriverUrl), capabilities);
        driver.manage().window().setSize(new Dimension(window_width, window_height));
        break;
      default:
        capabilities = DesiredCapabilities.firefox();
        driver = new RemoteWebDriver(new URL(remoteDriverUrl), capabilities);
        driver.manage().window().setSize(new Dimension(window_width, window_height));
        break;
    }
  }

  public void beforeScenario() throws MalformedURLException {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    stats.setStartTime(sdf.format(timestamp));
    logger.info("============================Scenario starts=========================================");
  }

  public void afterScenario(Scenario scenario, boolean isSelenium) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    stats.setEndTime(sdf.format(timestamp));
    stats.setScenario(scenario);
    stats.setWindowWidth(window_width);
    stats.setWindowHeight(window_height);
    stats.setTimeoutInSecond(timeout_in_second);
    stats.setScreenshotName("none");
    //Take screenshot if scenario fails and selenium
    if (isSelenium == true){
      try {
        if (scenario.isFailed()) {
          getScreenshot("fail_" + stats.getEndTime() + ".png");
          stats.setScreenshotName("fail_" + stats.getEndTime() + ".png");
        }
      } catch (Exception e) {
        logger.error("Error when taking a screenshot");
        e.printStackTrace();
      }
      //close instance
      try {
        driver.quit();
      } catch (Exception e) {
        logger.error("Error when closing the browser");
        e.printStackTrace();
      }
    }
    stats.render();
    logger.info("=============================Scenario ends==========================================");
  }

  public void loadPage(String urlWebsite) {
    logger.info("Page url requested : {}", urlWebsite);
    driver.get(urlWebsite);
  }

  public void fillFieldBy(String value, By by) {
    WebElement field = findElement(by, timeout_in_second);
    field.clear();
    field.sendKeys(value);
    logger.info("Filled field with : {}", value);
  }

  public void clickElementBy(By by) {
    WebElement elem = findElement(by, timeout_in_second);
    elem.click();
    logger.info("Clicked on element : {}", by.toString());
  }

  public void clickElementByForced(By by) {
    WebElement elem = findElement(by, timeout_in_second);
    //This work around help sometimes with some picture elements
    elem.sendKeys("\n");
    elem.click();
    logger.info("Clicked on element : {}", by.toString());
  }

  public void assertPageTitle(String title, String url) {
    waitForUrlRedirect(url, timeout_in_second);
    logger.info("Assert page title equal to : {}", title);
    logger.info("Current page title is: {}", driver.getTitle());
    assertThat(driver.getTitle(), containsString(title));
  }

  public void assertTextInElementBy(String value, By by) {
    WebElement elem = findElement(by, timeout_in_second);
    logger.info("Assert value: {}, for element : {}", value, by);
    assertThat(elem.getText(), containsString(value));
  }

  public void waitAndAssertTextInElementBy(String value, By by)
  {
    WebElement elem = findElement(by, timeout_in_second);
    logger.info("Assert value after waiting: {}, for element : {}", value, by);
    if (waitForText(by,timeout_in_second,value))
      assertThat(elem.getText(), containsString(value));
  }

  public boolean waitForText(By by, long timeoutInSeconds, String value)
  {
    logger.info("Wait for text {}, for element : {} ", value, by);
    return new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.textToBePresentInElementLocated(by, value));
  }

  public void assertAttributeInElementBy(String attributeName, String value, By by) {
    WebElement elem = findElement(by, timeout_in_second);
    logger.info("Assert value: {},  for element : {}, on attribute : {} ", value, by, attributeName);
    assertThat(elem.getAttribute(attributeName), containsString(value));
  }

  public WebElement waitUntilActive(By by, long timeoutInSeconds) {
    logger.info("Search element is active: {}", by.toString());
    return new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.presenceOfElementLocated(by));
  }

  public WebElement waitUntilVisible(final By by, long timeoutInSeconds) {
    logger.info("Search element is visible: {}", by.toString());
    return new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(by));
  }

  public void waitForUrlRedirect(String url, long timeoutInSeconds) {
    logger.info("Wait page redirect url: {}", url);
    (new WebDriverWait(driver, timeoutInSeconds)).until(ExpectedConditions.urlContains(url));
  }

  public void waitForUrlRedirect(String url) {
    waitForUrlRedirect(url,timeout_in_second);
  }

  public WebElement findElement(By by, long timeoutInSeconds) {
    return waitUntilVisible(by, timeoutInSeconds);
  }

  public void getScreenshot(String pngFileName) throws Exception {
    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile, new File(screenshot_path + pngFileName));
    logger.info("Screenshot taken and placed in : {} ", screenshot_path + pngFileName);
  }

  public void selectDropDownByValue(By by, String value){
    Select select = new Select(findElement(by, timeout_in_second));
    select.selectByValue(value);
  }

  public void selectDropDownByVisibleText(By by, String visibleText){
    Select select = new Select(findElement(by, timeout_in_second));
    select.selectByVisibleText(visibleText);
  }

  public void selectDropDownByIndex(By by, int index){
    Select select = new Select(findElement(by, timeout_in_second));
    select.selectByIndex(index);
  }

  public String getDropDownSelectedAttribute(By by, String attribute){
    Select select = new Select(findElement(by, timeout_in_second));
    WebElement option = select.getFirstSelectedOption();
    logger.info("Selected value in dropdown: {}, with attribute: {}, is:{} ", by.toString(), attribute, option.getAttribute(attribute));
    return option.getAttribute(attribute);
  }

  public String getDropDownSelectedValue(By by){
    return getDropDownSelectedAttribute(by,"value");
  }

  public String getInnerHtmlValue(By by){
    WebElement elem = findElement(by, timeout_in_second);
    logger.info("Get html value {}, in object {} ", elem.getAttribute("innerHTML"), by.toString() );
    return elem.getAttribute("innerHTML");
  }

  public void freezeProcess(long timeInSecond)
  {
    logger.info("Freezing process for {} seconds", timeInSecond );
    try {
      Thread.sleep(timeInSecond*1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void getIntermediateScreenshots(String prefix)
  {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    try {
      getScreenshot(prefix + "_" + sdf.format(timestamp)+ ".png");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void mouseOverOneHop(By firstElem, By secondElem)
  {
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(firstElem)).moveToElement(driver.findElement(secondElem)).click().build().perform();
  }
}
