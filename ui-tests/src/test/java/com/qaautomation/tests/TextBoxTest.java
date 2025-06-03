package com.qaautomation.tests;

import com.qaautomation.utils.DatabaseUtil;
import com.qaautomation.pages.TextBoxPage;
import com.qaautomation.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("UI Tests")
@Feature("TextBox Feature")
public class TextBoxTest extends BaseTest {

    @Story("Fill TextBox and Submit")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Fill and submit the TextBox form and verify DB entry")
    public void fillTextBoxForm() {
        // Test verileri burada tanımlı olmalı
        String name = "Yunus";
        String email = "yunus@example.com";
        String currentAddress = "İstanbul";
        String permanentAddress = "Türkiye";

        // Sayfaya git ve formu doldur
        textBoxPage.goTo();
        textBoxPage.fillForm(name, email, currentAddress, permanentAddress);
        textBoxPage.submitForm();

        // ✅ SQL doğrulama
        boolean isDataInserted = DatabaseUtil.isFormDataPresent(name, email);
        Assert.assertTrue(isDataInserted, "❌ Form verisi veritabanına eklenmemiş!");

        // Konsola yaz
        System.out.println("✅ Veritabanında kayıt bulundu: " + email);

        // Allure log
        Allure.step("✅ Veritabanında kayıt bulundu: " + email);
    }
}