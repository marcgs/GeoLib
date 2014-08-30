package com.mgomez.geolib.config;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public enum GeoLibConfigurationKey {
    DB_URL("db.url"),
    DB_USERNAME("db.username"),
    DB_PWD("db.pwd"),
    DB_NAME("db.name");

    private String key;

    GeoLibConfigurationKey(String key) {
        this.key = key;
    }

    public String getName() {
        return key;
    }
}
