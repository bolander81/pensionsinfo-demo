package driver.page;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPageDriver extends BasePageDriver {

    public LoginPageDriver(Page page) {
        super(page);
    }

    public void gaaTilLoginSide() {
        page.navigate("https://practice.expandtesting.com/login");
    }

    public void udfyldLogindata(String brugernavn, String password) {
        page.getByLabel("Username").fill(brugernavn);
        page.getByLabel("Password").fill(password);
    }

    public void klikLogin() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
    }

    public String hentBesked() {
        return page.locator("#flash").innerText();
    }
}