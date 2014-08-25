package com.mgomez.geolib.upload;

import com.google.common.collect.Lists;
import com.mgomez.geolib.file.boundary.TrackService;
import com.mgomez.geolib.file.entity.Track;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class MultipartRequestHandlerTest {

    public static final String FILE_NAME = "someFileName";
    public static final String CONTENT = "Some sample content";

    @Mock
    private HttpServletRequest requestMock;
    @Mock
    private HttpServletResponse responseMock;
    @Mock
    private TrackService trackService;

    private MultipartRequestHandler handler;

    @Test
    public void handleUpload() throws Exception {
        final Part partMockWithoutContentType = Mockito.mock(Part.class);
        final Part partMockWithContentType = Mockito.mock(Part.class);
        final String contentDisposition = "someField=someValue;filename=" + FILE_NAME + ";someOtherField=someOtherFieldValues";

        // train
        when(requestMock.getParts()).thenReturn(Lists.newArrayList(partMockWithoutContentType, partMockWithContentType));
        when(partMockWithoutContentType.getContentType()).thenReturn(null);

        when(partMockWithContentType.getContentType()).thenReturn("someContentType");
        when(partMockWithContentType.getInputStream()).thenReturn(new ByteArrayInputStream(CONTENT.getBytes()));
        when(partMockWithContentType.getHeader("content-disposition")).thenReturn(contentDisposition);

        //exercise
        final List<Track> actual = handler.handleUpload(requestMock);

        //assert
        verify(trackService).addTrack(refEq(new Track(FILE_NAME, CONTENT), "uploadedDate"));
        assertThat(actual.size(), is(1));
        assertThat(actual.get(0).getFileName(), is(FILE_NAME));
        assertThat(actual.get(0).getContent(), is(CONTENT));
    }

    @Test
    public void handleUpload_noInputStream() throws Exception {
        final Part partMockWithContentType = Mockito.mock(Part.class);

        // train
        when(requestMock.getParts()).thenReturn(Lists.newArrayList(partMockWithContentType));
        when(partMockWithContentType.getContentType()).thenReturn("someContentType");
        when(partMockWithContentType.getInputStream()).thenThrow(new IOException("cause"));

        //exercise
        try {
            handler.handleUpload(requestMock);
            fail("RuntimeException expected");
        } catch (RuntimeException ex) {
            //assert
            assertThat(ex.getMessage(), is("cause"));
        }
    }


    @Test
    public void handleUpload_noFilename() throws Exception {
        final Part partMockWithContentType = Mockito.mock(Part.class);
        final String contentDisposition = "someField=someValue;someOtherField=someOtherFieldValues";

        // train
        when(requestMock.getParts()).thenReturn(Lists.newArrayList(partMockWithContentType));
        when(partMockWithContentType.getContentType()).thenReturn("someContentType");
        when(partMockWithContentType.getInputStream()).thenReturn(new ByteArrayInputStream(CONTENT.getBytes()));
        when(partMockWithContentType.getHeader("content-disposition")).thenReturn(contentDisposition);

        //exercise
        try {
            handler.handleUpload(requestMock);
            fail("IllegalArgumentException expected");
        } catch (RuntimeException ex) {
            //assert
            assertThat(ex.getMessage(), is("No filename found in request"));
        }
    }


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        handler = new MultipartRequestHandler(trackService);
    }

}