import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class DeliveryTest {


    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());}
    @AfterAll
    static void tearDownAll() {    SelenideLogger.removeListener("allure");}


    @Test
    void shouldSuccessfulPlanAndReplanMeeting() {
        open("http://localhost:9999");
        User user = DataGenerator.Registration.generateUser();
        $("span[data-test-id='city'] input").setValue(user.getCity());
        $("span[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("span[data-test-id='date'] input").setValue(DataGenerator.generateDate(3));
        $("span[data-test-id='name'] input").setValue(user.getName());
        $("span[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + DataGenerator.generateDate(3)), Duration.ofSeconds(15)).shouldBe(Condition.visible);
        $("span[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("span[data-test-id='date'] input").setValue(DataGenerator.generateDate(10));
        $("button.button").click();
        $(withText("Необходимо подтверждение")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".button__text").click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + DataGenerator.generateDate(10)), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }
}
