package tests;

import dsl.LoginDsl;
import dsl.TestContext;
import utils.TestUtils;
import org.junit.jupiter.api.*;

public class LoginTest {
    private TestContext testContext;
    private LoginDsl dsl;

    @BeforeEach
    void setup() {
        testContext = new TestContext();
        dsl = new LoginDsl(testContext);
        testContext.getPage().navigate(TestUtils.LOGIN_URL);
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        testContext.close(testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Login_Positiv_Case")
    void brugerKanLoggeIndMedKorrekteData() {
        dsl.loginMedBruger(TestUtils.LOGIN_USER, TestUtils.LOGIN_PASSWORD);
        dsl.verificerLoginSucces();
    }

    @Test
    @DisplayName("Login_Negativ_Case")
    void brugerFaarFejlVedForkertData() {
        dsl.loginMedBruger(TestUtils.INVALID_USER, TestUtils.INVALID_PASSWORD);
        dsl.verificerLoginFejlet();
    }
}