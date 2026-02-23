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
                .setHeadless(false) // ← sæt til true for at køre uden UI (når den skal køres i CI)
                .setArgs(List.of("--no-sandbox", "--disable-gpu")));

        this.browserContext = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Path.of(RECORD_DIR)));

        this.tracing = browserContext.tracing();
        this.tracing.start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        this.page = browserContext.newPage();
    }

    public void close(String testName) {
        String sanitizedName = testName.replace(" ", "_");

        Path testFolder = Path.of(RECORD_DIR, sanitizedName);

        Video video = page.video();

        if (tracing != null) {
            Path tracePath = testFolder.resolve("trace_" + sanitizedName + ".zip");
            tracing.stop(new Tracing.StopOptions().setPath(tracePath));
            System.out.println("→ Trace gemt i: " + tracePath);
        }

        page.close();

        if (browserContext != null) {
            browserContext.close();

            if (video != null) {
                Path videoPath = testFolder.resolve("video_" + sanitizedName + ".webm");
                video.saveAs(videoPath);
                video.delete();
                System.out.println("→ Video gemt i: " + videoPath);
            }
        }

        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }

        System.out.println("→ TestContext ressourcer er ryddet op for: " + sanitizedName);
    }
}