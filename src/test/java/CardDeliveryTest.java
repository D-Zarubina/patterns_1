import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CardDeliveryTest {

    @Test
    @DisplayName("Should successful meeting")
    void shouldSuccessfulMeeting() {
        Selenide.open("http://localhost:9999/");
        DataGenerator.UserInformation validUser = DataGenerator.Registration.generateUser("ru");
        int dayForFirstMeeting = 3;
        String firstMeeting = DataGenerator.generatorData(dayForFirstMeeting);
        int dayNextMeeting = 10;
        String nextMeeting = DataGenerator.generatorData(dayNextMeeting);
        $("[placeholder='Город']").setValue(validUser.getCity());
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(firstMeeting);
        $("[name='name']").setValue(validUser.getName());
        $("[name='phone']").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + firstMeeting)).shouldBe(visible);
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(nextMeeting);
        $(byText("Запланировать")).click();
        $(byText("Перепланировать")).click();
        $(".notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + nextMeeting)).shouldBe(visible);

    }
}
