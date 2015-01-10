'use strict';

var webdriver = require('selenium-webdriver');

var driver = new webdriver.Builder().
    withCapabilities(webdriver.Capabilities.chrome()).
    build();

driver.get('http://192.168.33.33:8080/geolib/map.html');
driver.wait(function() {
    return driver.getTitle().then(function(title) {
        return title === 'GPX Viewer';
    });
}, 1000);

driver.findElement(webdriver.By.css('[data-js-selector="fileupload"]'))
      .sendKeys('/Users/marcgomez/Projects/GeoLib/src/test/resources/sample.gpx');

driver.close();