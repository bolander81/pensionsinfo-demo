package tests;

import dsl.PensionsinfoDsl;
import dsl.TestContext;
import org.junit.jupiter.api.*;

public class PensionsinfoTest {
    private TestContext testContext;
    private PensionsinfoDsl pensionsinfo;

    @BeforeEach
    void setup() {
        testContext = new TestContext();
        // Vi navngiver variablen 'pensionsinfo' for maksimal klarhed
        pensionsinfo = new PensionsinfoDsl(testContext);
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        // Vi sender testnavnet med til close-metoden for korrekt navngivning af video/trace
        testContext.close(testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Pensionsinfo_Demo_Flow")
    void brugerKanStarteEnPersonDemo() {
        pensionsinfo.gaaTilForside();
        pensionsinfo.startDemoForEnPerson();
        pensionsinfo.verificerLandingPage();
    }
}