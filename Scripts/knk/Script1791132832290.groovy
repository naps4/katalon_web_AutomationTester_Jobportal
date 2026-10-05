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

WebUI.navigateToUrl('http://127.0.0.1:8000/login')

WebUI.setText(findTestObject('Page_Laravel/input_namecompany.com'), 'company@jobportal.test')

WebUI.setEncryptedText(findTestObject('Page_Laravel/input_Masukan password Anda'), '8SQVv/p9jVScEs4/2CZsLw==')

WebUI.click(findTestObject('Page_Laravel/button_Masuk Sekarang'))

WebUI.delay(7)

WebUI.click(findTestObject('Page_Dashboard Perusahaan - Laravel/a_Kelola Lowongan'))

WebUI.waitForElementVisible(findTestObject('Page_Manajemen Lowongan - Laravel/a_Edit'), 10)
WebUI.click(findTestObject('Page_Manajemen Lowongan - Laravel/a_Edit'))

WebUI.selectOptionByValue(findTestObject('Page_Ubah Lowongan Full Stuck Developer - Laravel/select_Lokasi Wilayah _'), '8', 
    false)

WebUI.click(findTestObject('Page_Ubah Lowongan Full Stuck Developer - Laravel/input_Jumlah Orang _'))

WebUI.click(findTestObject('Page_Ubah Lowongan Full Stuck Developer - Laravel/input_salary_min'))

WebUI.click(findTestObject('Page_Ubah Lowongan Full Stuck Developer - Laravel/input_salary_max'))

WebUI.click(findTestObject('Page_Ubah Lowongan Full Stuck Developer - Laravel/h6_Persyaratan Tes Psikologi'))

WebUI.setText(findTestObject('Page_Ubah Lowongan Full Stuck Developer - Laravel/textarea_Tuliskan poin-poin tanggung jawab peker'), 
    'aaaaaaaaaaaaaa')

WebUI.click(findTestObject('Page_Ubah Lowongan Full Stuck Developer - Laravel/button_SIMPAN PERUBAHAN'))

