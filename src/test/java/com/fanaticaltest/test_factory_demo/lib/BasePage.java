package com.fanaticaltest.test_factory_demo.lib;

import cucumber.api.Scenario;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
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
  String timeStartScenario;
  String timeEndScenario;
  private LogTest logTest = new LogTest();
  private int ft_test_log_required = Integer.parseInt(prop.read("ft_test_log_required"));

  public enum browserNameOS {CHROME_PC, FIREFOX_PC, CHROME_MAC, FIREFOX_MAC, CHROME_LINUX, FIREFOX_LINUX, IEXPLORER_PC}

  public void beforeScenario(browserNameOS browser) throws MalformedURLException {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    timeStartScenario = sdf.format(timestamp);
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
    timeStartScenario = sdf.format(timestamp);
    logger.info("============================Scenario starts=========================================");
  }

  public void afterScenario(Scenario scenario, boolean isSelenium) {
    String urlRestApi = prop.read("ft_test_log_url");
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    timeEndScenario = sdf.format(timestamp);
    try {
      logger.info("******Scenario statistics******");
      logger.info("Test started at : {} ", timeStartScenario);
      urlRestApi += "testStartDate=" + URLEncoder.encode(timeStartScenario, "UTF-8") +"&";
      logger.info("Test finished at : {} ", timeEndScenario);
      urlRestApi += "testEndDdate=" + URLEncoder.encode(timeEndScenario, "UTF-8") +"&";
      logger.info("Scenario status : {} ", scenario.getStatus());
      urlRestApi += "testStatus=" + URLEncoder.encode(scenario.getStatus(), "UTF-8") +"&";
      logger.info("Tags : {} ", scenario.getSourceTagNames());
      urlRestApi += "tags=" + URLEncoder.encode(scenario.getSourceTagNames().toString(), "UTF-8") +"&";
      logger.info("Project Id : {} ", Tags.getProject(scenario.getSourceTagNames()));
      urlRestApi += "projectId=" + URLEncoder.encode(Tags.getProjectId(scenario.getSourceTagNames()), "UTF-8") +"&";
      logger.info("Feature name : {} ", Tags.getFeature(scenario.getSourceTagNames()));
      urlRestApi += "feature=" + URLEncoder.encode(Tags.getFeature(scenario.getSourceTagNames()), "UTF-8") +"&";
      logger.info("Scenario id : {} ", Tags.getId(scenario.getSourceTagNames()));
      urlRestApi += "scenarioId=" + URLEncoder.encode(Tags.getId(scenario.getSourceTagNames()), "UTF-8") +"&";
      logger.info("Scenario name : {} ", scenario.getName());
      urlRestApi += "scenarioName=" + URLEncoder.encode(scenario.getName(), "UTF-8") +"&";
      logger.info("Set window size to : {} by {}", window_width, window_height);
      urlRestApi += "testWindowsSize=" + URLEncoder.encode(window_width + " by "+window_height, "UTF-8") +"&";
      logger.info("Set default timeout to : {} ", timeout_in_second);
      urlRestApi += "testTimeout=" + URLEncoder.encode(String.valueOf(timeout_in_second), "UTF-8") +"&";
      logger.info("Test Suite : {} ", Tags.getTestSuite(scenario.getSourceTagNames()));
      urlRestApi += "testSuite=" + URLEncoder.encode(Tags.getTestSuite(scenario.getSourceTagNames()), "UTF-8") +"&" ;
    } catch (UnsupportedEncodingException e) {
      logger.info("UnsupportedEncodingException : {} ", e.toString());
    }
    //Take screenshot if scenario fails and selenium
    if (isSelenium == true){
      try {
        if (scenario.isFailed()) {
          getScreenshot(timeEndScenario + ".png");
          urlRestApi += "screenshotUrl=" + URLEncoder.encode(timeEndScenario + ".png", "UTF-8") +"&";
        }
        else
        {
          urlRestApi += "screenshotUrl=" + URLEncoder.encode("none", "UTF-8") +"&";
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
    if (ft_test_log_required==1){
      try {
        logger.info("ft-test-log url {}",urlRestApi);
        logTest.send(urlRestApi);
      } catch (IOException e) {
        logger.info("IOException : {} ", e.toString());
      }
    }
    else {
      logger.info("Test log is unable.");
    }
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
}
