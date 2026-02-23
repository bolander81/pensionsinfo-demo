package driver.page;

import com.microsoft.playwright.Page;

public abstract class BasePageDriver {
    protected final Page page;

    public BasePageDriver(Page page) {
        this.page = page;
    }
}