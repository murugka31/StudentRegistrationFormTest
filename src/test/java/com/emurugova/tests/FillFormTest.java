package com.emurugova.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.emurugova.reportData.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class FillFormTest {

    String firstName = "Alex";
    String lastName = "Alexandrov";
    String userEmail = "alex@yandex.ru";
    String userNumber = "8999999999";
    String subject = "Chemistry";
    String currentAddress = "some street";
    String state = "Haryana";
    String city = "Karnal";

    @BeforeAll
    static void beforeAll(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo",true);
        Configuration.browserCapabilities = capabilities;
        Configuration.browserSize = "2100x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
    }
    @AfterEach
    public void attachMethods() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    void fillFormTest() {
        step("Open students registration form", () -> {
            open("/automation-practice-form");
        });

        step("Fill the form", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(userEmail);
            $("#gender-radio-1").parent().click();
            $("#userNumber").setValue(userNumber);
        });

        step("Set the birth date", () -> {
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("July");
            $(".react-datepicker__year-select").selectOption("2005");
            $(".react-datepicker__day--015").click();
        });

        step("Set the subject", () -> {
            $("#subjectsInput").setValue(subject).pressEnter();
        });

        step("Set the hobby", () -> {
            $("#hobbies-checkbox-3").parent().click();
        });

        step("Upload the picture", () -> {
            $("#uploadPicture").uploadFromClasspath("img/image.png");
        });

        step("Set the address", () -> {
            $("#currentAddress").setValue(currentAddress );
            $("div").scrollTo();
            $("#state").click();
            $("#stateCity-wrapper").$(byText(state)).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText(city)).click();
        });

        step("Submit the form", () -> {
            $("#lastName").pressEnter();
        });

        step("Check the form", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(text(firstName + " " + lastName));
            $(".table-responsive").shouldHave(text(userEmail));
            $(".table-responsive").shouldHave(text("Male"));
            $(".table-responsive").shouldHave(text(userNumber));
            $(".table-responsive").shouldHave(text("15 July,2005"));
            $(".table-responsive").shouldHave(text(subject));
            $(".table-responsive").shouldHave(text("Music"));
            $(".table-responsive").shouldHave(text("image.png"));
            $(".table-responsive").shouldHave(text(currentAddress));
            $(".table-responsive").shouldHave(text(state + " " + city));
        });
    }
}