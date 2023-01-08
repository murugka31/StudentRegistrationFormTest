package com.emurugova.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.emurugova.tests.TestData.*;

public class FillFormWithCommentsTest extends TestBase {

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#gender-radio-1").parent().click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--015").click();
        $("#subjectsInput").setValue(subject).pressEnter();
        $("#hobbies-checkbox-3").parent().click();
        $("#uploadPicture").uploadFromClasspath(userDirectory);
        $("#currentAddress").setValue(currentAddress );
        $("div").scrollTo();
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#lastName").pressEnter();

        $("#example-modal-sizes-title-lg").shouldHave(text(finalFormTitle));
        $(".table-responsive").shouldHave(text(firstName +" "+ lastName));
        $(".table-responsive").shouldHave(text(userEmail));
        $(".table-responsive").shouldHave(text(userGender));
        $(".table-responsive").shouldHave(text(userNumber));
        $(".table-responsive").shouldHave(text("15" + " " + month + "," + year));
        $(".table-responsive").shouldHave(text(subject));
        $(".table-responsive").shouldHave(text(userHobby));
        $(".table-responsive").shouldHave(text(userFile));
        $(".table-responsive").shouldHave(text(currentAddress));
        $(".table-responsive").shouldHave(text(state +" "+ city));
        $("#closeLargeModal").click();
    }
}

