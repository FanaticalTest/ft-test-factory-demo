package com.fanaticaltest.testFactory.webDemo.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DefaultUrl("https://demo-website.fanaticaltest.com/shopping.html")
public class ShoppingCartPage extends PageObject {

    @FindBy(xpath="//div[@id='selected-product']")
    WebElementFacade selectedProductId;

    @FindBy(xpath="//select[@id='qt-selector']")
    WebElementFacade quantitySelector;

    @FindBy(xpath="//div[@id='price-wo-vat']")
    WebElementFacade priceWoVat;

    @FindBy(xpath="//div[@id='vat-value']")
    WebElementFacade vatValue;

    @FindBy(xpath="//div[@id='total-price']")
    WebElementFacade totalPrice;

    //page-redirect
    @FindBy(xpath="//input[@id='page-redirect']")
    WebElementFacade doneButton;

    private final Logger logger = LoggerFactory.getLogger(ShoppingCartPage.class);


    public void addToCart(String productId) {
        find(By.xpath("//input[@id='productid-"+productId+"-buy']")).click();
    }

    public void checkProductInShoppingCart(String productId) {
        assert (selectedProductId.getText().equals(productId));
    }

    public void selectQuantity(String quantity) {
        quantitySelector.selectByValue(quantity);
        quantitySelector.getValue();
    }

    public void checkTotalWithoutVat() {
        int valPriceWoVat = Integer.parseInt(priceWoVat.getText());
        int valProductPrice = Integer.parseInt(find(By.xpath("//div[@id='productid-"+selectedProductId.getText()+"-price']")).getText());
        int valProductQuantity = Integer.parseInt(quantitySelector.getValue());
        logger.info("Total without Vat : " + valProductPrice*valProductQuantity + " - value on cart : " + valPriceWoVat);
        if (valPriceWoVat == valProductPrice*valProductQuantity)
            assert (true);
        else
            assert (false);
    }

    public void checkVatValue() {
        int valPriceWoVat = Integer.parseInt(priceWoVat.getText());
        double totalVatValue = valPriceWoVat*0.08;
        logger.info("Value vat calculation : " + totalVatValue + " - value on cart : " + vatValue.getText());
        assert (String.valueOf(totalVatValue).equals(vatValue.getText()));
    }

    public void checkTotalValue() {
        double valPriceWoVat = Double.parseDouble(priceWoVat.getText());
        double totalVatValue = Double.parseDouble(vatValue.getText());
        logger.info("Value total calculation : " + String.valueOf(valPriceWoVat+totalVatValue) + " - value on cart : " + totalPrice.getText());
        assert (String.valueOf(valPriceWoVat+totalVatValue).equals(totalPrice.getText()));
    }

    public void clickDone(String buttonValue) {
        assert (doneButton.getValue().equals(buttonValue));
        doneButton.click();
    }
}
