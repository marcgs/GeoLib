package com.mgomez.geolib.file.resource;

import com.google.common.collect.ImmutableList;
import com.mgomez.geolib.file.boundary.GeoFileService;
import com.mgomez.geolib.file.entity.GeoFile;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@Path("geofiles")
public class GeoFileResource {

    @Inject
    private GeoFileService geoFileService;

    @GET
    public List<GeoFile> listFiles() {
        final ImmutableList<GeoFile> files = geoFileService.getFiles();
        System.out.println("getting files: " + files);
        return files;
    }

    @Path("mostRecent")
    @GET
    public byte[] getMostRecentFile() {
        return geoFileService.getMostRecentFile().getContent();
    }

    @Path("{fileName}")
    @GET
    public GeoFile getFile(@PathParam("fileName") String fileName) {
        return geoFileService.getFile(fileName);
    }

    @POST
    public Response addFile(GeoFile geoFile) {
        System.out.println("adding file " + geoFile);
        geoFileService.addFile(geoFile);
        return Response.status(200).entity("Adding file: " + geoFile.toString()).build();
    }

}
