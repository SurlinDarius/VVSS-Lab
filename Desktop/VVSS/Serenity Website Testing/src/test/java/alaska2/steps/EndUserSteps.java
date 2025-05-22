package alaska2.steps;

import net.thucydides.core.annotations.Step;
import alaska2.pages.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class EndUserSteps {

    LoginPage loginPage;

    @Step
    public void isOnTheLoginPage() {
        loginPage.open();
    }

    @Step
    public void entersUsername(String username) {
        loginPage.enterUsername(username);
    }

    @Step
    public void entersPassword(String password) {
        loginPage.enterPassword(password);
    }

    @Step
    public void clicksLoginButton() {
        loginPage.clickLoginButton();
    }

    @Step
    public void shouldSeeHomePage() {
        assertThat("Home page should be displayed", !loginPage.containsText("submit"));
    }
    @Step
    public void shouldSeeErrorPage() {
        System.out.println(loginPage);
        assertThat("Error message should be displayed", loginPage.containsText("Error while logging in"));
    }
}