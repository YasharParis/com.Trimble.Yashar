package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


import utilities.dataRelated.ExcelManager;
import utilities.dataRelated.JDBCManager;
import utilities.dataRelated.JsonManager;
import utilities.elements.DriverUtil;
import utilities.reportRelated.TestListener;

public class TestBase extends TestListener {

	

	protected static JDBCManager sqlManager = new JDBCManager();
		
	protected static ExcelManager xlread = new ExcelManager();
//	protected static ExcelEZ ezexcel = new ExcelEZ();
	
	String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/sampleDataJson.json";
	protected JsonManager json = new JsonManager(filePath);
	

	
	
	
	
	
	// --------------- Before all @Test --------------------
	@BeforeTest
	public void beforeAllTestCase() {

		onExtentSetup();

	}
	
	
	

	// --------------- Before each @Test --------------------

	@BeforeMethod
	@Parameters("browser") // this parameter is used in testNG.xml
	public void beforeEachTestCase(String browser) {

		driver = DriverUtil.startBrowser(browser);
		 //driver = DriverUtil.startBrowserInPrivateMode(browser);
		// driver = DriverUtil.startDockerContainerBrowser(browser);

	}
	
	//  Use this in terminal do Lunch Zelinium
/*	docker run hello-world 


	docker run --rm -ti --name zalenium -p 4444:4444 -v /var/run/docker.sock:/var/run/docker.sock -v /tmp/videos:/home/seluser/videos --privileged dosel/zalenium start --desiredContainers 2 --maxTestSessions 10


	http:///192.168.99.100:4444/grid/admin/live

	http:///localhost:4444/grid/admin/live 
*/
	
	

	@AfterMethod
	public void tearDown() throws Exception {
		// Close the browser
		driver.quit();
	}

	// --------------- After all @Test--------------------
	@AfterTest
	public void afterAllTestCase() {
		// write the report to output view
		extent.flush();

	}
}
