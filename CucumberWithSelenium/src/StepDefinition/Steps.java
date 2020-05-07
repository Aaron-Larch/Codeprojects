package StepDefinition;

import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;		
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {				    
	private static boolean initialized = false;
    private static WebDriver driver;

    public void initialize(String website){
    	if (!initialized){
            initialized = true;
            System.setProperty("webdriver.gecko.driver", "C:/Users/gce/Desktop/programs/jar_files/geckodriver.exe");					
            driver= new FirefoxDriver();					
            driver.manage().window().maximize();
    		driver.get(website);
        }
    }
	
	@Given("^Open the Firefox and launch the application$")				
    public void open_the_Firefox_and_launch_the_application() throws Throwable							
    {					
		initialize("http://demo.guru99.com/v4");										
    }		
	
    @When("^Enter the ([^\"]*) and ([^\"]*)$")
    public void enter_the_Username_User_and_Password_password(String username, String password) throws Throwable {
    	driver.findElement(By.name("uid")).sendKeys(username);							
        driver.findElement(By.name("password")).sendKeys(password);		
    }
	
    @Then("^Reset the credential$")					
    public void Reset_the_credential() throws Throwable 							
    {    		
    	driver.findElement(By.name("btnReset")).click();						
    }

	@When("^Enter the Username \"([^\"]*)\" and the Password \"([^\"]*)\"$")
	public void enter_The_Username_And_The_Password(String arg1, String arg2) throws Throwable {
		driver.findElement(By.name("uid")).sendKeys(arg1);							
        driver.findElement(By.name("password")).sendKeys(arg2);	
	}
}