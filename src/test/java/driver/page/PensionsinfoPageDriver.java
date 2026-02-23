package driver.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class PensionsinfoPageDriver extends BasePageDriver {

    public PensionsinfoPageDriver(Page page) {
        super(page);
    }

    public Locator overblikNavnLocator() {
        return page.getByRole(AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("Demo Demosen")).first();
    }

    public void haandterCookieBanner() {
        var acceptKnap = page.locator(".coi-banner__accept").first();
        if (acceptKnap.isVisible()) {
            acceptKnap.click();
            acceptKnap.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
        }
    }

    public void gaaTilForside(String url) {
        page.navigate(url);
    }

    public void klikProevDemo() {
        page.locator("a[alt='Prøv demo']").click();
    }

    public void vaelgEnPersonDemo() {
        var enPersonKnap = page.locator("a[log-btn='DemoOnePersonLink']");
        enPersonKnap.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        enPersonKnap.click();
    }

    public void navigerTilPension() {
        page.locator("a[log-btn='NavigationPensionLink']").click();
    }

    public void saetPensionsalder(String alder) {
        String selector = String.format("div[log-btn-age='%s']", alder);
        var knap = page.locator(selector);

        knap.waitFor();
        knap.click();
    }

    public String hentBeloebFraFarvetBoks(String overskrift) {
        var kolonne = page.locator(".table-first-year-column")
                .filter(new Locator.FilterOptions().setHas(page.locator(".legend .text").getByText(overskrift)));

        var beloebLocator = kolonne.locator(".legend .amount span").first();

        beloebLocator.waitFor();
        return beloebLocator.innerText();
    }
}