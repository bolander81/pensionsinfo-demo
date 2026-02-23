package dsl;

import com.microsoft.playwright.*;
import lombok.Getter;
import java.nio.file.Path;
import java.util.List;

public class TestContext {
    public static final String PENSIONSINFO_URL = "https://www.pensionsinfo.dk/Welcome";
    public static final String RECORD_DIR = "recordings";

    private final Playwright playwright;
    private final Browser browser;
    private final BrowserContext browserContext;
    private final Tracing tracing;
    @Getter
    private final Page page;

    public TestContext() {
        this.playwright = Playwright.create();
        this.browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false) // Behåll denne som false, hvis du vil se browseren køre normalt
                .setArgs(List.of("--no-sandbox", "--disable-gpu")));

        // Her tilføjer vi video-optagelse til contexten
        this.browserContext = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Path.of(RECORD_DIR)));

        // Start Tracing
        this.tracing = browserContext.tracing();
        this.tracing.start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        this.page = browserContext.newPage();
    }

    public void close(String testName) {
        String sanitizedName = testName.replace(" ", "_");

        // 1. Hent referencerne mens alt er åbent
        Video video = page.video();

        // 2. Stop tracing og gem filen
        if (tracing != null) {
            String tracePath = RECORD_DIR + "/trace_" + sanitizedName + ".zip";
            tracing.stop(new Tracing.StopOptions().setPath(Path.of(tracePath)));
            System.out.println("Trace gemt: " + tracePath);
        }

        // 3. Luk selve siden
        page.close();

        // 4. Luk context (dette færdiggør videofilen)
        if (browserContext != null) {
            browserContext.close();

            // Gem videoen med det rigtige navn efter context er lukket
            if (video != null) {
                Path videoPath = Path.of(RECORD_DIR + "/video_" + sanitizedName + ".webm");
                video.saveAs(videoPath);
                video.delete(); // Slet den autogenererede fil med det kryptiske navn
                System.out.println("Video gemt: " + videoPath);
            }
        }

        // 5. Luk browser og playwright (Vigtigt for at rydde helt op)
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }

        System.out.println("TestContext ressourcer er ryddet op.");
    }
}