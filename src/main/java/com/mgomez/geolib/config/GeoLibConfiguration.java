package com.mgomez.geolib.config;

import com.google.common.annotations.VisibleForTesting;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class GeoLibConfiguration {

    public static final String DEFAULT_CONFIG_FILE = "geolib.properties";
    public static final String GEOLIB_SECRET_ENV = "GEOLIB_SECRET";

    private Properties properties;
    private String configFile;


    public GeoLibConfiguration() {
        this(DEFAULT_CONFIG_FILE);
    }

    public GeoLibConfiguration(String configFile) {
        this.configFile = configFile;
    }

    @PostConstruct
    public void postConstruct() {
        properties = loadProperties();
    }

    public String getConfigEntry(GeoLibConfigurationKey key) {
        return (String) properties.get(key.getName());
    }

    private Properties loadProperties() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(getSecret());
        Properties props = new EncryptableProperties(encryptor);
        loadConfigFile(props);
        return props;
    }

    private void loadConfigFile(Properties props) {
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(configFile);
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @VisibleForTesting
    String getSecret() {
        return System.getenv(GEOLIB_SECRET_ENV);
    }
}
