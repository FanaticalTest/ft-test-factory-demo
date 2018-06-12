package com.fanaticaltest.testFactory.webDemo.steps;

import com.fanaticaltest.testFactory.webDemo.steps.serenity.CustomerSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import net.thucydides.core.annotations.Steps;

public class Licensing {

    @Steps
    CustomerSteps customer;

    @Given("^the customer arrives on the Terms and Conditions Page$")
    public void the_customer_arrives_on_the_Terms_and_Conditions_Page() throws Exception {
        customer.goes_to_terms_and_conditions();
    }


    @When("^the customer submits the form without agreeing the Terms and Conditions$")
    public void the_customer_submits_the_form_without_agreeing_the_Terms_and_Conditions() throws Exception {
        customer.does_not_agree_terms_and_conditions();
    }

    @Then("^the customers sees the error message in Terms and Conditions$")
    public void the_customers_sees_the_error_message_in_Terms_and_Conditions() throws Exception {
        customer.sees_error_terms_and_conditions();
    }

    @When("^the customer submits the form with agreeing the Terms and Conditions$")
    public void the_customer_submits_the_form_with_agreeing_the_Terms_and_Conditions() throws Exception {
        customer.agrees_terms_and_conditions();
    }

}
