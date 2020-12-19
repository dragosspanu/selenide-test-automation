import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;
import static utils.FileUtil.readTextFileAsString;

public class ContactFormTest {

    @BeforeEach
    public void loadWebpage() {
        open("http://automationpractice.com/index.php?controller=contact");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/contactForm.csv", numLinesToSkip = 1)
    public void testContactForm(String subjectHeading, String emailAddress, String orderReference, String message, String uploadFile) {
        $(By.id("id_contact")).selectOption(subjectHeading);
        $(By.id("email")).setValue(emailAddress);
        $(By.id("id_order")).setValue(orderReference);
        $(By.id("message")).setValue(readTextFileAsString(message));
        $(By.id("fileUpload")).uploadFile(new File(uploadFile));
        $(By.id("submitMessage")).click();
        $(By.className("alert-success")).shouldHave(Condition.text("Your message has been successfully sent to our team"));
    }

    @AfterEach
    public void closeBrowser() {
        closeWebDriver();
    }
}