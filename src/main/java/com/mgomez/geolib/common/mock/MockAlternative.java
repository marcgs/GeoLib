package com.mgomez.geolib.common.mock;

import javax.enterprise.inject.Stereotype;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@Stereotype
@Mockable
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MockAlternative {
}
