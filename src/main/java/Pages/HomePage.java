package Pages;





import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {



   WebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void navigateToHomePage() {
        //   System.out.println(driver.getCurrentUrl());
         if (!driver.getCurrentUrl().equals(this.url)) {
             driver.get(this.url);
         }
        //SeleniumWrapper.navigate(driver, url);
    }

    @FindBy(xpath = "//div[contains(text(),'Logout')]")
    WebElement LogoutBtn;


    @FindBy(xpath = "//a[contains(text(),'Register')] ")
    WebElement RegisterBtn;

    @FindBy(xpath=" //input[contains(@placeholder,'Search a City')]")
    WebElement SearchBox ;

    @FindBy(xpath=" //h5[contains(text(),'No City found')]")
    WebElement CityNotFound ;





    public void ClickOnRegister() throws MalformedURLException {
        RegisterBtn.click();

        //SeleniumWrapper.click(RegisterBtn);
    }

    public boolean isUserLoggedIn() {

        try {



            if(LogoutBtn.isDisplayed())
            {
                return true ;
            }
        } catch (Exception e) {
            //TODO: handle exception

            System.out.println("Element not found");
            return false ;
        }

        return false ;

    }

    public void logoutUser() throws MalformedURLException {
         LogoutBtn.click();
      //  SeleniumWrapper.click(LogoutBtn);


    }

    public void searchCity(String city) throws InterruptedException {
        
         SearchBox.clear();
          Thread.sleep(1000);
         SearchBox.sendKeys(city);
       // SeleniumWrapper.sendKeys(SearchBox , city ) ;

    }

    public boolean verifyCityNotFound() throws InterruptedException
    {
        try {
            Thread.sleep(1000);

            if(CityNotFound.isDisplayed())
                return true ;
        } catch ( Exception e) {

            return false  ;

        }
        return false ;
    }

    public boolean assertAutoCompletetext(String city) {

        String cityName =driver.findElement(By.xpath(" //li[contains(text(),'"+city+"')]")).getText();


        if(cityName.equalsIgnoreCase(city))
            return true ;
        else
            return false  ;



    }



    public void SelectCity(String city) throws MalformedURLException {
        WebElement City= driver.findElement(By.xpath("//li[contains(text(),'"+city+"')]"));


        City.click();
       // SeleniumWrapper.click(City) ;

    }



}

