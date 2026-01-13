package com.codezyng.automation.dataproviders;

import com.codezyng.automation.utils.ExcelUtils;
import org.testng.annotations.DataProvider;

public class LoginDataProvider {

	@DataProvider(name = "loginExcelData")
	public Object[][] getLoginData() { // Fixed return type to Object[][]
		String filePath = "src/test/resources/testdata/LoginData.xlsx".replace("/",
				System.getProperty("file.separator"));
		return ExcelUtils.getTestData(filePath, "Login");
	}
}
