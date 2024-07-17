package Pages;

import java.net.MalformedURLException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HistoryPage {


    WebDriver driver;



    public HistoryPage( WebDriver driver)
    {
        this.driver = driver ;
        PageFactory.initElements(this.driver, this);

    }

    @FindBy(xpath = "//a[contains(text(),'Reservations')]")
    WebElement Reservation ;

    @FindBy(xpath ="//td//preceding-sibling::th")
    WebElement transactionID  ;

    @FindBy (xpath="//button[@class='cancel-button']")
    WebElement Cancelreservation ;

    @FindBy ( xpath="//a[contains(text(),'Home')]")
    WebElement  HomeBtn;


    @FindBy (xpath =" //a[contains(text(),'Visit Adventure')]")

    List<WebElement>  numberOfReservation  ;

    public boolean transactionId()
    {
        try {
            if(transactionID.isDisplayed())
                return true ;
        } catch (Exception e) {
            //TODO: handle exception
            return  false ;
        }
        return false ;


    }

    public void  getReservations() throws InterruptedException, MalformedURLException
    {

         Reservation.click();
        Thread.sleep(1000);
//        SeleniumWrapper.click(Reservation);
        Thread.sleep(5000);
       // System.out.println(transactionID.getText());
    }


    public void cancelreservation() throws MalformedURLException
    {
           Cancelreservation.click();

       // SeleniumWrapper.click(Cancelreservation);
    }



    public void clickonHome() throws MalformedURLException
    {

        HomeBtn.click() ;
        //SeleniumWrapper.click(HomeBtn) ;
    }



    public  boolean verifyNumberofReservationCount()
    {

        int size = numberOfReservation.size() ;
        System.out.println(size);
        if (size == 3)
        {
            return true  ;
        }
        else
            return  false  ;
    }

}
