package dsl;

import driver.page.PensionsinfoPageDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PensionsinfoDsl {
    private final TestContext testContext;

    public PensionsinfoDsl(TestContext testContext) {
        this.testContext = testContext;
    }

    private PensionsinfoPageDriver pensionsinfoPage() {
        return new PensionsinfoPageDriver(testContext.getPage());
    }

    public void gaaTilForside() {
        System.out.println("Går til forsiden af Pensionsinfo...");
        pensionsinfoPage().gaaTilForside(TestContext.PENSIONSINFO_URL);

        System.out.println("Håndterer cookie-banner...");
        pensionsinfoPage().haandterCookieBanner();
    }

    public void startDemoForEnPerson() {
        System.out.println("Klikker på 'Prøv demo'...");
        pensionsinfoPage().klikProevDemo();

        System.out.println("Vælger 'Demo - én person' i popup...");
        pensionsinfoPage().vaelgEnPersonDemo();
    }

    public void verificerLandingPage() {
        System.out.println("Verificerer landingpage...");
        var navn = pensionsinfoPage().overblikNavnLocator();
        navn.waitFor();

        assertTrue(navn.isVisible(), "Kunne ikke finde navnet 'Demo Demosen' på landingpagen.");
        System.out.println("Verifikation succesfuld: Er landet på overblikssiden!");
    }
}