package dataProvider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider {

    @DataProvider(name = "loginDetails")
    public static Object[][] getData()
    {
        Object[][] arr = ExcelReader.getDataFromSheet("TestCase01");
        return arr;
    }
    
    @DataProvider(name = "TestCase2")
	public static Object[][] getDataFrom() 
	{
		Object[][] arr = ExcelReader.getDataFromSheet("TestCase02");
		return arr;
	}

}
