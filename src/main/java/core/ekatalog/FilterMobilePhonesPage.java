package core.ekatalog;

import core.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.stream.Collectors;

public class FilterMobilePhonesPage extends AbstractPage {

    @FindBys({
            @FindBy(xpath = "//span[@class='u']")
    })
    private List<WebElement> filterItems;

    public FilterMobilePhonesPage(final WebDriver driver) {
        super(driver);
    }

    public List<String> getFilterItems() {
        return filterItems.stream()
                //.filter(item -> item != null)
                .map(this::replaceText)
                //.map(item -> replaceText(item, ""))
                //.map(WebElement::getText)
                .collect(Collectors.toList());
    }

    private String replaceText(final WebElement webElement) {
        return webElement.getText().trim();
    }
}
