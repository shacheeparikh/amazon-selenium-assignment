package pom.amazon.amazon;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon_test {

	@DataProvider(name = "BookData")
	public Object[][] getData() {

		return JsonReader.getBookData();
	}

	WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://www.amazon.in");
	}

	@Test(dataProvider = "BookData")
	public void Amazon_books(String bookName) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement booksOption = driver.findElement(
				By.xpath("//select[@id='searchDropdownBox']/option[text()='Books']"));
		booksOption.click();

		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.clear();

		searchBox.sendKeys(bookName);

		searchBox.sendKeys(Keys.ENTER);

		Thread.sleep(3000);

		WebElement price = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='a-price-whole'])[1]")));

		System.out.println("Book Name: " + bookName);

		System.out.println("Price: " + price.getText());

		System.out.println("----------------------");

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
