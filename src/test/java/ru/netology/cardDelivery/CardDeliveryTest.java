package ru.netology.cardDelivery;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    public String getDate(int days, String dateFormat) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(dateFormat));
    }

    @Test
    void shouldFillOrderCardForm() {
        String meetingDate = getDate(3, "dd.MM.yyyy");

        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Краснодар");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(meetingDate);
        $("[data-test-id =\"name\" ] input").setValue("Иванов Иван");
        $("[data-test-id =\"phone\"] input").setValue("+79189189818");
        $("[data-test-id =\"agreement\"]").click();
        $(By.className("button")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(30));
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(30)).shouldHave(exactText("Встреча успешно забронирована на " + meetingDate));
    }
}
