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
        System.out.println("→ Logger ind på Expand Testing...");
        loginPage().gaaTilLoginSide();
        loginPage().udfyldLogindata(bruger, pass);
        loginPage().klikLogin();
    }

    public void verificerLoginSucces() {
        String besked = loginPage().hentBesked();
        System.out.println("→ Verificerer login besked: " + besked);
        assertTrue(besked.contains("You logged into a secure area!"), "Login fejlede!");
    }

    public void verificerLoginFejlet() {
        System.out.println("→ Verificerer fejlbesked på login-siden...");
        String besked = loginPage().hentBesked();

        assertTrue(besked.contains("Your username is invalid!"),
                "Forventede en fejlbesked, men fandt: " + besked);

        System.out.println("→ Verifikation succesfuld: Brugeren fik den korrekte fejlbesked.");
    }
}