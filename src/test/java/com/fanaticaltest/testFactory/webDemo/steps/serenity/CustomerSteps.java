package com.fanaticaltest.testFactory.webDemo.steps.serenity;

import com.fanaticaltest.testFactory.webDemo.pages.LoginPage;
import com.fanaticaltest.testFactory.webDemo.pages.YopMail;
import net.thucydides.core.steps.ScenarioSteps;


public class CustomerSteps extends ScenarioSteps {

    LoginPage loginPage;
    YopMail yopMail;

    public void enters_his_credential(String username, String password) {
        loginPage.login(username, password);
    }

    public void goes_to_login_page() {
        loginPage.open();
    }

    public void gets_login_error(String message) {
        loginPage.loginError(message);
    }

    public void goes_to_yopmail(String usernameAddress) {
        yopMail.open();
        yopMail.select(usernameAddress);
    }

    public void checks_his_mail_box_label(String labelContent) {
        yopMail.checkInBoxLabel(labelContent);
    }
}
