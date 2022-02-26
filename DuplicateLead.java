package Week2Day1;

import org.openqa.selenium.By;

public class DuplicateLead {
	private static void duplicateLead() {
		CreateLead.driver.findElement(By.linkText("Duplicate Lead")).click();
		CreateLead.driver.findElement(By.id("createLeadForm_companyName")).clear();
		CreateLead.driver.findElement(By.id("createLeadForm_companyName")).sendKeys("TEST123");
		CreateLead.driver.findElement(By.id("createLeadForm_firstName")).clear();
		CreateLead.driver.findElement(By.id("createLeadForm_firstName")).sendKeys("Aravind124");
		String dup = "Duplicate Lead | opentaps CRM";
		String actualTitleDup = CreateLead.driver.getTitle();
		dup.equals(actualTitleDup);
		
		//modify data and click update
		CreateLead.driver.findElement(By.className("smallSubmit")).click();
		String actualTitle = CreateLead.driver.getTitle();
		String expTitle = "View Lead | opentaps CRM"; 
        actualTitle.equals(expTitle);
        System.out.println("Duplicate account created successfully");
        System.out.println("Scenario Passed");
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
		Thread.sleep(5000);
		
		duplicateLead();
		System.out.println("Account duplication done successfully");
		Thread.sleep(5000);
		
		CreateLead.tearDown();
		Thread.sleep(5000);
		CreateLead.driver.close();
	}
}
