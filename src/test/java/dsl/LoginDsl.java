package dsl;

import driver.page.LoginPageDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginDsl {
    private final TestContext testContext;

    public LoginDsl(TestContext testContext) {
        this.testContext = testContext;
    }

    private LoginPageDriver loginPage() {
        return new LoginPageDriver(testContext.getPage());
    }

    public void loginMedBruger(String bruger, String pass) {
        System.out.println("→ Logger ind på Practice Test Automation...");
        loginPage().indtastBrugernavn(bruger);
        loginPage().indtastPassword(pass);
        loginPage().klikLogin();
    }

    public void verificerLoginSucces() {
        String overskrift = loginPage().hentBesked();
        assertTrue(overskrift.contains("Logged In Successfully"),
                "Login lykkedes ikke! Overskrift var: " + overskrift);
    }

    public void verificerLoginFejlet() {
        String fejlbesked = loginPage().hentBesked();
        assertTrue(fejlbesked.contains("invalid"),
                "→ Forventede en fejlbesked med 'invalid', men fandt: " + fejlbesked);
    }
}