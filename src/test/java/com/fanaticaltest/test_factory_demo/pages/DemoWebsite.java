package com.fanaticaltest.test_factory_demo.pages;

import com.fanaticaltest.test_factory_demo.lib.BasePage;
import com.fanaticaltest.test_factory_demo.lib.Property;
import org.openqa.selenium.By;

public class DemoWebsite extends BasePage {

  private Property prop = new Property();
  private String ft_demo_website_url = prop.read("ft_demo_website_url");

  private static final By AGREEMENT_LABEL = By.id("agreement-text");
  private static final By AGREEMENT_CHECKBOX = By.name("MyCheckbox");
  private static final By SUBMIT_BUTTON = By.name("submit");
  private static final By ERROR_LABEL = By.id("validatorChkBox");
  private static final By ERROR_LOGIN_LABEL = By.id("validatorLogin");
  private static final By SELECTED_PRODUCT_LABEL = By.id("selected-product");
  private static final By DONE_BUTTON = By.id("page-redirect");
  private static final By PHP_VERSION_LABEL = By.xpath("/html/body/div/table[1]/tbody/tr/td/h1");
  private static final By USERNAME_TEXTFIELD = By.id("username-field");
  private static final By PASSWORD_TEXTFIELD = By.id("password-field");
  private static final By LOGIN_BUTTON = By.id("login-submit");
  private static final By DROPDOWN_QUANTITY = By.id("qt-selector");
  private static final By PRICE_WO_VAT_LABEL = By.id("price-wo-vat");
  private static final By VAT_VALUE_LABEL = By.id("vat-value");
  private static final By TOTAL_PRICE_LABEL = By.id("total-price");
  private static String AUTHENTICATION_URL = "login.html";
  private static String PHP_INFO_URL = "info.php";
  private static final double VAT_PERCENTAGE = 0.08;


  public void clickOnAgreementLic(String agreementText) {
    assertTextInElementBy(agreementText, AGREEMENT_LABEL);
    clickElementBy(AGREEMENT_CHECKBOX);
  }

  public void clickOnSubmit(String buttonValue) {
    if (buttonValue.equals("Submit!")) {
      assertAttributeInElementBy("value", buttonValue, SUBMIT_BUTTON);
      clickElementBy(SUBMIT_BUTTON);
    } else if (buttonValue.equals("Login")) {
      assertAttributeInElementBy("value", buttonValue, LOGIN_BUTTON);
      clickElementBy(LOGIN_BUTTON);
    } else if (buttonValue.equals("Done")) {
      assertAttributeInElementBy("value", buttonValue, DONE_BUTTON);
      clickElementBy(DONE_BUTTON);
    }
  }

  public void checkRedirection(String url) {
    waitForUrlRedirect(url);
  }

  public void seeErrorMessage(String errorMessage) {
    if (errorMessage.equals("You need to agree the Terms and Conditions")) {
      assertTextInElementBy(errorMessage, ERROR_LABEL);
    } else if (errorMessage.equals("Wrong username and password")) {
      assertTextInElementBy(errorMessage, ERROR_LOGIN_LABEL);
    }
  }

  public void clickOnBuy(String id) {
    By buttonBuy = By.id("productid-" + id + "-buy");
    clickElementBy(buttonBuy);
  }

  public void checkValueInShoppringCart(String id) {
    assertTextInElementBy(id, SELECTED_PRODUCT_LABEL);
  }

  // Background check
  public void checkPHPVersion(String version) {
    loadPage(ft_demo_website_url + PHP_INFO_URL);
    assertTextInElementBy("PHP Version "+ version, PHP_VERSION_LABEL);
  }

  // Background
  public void athentifyUser(String username, String password) {
    loadPage(ft_demo_website_url + AUTHENTICATION_URL);
    fillFieldBy(username, USERNAME_TEXTFIELD);
    fillFieldBy(password, PASSWORD_TEXTFIELD);
    clickElementBy(LOGIN_BUTTON);
  }

  public void shoppingSelectQt(String qt) {
    selectDropDownByValue(DROPDOWN_QUANTITY, qt);
  }

  public void checkTotalPriceWoVat() {
    int qt = Integer.parseInt(getDropDownSelectedValue(DROPDOWN_QUANTITY));
    By unitPriceLabel = By.id("productid-" + getInnerHtmlValue(SELECTED_PRODUCT_LABEL) + "-price");
    int unitPrice = Integer.parseInt(getInnerHtmlValue(unitPriceLabel));
    int totalPriceWoVat = qt * unitPrice;
    assertTextInElementBy(String.valueOf(totalPriceWoVat), PRICE_WO_VAT_LABEL);
  }

  public void checkVatValue() {
    int totalPriceWoVat = Integer.parseInt(getInnerHtmlValue(PRICE_WO_VAT_LABEL));
    double vatValue = totalPriceWoVat * VAT_PERCENTAGE;
    assertTextInElementBy(String.valueOf(vatValue), VAT_VALUE_LABEL);
  }

  public void checkTotalPrice() {
    int totalPriceWoVat = Integer.parseInt(getInnerHtmlValue(PRICE_WO_VAT_LABEL));
    double vatValue = Double.parseDouble(getInnerHtmlValue(VAT_VALUE_LABEL));
    double totalPrice = (double) (vatValue + totalPriceWoVat);
    assertTextInElementBy(String.valueOf(totalPrice), TOTAL_PRICE_LABEL);
    getIntermediateScreenshots("shop");
  }

  public void userRedirectedToLoginPage() {
    loadPage(ft_demo_website_url + AUTHENTICATION_URL);
  }

  public void userEnterUsername(String username) {
    fillFieldBy(username, USERNAME_TEXTFIELD);
  }

  public void userenterPassword(String password) {
    fillFieldBy(password, PASSWORD_TEXTFIELD);
  }

}
