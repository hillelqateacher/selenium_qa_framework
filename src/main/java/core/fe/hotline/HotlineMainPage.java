package core.fe.hotline;

import core.fe.AbstractPage;
import core.fe.BaseUrl;
import org.openqa.selenium.WebDriver;

@BaseUrl(value = "https://hotline.ua")
public class HotlineMainPage extends AbstractPage {

    public HotlineMainPage(final WebDriver driver) {
        super(driver);
    }
}
