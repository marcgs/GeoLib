package com.mgomez.geolib.config;

import com.google.common.annotations.VisibleForTesting;
import com.mgomez.geolib.common.config.EnvVars;
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

    private Properties properties;
    private String configFile;


    public GeoLibConfiguration() {
        this(DEFAULT_CONFIG_FILE);
    }

    @VisibleForTesting
    GeoLibConfiguration(String configFile) {
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

    @VisibleForTesting
    void loadConfigFile(Properties props) {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(configFile);
        if (in == null) {
            throw new IllegalArgumentException("Property file not found: " + configFile);
        }
        try {
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Could not load property file: " + configFile, e);
        }
    }

    @VisibleForTesting
    String getSecret() {
        return System.getenv(EnvVars.GEOLIB_SECRET.name());
    }
}
