package tests;

import dsl.LoginDsl;
import dsl.TestContext;
import org.junit.jupiter.api.*;

public class LoginTest {
    private TestContext testContext;
    private LoginDsl dsl;

    @BeforeEach
    void setup() {
        testContext = new TestContext();
        dsl = new LoginDsl(testContext);
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        testContext.close(testInfo.getDisplayName());
    }

    @Test
    @DisplayName("ExpandTesting_Login_Flow")
    void brugerKanLoggeInd() {
        dsl.loginMedBruger("practice", "SuperSecretPassword!");
        dsl.verificerLoginSucces();
    }
}