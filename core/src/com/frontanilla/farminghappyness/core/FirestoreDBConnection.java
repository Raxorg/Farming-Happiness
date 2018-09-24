package com.frontanilla.farminghappyness.core;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.utils.FetchListener;
import com.frontanilla.farminghappyness.utils.OnResultListener;

public class FirestoreDBConnection {

    // Singleton instance
    private static FirestoreDBConnection instance;

    private FirestoreInterface firestore;

    // Singleton constructor
    private FirestoreDBConnection() {
    }

    // Singleton getter
    public static FirestoreDBConnection getInstance() {
        if (instance == null) {
            instance = new FirestoreDBConnection();
        }
        return instance;
    }

    void setFirestore(FirestoreInterface firestore) {
        this.firestore = firestore;
    }

    //--------------------------------------
    //              FETCHING
    //--------------------------------------

    public void fetchGridRows(FetchListener<String[]> fetchListener) {
        firestore.fetchGridRows(fetchListener);
    }

    public void fetchRequests(FetchListener<String[]> additionsListener,
                              FetchListener<Boolean> passTurnListener,
                              FetchListener<String[]> placementsListener) {
        firestore.fetchRequests(additionsListener, passTurnListener, placementsListener);
    }

    public void fetchTurn(FetchListener<Integer> turnListener) {
        firestore.fetchTurn(turnListener);
    }

    public void fetchPlayers(FetchListener<DelayedRemovalArray<Player>> fetchListener) {
        firestore.fetchPlayers(fetchListener);
    }

    //--------------------------------------
    //             REQUESTING
    //--------------------------------------

    public void playerAdditionRequest(String request, OnResultListener listener) {
        firestore.playerAdditionRequest(request, listener);
    }

    public void passTurnRequest(OnResultListener listener) {
        firestore.passTurnRequest(listener);
    }

    public void placementRequest(String request, OnResultListener listener) {
        firestore.placementRequest(request, listener);
    }
}