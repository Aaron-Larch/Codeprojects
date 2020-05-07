package WebElementsLibrary;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Elements {
	static WebDriver driver;
	
	public void testExplicitWait(WebDriver driver) {
		   // Assume we're already on a fully loaded webpage.
		   driver.findElement(By.id("myForm")).submit();
		   // Now the next page starts loading.
		 
		   // Here's the explicit wait.
		   WebDriverWait wdw = new WebDriverWait(driver, 10);
		   ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
		      @Override
		      public Boolean apply(WebDriver d) {
		         WebElement result = d.findElement(By.className("myResult"));
		         return "The Next Page".equals(result.getText());
		         // Returns true as soon as an element of class 'myResult' is found
		         // where the element's text value is "The Next Page".
		     }
		   };
		   wdw.until(condition); // Won't get past here til timeout or element is found
		 
		   // It is safe to operate on the element now.
		   WebElement result = driver.findElement(By.className("myResult"));
		   Assert.assertEquals("The Next Page", result.getText());
		}
}
