package pl.adik.TestSelenium;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.AssertionFailedError;
import pl.adik.TestSelenium.Questionaire;

public class TestQuestionaire {
	
	private ChromeDriver driver;
	
	@Before
	public void setUp() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Tools\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://******************************");
		Questionaire.fillAll(driver);
	}

	@After
	public void tearDown() throws Exception {
		
		//driver.quit();
		
	}

	@Test
	public void testAddName() {
		
		WebElement weFirstName = driver.findElementByXPath("//*[@id='id_1_1']");
		String temp = Questionaire.randomChars();
		weFirstName.clear();
		weFirstName.sendKeys(temp);
		assertEquals(temp, weFirstName.getAttribute("value"));
	
	}
	
	@Test
	public void testAddLastName() {
	
		WebElement weLastName = driver.findElementByXPath("//*[@id='id_1_2']");
		String temp = Questionaire.randomChars();
		weLastName.clear();
		weLastName.sendKeys(temp);
		assertEquals(temp,weLastName.getAttribute("value"));
	}
	
	@Test
	public void testNoLastName() {
		//Try to send questionaire with empty field
		WebElement weLastName = driver.findElementByXPath("//*[@id='id_1_2']");
		weLastName.clear();
		
		Questionaire.send(driver);
		try {
			WebElement we = driver.findElementByXPath("//*[@class='text-center alert alert-success']");
			assertFalse(we.isEnabled());
		}catch(NoSuchElementException e) {
			System.out.println("Niepoprawny numer Nip");
			assertTrue(true);
		}
		
	}
	
	
	@Test
	public void testSexSelect() {
		//Checking if several radioButtons can be sellected at one time
			Random rand = new Random();
			WebElement weFemale = driver.findElementByXPath("//*[@type='radio' and @id='id_1_3_1']");
			WebElement weMale = driver.findElementByXPath("//*[@type='radio' and @id='id_1_3_2']");
			
			for(int i=0; i<rand.nextInt(7)+2; i++) {
				if(i % 2 == 0) {
					weFemale.click();		
			} else {
				weMale.click();
			}
		}
		assertEquals(weMale.isSelected(), !weFemale.isSelected());
	}
	
	@Test
	public void testPeselWrongNumber() {
		WebElement wePesel = driver.findElementByXPath("//*[@id='id_1_4']");
		WebElement wePeselBox = driver.findElementByXPath("//*[@type='checkbox' and @id='id_1_69_73']");
		if(wePeselBox.isSelected()) {
			wePeselBox.click();
		}
		wePesel.clear();
		wePesel.sendKeys(Questionaire.randomNumber(7));
		System.out.println("Pesel: " + wePesel.getAttribute("value"));
		
		Questionaire.send(driver);
		try {
			WebElement we = driver.findElementByXPath("//*[@class='text-center alert alert-success']");
			assertFalse(we.isEnabled());
		}catch(NoSuchElementException e) {
			System.out.println("Niepoprawny numer pesel");
			assertTrue(true);
		}
	}
	
	@Test
	public void testPeselWithLetters() {
		WebElement wePesel = driver.findElementByXPath("//*[@id='id_1_4']");
		WebElement wePeselBox = driver.findElementByXPath("//*[@type='checkbox' and @id='id_1_69_73']");
		if(wePeselBox.isSelected()) {
			wePeselBox.click();	
		} 
		wePesel.clear();
		wePesel.sendKeys("qwertyuiopa");
		System.out.println("Pesel: " + wePesel.getAttribute("value"));
		
		Questionaire.send(driver);
		try {
			WebElement we = driver.findElementByXPath("//*[@class='text-center alert alert-success']");
			assertFalse(we.isEnabled());
		}catch(NoSuchElementException e) {
			System.out.println("Niepoprawny numer pesel");
			assertTrue(true);
		}
	}
	
	@Test
	public void testNipWrongNumber() {
		WebElement weNip = driver.findElementByXPath("//*[@id='id_1_70']");
		WebElement weNipBox = driver.findElementByXPath("//*[@type='checkbox' and @id='id_1_71_75']");
		if(weNipBox.isSelected()) {
			weNipBox.click();
		}
		weNip.clear();
		weNip.sendKeys(Questionaire.randomNumber(14));
		System.out.println("NIp: " + weNip.getAttribute("value"));
		
		Questionaire.send(driver);
		try {
			WebElement we = driver.findElementByXPath("//*[@class='text-center alert alert-success']");
			assertFalse(we.isEnabled());
		}catch(NoSuchElementException e) {
			System.out.println("Niepoprawny numer Nip");
			assertTrue(true);
		}
	}
	
	@Test
	public void testEducationRadioButtons() {
		//Checking if several radioButtons can be sellected at one time
		int rand = Questionaire.randomInt(6);
		ArrayList<WebElement> array = new ArrayList<WebElement>();
		for(int i=3; i<9; i++) {
			WebElement weEducationRadio = driver.findElementByXPath("//*[@type='radio' and @id='id_1_5_" + i + "']");
			weEducationRadio.click();
			array.add(weEducationRadio);
		}
		//Checking if all other radioButtons are unselected
		array.get(rand).click();
		for(int i=0; i<6; i++) {
			if(i != rand) {
				if(array.get(i).isSelected()) {
					assertTrue(false);
				}
			}
		}
	}
	
	
}
