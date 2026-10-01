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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// Definisi elemen form lewat atribut name (tidak butuh Object Repository)
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

def data = findTestData('Data Files/Register_Negatif')

for (int i = 1; i <= data.getRowNumbers(); i++) {
	String nama = data.getValue('nama', i).trim()
	String email = data.getValue('email', i).trim()
	String password = data.getValue('password', i).trim()
	String konfirmasi = data.getValue('konfirmasi', i).trim()
	String pesan = data.getValue('pesan', i).trim()

	WebUI.openBrowser('')
	WebUI.maximizeWindow()

	WebUI.navigateToUrl(GlobalVariable.BASE_URL + '/register')
	WebUI.waitForPageLoad(10)

	WebUI.setText(inputNama, nama)
	WebUI.setText(inputEmail, email)
	WebUI.setText(inputPassword, password)
	WebUI.setText(inputKonfirmasi, konfirmasi)
	WebUI.click(tombolDaftar)
	WebUI.waitForPageLoad(10)

	println("Baris ${i} -> URL: ${WebUI.getUrl()}")

	// pendaftaran harus GAGAL: tetap di halaman register, tidak masuk dashboard/verifikasi
	WebUI.verifyMatch(WebUI.getUrl(), '.*register.*', true)

	if (pesan != '') {
		WebUI.verifyTextPresent(pesan, false)
	}

	WebUI.closeBrowser()
}