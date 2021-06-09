package ru.netology.web.page;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;


import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement button1 = $("[data-test-id = '92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button");
    private SelenideElement button2 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button");
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement update = $("[data-test-id = 'action-reload']");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    public DashboardPage() {
        heading.shouldBe(visible, Duration.ofSeconds(60000));
    }

    public DashboardRefillPage clickRefill1() {
        button1.click();
        return new DashboardRefillPage();
    }

    public DashboardRefillPage clickRefill2() {
        button2.click();
        return new DashboardRefillPage();
    }

    public void updating() {
        update.click();
    }

    public int getCardBalance(String id) {

        val text2 = cards.find(attribute("data-test-id", id)).text();

        return extractBalance(text2);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }


}

