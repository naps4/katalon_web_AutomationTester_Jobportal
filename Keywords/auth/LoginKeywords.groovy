package auth

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

class LoginKeywords {

	@Keyword
	def login(String email, String password) {
		WebUI.navigateToUrl(GlobalVariable.BASE_URL + '/login')
		WebUI.setText(findTestObject('Object Repository/Page_Laravel/input_email'), email)
		WebUI.setText(findTestObject('Object Repository/Page_Laravel/input_password'), password)
		WebUI.click(findTestObject('Object Repository/Page_Laravel/button_Masuk Sekarang'))
		WebUI.waitForPageLoad(10)
	}

	// untuk test yang memakai password terenkripsi hasil rekaman Katalon
	@Keyword
	def loginEncrypted(String email, String encryptedPassword) {
		WebUI.navigateToUrl(GlobalVariable.BASE_URL + '/login')
		WebUI.setText(findTestObject('Object Repository/Page_Laravel/input_email'), email)
		WebUI.setEncryptedText(findTestObject('Object Repository/Page_Laravel/input_password'), encryptedPassword)
		WebUI.click(findTestObject('Object Repository/Page_Laravel/button_Masuk Sekarang'))
		WebUI.waitForPageLoad(10)
	}
}