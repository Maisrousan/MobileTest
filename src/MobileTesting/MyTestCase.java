package MobileTesting;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class MyTestCase {

	AndroidDriver driver;
	DesiredCapabilities cap = new DesiredCapabilities();

	@BeforeTest
	public void MySetUp() {

		cap.setCapability("platformName", "Android");
		cap.setCapability("appium:deviceName", "ABCD");

		File MyApp = new File("src/MyApplication/calculator.apk");
		cap.setCapability("appium:app", "MyApp.getAbsolutePath()");
	}

	@BeforeMethod
	public void BeforeEachTest() throws MalformedURLException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
	}

	@Test
	public void MyFirstTest() throws MalformedURLException {
		WebElement Number8 = driver.findElement(By.id("com.google.android.calculator:id/digit_8"));
		WebElement MultiplicationSigns=  driver.findElement(By.id("com.google.android.calculator:id/op_mul"));
		WebElement Number5 = driver.findElement(By.id("com.google.android.calculator:id/digit_5"));
		
		Number8.click();
		MultiplicationSigns.click();
		Number5.click();

		String TheResults = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();

		System.out.println(TheResults);

		Assert.assertEquals(TheResults, "40");

	}

	@Test(enabled = false)
	public void PressOnAllButtons() {

		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < AllButtons.size(); i++) {
			AllButtons.get(i).click();
		}
	}

	@Test(enabled = false)
	public void PressOnAllNumbers() {

		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < AllButtons.size(); i++) {

			if (AllButtons.get(i).getDomAttribute("resource-id").contains("digit")) {
				AllButtons.get(i).click();

			}
		}

		String ActualResutl = driver.findElement(By.id("com.google.android.calculator:id/formula")).getText();

		Assert.assertEquals(ActualResutl, "7894561230");
	}

	@Test(enabled = false)
	public void PressOnEvenNumbers() {

		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < AllButtons.size(); i++) {

			if (AllButtons.get(i).getDomAttribute("resource-id").contains("8")
					|| AllButtons.get(i).getDomAttribute("resource-id").contains("6")
					|| AllButtons.get(i).getDomAttribute("resource-id").contains("4")
					|| AllButtons.get(i).getDomAttribute("resource-id").contains("2")
					|| AllButtons.get(i).getDomAttribute("resource-id").contains("0")) {
				AllButtons.get(i).click();

			}
		}

	}

	@Test
	public void pressOnEvenNumbers2() {
		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (WebElement button : allButtons) {
			String label = button.getDomAttribute("content-desc");

			// Check if it's a digit and also an even number
			if (label != null && label.matches("\\d")) {
				int num = Integer.parseInt(label);
				if (num % 2 == 0) {
					System.out.println("Clicking even number: " + num);
					button.click();
				}
			}
		}
	}

}
