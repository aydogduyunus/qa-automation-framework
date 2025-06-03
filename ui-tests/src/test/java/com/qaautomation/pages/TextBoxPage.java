package com.qaautomation.pages;

import com.qaautomation.utils.DatabaseUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TextBoxPage {
    WebDriver driver;
    WebDriverWait wait;
    String browserName; // ✅ Burada da field tanımı yapılmalı

    public TextBoxPage(WebDriver driver, String browserName) { // ✅ Parametreli constructor
        this.driver = driver;
        this.browserName = browserName;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By elementsCard = By.xpath("//div[@class='card mt-4 top-card' and contains(., 'Elements')]");
    By textBoxMenu = By.xpath("//span[@class='text' and contains(text(), 'Text Box')]");
    By nameField = By.id("userName");
    By emailField = By.id("userEmail");
    By currentAddress = By.id("currentAddress");
    By permanentAddress = By.id("permanentAddress");
    By submitButton = By.id("submit");

    @Step("Ana sayfada 'Elements' bölümünü aç")
    public void openElementsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(elementsCard)).click();
    }

    @Step("'Text Box' formunu aç")
    public void openTextBoxForm() {
        wait.until(ExpectedConditions.elementToBeClickable(textBoxMenu)).click();
    }

    @Step("Formu doldur: İsim={0}, Email={1}, Adres={2}, Kalıcı Adres={3}")
    public void fillForm(String name, String email, String currentAddressText, String permanentAddressText) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(currentAddress).sendKeys(currentAddressText);
        driver.findElement(permanentAddress).sendKeys(permanentAddressText);

        // ✅ Veritabanına browserName dahil olarak kayıt
        DatabaseUtil.insertFormData(name, email, currentAddressText, permanentAddressText, browserName);
    }

    @Step("Formu submit et")
    public void submitForm() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelectorAll('iframe').forEach(function(e) { e.remove(); });");
        WebElement submit = driver.findElement(submitButton);
        js.executeScript("arguments[0].scrollIntoView(true);", submit);
        submit.click();
    }

    public void goTo() {
        driver.get("https://demoqa.com/text-box");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
    }
}