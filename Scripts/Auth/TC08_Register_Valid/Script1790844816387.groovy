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
import com.kms.katalon.core.testobject.ConditionType
import java.text.SimpleDateFormat
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// Email unik tiap kali dijalankan, contoh: qa.20261001153045@example.com
String stamp = new SimpleDateFormat('yyyyMMddHHmmss').format(new Date())
String emailBaru = "herbatech.${stamp}@example.com"

TestObject inputNama = new TestObject('inputNama')
inputNama.addProperty('xpath', ConditionType.EQUALS, "//input[@name='name']")

TestObject inputEmail = new TestObject('inputEmail')
inputEmail.addProperty('xpath', ConditionType.EQUALS, "//input[@name='email']")

TestObject inputPassword = new TestObject('inputPassword')
inputPassword.addProperty('xpath', ConditionType.EQUALS, "//input[@name='password']")

TestObject inputKonfirmasi = new TestObject('inputKonfirmasi')
inputKonfirmasi.addProperty('xpath', ConditionType.EQUALS, "//input[@name='password_confirmation']")

TestObject tombolDaftar = new TestObject('tombolDaftar')
tombolDaftar.addProperty('xpath', ConditionType.EQUALS, "(//form//button[@type='submit'])[1]")

WebUI.openBrowser('')
WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.BASE_URL + '/register')
WebUI.waitForPageLoad(10)

WebUI.setText(inputNama, 'QA Tester')
WebUI.setText(inputEmail, emailBaru)
WebUI.setText(inputPassword, 'Password123!')
WebUI.setText(inputKonfirmasi, 'Password123!')
WebUI.click(tombolDaftar)
WebUI.waitForPageLoad(10)

println("Akun dibuat: ${emailBaru} | URL setelah daftar: ${WebUI.getUrl()}")

// berhasil = tidak lagi di halaman register
WebUI.verifyMatch(WebUI.getUrl(), '.*(dashboard|verify-email).*', true)

WebUI.closeBrowser()