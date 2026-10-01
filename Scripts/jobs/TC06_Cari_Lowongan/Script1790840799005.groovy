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

def data = findTestData('Data Files/Cari_Lowongan')

for (int i = 1; i <= data.getRowNumbers(); i++) {
	String keyword = data.getValue('keyword', i).trim()
	String location = data.getValue('location', i).trim()
	String category = data.getValue('category', i).trim()
	String ada = data.getValue('harapan_ada', i).trim()
	String tidakAda = data.getValue('harapan_tidak_ada', i).trim()

	WebUI.openBrowser('')
	WebUI.maximizeWindow()

	CustomKeywords.'auth.LoginKeywords.loginEncrypted'('seeker@jobportal.test', '8SQVv/p9jVScEs4/2CZsLw==')

	String query = '?keyword=' + URLEncoder.encode(keyword, 'UTF-8') + '&location=' + location + '&category=' + category

	WebUI.navigateToUrl(GlobalVariable.BASE_URL + '/seeker/jobs' + query)
	WebUI.waitForPageLoad(10)

	println("Baris ${i} -> URL: ${WebUI.getUrl()}")

	if (ada != '') {
		WebUI.verifyTextPresent(ada, false)
	}
	if (tidakAda != '') {
		WebUI.verifyTextNotPresent(tidakAda, false)
	}

	WebUI.closeBrowser()
}