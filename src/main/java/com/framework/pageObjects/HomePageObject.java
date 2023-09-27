package com.framework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePageObject {

    public HomePageObject(WebDriver driver){
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Integer.parseInt("10")), this);
    }

    @FindBy(linkText = "Create an Account")
    private WebElement createAccount;

    @FindBy(id = "firstname")
    private WebElement firstname_id;

    @FindBy(id = "lastname")
    private WebElement lastname_id;

    @FindBy(id = "email_address")
    private WebElement email_address_id;

    @FindBy(id = "password")
    private WebElement password_id;

    @FindBy(id = "password-confirmation")
    private WebElement password_confirmation_id;

    public void createAccount(){
        createAccount.click();
    }

    public void enterdetail(String firstname, String lastname, String email, String password, String confirm_pass){

        firstname_id.sendKeys(firstname);
        lastname_id.sendKeys(lastname);
        email_address_id.sendKeys(email);
        password_id.sendKeys(password);
        password_confirmation_id.sendKeys(confirm_pass);

    }
}
