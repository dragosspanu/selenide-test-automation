import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class WiktionaryTest {

    @BeforeEach
    public void loadWebpage() {
        open("http://en.wiktionary.org/wiki/Wiktionary");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/words.csv")
    public void testWordSearch(String word) {
        $(By.name("search")).setValue(word);
        $(By.name("go")).click();
        $(By.id("firstHeading")).shouldHave(Condition.exactText(word));
    }

    @AfterEach
    public void closeBrowser() {
        closeWebDriver();
    }
}