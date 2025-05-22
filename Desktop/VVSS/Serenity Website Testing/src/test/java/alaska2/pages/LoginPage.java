package alaska2.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;

@DefaultUrl("https://alaska2.ro/index.php")
public class LoginPage extends PageObject {
    @FindBy(name="username")
    private WebElementFacade usernameField;

    @FindBy(name="password")
    private WebElementFacade passwordField;

    @FindBy(name="submit")
    private WebElementFacade loginButton;

    public void enterUsername(String username) {
        usernameField.type(username);
    }

    public void enterPassword(String password) {
        passwordField.type(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }
}
