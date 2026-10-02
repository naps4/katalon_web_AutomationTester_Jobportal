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

WebUI.openBrowser(null)

CustomKeywords.'auth.LoginKeywords.loginEncrypted'('seeker@jobportal.test', '8SQVv/p9jVScEs4/2CZsLw==')

WebUI.delay(5)

WebUI.click(findTestObject('Page_HerbaTech - Career Portal/a_Cari Lowongan'))

WebUI.click(findTestObject('Page_HerbaTech - Career Portal/a_Detail'))

TestObject judulLowongan = new TestObject('judulLowongan')
judulLowongan.addProperty('xpath', ConditionType.EQUALS, "(//main//h1 | //main//h2)[1]")
String judul = WebUI.getText(judulLowongan).trim()
println('Melamar lowongan: ' + judul)

WebUI.click(findTestObject('Page_HerbaTech - Career Portal/a_Lamar Sekarang'))

WebUI.uploadFile(findTestObject('Page_HerbaTech - Career Portal/input_cover_letter_file'), 'C:/Users/THINKPAD T480s/Documents/katalon-data/cv_dummy.pdf')

WebUI.click(findTestObject('Page_HerbaTech - Career Portal/button_Lanjut'))

// BARU: tunggu langkah pertanyaan tampil
WebUI.waitForElementVisible(findTestObject('Page_HerbaTech - Career Portal/select_q1'), 10)

WebUI.selectOptionByValue(findTestObject('Page_HerbaTech - Career Portal/select_q1'), 'Ya', false)

WebUI.selectOptionByValue(findTestObject('Page_HerbaTech - Career Portal/select_q2'), 'Ya', false)

WebUI.selectOptionByValue(findTestObject('Page_HerbaTech - Career Portal/select_q3'), 'Ya', false)

WebUI.selectOptionByValue(findTestObject('Page_HerbaTech - Career Portal/select_q4'), 'Ya', false)

WebUI.setText(findTestObject('Page_HerbaTech - Career Portal/input_Contoh_ 5000000'), '1999988')

WebUI.setText(findTestObject('Page_HerbaTech - Career Portal/input_q6Range'), '8')

// DIUBAH: tanggal 7 hari dari sekarang (sebelumnya '2026-10-03')
WebUI.setText(findTestObject('Page_HerbaTech - Career Portal/input_q15'), java.time.LocalDate.now().plusDays(7).toString())

WebUI.setText(findTestObject('Page_HerbaTech - Career Portal/textarea_Jelaskan secara singkat apa yang Anda c'), 'qwpwmdkwmdkakamsd')

WebUI.setText(findTestObject('Page_HerbaTech - Career Portal/input_Contoh_ Saya mahir menggunakan framework L'), 'psaskmdkskdapskda')

WebUI.selectOptionByValue(findTestObject('Page_HerbaTech - Career Portal/select_q9'), 'Fleksibel', false)

WebUI.setText(findTestObject('Page_HerbaTech - Career Portal/input_Contoh_ Lingkungan yang terbuka, transpara'), 'lingkungan yang sehat')

WebUI.setText(findTestObject('Page_HerbaTech - Career Portal/textarea_Tuliskan jawaban Anda di sini'), 'asadsdsdsd')

WebUI.setText(findTestObject('Page_HerbaTech - Career Portal/textarea_Bagaimana situasinya dan bagaimana Anda'), 'sdsdsdsdsdsd')

WebUI.setText(findTestObject('Page_HerbaTech - Career Portal/textarea_Apa yang membuat Anda memilih kami diba'), 'dsdasdsadsdsasdsad')

WebUI.setText(findTestObject('Page_HerbaTech - Career Portal/input_Contoh_ Saya ingin menjadi Senior Develope'), 'sdsasdsadsdsd')

WebUI.click(findTestObject('Page_HerbaTech - Career Portal/button_Lanjut ke Review'))

// BARU: tunggu halaman review dan tombol kirim siap
WebUI.waitForElementClickable(findTestObject('Page_HerbaTech - Career Portal/button_Kirim Lamaran Sekarang'), 10)

WebUI.click(findTestObject('Page_HerbaTech - Career Portal/button_Kirim Lamaran Sekarang'))

// BARU: verifikasi lamaran masuk
WebUI.waitForPageLoad(10)
println('URL setelah kirim: ' + WebUI.getUrl())

// berhasil kirim = diarahkan ke halaman Lamaran Saya
WebUI.verifyMatch(WebUI.getUrl(), '.*seeker/applications.*', true)

WebUI.navigateToUrl(GlobalVariable.BASE_URL + '/seeker/applications')
WebUI.waitForPageLoad(10)
WebUI.verifyTextPresent(judul, false)
WebUI.closeBrowser()