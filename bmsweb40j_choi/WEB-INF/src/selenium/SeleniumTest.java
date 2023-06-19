package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

	public static void main(String[] args) {

		SeleniumTest selTest = new SeleniumTest();
		selTest.crawl();

	}

	// WebDriver
	private WebDriver driver;

	private WebElement element;
	CharSequence[] c = new CharSequence[1];

	// Properties
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\Users\\Globalway\\Downloads\\chromedriver_win32/chromedriver.exe";

	// 크롤링 할 URL
	private String base_url;

	public SeleniumTest() {
		super();

		// System Property SetUp
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

		// Driver SetUp
		driver = new ChromeDriver();

		base_url = "http://localhost:8080/bmsweb40j_choi/view/login.jsp";

	}

	public void crawl() {

		try {
			// get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
			driver.get(base_url);

			//text
			element = driver.findElement(By.name("id"));
			element.click();
			c[0] = (CharSequence) "test";
			element.sendKeys(c);

			element = driver.findElement(By.name("pass"));
			element.click();
			c[0] = (CharSequence) "test";
			element.sendKeys(c);

			//enter
			element.sendKeys(Keys.RETURN);

			//click
			element = driver.findElements(By.className("m_td")).get(1);
			element.click();

			Thread.sleep(20000);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			driver.close();
		}

	}

}
