package com.fanaticaltest.testFactory.webDemo.steps;

import com.fanaticaltest.testFactory.webDemo.steps.serenity.CustomerSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class YopMail {

    @Steps
    CustomerSteps customer;

    @When("^the customer checks his email address \"([^\"]*)\"$")
    public void the_customer_checks_his_email_address(String usernameAddress) throws Exception {
        customer.goes_to_yopmail(usernameAddress);
    }

    @Then("^the customer sees his email interface with \"([^\"]*)\" as label$")
    public void the_customer_sees_his_email_interface_with_as_label(String labelContent) throws Exception {
        customer.checks_his_mail_box_label(labelContent);
    }
}
