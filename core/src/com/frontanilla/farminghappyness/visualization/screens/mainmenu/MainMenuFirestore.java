package com.frontanilla.farminghappyness.visualization.screens.mainmenu;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.core.FirestoreDBConnection;
import com.frontanilla.farminghappyness.core.Player;
import com.frontanilla.farminghappyness.utils.FetchListener;
import com.frontanilla.farminghappyness.visualization.screens.FirestoreConnection;

public class MainMenuFirestore extends FirestoreConnection {

    public MainMenuFirestore(MainMenuConnector connector) {
        super(connector);
    }

    public void getPlayers() {
        FirestoreDBConnection.getInstance().fetchPlayers(new FetchListener<DelayedRemovalArray<Player>>() {
            @Override
            public void onDataFetched(DelayedRemovalArray<Player> players) {
                ((MainMenuLogic) connector.getLogic()).onPlayersFetched(players);
            }
        });
    }

    public void getRequests() {
        FirestoreDBConnection.getInstance().fetchRequests(
                new FetchListener<String[]>() {
                    @Override
                    public void onDataFetched(String[] additionRequests) {
                        ((MainMenuLogic) connector.getLogic()).onRequestsFetched(additionRequests);
                    }
                },
                new FetchListener<Boolean>() {
                    @Override
                    public void onDataFetched(Boolean passTurn) {
                        // Do nothing with the passTurn request
                    }
                },
                new FetchListener<String[]>() {
                    @Override
                    public void onDataFetched(String[] placementRequests) {
                        // Do nothing with placement requests
                    }
                }
        );
    }

    public void fetchBattleData() {
        FirestoreDBConnection.getInstance().fetchGridRows(new FetchListener<String[]>() {
            @Override
            public void onDataFetched(final String[] gridRows) {
                FirestoreDBConnection.getInstance().fetchTurn(new FetchListener<Integer>() {
                    @Override
                    public void onDataFetched(final Integer turn) {
                        FirestoreDBConnection.getInstance().fetchPlayers(new FetchListener<DelayedRemovalArray<Player>>() {
                            @Override
                            public void onDataFetched(DelayedRemovalArray<Player> players) {
                                ((MainMenuScreen) connector.getScreen()).onBattleDataFetched(gridRows, turn, players);
                            }
                        });
                    }
                });
            }
        });
    }
}
