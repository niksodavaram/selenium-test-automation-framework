package util;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YamlConfigLoader {

    private static final Map<String, Object> config;

    static {
        try (InputStream in = YamlConfigLoader.class.getClassLoader().getResourceAsStream("config.yml")) {
            if (in == null) {
                throw new RuntimeException("config.yml not found in resources");
            }
            Yaml yaml = new Yaml();
            config = yaml.load(in);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.yml", e);
        }
    }

    // Convenience method to get the URL
    public static String getUrl() {
        // Navigate the map: webdriver -> pages -> url
        Object webdriverObj = config.get("webdriver");
        if (!(webdriverObj instanceof Map<?, ?> webdriver)) {
            throw new IllegalStateException("Malformed config.yml: 'webdriver' is missing or not a map");
        }
        Object pagesObj = webdriver.get("pages");
        if (!(pagesObj instanceof Map<?, ?> pages)) {
            throw new IllegalStateException("Malformed config.yml: 'pages' is missing or not a map");
        }
        Object urlObj = pages.get("url");
        if (!(urlObj instanceof String)) {
            throw new IllegalStateException("Malformed config.yml: 'url' is missing or not a string");
        }
        return (String) urlObj;
    }
}
