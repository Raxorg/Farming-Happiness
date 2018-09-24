package com.frontanilla.farminghappyness;

import android.support.annotation.NonNull;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.core.FirestoreInterface;
import com.frontanilla.farminghappyness.core.Player;
import com.frontanilla.farminghappyness.utils.FetchListener;
import com.frontanilla.farminghappyness.utils.OnResultListener;
import com.frontanilla.farminghappyness.utils.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.frontanilla.farminghappyness.utils.Constants.GRID_ROWS;


class FirestoreDB implements FirestoreInterface {

    private DocumentReference gridReference;
    private DocumentReference requestsReference;
    private DocumentReference turnReference;
    private CollectionReference playerDataReference;

    FirestoreDB() {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        gridReference = database.document("gameData/grid");
        requestsReference = database.document("gameData/requests");
        turnReference = database.document("gameData/turn");
        playerDataReference = database.collection("playerData");
    }

    //--------------------------------
    //           FETCHING
    //--------------------------------

    @Override
    public void fetchGridRows(final FetchListener<String[]> fetchListener) {
        gridReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String[] rows = new String[GRID_ROWS];
                for (int i = 0; i < rows.length; i++) {
                    String rowNumber = i < 10 ? "0" + i : i + "";
                    rows[i] = documentSnapshot.getString("row" + rowNumber);
                }
                fetchListener.onDataFetched(rows);
            }
        });
    }

    @Override
    public void fetchRequests(final FetchListener<String[]> additionsListener,
                              final FetchListener<Boolean> passTurnListener,
                              final FetchListener<String[]> placementsListener) {
        requestsReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot snapshot) {
                String[] additionRequests = snapshot.getString("additions").split(",");
                additionsListener.onDataFetched(additionRequests);
                String[] placementRequests = snapshot.getString("placements").split(",");
                placementsListener.onDataFetched(placementRequests);
                boolean passTurnRequest = snapshot.getBoolean("passTurn");
                passTurnListener.onDataFetched(passTurnRequest);
            }
        });
    }

    @Override
    public void fetchTurn(final FetchListener<Integer> turnListener) {
        turnReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                turnListener.onDataFetched(documentSnapshot.getLong("turn").intValue());
            }
        });
    }

    @Override
    public void fetchPlayers(final FetchListener<DelayedRemovalArray<Player>> playersListener) {
        playerDataReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot snapshots) {
                // Create a new list of players
                DelayedRemovalArray<Player> players = new DelayedRemovalArray<>();
                // Populate the list
                for (DocumentSnapshot snapshot : snapshots) {
                    if (!snapshot.getId().equals("nextPlayerTurn")) {
                        String phoneID = snapshot.getId();
                        Color color = Util.getColorFromString(snapshot.getString("color"));
                        String name = snapshot.getString("name");
                        int money = snapshot.getLong("money").intValue();
                        int turn = snapshot.getLong("turn").intValue();
                        players.add(new Player(phoneID, name, color, money, turn));
                    }
                }
                // Return the list
                playersListener.onDataFetched(players);
            }
        });
    }

    //--------------------------------
    //           REQUESTING
    //--------------------------------

    @Override
    public void playerAdditionRequest(final String request, final OnResultListener listener) {
        requestsReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String additions = documentSnapshot.getString("additions");
                boolean passTurn = documentSnapshot.getBoolean("passTurn");
                String placements = documentSnapshot.getString("placements");
                Map<String, Object> requestsMap = new HashMap<>();
                requestsMap.put("additions", additions + request);
                requestsMap.put("passTurn", passTurn);
                requestsMap.put("placements", placements);
                requestsReference.set(requestsMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        listener.onResult(task.isSuccessful());
                    }
                });
            }
        });
    }

    // TODO there's something wrong with "turn" being zero
    @Override
    public void passTurnRequest(final OnResultListener listener) {
        Map<String, Object> turnMap = new HashMap<>();
        turnMap.put("turn", 0);
        turnReference.set(turnMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                listener.onResult(task.isSuccessful());
            }
        });
    }

    @Override
    public void placementRequest(final String request, final OnResultListener listener) {
        requestsReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String additions = documentSnapshot.getString("additions");
                boolean passTurn = documentSnapshot.getBoolean("passTurn");
                String placements = documentSnapshot.getString("placements");
                Map<String, Object> requestsMap = new HashMap<>();
                requestsMap.put("additions", additions);
                requestsMap.put("passTurn", passTurn);
                requestsMap.put("placements", placements + request);
                requestsReference.set(requestsMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        listener.onResult(task.isSuccessful());
                    }
                });
            }
        });
    }
}
