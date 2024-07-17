package Pages;


import java.net.MalformedURLException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage {
    WebDriver driver ;

    public AdventurePage(WebDriver driver) {

        this.driver = driver ;
        PageFactory.initElements(this.driver,this);
    }


    @FindBy (id="duration-select")
    WebElement dropdownElement ;


    @FindBy (id="category-select")
    WebElement CategoryDropdown  ;

    @FindBy (xpath=" //*[@id='data']/div")
    List<WebElement> numberOfAdventure ;

    @FindBy (xpath ="//select[@name='duration']//following-sibling::div")
    WebElement clearbtnOfDurationFilter ;

    @FindBy (xpath ="//select[@id='category-select']//following-sibling::div")
    WebElement clearbtnOfCategoryFilter ;






    public void  setFilterValue(String value) throws InterruptedException, MalformedURLException
    {

         clearbtnOfDurationFilter.click();
       // SeleniumWrapper.click(clearbtnOfDurationFilter);

        Thread.sleep(3000);
        Select dropdown = new Select(dropdownElement);

        dropdown.selectByVisibleText(value);

    }


    public void  setCategoryValue(String value) throws InterruptedException, MalformedURLException
    {

        clearbtnOfCategoryFilter.click();
        //SeleniumWrapper.click( clearbtnOfCategoryFilter) ;

        Thread.sleep(3000);
        Select selectCategory = new Select(CategoryDropdown);

        selectCategory.selectByVisibleText(value);
    }

    public void  clearFilter() throws InterruptedException, MalformedURLException
    {

        Thread.sleep(1000);

        // System.out.println( clearbtnOfDurationFilter.isDisplayed());
        // System.out.println( clearbtnOfDurationFilter.isEnabled());
         clearbtnOfDurationFilter.click();
         Thread.sleep(1000);
         clearbtnOfCategoryFilter.click();

//        SeleniumWrapper.click(clearbtnOfDurationFilter);
//
//        SeleniumWrapper.click(clearbtnOfCategoryFilter);
        // System.out.println(clearbtnOfCategoryFilter.isDisplayed());
        // System.out.println(clearbtnOfCategoryFilter.isEnabled());
    }

    public  int  getResultCount()
    {
        return numberOfAdventure.size() ;

    }
    public void selectAdventure (String adventure) throws InterruptedException, MalformedURLException
    {

         clearbtnOfCategoryFilter.click();
         clearbtnOfDurationFilter.click();
//        SeleniumWrapper.click( clearbtnOfCategoryFilter);
//        SeleniumWrapper.click(clearbtnOfDurationFilter);
        Thread.sleep(2000);
        WebElement adventureEle= driver.findElement(By.xpath("//h5[contains(text(),'"+adventure+"')]//ancestor::div[@class='col-6 col-lg-3 mb-4']"));
        Thread.sleep(2000);
        adventureEle.click();

       // SeleniumWrapper.click(adventureEle);

    }
}
