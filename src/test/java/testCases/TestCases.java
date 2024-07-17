package testCases;

import Pages.AdventurePage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.RegisterPage;
import base.BaseClass;
import dataProvider.CustomDataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import listeners.ExtentTestNGITestListener; 

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Listeners(ExtentTestNGITestListener.class)
public class TestCases extends BaseClass {

    boolean status ;
    String last_Generated_Username;
    
    private static final int NUM_THREADS = 5;
    private static final ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

   

    @Test(dataProvider = "loginDetails",dataProviderClass = CustomDataProvider.class )
    public void testRegistration_Login(String UserName , String Password) throws MalformedURLException, InterruptedException {
        status =true ;
        RegisterPage register = new RegisterPage(driver) ;
        register.Click_On_register_Btn();
        register.RegisterNewUser( UserName,  Password,  Password,status);
        last_Generated_Username=register.lastGeneratedUsername();
        Assert.assertTrue( register.check_Registration_Complete());
        LoginPage login  = new LoginPage(driver);
        login.PerformLogin(last_Generated_Username, Password);
        Assert.assertTrue(login.Confirm_Login_Complete());

    }
    
    
    @Test(dataProvider = "TestCase2",dataProviderClass = CustomDataProvider.class ,description = "Verify Search and Filter flow  ",priority=2,groups={"Search and Filter flow"})
    public void TestCase02(  String CityName, String CategoryFilter, String DurationFilter,
            String expectedFilterResult, String expectedUnfilteredResult)
            throws InterruptedException, MalformedURLException {

        
         String CityNotPresent = "Delhi"  ;
         HomePage home = new HomePage(driver);
         home.navigateToHomePage();
         home.searchCity(CityNotPresent);
         Assert.assertTrue(home.verifyCityNotFound(), "No matches found is not displayed ");
         home.searchCity(CityName);
         Thread.sleep(1000);
         Assert.assertTrue(home.assertAutoCompletetext(CityName), "Same city is not displayed");
         Thread.sleep(1000);
         home.SelectCity(CityName);
         Thread.sleep(2000);
         AdventurePage adventurePage = new AdventurePage(driver);
         adventurePage.clearFilter();
         Thread.sleep(1000);
         int expectedUnfilterCount = Integer.parseInt(expectedUnfilteredResult);

        Assert.assertEquals(adventurePage.getResultCount(), expectedUnfilterCount);

        adventurePage.setFilterValue(DurationFilter);

        adventurePage.setCategoryValue(CategoryFilter);

        int expectedFiltercount = Integer.parseInt(expectedFilterResult);
      Assert.assertEquals(adventurePage.getResultCount(), expectedFiltercount);
                

        



    }








}
