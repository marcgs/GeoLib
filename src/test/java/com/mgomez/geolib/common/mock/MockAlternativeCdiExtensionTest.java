package com.mgomez.geolib.common.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.ProcessAnnotatedType;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class MockAlternativeCdiExtensionTest {

    @Mock
    private ProcessAnnotatedType eventMock;
    @Mock
    private AnnotatedType annotatedTypeMock;

    private MockAlternativeCdiExtension extension;

    @Test
    public void processAnnotated_mocksActive_notMockAlternative() throws Exception {
        extension = new MockAlternativeCdiExtension(true);
        when(annotatedTypeMock.isAnnotationPresent(MockAlternative.class)).thenReturn(false);

        extension.processAnnotated(eventMock);

        verify(eventMock).veto();
    }

    @Test
    public void processAnnotated_mocksActive_mockAlternative() throws Exception {
        extension = new MockAlternativeCdiExtension(true);
        when(annotatedTypeMock.isAnnotationPresent(MockAlternative.class)).thenReturn(true);

        extension.processAnnotated(eventMock);

        verify(eventMock).getAnnotatedType();
        verifyNoMoreInteractions(eventMock);
    }

    @Test
    public void processAnnotated_mocksNotActive_notMockAlternative() throws Exception {
        extension = new MockAlternativeCdiExtension(false);
        when(annotatedTypeMock.isAnnotationPresent(MockAlternative.class)).thenReturn(false);

        extension.processAnnotated(eventMock);

        verify(eventMock).getAnnotatedType();
        verifyNoMoreInteractions(eventMock);
    }

    @Test
    public void processAnnotated_mocksNotActive_mockAlternative() throws Exception {
        extension = new MockAlternativeCdiExtension(false);
        when(annotatedTypeMock.isAnnotationPresent(MockAlternative.class)).thenReturn(true);

        extension.processAnnotated(eventMock);

        verify(eventMock).veto();
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(eventMock.getAnnotatedType()).thenReturn(annotatedTypeMock);
    }
}