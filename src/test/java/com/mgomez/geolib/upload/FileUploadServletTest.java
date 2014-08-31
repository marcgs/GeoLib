package com.mgomez.geolib.upload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.mgomez.geolib.track.resource.TrackMeta;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

import static com.mgomez.geolib.upload.FileUploadServlet.ERROR_CODE_NOT_MULTIPART;
import static com.mgomez.geolib.upload.FileUploadServlet.MULTIPART_FORM_DATA_CONTENT_TYPE;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FileUploadServletTest {

    @Mock
    private HttpServletRequest requestMock;
    @Mock
    private HttpServletResponse responseMock;
    @Mock
    private MultipartRequestHandler multipartRequestHandlerMock;
    @Mock
    private ObjectMapper objectMapperMock;
    @Mock
    private Logger loggerMock;

    private FileUploadServlet servlet;


    @Test
    public void doPost_notMultipart() throws Exception {
        //train
        when(requestMock.getContentType()).thenReturn("someOtherContentType");

        //exercise
        servlet.doPost(requestMock, responseMock);

        //assert
        verify(loggerMock).severe("Not multipart request: someOtherContentType");
        verify(responseMock).sendError(ERROR_CODE_NOT_MULTIPART, "Not multipart request: someOtherContentType");

    }

    @Test
    public void doPost() throws Exception {
        final List<TrackMeta> tracks = Lists.newArrayList();
        ServletOutputStream outputStreamMock = Mockito.mock(ServletOutputStream.class);

        //train
        when(requestMock.getContentType()).thenReturn(MULTIPART_FORM_DATA_CONTENT_TYPE);
        when(multipartRequestHandlerMock.handleUpload(requestMock)).thenReturn(tracks);
        when(responseMock.getOutputStream()).thenReturn(outputStreamMock);

        //exercise
        servlet.doPost(requestMock, responseMock);

        //assert
        verify(responseMock).setContentType(FileUploadServlet.APPLICATION_JSON_CONTENT_TYPE);
        verify(objectMapperMock).writeValue(same(outputStreamMock), same(tracks));
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        servlet = new FileUploadServlet(multipartRequestHandlerMock, objectMapperMock, loggerMock);
    }
}