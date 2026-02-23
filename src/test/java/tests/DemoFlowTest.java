package tests;

import dsl.DemoDsl;
import dsl.TestContext;
import org.junit.jupiter.api.*;

public class DemoFlowTest {
    private TestContext testContext;
    private DemoDsl dsl;

    @BeforeEach
    void setup() {
        testContext = new TestContext();
        dsl = new DemoDsl(testContext);
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        // Vi sender testnavnet med til close-metoden
        testContext.close(testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Pensionsinfo_Demo_Flow")
    void brugerKanStarteEnPersonDemo() {
        dsl.gaaTilForside();
        dsl.startDemoForEnPerson();
        dsl.verificerLandingPage();
    }
}