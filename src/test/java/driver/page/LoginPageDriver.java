package driver.page;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPageDriver extends BasePageDriver {

    public LoginPageDriver(Page page) {
        super(page);
    }

    public void indtastBrugernavn(String user) {
        page.locator("#username").fill(user);
    }

    public void indtastPassword(String pass) {
        page.locator("#password").fill(pass);
    }

    public void klikLogin() {
        page.locator("#submit").click();
    }

    public String hentBesked() {
        // Fejlbeskeder vises i en div med id="error"
        // Succesbeskeder eller overskrifter kan findes på den nye side
        if (page.locator("#error").isVisible()) {
            return page.locator("#error").innerText();
        }
        return page.locator("h1").innerText(); // Efter login er der en <h1> "Logged In Successfully"
    }
}