package core.sportchek;

import core.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.Optional;

public class SportchekProductDetailsPage extends AbstractPage {

    @FindBy(xpath = "//a[@data-control-type='color' and not(contains(@class, 'selected'))]")
    private WebElement color;

    @FindBys({
            @FindBy(xpath = "//span[@class='option-tiles__item-title']")
    })
    private List<WebElement> sizes;

    @FindBy(xpath = "//button[contains(@class, 'add-cart')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[@id='header-cart__count']")
    private WebElement redirectToShoppingCartIcon;

    public SportchekProductDetailsPage(final WebDriver driver) {
        super(driver);
    }

    public void selectRequiredSize(final String size) {
        Optional<WebElement> requiredSize = sizes.stream()
                .filter(item -> item.getText().equals(size))
                .findFirst();
        if(requiredSize.isPresent()) {
            final WebElement element = requiredSize.get();
            element.click();
        }
    }

    public SportcheckShopppingCartPage proceedToShoppingCartPage() {
        waitForJsToLoad();
        redirectToShoppingCartIcon.click();
        return new SportcheckShopppingCartPage(driver);
    }

    public void clickAddToCartButton() {
        addToCartButton.click();
    }
}
