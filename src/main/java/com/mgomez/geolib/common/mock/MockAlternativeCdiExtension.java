package com.mgomez.geolib.common.mock;

import com.google.common.annotations.VisibleForTesting;
import com.mgomez.geolib.common.config.EnvVars;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.WithAnnotations;
import java.util.logging.Logger;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class MockAlternativeCdiExtension implements Extension {

    private static Logger LOGGER = Logger.getLogger(MockAlternativeCdiExtension.class.toString());
    private boolean mockingEnabled;

    public MockAlternativeCdiExtension() {
        this(Boolean.parseBoolean(System.getenv(EnvVars.GEOLIB_MOCK.name())));
    }

    @VisibleForTesting
    MockAlternativeCdiExtension(boolean mockingEnabled) {
        this.mockingEnabled = mockingEnabled;
    }

    public <T> void processAnnotated(@Observes @WithAnnotations({Mockable.class}) ProcessAnnotatedType<T> event) {
        AnnotatedType<T> annotatedType = event.getAnnotatedType();
        boolean isMockAlternative = annotatedType.isAnnotationPresent(MockAlternative.class);
        if (!mockingEnabled && isMockAlternative || mockingEnabled && !isMockAlternative) {
            LOGGER.info("Veto for class '" + annotatedType.getJavaClass());
            event.veto();
        }
    }

}