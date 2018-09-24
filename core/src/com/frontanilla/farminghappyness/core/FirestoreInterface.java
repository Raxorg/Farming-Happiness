package com.frontanilla.farminghappyness.core;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.utils.FetchListener;
import com.frontanilla.farminghappyness.utils.OnResultListener;

public interface FirestoreInterface {

    //-------------------
    //     FETCHING
    //-------------------

    void fetchGridRows(FetchListener<String[]> listener);

    void fetchRequests(FetchListener<String[]> additionsListener,
                       FetchListener<Boolean> passTurnListener,
                       FetchListener<String[]> placementsListener);

    void fetchTurn(FetchListener<Integer> turnListener);

    void fetchPlayers(FetchListener<DelayedRemovalArray<Player>> listener);

    //-------------------
    //    REQUESTING
    //-------------------

    void playerAdditionRequest(String request, OnResultListener listener);

    void passTurnRequest(OnResultListener listener);

    void placementRequest(String request, OnResultListener listener);
}
