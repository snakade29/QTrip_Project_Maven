package Pages;

import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdventureDetailsPage {

     WebDriver driver ;


    public AdventureDetailsPage( WebDriver driver)
    {

        this.driver = driver ;
        PageFactory.initElements(this.driver,this);
    }




    @FindBy (name="name")
    WebElement nameEle  ;

    @FindBy (name="date")
    WebElement dateEle ;

    @FindBy (name="person")
    WebElement countEle ;



    @FindBy (xpath="//button[contains(text(),'Reserve')]")
    WebElement  reserveBtn ;

    @FindBy (xpath="//div[contains(text(),'Greetings! Reservation for this')]")
    WebElement reservation ;


    public void bookAdventure ( String guestname ,String  date ,String count ) throws InterruptedException, MalformedURLException
    {

           nameEle.sendKeys(guestname);

           dateEle.sendKeys(date);


           countEle.clear();
           countEle.sendKeys( count);

           reserveBtn.click() ;

//        SeleniumWrapper.sendKeys(nameEle,guestname );
//        SeleniumWrapper.sendKeys(dateEle, date);
//        SeleniumWrapper.sendKeys(countEle,count );
//        SeleniumWrapper.click(reserveBtn);


    }



    // boolean
    public boolean isBookingSuccessfull ()
    {

        try {

            if(reservation.isDisplayed())
                return true ;



        } catch (Exception e) {
            //TODO: handle exception

            return false ;
        }
        return false ;



    }



}
