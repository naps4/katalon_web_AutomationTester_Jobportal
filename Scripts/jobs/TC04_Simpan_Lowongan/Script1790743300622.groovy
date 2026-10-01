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

WebUI.openBrowser(null)

CustomKeywords.'auth.LoginKeywords.loginEncrypted'('seeker@jobportal.test', '8SQVv/p9jVScEs4/2CZsLw==')

WebUI.delay(5)

WebUI.click(findTestObject('Page_HerbaTech - Career Portal/a_Cari Lowongan'))

WebUI.waitForElementVisible(findTestObject('Page_HerbaTech - Career Portal/a_Detail'), 15)

WebUI.click(findTestObject('Page_HerbaTech - Career Portal/a_Detail'))

if (WebUI.verifyElementPresent(findTestObject('Page_HerbaTech - Career Portal/button_Simpan'), 3, FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('Page_HerbaTech - Career Portal/button_Simpan'))
} else {
    WebUI.comment('Lowongan sudah tersimpan, skip klik Simpan')
}

WebUI.click(findTestObject('Page_HerbaTech - Career Portal/a_Tersimpan'))

WebUI.waitForPageLoad(10)

// verifikasi: halaman Tersimpan terbuka dan ada minimal satu lowongan
WebUI.verifyTextPresent('Lowongan Tersimpan', false)
WebUI.verifyTextPresent('Software Developer', false)

WebUI.closeBrowser()

