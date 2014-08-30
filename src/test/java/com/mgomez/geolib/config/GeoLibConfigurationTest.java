package com.mgomez.geolib.config;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GeoLibConfigurationTest {

    private GeoLibConfigurationForTest config;

    @Test
    public void getUnencryptedProperty() {
        final String actualDbUrl = config.getConfigEntry(GeoLibConfigurationKey.DB_URL);
        assertThat(actualDbUrl, is("http://someurl.com"));
    }

    @Test
    public void getEncryptedProperty() {
        final String actualDbPwd = config.getConfigEntry(GeoLibConfigurationKey.DB_PWD);
        assertThat(actualDbPwd, is("theTestPassword"));
    }

    @Before
    public void setUp() {
        config = new GeoLibConfigurationForTest("test.properties");
        config.postConstruct();
    }

    static class GeoLibConfigurationForTest extends GeoLibConfiguration {

        GeoLibConfigurationForTest(String configFile) {
            super(configFile);
        }

        @Override
        String getSecret() {
            return "testSecret";
        }
    }
}