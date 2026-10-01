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

def data = findTestData('Data Files/Akses_Role')

for (int i = 1; i <= data.getRowNumbers(); i++) {
	String email = data.getValue('email', i).trim()
	String password = data.getValue('password', i).trim()
	String path = data.getValue('path', i).trim()
	String hasil = data.getValue('hasil', i).trim()

	WebUI.openBrowser('')
	WebUI.maximizeWindow()

	if (email != '') {
		CustomKeywords.'auth.LoginKeywords.login'(email, password)
	}

	WebUI.navigateToUrl(GlobalVariable.BASE_URL + path)
	WebUI.waitForPageLoad(10)

	boolean masihDiHalaman = WebUI.getUrl().contains(path)
	boolean forbidden = WebUI.verifyTextPresent('403', false, FailureHandling.OPTIONAL)
	println("Baris ${i}: ${email} -> ${path} | URL akhir: ${WebUI.getUrl()}")

	if (hasil == 'allowed') {
		assert masihDiHalaman && !forbidden : "Baris ${i}: seharusnya boleh, tapi ditolak"
	} else {
		assert (!masihDiHalaman || forbidden) : "Baris ${i}: seharusnya ditolak, tapi bisa masuk"
	}

	WebUI.closeBrowser()
}