package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV3() {
        val loginPage = open("http://localhost:9999", LoginPageV3.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balance1 = dashboardPage.getCardBalance(DataHelper.ID_FIRST_CARD);
        int balance2 = dashboardPage.getCardBalance(DataHelper.ID_SECOND_CARD);
        val dashboardRefillPage = dashboardPage.clickRefill1();
        val dataTransferMoney = DataHelper.TransferMoney.transferOnCardFirst();
        val dataGetAmount = DataHelper.Amount.transferSum();
        val dashboardPage1 = dashboardRefillPage.validRefill(dataTransferMoney, dataGetAmount);
        dashboardPage1.updating();
        val amount = dataGetAmount.getSum();
        int balance1Refill1 = dashboardPage1.getCardBalance(DataHelper.ID_FIRST_CARD);
        int balance2Refill1 = dashboardPage1.getCardBalance(DataHelper.ID_SECOND_CARD);
        assertEquals(balance1 + amount, balance1Refill1);
        assertEquals(balance2 - amount, balance2Refill1);
        val dashboardRefillPage2 = dashboardPage.clickRefill2();
        val dataTransferMoney2 = DataHelper.TransferMoney.transferOnCardSecond();
        val dashboardPage2 = dashboardRefillPage2.validRefill(dataTransferMoney2, dataGetAmount);
        dashboardPage2.updating();
        int balance1Refill2 = dashboardPage2.getCardBalance(DataHelper.ID_FIRST_CARD);
        int balance2Refill2 = dashboardPage2.getCardBalance(DataHelper.ID_SECOND_CARD);
        assertEquals(balance1Refill1 - amount, balance1Refill2);
        assertEquals(balance2Refill1 + amount, balance2Refill2);

    }

    /**
     * @Test void inputSummaMoreBalance() {
     * val loginPage = open("http://localhost:9999", LoginPageV3.class);
     * val authInfo = DataHelper.getAuthInfo();
     * val verificationPage = loginPage.validLogin(authInfo);
     * val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
     * val dashboardPage = verificationPage.validVerify(verificationCode);
     * val dashboardRefillPage = dashboardPage.clickRefill1();
     * val summa = DataHelper.Amount.transferBigSum();
     * val card = DataHelper.TransferMoney.transferOnCardFirst();
     * dashboardRefillPage.validRefill(card, summa);
     * $(".notification__content").shouldBe(Condition.visible).shouldHave(exactText("Ошибка! " + "Произошла ошибка"));
     * <p>
     * }
     * @Test void emptyFields() {
     * val loginPage = open("http://localhost:9999", LoginPageV3.class);
     * val authInfo = DataHelper.getAuthInfo();
     * val verificationPage = loginPage.validLogin(authInfo);
     * val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
     * val dashboardPage = verificationPage.validVerify(verificationCode);
     * val dashboardRefillPage = dashboardPage.clickRefill1();
     * dashboardRefillPage.emptyRefill();
     * $(".notification__content").shouldBe(Condition.visible).shouldHave(exactText("Ошибка! " + "Произошла ошибка"));
     * <p>
     * }
     * @Test void emptyFieldSumma() {
     * val loginPage = open("http://localhost:9999", LoginPageV3.class);
     * val authInfo = DataHelper.getAuthInfo();
     * val verificationPage = loginPage.validLogin(authInfo);
     * val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
     * val dashboardPage = verificationPage.validVerify(verificationCode);
     * val dashboardRefillPage = dashboardPage.clickRefill1();
     * val emptySumma = DataHelper.TransferMoney.transferOnCardFirst();
     * dashboardRefillPage.emptyRefillSumma(emptySumma);
     * $(".notification__content").shouldBe(Condition.visible).shouldHave(exactText("Ошибка! " + "Произошла ошибка"));
     * <p>
     * }
     */

    @Test
    void emptyFieldCard() {
        val loginPage = open("http://localhost:9999", LoginPageV3.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val dashboardRefillPage = dashboardPage.clickRefill1();
        val summa = DataHelper.Amount.transferSum();
        dashboardRefillPage.emptyRefillCod(summa);
        dashboardRefillPage.shouldMessageAboutError();
    }

    /** @Test void invalidSummaDouble() {
    val loginPage = open("http://localhost:9999", LoginPageV3.class);
    val authInfo = DataHelper.getAuthInfo();
    val verificationPage = loginPage.validLogin(authInfo);
    val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    val dashboardPage = verificationPage.validVerify(verificationCode);
    val dashboardRefillPage = dashboardPage.clickRefill1();
    val summa = DataHelper.AmountDouble.transferSumDouble();
    val card1 = DataHelper.TransferMoney.transferOnCardFirst();
    dashboardRefillPage.validRefillCardAndDoubleSumma(card1, summa);
    $(".notification__content").shouldBe(Condition.visible).shouldHave(exactText("Ошибка! " + "Произошла ошибка"));

    }

     @Test void invalidInputCard() {
     val loginPage = open("http://localhost:9999", LoginPageV3.class);
     val authInfo = DataHelper.getAuthInfo();
     val verificationPage = loginPage.validLogin(authInfo);
     val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
     val dashboardPage = verificationPage.validVerify(verificationCode);
     val dashboardRefillPage = dashboardPage.clickRefill1();
     val summa = DataHelper.Amount.transferSum();
     val card2 = DataHelper.TransferMoney.transferOnCardSecond();
     dashboardRefillPage.validRefill(card2, summa);
     $(".notification__content").shouldBe(Condition.visible).shouldHave(exactText("Ошибка! " + "Произошла ошибка"));

     }*/

    @Test
    void invalidCod() {
        val loginPage = open("http://localhost:9999", LoginPageV3.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.invalidLogin(authInfo);
        val verificationCode = DataHelper.invalidGetVerificationCodeFor(authInfo);
        verificationPage.invalidVerify(verificationCode);
        verificationPage.shouldMessageAboutErrorCod();
    }
}
