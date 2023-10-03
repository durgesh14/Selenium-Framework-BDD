package com.framework.pageObjects;

import com.framework.utility.DataReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;
import java.util.Map;

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

    public void enterdetail(Map <String, String> testDataMap) throws IOException {

        firstname_id.sendKeys(testDataMap.get(DataReader.getProperty("firstNameColumn")));
        lastname_id.sendKeys(testDataMap.get(DataReader.getProperty("lastNameColumn")));
        email_address_id.sendKeys(testDataMap.get(DataReader.getProperty("emailColumn")));
        password_id.sendKeys(testDataMap.get(DataReader.getProperty("passwordColumn")));
        password_confirmation_id.sendKeys(testDataMap.get(DataReader.getProperty("ConfirmPasswordColumn")));

    }

}
