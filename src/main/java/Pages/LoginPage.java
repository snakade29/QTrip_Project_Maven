package Pages;


import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver ;
//    String url ="https://qtripdynamic-qa-frontend.vercel.app";

    public LoginPage( WebDriver driver) {
        this.driver = driver ;
        PageFactory.initElements(this.driver,this);
    }
//    public void navigateToLoginPage() {
//         if (!driver.getCurrentUrl().equals(this.url)) {
//             driver.get(this.url);
//         }
//
//       // SeleniumWrapper.navigate(driver, url);
//    }

    @FindBy(xpath=" //button[contains(text(),'Login to QTrip')]")
    WebElement loginBtn ;


    @FindBy(name="email")
    WebElement usernametxt ;

    @FindBy(xpath="//input[@name='password']")
    WebElement  passwordtxt ;

//@FindBy(xpath="//*[contains(text(),'Logout')]")
//WebElement logoutbtn ;


    public void PerformLogin(String username ,String password) throws MalformedURLException
    {
         usernametxt.sendKeys(username);
         passwordtxt.sendKeys(password);
         loginBtn.click();
//        SeleniumWrapper.sendKeys(usernametxt, username );
//        SeleniumWrapper.sendKeys(passwordtxt, password );
//        SeleniumWrapper.click(loginBtn);
    }


    public boolean Confirm_Login_Complete()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Logout')]")));
        return true ;
    }



}
