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
import org.openqa.selenium.Keys as Keys

// 1. Ambil tabel data login
def data = findTestData('Data Files/LoginData')

// 2. Ulangi untuk setiap baris
for (int i = 1; i <= data.getRowNumbers(); i++) {

	// 3. Baca kolom di baris ini (trim = buang spasi/tab tak terlihat)
	String email = data.getValue('email', i).trim()
	String password = data.getValue('password', i).trim()
	String expected = data.getValue('expected', i).trim()

	// 4. Browser baru per baris supaya sesi bersih
	WebUI.openBrowser('')
	WebUI.maximizeWindow()

	// 5. Login lewat keyword
	CustomKeywords.'auth.LoginKeywords.login'(email, password)

	// 6. Cetak hasil untuk diagnosa
	println("Baris ${i}: ${email} -> URL: ${WebUI.getUrl()}")

	// 7. Verifikasi URL akhir sesuai kolom expected
	WebUI.verifyMatch(WebUI.getUrl(), expected, true)

	WebUI.closeBrowser()
}