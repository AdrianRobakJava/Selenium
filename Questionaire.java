package pl.adik.TestSelenium;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Questionaire {

	

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:\\Tools\\ChromeDriver\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		driver.get("http://****************************************");
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
		fillAll(driver);
		//closeDriver(driver);
		//send(driver);
		
		
	}
	
	public static String randomChars() {
		
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		for(int i=0; i<rand.nextInt(10)+5; i++) {
			char c = (char)(rand.nextInt(26) + 'a');
			sb.append(c);
		}
		
		return sb.toString();
	}
	
	public static int yesOrNo() {
		
		Random rand = new Random();
		return rand.nextInt(2);
	}
	
	public static String randomNumber(int x) {
		
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		for(int i=0; i<x; i++) {
			sb.append(rand.nextInt(10));
		}
		return sb.toString();
	}
	
	public static int randomInt(int x) {
		
		Random rand = new Random();
		return rand.nextInt(x);
	}
	
	public static void send(ChromeDriver driver) {
		
		//Send button
		WebElement weSendButton = driver.findElementByXPath("//*[@name='yt0']");
		weSendButton.click();
		
	}
	
	public static void closeDriver(ChromeDriver driver) {
		driver.quit();
	}
	
	public static void fillAll(ChromeDriver driver) {
		
		
		boolean unemployed1;
		boolean unemployed2;

		try {
			//entrs first name to the field 
			WebElement weFirstName = driver.findElementByXPath("//*[@id='id_1_1']");
			weFirstName.sendKeys(randomChars());
			System.out.println("Imię: " + weFirstName.getAttribute("value"));
			
			//entrs last name to the field 
			WebElement weLastName = driver.findElementByXPath("//*[@id='id_1_2']");
			weLastName.sendKeys(randomChars());
			System.out.println("Nazwisko: " + weLastName.getAttribute("value"));
			
			//sex selection
			int MaleOrFemale = yesOrNo();
			if(MaleOrFemale == 0) {
				WebElement weFemale = driver.findElementByXPath("//*[@type='radio' and @id='id_1_3_1']");
				weFemale.click();
				System.out.println("Mężczyzna: " + weFemale.isSelected());
			} else {
				WebElement weMale = driver.findElementByXPath("//*[@type='radio' and @id='id_1_3_2']");
				weMale.click();
				System.out.println("Kobieta: " + weMale.isSelected());
			}
			
			//pesel section
			int peselSection = yesOrNo();
			if(peselSection == 0) {
				WebElement wePesel = driver.findElementByXPath("//*[@id='id_1_4']");
				wePesel.sendKeys(randomNumber(11));
				System.out.println("Pesel: " + wePesel.getAttribute("value"));
			} else {
				WebElement wePeselBox = driver.findElementByXPath("//*[@type='checkbox' and @id='id_1_69_73']");
				wePeselBox.click();
				System.out.println("Obcokrajowiec: " + wePeselBox.isSelected());
			}
			
			//nip section
			int nipSection = yesOrNo();
			if(nipSection == 0) {
				WebElement weNip = driver.findElementByXPath("//*[@id='id_1_70']");
				weNip.sendKeys(randomNumber(10));
				System.out.println("Nip: " + weNip.getAttribute("value"));
			} else {
				WebElement weNipBox = driver.findElementByXPath("//*[@type='checkbox' and @id='id_1_71_75']");
				weNipBox.click();
				System.out.println("Obcokrajowiec: " + weNipBox.isSelected());
			}
			
			//education section
			int educationSection = randomInt(6);
			int eduID = educationSection + 3;
			WebElement weEducationRadio = driver.findElementByXPath("//*[@type='radio' and @id='id_1_5_" + eduID + "']");
			WebElement weEducationLabel = driver.findElementByXPath("//*[@id='id_1_5_" + eduID + "']");
			weEducationRadio.click();
			System.out.println("Edukacja: " + weEducationLabel.getText() + " - " + weEducationRadio.isSelected());
			
			//diploma
			WebElement weDiploma = driver.findElementByXPath("//*[@id='id_1_43']");
			weDiploma.sendKeys("c:\\DoJava\\arbuz.jpg");
			System.out.println("Ściezka do pliku: " + weDiploma.getAttribute("value"));
			
			WebElement weLicense = driver.findElementByXPath("//*[@id='id_1_6']");
			weLicense.sendKeys(randomNumber(7));
			System.out.println("Numer Licencji: " + weLicense.getAttribute("value"));
			
			//workplace section
			int Workplace = yesOrNo();
			if(Workplace == 0 ) {
				WebElement weWorkplace = driver.findElementByXPath("//*[@id='id_1_7_9']");
				weWorkplace.click();
				unemployed1 = false;
				WebElement weWorkplaceAddress = driver.findElementByXPath("//*[@id='id_1_9']");
				weWorkplaceAddress.sendKeys(randomChars());
				System.out.println("Miejsce pracy: " + weWorkplaceAddress.getAttribute("value"));
				
			} else {
				WebElement weWorkplace = driver.findElementByXPath("//*[@id='id_1_7_10']");
				weWorkplace.click();
				unemployed1 = true;
				System.out.println("Bezrobotny: " + weWorkplace.isSelected());
			}
			
			//disability
			int disability = yesOrNo();
			if(disability == 0) {
				WebElement weDisability = driver.findElementByXPath("//*[@type='radio' and @id='id_1_10_11']");
				weDisability.click();
				System.out.println("Niepełnosprawność: " + weDisability.isSelected());
				WebElement weDisabilityCertyficate = driver.findElementByXPath("//*[@id='id_1_40']");
				weDisabilityCertyficate.sendKeys("c:/DoJava/arbuz.jpg");
				System.out.println("Ściezka do pliku: " + weDisabilityCertyficate.getAttribute("value"));
			} else {
				WebElement weDisability = driver.findElementByXPath("//*[@type='radio' and @id='id_1_10_12']");
				weDisability.click();
				System.out.println("Niepełnosprawność: BRAK " + weDisability.isSelected());
			}
			
			//small village employment
			int smallVillage = yesOrNo();
			if(smallVillage == 0) {
				WebElement weSmall = driver.findElementByXPath("//*[@type='radio' and @id='id_1_11_13']");
				weSmall.click();
				unemployed2 = false;
				System.out.println("Zatrudnienie na wsi: " + weSmall.isSelected());
			} else {
				WebElement weSmall = driver.findElementByXPath("//*[@type='radio' and @id='id_1_11_14']");
				weSmall.click();
				unemployed2 = true;
				System.out.println("Brak zatrudnienia na wsi: " + weSmall.isSelected());
			}
			
			//Residence section
			//Street name
			WebElement weStreet = driver.findElementByXPath("//*[@id='id_2_12']");
			weStreet.sendKeys(randomChars());
			System.out.println("Ulica: " + weStreet.getAttribute("value"));
			
			//Bulding number
			WebElement weBuildingNumber = driver.findElementByXPath("//*[@id='id_2_13']");
			weBuildingNumber.sendKeys(randomNumber(2));
			System.out.println("Numer Budynku: " + weBuildingNumber.getAttribute("value"));
			
			//Apartment number
			WebElement weApartmentNumber = driver.findElementByXPath("//*[@id='id_2_14']");
			weApartmentNumber.sendKeys(randomNumber(3));
			System.out.println("Numer Lokalu: " + weApartmentNumber.getAttribute("value"));
			
			//City
			WebElement wePlace = driver.findElementByXPath("//*[@id='id_2_15']");
			wePlace.sendKeys(randomChars());
			System.out.println("Miejscowość: " + wePlace.getAttribute("value"));
			
			//Zip code
			WebElement weZipCode = driver.findElementByXPath("//*[@id='id_2_16']");
			String zip = randomNumber(2) + "-" + randomNumber(3);
			weZipCode.sendKeys(zip);
			System.out.println("Kod pocztowy: " + weZipCode.getAttribute("value"));
			
			//Community
			WebElement weCommunity = driver.findElementByXPath("//*[@id='id_2_17']");
			weCommunity.sendKeys(randomChars());
			System.out.println("Gmina: " + weCommunity.getAttribute("value"));
			
			//Poviat
			WebElement weCounty = driver.findElementByXPath("//*[@id='id_2_18']");
			weCounty.sendKeys(randomChars());
			System.out.println("Powiat: " + weCounty.getAttribute("value"));
			
			//Province
			WebElement weProvince = driver.findElementByXPath("//*[@id='id_2_19']");
			weProvince.sendKeys(randomChars());
			System.out.println("Województwo: " + weProvince.getAttribute("value"));
			
			//Country
			WebElement weCountry = driver.findElementByXPath("//*[@id='id_2_20']");
			weCountry.sendKeys(randomChars());
			System.out.println("Województwo: " + weCountry.getAttribute("value"));
			
			//E-mail
			WebElement weMail = driver.findElementByXPath("//*[@id='id_2_21']");
			weMail.sendKeys(randomChars() + "@gmail.com");
			System.out.println("Województwo: " + weMail.getAttribute("value"));
			
			//Phone number
			WebElement wePhoneNumber = driver.findElementByXPath("//*[@id='id_2_68']");
			wePhoneNumber.sendKeys(randomNumber(9));
			System.out.println("Numer Lokalu: " + wePhoneNumber.getAttribute("value"));
			
			//Employement
			if(unemployed1 && unemployed2) {
				WebElement weUnemploye = driver.findElementByXPath("//*[@type='checkbox' and @id='id_2_22_15']");
				weUnemploye.click();
				System.out.println("Bezrobotny: " + weUnemploye.isSelected());
				//Not Employed section
				int position = randomInt(3);
				int radioChoose;
				if(position == 0) {
					radioChoose = randomInt(2);
					if(radioChoose == 0) {
						WebElement weUnregistered = driver.findElementByXPath("//*[@type='radio' and @id='id_2_23_16']");
						weUnregistered.click();
						System.out.println("Bezrobotny nie zarejestrowany długo: " + weUnregistered.isSelected());
					} else {
						WebElement weUnregistered = driver.findElementByXPath("//*[@type='radio' and @id='id_2_23_17']");
						weUnregistered.click();
						System.out.println("Bezrobotny nie zarejestrowany inne: " + weUnregistered.isSelected());
					}
					
				} else if( position == 1) {
					radioChoose = randomInt(2);
					if(radioChoose == 0) {
						WebElement weRegistered = driver.findElementByXPath("//*[@type='radio' and @id='id_2_24_18']");
						weRegistered.click();
						System.out.println("Bezrobotny zarejestrowany długo: " + weRegistered.isSelected());
					} else {
						WebElement weRegistered = driver.findElementByXPath("//*[@type='radio' and @id='id_2_24_19']");
						weRegistered.click();
						System.out.println("Bezrobotny zarejestrowany inne: " + weRegistered.isSelected());
					}
				} else {
					radioChoose = randomInt(3);
					if(radioChoose == 0) {
						WebElement wePassive = driver.findElementByXPath("//*[@type='radio' and @id='id_2_25_20']");
						wePassive.click();
						System.out.println("Osoba ucząca się : " + wePassive.isSelected());
					} else if (radioChoose == 1){
						WebElement wePassive = driver.findElementByXPath("//*[@type='radio' and @id='id_2_25_21']");
						wePassive.click();
						System.out.println("Osoba nie ucząca się : " + wePassive.isSelected());
					} else {
						WebElement wePassive = driver.findElementByXPath("//*[@type='radio' and @id='id_2_25_22']");
						wePassive.click();
						System.out.println("Inne: " + wePassive.isSelected());
					}
				}
			} else {
				//Employed section
				
				int workPlace = randomInt(6);
				int workID = educationSection + 23;
				WebElement weWorkPlaceRadio = driver.findElementByXPath("//*[@type='radio' and @id='id_2_27_" + workID + "']");
				WebElement weWorkPlaceLabel = driver.findElementByXPath("//*[@id='id_2_27_" + workID + "']");
				weWorkPlaceRadio.click();
				System.out.println("Zatrudnienie: " + weWorkPlaceLabel.getText() + " - " + weWorkPlaceRadio.isSelected());
				
				//Profession
				int radioChoose = randomInt(2);
				if(radioChoose == 0) {
					WebElement weProfession = driver.findElementByXPath("//*[@type='radio' and @id='id_2_28_31']");
					weProfession.click();
					System.out.println("Pracownik służby zdrowia: " + weProfession.isSelected());
					
					WebElement weAddress = driver.findElementByXPath("//*[@id='id_2_30']");
					weAddress.sendKeys(randomChars());
					System.out.println("Adres placówki: " + weAddress.getAttribute("value"));
				} else {
					WebElement weProfession = driver.findElementByXPath("//*[@type='radio' and @id='id_2_28_30']");
					weProfession.click();
					System.out.println("Pracownik inny: " + weProfession.isSelected());
					
					WebElement weAddress = driver.findElementByXPath("//*[@id='id_2_30']");
					weAddress.sendKeys(randomChars());
					System.out.println("Adres placówki: " + weAddress.getAttribute("value"));
					
					WebElement weProfession2 = driver.findElementByXPath("//*[@id='id_2_29']");
					weProfession2.sendKeys(randomChars());
					System.out.println("Zawód: " + weProfession2.getAttribute("value"));
				}
			}
			
			//Candidate status section
			int radioChoose2 = randomInt(3);
			if(radioChoose2 == 0) {
				WebElement weMinority = driver.findElementByXPath("//*[@type='radio' and @id='id_3_32_32']");
				weMinority.click();
				System.out.println("Mniejszość etniczna : " + !weMinority.isSelected());
			} else if (radioChoose2 == 1){
				WebElement weMinority = driver.findElementByXPath("//*[@type='radio' and @id='id_3_32_33']");
				weMinority.click();
				System.out.println("Mniejszość etniczna - Brak odpowiedzi : " + weMinority.isSelected());
			} else {
				WebElement weMinority = driver.findElementByXPath("//*[@type='radio' and @id='id_3_32_34']");
				weMinority.click();
				System.out.println("Mniejszość etniczna : " + weMinority.isSelected());
			}
			
			int radioChoose3 = randomInt(2);
			if(radioChoose3 == 0) {
				WebElement weHomeless = driver.findElementByXPath("//*[@type='radio' and @id='id_3_33_35']");
				weHomeless.click();
				System.out.println("Osoba bezdoman : " + !weHomeless.isSelected());
			} else {
				WebElement weHomeless = driver.findElementByXPath("//*[@type='radio' and @id='id_3_33_36']");
				weHomeless.click();
				System.out.println("Osoba bezdomna : " + weHomeless.isSelected());
			} 
			
			int radioChoose4 = randomInt(3);
			if(radioChoose4== 0) {
				WebElement weInvalid = driver.findElementByXPath("//*[@type='radio' and @id='id_3_34_37']");
				weInvalid.click();
				System.out.println("Osoba z niepełnosprawnościami : " + !weInvalid.isSelected());
			} else if (radioChoose4 == 1){
				WebElement weInvalid = driver.findElementByXPath("//*[@type='radio' and @id='id_3_34_38']");
				weInvalid.click();
				System.out.println("Osoba z niepełnosprawnosciami - Brak odpowiedzi : " + weInvalid.isSelected());
			} else {
				WebElement weInvalid = driver.findElementByXPath("//*[@type='radio' and @id='id_3_34_39']");
				weInvalid.click();
				System.out.println("Osoba z niepełnosprawnościami : " + weInvalid.isSelected());
			}
			
			int radioChoose5 = randomInt(2);
			if(radioChoose5 == 0) {
				WebElement weNotWorking = driver.findElementByXPath("//*[@type='radio' and @id='id_3_36_40']");
				weNotWorking.click();
				System.out.println("Gospodarstwo bez osób pracujących : " + !weNotWorking.isSelected());
			} else {
				WebElement weNotWorking = driver.findElementByXPath("//*[@type='radio' and @id='id_3_36_41']");
				weNotWorking.click();
				System.out.println("Gospodarstwo bez osób pracujących : " + weNotWorking.isSelected());
			}
			
			int radioChoose6 = randomInt(2);
			if(radioChoose6 == 0) {
				WebElement weChildren = driver.findElementByXPath("//*[@type='radio' and @id='id_3_37_42']");
				weChildren.click();
				System.out.println("Dzieci na utrzymaniu : " + !weChildren.isSelected());
			} else {
				WebElement weChildren = driver.findElementByXPath("//*[@type='radio' and @id='id_3_37_43']");
				weChildren.click();
				System.out.println("Dzieci na utrzymaniu : " + weChildren.isSelected());
			}
			
			int radioChoose7 = randomInt(2);
			if(radioChoose7 == 0) {
				WebElement weOnePerson = driver.findElementByXPath("//*[@type='radio' and @id='id_3_38_44']");
				weOnePerson.click();
				System.out.println("Jedna dorosła osoba z dziecimi : " + !weOnePerson.isSelected());
			} else {
				WebElement weOnePerson = driver.findElementByXPath("//*[@type='radio' and @id='id_3_38_45']");
				weOnePerson.click();
				System.out.println("Jedna dorosła osoba z dziecimi : " + weOnePerson.isSelected());
			}
			
			int radioChoose8 = randomInt(3);
			if(radioChoose8== 0) {
				WebElement weOther = driver.findElementByXPath("//*[@type='radio' and @id='id_3_39_46']");
				weOther.click();
				System.out.println("Inna niekorzystna sytuacja : " + !weOther.isSelected());
			} else if (radioChoose8 == 1){
				WebElement weOther = driver.findElementByXPath("//*[@type='radio' and @id='id_3_39_47']");
				weOther.click();
				System.out.println("Inna niekorzystna sytuacja - Brak odpowiedzi : " + weOther.isSelected());
			} else {
				WebElement weOther = driver.findElementByXPath("//*[@type='radio' and @id='id_3_39_48']");
				weOther.click();
				System.out.println("Inna niekorzystna sytuacja : " + weOther.isSelected());
			}
			
			
		
			
		
		}catch(NoSuchElementException e) {
			System.out.println("Nie poprawna ścieżka");
		}
		
		
	}
	
	
}









