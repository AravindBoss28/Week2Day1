package Week2Day1;

import org.openqa.selenium.By;

public class EditLead {
	
	
	private static void modifyDescription() throws InterruptedException {
		CreateLead.driver.findElement(By.linkText("Edit")).click();
		CreateLead.driver.findElement(By.name("description")).clear();
		CreateLead.driver.findElement(By.name("description")).sendKeys("Modify description details");
		String editExpectedRes = "opentaps CRM";
		String actualEditRes = CreateLead.driver.getTitle();
		editExpectedRes.equals(actualEditRes);
		System.out.println("Verify before updating");
		CreateLead.driver.findElement(By.className("smallSubmit")).click();
		String actualTitle = CreateLead.driver.getTitle();
		String expTitle = "View Lead | opentaps CRM"; 
        actualTitle.equals(expTitle);
        System.out.println("Verify after updating");
		Thread.sleep(5000);
	}
	
	public static void main(String[] args) throws InterruptedException {
		CreateLead.initializeChrome();
		System.out.println("Chrome Loaded");

		// Login application
		boolean isLoggedIn = CreateLead.login();
		System.out.println("Application logged in " + isLoggedIn);
		Thread.sleep(5000);

		// Navigate to test lead tab
		CreateLead.navigateToLeadTab();
		System.out.println("application Navigates to create lead tab");

		// enter details to create lead
		CreateLead.enterDetails();
		System.out.println("Account created successfully");
		
		modifyDescription();
		System.out.println("Account modified successfully");
		
		CreateLead.tearDown();
		CreateLead.driver.close();
	}
}
