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
        pensionsinfo = new PensionsinfoDsl(testContext);
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
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