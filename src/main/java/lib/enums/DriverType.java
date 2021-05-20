package lib.enums;

import lib.driver.SeleniumConfig;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public enum DriverType {

    WEB_DRIVER("webDriver", () -> new SeleniumConfig().getWebDriver());

   private static final Map<String, DriverType> DRIVER_TYPE_MAP =
            Arrays.stream(DriverType.values()).collect(Collectors.toMap(DriverType::getDriverTypeKey, e -> e));
                    

    private String driverTypeKey;
    private Supplier<WebDriver> initDriverSupplier;

    public String getDriverTypeKey() {
        return this.driverTypeKey;
    }

    public Supplier<WebDriver> getInitDriverSupplier() {
        return this.initDriverSupplier;
    }

    DriverType(final String key, final Supplier<WebDriver> driverSupplier) {
        this.driverTypeKey = key;
        this.initDriverSupplier = driverSupplier;
    }

    public static DriverType of(String key) {
        return Optional.of(DRIVER_TYPE_MAP.get(key))
                .orElseThrow(() -> new RuntimeException("Web driver is not valid"));
    }
}
