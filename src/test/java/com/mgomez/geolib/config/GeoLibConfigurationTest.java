package com.mgomez.geolib.config;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doThrow;

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

    @Test
    public void getEncryptedProperty_nonExistingPropertyFile() {
        GeoLibConfigurationForTest nonExistingConfig = new GeoLibConfigurationForTest("wrong.properties");
        try {
            nonExistingConfig.postConstruct();
            fail("Exception expected");
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is("Property file not found: wrong.properties"));
        }
    }

    @Test
    public void getEncryptedProperty_corruptPropertyFile() throws IOException {
        final Properties propertiesMock = Mockito.mock(Properties.class);

        doThrow(new IOException("cause")).when(propertiesMock).load((java.io.InputStream) anyObject());

        try {
            config.loadConfigFile(propertiesMock);
            fail("Exception expected");
        } catch (RuntimeException ex) {
            assertThat(ex.getMessage(), is("Could not load property file: test.properties"));
        }
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