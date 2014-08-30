package com.mgomez.geolib.track.controller.berkeleydb;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.mgomez.geolib.track.controller.TrackPersistenceController;
import com.mgomez.geolib.track.entity.Track;
import com.mgomez.geolib.track.entity.TrackMeta;
import com.sleepycat.bind.tuple.StringBinding;
import com.sleepycat.je.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
// TODO: still prototype code.
@BerkeleyDb
public class BerkeleyDbTrackPersistenceController implements TrackPersistenceController {

    @Inject
    private ObjectMapper objectMapper;
    private Environment dbEnv;
    private Database tracks;

    @Inject
    public BerkeleyDbTrackPersistenceController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void postConstruct() throws DatabaseException {
        final File dbFile = new File("/tmp/berkeleydb");
        dbFile.mkdir();
        EnvironmentConfig envConf = new EnvironmentConfig();
        envConf.setAllowCreate(true);
        dbEnv = new Environment(dbFile, envConf);
        DatabaseConfig dbConf = new DatabaseConfig();
        dbConf.setAllowCreate(true);
        tracks = dbEnv.openDatabase(null, "tracks", dbConf);
    }

    @PreDestroy
    public void preDestroy() throws DatabaseException {
        tracks.close();
        dbEnv.close();
    }

    @Override
    public List<TrackMeta> listTracks() {
        DatabaseEntry key = new DatabaseEntry();
        DatabaseEntry data = new DatabaseEntry();
        StringBinding.stringToEntry("keyInventory", key);

        try {
            tracks.get(null, key, data, null);
            final byte[] content = data.getData();
            if (content == null) {
                return Lists.newArrayList();
            }
            final List<String> keys = objectMapper.readValue(content, ArrayList.class);
            return keys.stream()
                    .map(TrackMeta::new)
                    .collect(Collectors.toList());
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }

    @Override
    public void addTrack(Track track) {
        try {
            DatabaseEntry key = new DatabaseEntry();
            DatabaseEntry data = new DatabaseEntry();
            StringBinding.stringToEntry(track.getTrackMeta().getTrackName(), key);
            final String val = objectMapper.writeValueAsString(track);
            StringBinding.stringToEntry(val, data);
            tracks.put(null, key, data);

            DatabaseEntry keyInventoryKey = new DatabaseEntry();
            data = new DatabaseEntry();
            StringBinding.stringToEntry("keyInventory", keyInventoryKey);
            tracks.get(null, keyInventoryKey, data, null);

            final byte[] byteData = data.getData();
            final List<String> keys = byteData == null ? Lists.newArrayList() : objectMapper.readValue(byteData, List.class);
            keys.add(track.getTrackMeta().getTrackName());
            data = new DatabaseEntry();
            StringBinding.stringToEntry(objectMapper.writeValueAsString(keys), data);
            tracks.put(null, keyInventoryKey, data);

        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Track getTrack(String trackName) {
        DatabaseEntry key = new DatabaseEntry();
        DatabaseEntry data = new DatabaseEntry();
        StringBinding.stringToEntry(trackName, key);
        try {
            tracks.get(null, key, data, null);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        try {
            final String content = StringBinding.entryToString(data);
            return objectMapper.readValue(content, Track.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Track> getTracks() {
        return null;
    }


}
