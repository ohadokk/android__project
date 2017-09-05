package com.example.ohad.test;

import com.google.api.services.youtube.model.Playlist;

import java.util.List;

/**
 * Created by ohad on 4/4/2017.
 */

public interface Y__AsyncResponse {
    void ProcessFinish(List<Playlist> output);
    void ProcessFinish(Object output);
}
