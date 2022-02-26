package Week2Day1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLead {
	static ChromeDriver driver;
	static void initializeChrome() {

		// it helps to avoid manual chrome driver path setup
		WebDriverManager.chromedriver().setup();

		// initialize chrome
		driver = new ChromeDriver();

		// maximize screen
		driver.manage().window().maximize();
	}

	static boolean login() {

		/**
		 * login to application
		 */
		boolean flag = true;
		driver.get("http://leaftaps.com/opentaps/control/main");
		String title = driver.getTitle();
		if (title.contains("Leaftaps")) {
			flag = true;
		}
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		return flag;
	}

	static void navigateToLeadTab() {
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();
	}

	static boolean selectProvince(String s) {
		boolean flag = false;
		/**
		 * create a select and pass locator for drop down get drop down options loop
		 * through options and when condition matches select the passed value
		 */
		Select states = new Select(driver.findElement(By.name("generalStateProvinceGeoId")));
		List<WebElement> options = states.getOptions();
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().equals(s)) {
				states.selectByVisibleText(s);
				flag = true;
			}
		}
		return flag;
	}

	static void enterDetails() throws InterruptedException {
		boolean details = false;
		driver.findElement(By.linkText("Create Lead")).click();
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys("TEST");	
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys("Aravind");
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys("Baskaran");
		driver.findElement(By.name("departmentName")).sendKeys("Automation Testing");
		driver.findElement(By.name("description")).sendKeys("hello entering description details");
		driver.findElement(By.id("createLeadForm_primaryEmail")).sendKeys("arboss@gmail.com");
		details = true;
		boolean res = selectProvince("New York");
		if (details && res) {
			driver.findElement(By.className("smallSubmit")).click();
			Thread.sleep(5000);
			String actualTitle = driver.getTitle();
			String expTitle = "View Lead | opentaps CRM"; 
	        actualTitle.equals(expTitle);
	        System.out.println("account created successfully");
	        System.out.println("Scenario Passed");
		} else {
			System.out.println("there is a issue");
		}
	}
    
	static void tearDown() {
	  driver.findElement(By.className("subMenuButtonDangerous")).click();	
	}
	
	public static void main(String... args) throws InterruptedException {
      // Open Chrome
	  initializeChrome();
      System.out.println("Chrome Loaded");
      
      // Login application
      boolean isLoggedIn = login();
      System.out.println("Application logged in " + isLoggedIn);
      Thread.sleep(5000);
      
      // Navigate to testlead tab
      navigateToLeadTab();
      System.out.println("application Navigates to create lead tab");
      
      // enter details to create lead
      enterDetails();
      
      // tear down --> delete a created Lead
      tearDown();
      System.out.println("Account Deleted Successfully");
      
      driver.close();
	}

}
