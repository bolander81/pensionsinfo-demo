package tests;

import dsl.PensionsinfoDsl;
import dsl.TestContext;
import org.junit.jupiter.api.*;

import static utils.TestUtils.*;

public class PensionsinfoTest {
    private TestContext testContext;
    private PensionsinfoDsl pensionsinfo;

    @BeforeEach
    void setup() {
        testContext = new TestContext();
        pensionsinfo = new PensionsinfoDsl(testContext);

        pensionsinfo.gaaTilForside();
        pensionsinfo.startDemoForEnPerson();
        pensionsinfo.verificerLandingPage();
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        testContext.close(testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Dashboard_Slider_Validering")
    void verificerPensionstalVedForskelligeAldre() {
        pensionsinfo.undersoegPensionVedAlder("71");
        pensionsinfo.verificerBeloeb(GUL_BOKS, "448.000");
        pensionsinfo.verificerBeloeb(LILLA_BOKS, "90.000");
    }

    @Test
    @DisplayName("Dashboard_Folkepension_Check")
    void tjekFolkepensionVed68aar() {
        pensionsinfo.undersoegPensionVedAlder("68");
        pensionsinfo.verificerBeloeb(GROEN_BOKS, "127.000");
    }
}