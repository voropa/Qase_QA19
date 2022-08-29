import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class Page {
    private final static String BUTTON_LOCATOR = "qwe";
    private final static String ELEMENT = "zxc";
    private final static String PARENT_ELEMENT = "zxc";


    public String getElementText() {
        $(ELEMENT).shouldBe(Condition.visible);
        return $(ELEMENT).getText();
    }
}
