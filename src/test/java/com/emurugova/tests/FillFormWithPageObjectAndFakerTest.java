package com.emurugova.tests;

import com.emurugova.pages.RegistrationPage;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

public class FillFormWithPageObjectAndFakerTest extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            currentAddress = faker.address().streetAddress();

    @Test
    void fillFormTest() {
        registrationPage.openPage();
        registrationPage.typeFirstName(firstName)
                        .typeLastName(lastName)
                        .typeUserEmail(userEmail)
                        .selectGender()
                        .typeUserNumber(TestData.userNumber)
                        .setDate(TestData.day, TestData.month, TestData.year)
                        .selectSubject(TestData.subject)
                        .selectHobby()
                        .loadFile(TestData.userDirectory)
                        .typeCurrentAddress(currentAddress)
                        .scrollPage()
                        .selectState(TestData.state)
                        .selectCity(TestData.city)
                        .callTheFinalForm();

        registrationPage.checkFinalFormTitle(TestData.finalFormTitle);
        registrationPage.checkStudentName(firstName,lastName)
                        .checkUserEmail(userEmail)
                        .checkUserGender(TestData.userGender)
                        .checkUserNumber(TestData.userNumber)
                        .checkUserDate(TestData.day, TestData.month, TestData.year)
                        .checkUserSubject(TestData.subject)
                        .checkUserHobby(TestData.userHobby)
                        .checkLoadedFile(TestData.userFile)
                        .checkCurrentAddress(currentAddress)
                        .checkStateAndCity(TestData.state, TestData.city)
                        .closeTheForm();
    }
}

