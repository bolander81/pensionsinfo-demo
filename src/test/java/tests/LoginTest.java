package tests;

import dsl.LoginDsl;
import dsl.TestContext;
import utility.TestUtils;
import org.junit.jupiter.api.*;

public class LoginTest {
    private TestContext testContext;
    private LoginDsl login;

    @BeforeEach
    void setup() {
        testContext = new TestContext();
        login = new LoginDsl(testContext);
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        testContext.close(testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Login_Positiv_Case")
    void brugerKanLoggeIndMedKorrekteData() {
        login.loginMedBruger(TestUtils.LOGIN_USER, TestUtils.LOGIN_PASSWORD);
        login.verificerLoginSucces();
    }

    @Test
    @DisplayName("Login_Negativ_Case")
    void brugerFaarFejlVedForkertData() {
        login.loginMedBruger(TestUtils.INVALID_USER, TestUtils.INVALID_PASSWORD);
        login.verificerLoginFejlet();
    }
}