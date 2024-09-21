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
	public static Object[][] getDataFrom2() 
	{
		Object[][] arr = ExcelReader.getDataFromSheet("TestCase02");
		return arr;
	}
    
    
    @DataProvider(name = "TestCase3")
   	public static Object[][] getDataFrom3() 
   	{
   		Object[][] arr = ExcelReader.getDataFromSheet("TestCase03");
   		return arr;
   	}

    @DataProvider(name = "TestCase4")
   	public static Object[][] getDataFrom4() 
   	{
   		Object[][] arr = ExcelReader.getDataFromSheet("TestCase04");
   		return arr;
   	}
}
