package com.codezyng.automation.dataproviders;

import com.codezyng.automation.utils.ExcelUtils;
import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "loginExcelData")
    public Object[][] getLoginData() {
        return ExcelUtils.getTestData(
                "src/test/resources/testdata/LoginData.xlsx",
                "Login"
        );
    }
}
