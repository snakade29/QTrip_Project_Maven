package testCases;

import Pages.AdventureDetailsPage;
import Pages.AdventurePage;
import Pages.HistoryPage;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@Listeners(ExtentTestNGITestListener.class)
public class TestCases extends BaseClass {

	boolean status;
	String last_Generated_Username;

	private static final int NUM_THREADS = 5;
	private static final ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

	
	
	@Test(dataProvider = "loginDetails", dataProviderClass = CustomDataProvider.class,retryAnalyzer = listeners.RetryAnalyzer.class)
	public void testRegistration_Login(String UserName, String Password)
			throws MalformedURLException, InterruptedException {
		 status = true;
		RegisterPage register = new RegisterPage(driver);
		register.Click_On_register_Btn();
		register.RegisterNewUser(UserName, Password, Password, status);
		last_Generated_Username = register.lastGeneratedUsername();
		Assert.assertTrue(register.check_Registration_Complete());
		LoginPage login = new LoginPage(driver);
		login.PerformLogin(last_Generated_Username, Password);
		Assert.assertTrue(login.Confirm_Login_Complete());

	}

	@Test(dataProvider = "TestCase2", dataProviderClass = CustomDataProvider.class, description = "Verify Search and Filter flow  ", priority = 2, groups = {
			"Search and Filter flow" })
	public void TestCase02(String CityName, String CategoryFilter, String DurationFilter, String expectedFilterResult,
			String expectedUnfilteredResult) throws InterruptedException, MalformedURLException {

		String CityNotPresent = "Delhi";
		HomePage home = new HomePage(driver);
		home.navigateToHomePage();
		home.searchCity(CityNotPresent);
		Assert.assertTrue(home.verifyCityNotFound(), "No matches found is not displayed ");
		home.searchCity(CityName);
		// Thread.sleep(2000);
		Assert.assertTrue(home.assertAutoCompletetext(CityName), "Same city is not displayed");
		// Thread.sleep(2000);
		home.SelectCity(CityName);
//         Thread.sleep(2000);
		AdventurePage adventurePage = new AdventurePage(driver);
		adventurePage.clearFilter();
//         Thread.sleep(2000);
		int expectedUnfilterCount = Integer.parseInt(expectedUnfilteredResult);

		Assert.assertEquals(adventurePage.getResultCount(), expectedUnfilterCount);

		adventurePage.setFilterValue(DurationFilter);

		adventurePage.setCategoryValue(CategoryFilter);

		int expectedFiltercount = Integer.parseInt(expectedFilterResult);
		Assert.assertEquals(adventurePage.getResultCount(), expectedFiltercount);

	}

	@Test(dataProvider = "TestCase3", dataProviderClass = CustomDataProvider.class, description = "verify Booking and Cancellation Flow", priority = 3, groups = {
			"Booking and Cancellation Flow" })
	public void TestCase03(String UserName, String Password, String searchcity, String adventurename, String guestname,
			String date, String count) throws InterruptedException, MalformedURLException {

		status = true;
		RegisterPage register = new RegisterPage(driver);
		register.Click_On_register_Btn();
		register.RegisterNewUser(UserName, Password, Password, status);
		last_Generated_Username = register.lastGeneratedUsername();
		Assert.assertTrue(register.check_Registration_Complete());
		LoginPage login = new LoginPage(driver);
		login.PerformLogin(last_Generated_Username, Password);
		Assert.assertTrue(login.Confirm_Login_Complete());

		HomePage home = new HomePage(driver);

		home.searchCity(searchcity);

		Assert.assertTrue(home.assertAutoCompletetext(searchcity), "Same city is not displayed");
//		Thread.sleep(1000);
		home.SelectCity(searchcity);

//		Thread.sleep(2000);
		AdventurePage aPage = new AdventurePage(driver);

//		Thread.sleep(1000);
		aPage.selectAdventure(adventurename);

//		Thread.sleep(1000);

		AdventureDetailsPage adetailPage = new AdventureDetailsPage(driver);

		// Thread.sleep(1000);
		adetailPage.bookAdventure(guestname, date, count);
//		Thread.sleep(5000);

		Assert.assertTrue(adetailPage.isBookingSuccessfull(), "Booking Failed");

//		Thread.sleep(2000);
		HistoryPage history = new HistoryPage(driver);
//		Thread.sleep(2000);

		history.getReservations();
		Thread.sleep(3000);
		history.cancelreservation();
		Thread.sleep(3000);
		driver.navigate().refresh();

		Thread.sleep(3000);

		Assert.assertFalse(history.transactionId(), "Reservation Not canceled");

		home.logoutUser();

		Thread.sleep(3000);

	}
	
	
	@Test (dataProvider = "TestCase4",dataProviderClass = CustomDataProvider.class ,enabled=true ,description = "Verify booking history can be viewed",priority=4,groups={"Reliability Flow"})
	public void  TestCase04( String Username ,String Password ,String  dataset1 ,String  dataset2 ,String dataset3 ) throws InterruptedException, MalformedURLException
	{

		status = true;
		RegisterPage register = new RegisterPage(driver);
		register.Click_On_register_Btn();
		register.RegisterNewUser(Username, Password, Password, status);
		last_Generated_Username = register.lastGeneratedUsername();
		Assert.assertTrue(register.check_Registration_Complete());
		LoginPage login = new LoginPage(driver);
		login.PerformLogin(last_Generated_Username, Password);
		Assert.assertTrue(login.Confirm_Login_Complete());
		
		
	          HomePage home = new HomePage(driver);

	          Thread.sleep(1000);

	          String [] data1 =  dataset1.split(";");
	          String [] data2 =  dataset2.split(";");
	          String [] data3 =  dataset3.split(";");

	          List<String[]>  list = new ArrayList<>();

	          list.add(data1);
	          list.add(data2);
	          list.add(data3);
	         
	         for(String[]  data : list)
	          {
	          home.searchCity(data[0]);

	          Thread.sleep(1000);
	      


	                   Assert.assertTrue(home.assertAutoCompletetext(data[0]), "Same city is not displayed");
	                   Thread.sleep(1000);
	                   home.SelectCity( data[0]);


	                   Thread.sleep(1000);
	                   AdventurePage  aPage  = new AdventurePage(driver) ;
	                   

	                   aPage.selectAdventure( data[1]);
	                   Thread.sleep(1000);
	                   Thread.sleep(1000);
	                   AdventureDetailsPage adetailPage = new AdventureDetailsPage(driver); 
	                   Thread.sleep(1000);
	                   Thread.sleep(1000);
	                   adetailPage.bookAdventure(data[2] ,  data[3], data[4]);
	                   Thread.sleep(3000);
	                   Assert.assertTrue( adetailPage.isBookingSuccessfull() , "Booking Failed");
	                   System.out.println(adetailPage.isBookingSuccessfull());
	                   Thread.sleep(3000);
	                   HistoryPage history = new HistoryPage(driver);
	                   Thread.sleep(3000);

	                   history.getReservations();
	                   Thread.sleep(3000);
	                   history.clickonHome();
	          }
	          Thread.sleep(3000);
	          HistoryPage history = new HistoryPage(driver);
	          history.getReservations();
	          Thread.sleep(3000);
	         
	             
	            Assert.assertTrue(history.verifyNumberofReservationCount()) ;  
	         
	           

	        Thread.sleep(3000);
	        home.logoutUser();
	}
	  



}
