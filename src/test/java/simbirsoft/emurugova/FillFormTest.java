package simbirsoft.emurugova;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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
        Configuration.browserSize = "2100x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#gender-radio-1").parent().click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2005");
        $(".react-datepicker__day--015").click();
        $("#subjectsInput").setValue(subject).pressEnter();
        $("#hobbies-checkbox-3").parent().click();
        $("#uploadPicture").uploadFromClasspath("img/image.png");
        $("#currentAddress").setValue(currentAddress );
        $("div").scrollTo();
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#lastName").pressEnter();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName +" "+ lastName));
        $(".table-responsive").shouldHave(text(userEmail));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text(userNumber));
        $(".table-responsive").shouldHave(text("15 July,2005"));
        $(".table-responsive").shouldHave(text(subject));
        $(".table-responsive").shouldHave(text("Music"));
        $(".table-responsive").shouldHave(text("image.png"));
        $(".table-responsive").shouldHave(text(currentAddress));
        $(".table-responsive").shouldHave(text(state +" "+ city));
        $("#closeLargeModal").click();
    }
}

