package util;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class YmlConfigLoader {
    public static Config load() {
        Yaml yaml = new Yaml();
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.yml")) {
            if (in == null) throw new RuntimeException("Config file not found: config.yml");
            return yaml.loadAs(in, Config.class);
        } catch (Exception e) {
            throw new RuntimeException("Error loading config: config.yml", e);
        }
    }
}
