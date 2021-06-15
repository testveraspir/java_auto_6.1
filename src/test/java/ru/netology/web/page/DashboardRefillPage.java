package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardRefillPage {
    private SelenideElement amount = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");
    private SelenideElement button = $(withText("Пополнить"));

    public DashboardRefillPage() {
        amount.shouldBe(Condition.visible, Duration.ofSeconds(60000));
    }


    public DashboardPage validRefill(DataHelper.TransferMoney transferMoney, DataHelper.Amount amount1) {
        from.sendKeys(Keys.CONTROL, "a");
        from.sendKeys(Keys.DELETE);
        from.setValue(transferMoney.getNumber());
        amount.sendKeys(Keys.CONTROL, "a");
        amount.sendKeys(Keys.DELETE);
        amount.setValue(String.valueOf(amount1.getSum()));
        button.click();
        return new DashboardPage();
    }

    public DashboardPage emptyRefill() {
        button.click();
        return new DashboardPage();
    }

    public DashboardPage emptyRefillSumma(DataHelper.TransferMoney transferMoney) {
        from.sendKeys(Keys.CONTROL, "a");
        from.sendKeys(Keys.DELETE);
        from.setValue(transferMoney.getNumber());
        button.click();
        return new DashboardPage();
    }

    public DashboardPage emptyRefillCod(DataHelper.Amount amount1) {
        amount.sendKeys(Keys.CONTROL, "a");
        amount.sendKeys(Keys.DELETE);
        amount.setValue(String.valueOf(amount1.getSum()));
        button.click();
        return new DashboardPage();
    }

    public void shouldMessageAboutError() {
        $(".notification__content").shouldBe(Condition.visible).shouldHave(exactText("Ошибка! " + "Произошла ошибка"));
    }

    public DashboardPage validRefillCardAndDoubleSumma(DataHelper.TransferMoney transferMoney, DataHelper.AmountDouble amount1) {
        from.sendKeys(Keys.CONTROL, "a");
        from.sendKeys(Keys.DELETE);
        from.setValue(transferMoney.getNumber());
        amount.sendKeys(Keys.CONTROL, "a");
        amount.sendKeys(Keys.DELETE);
        amount.setValue(String.valueOf(amount1.getSumDouble()));
        button.click();
        return new DashboardPage();
    }


}
