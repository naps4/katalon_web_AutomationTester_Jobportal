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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys

String judulKerja = 'Softaware Developer ' + new Date().format('yyyyMMddHHmmss')

WebUI.openBrowser(null)

WebUI.maximizeWindow()

CustomKeywords.'auth.LoginKeywords.loginEncrypted'('seeker@jobportal.test', '8SQVv/p9jVScEs4/2CZsLw==')

WebUI.delay(3)

WebUI.click(findTestObject('Page_HerbaTech - Career Portal/a_navbarDropdown'))

WebUI.waitForElementClickable(findTestObject('Page_HerbaTech - Career Portal/a_Profil Saya'), 10)

WebUI.click(findTestObject('Page_HerbaTech - Career Portal/a_Profil Saya'))

WebUI.waitForPageLoad(10)

WebUI.click(findTestObject('Page_HerbaTech - Career Portal/a_Riwayat'))

WebUI.waitForElementClickable(findTestObject('Page_HerbaTech - Career Portal/button_Tambah'), 10)

WebUI.click(findTestObject('Page_HerbaTech - Career Portal/button_Tambah'))

WebUI.waitForElementVisible(findTestObject('Page_HerbaTech - Career Portal/input_job_title'), 10)

WebUI.setText(findTestObject('Page_HerbaTech - Career Portal/input_job_title'), judulKerja)

WebUI.setText(findTestObject('Page_HerbaTech - Career Portal/input_company_name'), 'PT Teknologi Uji')

WebUI.setText(findTestObject('Page_HerbaTech - Career Portal/input_start_date'), '27-09-2026')

WebUI.setText(findTestObject('Page_HerbaTech - Career Portal/input_end_date'), '1-10-2026')

WebUI.setText(findTestObject('Page_HerbaTech - Career Portal/textarea_description'), 'Pengalaman untuk pengujian otomatis')

WebUI.click(findTestObject('Page_HerbaTech - Career Portal/button_Simpan Karier'))

WebUI.waitForPageLoad(10)

WebUI.delay(2)

// DIAGNOSA 1: apakah ada pesan error validasi?
def errors = DriverFactory.getWebDriver().findElements(By.cssSelector('.invalid-feedback, .alert-danger, .text-danger'))
println('Pesan error di halaman: ' + errors.collect { it.getText() }.findAll { it }.toString())

println('URL setelah simpan: ' + WebUI.getUrl())
println('Screenshot: ' + WebUI.takeScreenshot())

WebUI.click(findTestObject('Page_HerbaTech - Career Portal/a_Riwayat'))

WebUI.delay(1)

// DIAGNOSA 2: apakah judul ada di teks yang tampil, dan di HTML?
String teksHalaman = DriverFactory.getWebDriver().findElement(By.tagName('body')).getText()
String sumberHalaman = DriverFactory.getWebDriver().getPageSource()

println('Judul ada di HTML: ' + sumberHalaman.contains(judulKerja))
println('Judul ada di teks tampil (tanpa beda huruf): ' + teksHalaman.toLowerCase().contains(judulKerja.toLowerCase()))
println('Judul ada di teks tampil (persis): ' + teksHalaman.contains(judulKerja))

WebUI.closeBrowser()