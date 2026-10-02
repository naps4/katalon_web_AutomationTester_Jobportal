import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.llm.keyword.LlmKeywords as LLM
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser(null)

WebUI.maximizeWindow()                 // BARU

// DIUBAH: login lewat keyword (menggantikan navigateToUrl, setText, setEncryptedText, click)
CustomKeywords.'auth.LoginKeywords.loginEncrypted'('seeker@jobportal.test', '8SQVv/p9jVScEs4/2CZsLw==')

WebUI.delay(3)
WebUI.click(findTestObject('Page_HerbaTech - Career Portal/a_Lamaran Saya'))

// BARU: tunggu halaman lalu hitung lamaran sebelum ditarik
WebUI.waitForPageLoad(10)

String xpathBaris = "//tr[.//a[contains(@href,'/seeker/applications/')]]"
int sebelum = DriverFactory.getWebDriver().findElements(By.xpath(xpathBaris)).size()
println("Jumlah lamaran sebelum: ${sebelum}")
assert sebelum > 0 : 'Tidak ada lamaran untuk ditarik. Jalankan TC10 dulu.'

WebUI.click(findTestObject('Page_HerbaTech - Career Portal/button_Tarik Lamaran'))

// BARU: terima popup konfirmasi kalau ada
if (WebUI.waitForAlert(3, FailureHandling.OPTIONAL)) {
	WebUI.acceptAlert()
}

// BARU: muat ulang daftar dan hitung lagi
WebUI.waitForPageLoad(10)
WebUI.navigateToUrl(GlobalVariable.BASE_URL + '/seeker/applications')
WebUI.waitForPageLoad(10)

int sesudah = DriverFactory.getWebDriver().findElements(By.xpath(xpathBaris)).size()
println("Jumlah lamaran sesudah: ${sesudah}")

// verifikasi: berkurang tepat satu
assert sesudah == sebelum - 1 : "Lamaran seharusnya berkurang 1, tapi ${sebelum} -> ${sesudah}"

WebUI.closeBrowser()
