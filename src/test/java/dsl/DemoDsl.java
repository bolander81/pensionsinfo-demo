package dsl;

import driver.page.DemoPageDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoDsl {
    private final TestContext testContext;

    public DemoDsl(TestContext testContext) {
        this.testContext = testContext;
    }

    private DemoPageDriver demoPage() {
        return new DemoPageDriver(testContext.getPage());
    }

    public void gaaTilForside() {
        System.out.println("Går til forsiden af Pensionsinfo...");
        demoPage().gaaTilForside(TestContext.PENSIONSINFO_URL);

        System.out.println("Håndterer cookie-banner...");
        demoPage().haandterCookieBanner();
    }

    public void startDemoForEnPerson() {
        System.out.println("Klikker på 'Prøv demo'...");
        demoPage().klikProevDemo();

        System.out.println("Vælger 'Demo - én person' i popup...");
        demoPage().vaelgEnPersonDemo();
    }

    public void verificerLandingPage() {
        System.out.println("Verificerer landingpage...");

        // Vi venter på at navnet bliver synligt på siden
        var navn = demoPage().demoNavnLocator();
        navn.waitFor();

        assertTrue(navn.isVisible(), "Kunne ikke finde navnet 'Demo Demosen' på landingpagen.");
        System.out.println("Verifikation succesfuld: Er landet på overblikssiden for Demo Demosen!");
    }
}