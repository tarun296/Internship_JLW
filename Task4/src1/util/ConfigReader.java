package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	public static Properties readProperties() {
        Properties prop = new Properties();
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new RuntimeException("config.properties not found in classpath.");
            }
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
