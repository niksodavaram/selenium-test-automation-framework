package util;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;

public class UnleashConfig {

    private static Unleash unleash;

    private UnleashConfig() {}

    public static synchronized Unleash getUnleash() {
        if (unleash == null) {
            UnleashConfig config = UnleashConfig.builder()
                    .appName("my-selenium-app")
                    .instanceId("automation")
                    .unleashAPI("http://your-unleash-server:4242/api/")
                    .apiToken("Your-client-token")
                    .build();
            unleash = new DefaultUnleash(config);
        }
        return unleash;
    }

    public static boolean isFeatureEnabled(String featureName) {
        return getUnleash().isEnabled(featureName);
    }
}
