package Pages;



import java.net.MalformedURLException;
import java.time.Duration;
import java.util.UUID;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class RegisterPage {



    WebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";
    public String lastGeneratedUsername = "";

    public RegisterPage( WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

//    public void navigateToRegisterPage() {
//
////        SeleniumWrapper.navigate(driver, url);
//         if (!driver.getCurrentUrl().equals(this.url)) {
//             driver.get(this.url);
//         }
//    }

    @FindBy(xpath="//a[contains(text(),'Register')]")
    WebElement registerBtn ;




    @FindBy(name="email")
    WebElement usernametxt  ;



    @FindBy(name ="password")
    WebElement passwordtxt ;

    @FindBy(name="confirmpassword")
    WebElement ConfirmPasswordtxt;

    @FindBy(xpath="//button[contains(text(),'Register Now')]")
    WebElement RegisterNowBtn ;

   public void Click_On_register_Btn() throws InterruptedException {
       Thread.sleep(5000);
       registerBtn.click();
   }
    public void RegisterNewUser(String username , String password , String ConfirmPassword , Boolean generateRandomUsername  ) throws MalformedURLException, InterruptedException {

        if (generateRandomUsername == true)
            username = username+UUID.randomUUID().toString();
        lastGeneratedUsername=username;
         usernametxt.sendKeys(username);
         passwordtxt.sendKeys( password);
         ConfirmPasswordtxt.sendKeys(ConfirmPassword);
        Thread.sleep(5000);

        JavascriptExecutor js =  (JavascriptExecutor)driver ;
       // js.executeScript("arguments[0].click();",  RegisterNowBtn);
        js.executeScript("arguments[0].scrollIntoView(true);",  RegisterNowBtn);

        Thread.sleep(5000);
        //js.executeScript("arguments[0].click();",  RegisterNowBtn);
         RegisterNowBtn.click() ;
//        SeleniumWrapper.sendKeys(usernametxt, username );
//        SeleniumWrapper.sendKeys(  passwordtxt, password  );
//        SeleniumWrapper.sendKeys(   ConfirmPasswordtxt , ConfirmPassword );
//        SeleniumWrapper.click(RegisterNowBtn);
    }

  public boolean check_Registration_Complete()
  {
      WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/login"));
     return true ;
  }

    public String lastGeneratedUsername ()
    {
        return lastGeneratedUsername;
    }
}
