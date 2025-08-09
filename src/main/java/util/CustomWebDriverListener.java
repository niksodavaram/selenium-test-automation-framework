package util;

import com.aventstack.extentreports.ExtentTest;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import reportgenerator.ExtentFactory;

import java.util.Arrays;

public class CustomWebDriverListener implements WebDriverListener {

    @Override
    public void beforeClick(WebElement element) {
        Allure.step("Clicking: " + describe(element));
        ExtentTest extentTest = ExtentFactory.getInstance().getExtent();
        if (extentTest != null) {
            extentTest.info("[WebDriverListener] Clicking: " + describe(element));
        }
        System.out.println("[WebDriverListener] Clicking: " + describe(element));
    }

    @Override
    public void afterClick(WebElement element) {
        System.out.println("[WebDriverListener] Clicked: " + describe(element));
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        System.out.println("[WebDriverListener] Typing '" + Arrays.toString(keysToSend) + "' in: " + describe(element));
    }

    private String describe(WebElement element) {
        try {
            String tag = element.getTagName();
            String id = element.getAttribute("id");
            String text = element.getText();
            return String.format("<%s id='%s' text='%s'>", tag, id, text);
        } catch (Exception e) {
            return "[unknown element]";
        }
    }
}
