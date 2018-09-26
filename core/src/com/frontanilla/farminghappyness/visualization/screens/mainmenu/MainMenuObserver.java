package com.frontanilla.farminghappyness.visualization.screens.mainmenu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.core.FarmingGame;
import com.frontanilla.farminghappyness.core.FirestoreDBConnection;
import com.frontanilla.farminghappyness.core.Player;
import com.frontanilla.farminghappyness.utils.FetchListener;
import com.frontanilla.farminghappyness.utils.OnResultListener;
import com.frontanilla.farminghappyness.utils.Util;
import com.frontanilla.farminghappyness.visualization.components.Button;
import com.frontanilla.farminghappyness.visualization.screens.Observer;

import static com.frontanilla.farminghappyness.utils.Constants.FETCHING_INTERVAL;

public class MainMenuObserver extends Observer {

    // Logic attributes
    private Color selectedColor;
    private boolean requestSent;
    private float startTime;

    public MainMenuObserver(MainMenuConnector connector) {
        super(connector);
    }

    @Override
    public void init() {

    }

    @Override
    public void update(float delta) {
        if (requestSent) {
            startTime = Math.min(startTime + delta, FETCHING_INTERVAL);
            if (startTime == FETCHING_INTERVAL) {
                startTime = 0;
                requestSent = false;
                checkIfAlreadyPlaying();
            }
        }
    }

    @Override
    public void onScreenShow() {
        startTime = 0;
        requestSent = false;
        checkIfAlreadyPlaying();
    }

    private void checkIfAlreadyPlaying() {
        System.out.println("MainMenuObserver-checkIfAlreadyPlaying");
        ((MainMenuStuff) connector.getStuff()).getMessageLabel().setText("Refreshing...");
        ((MainMenuStuff) connector.getStuff()).getLoadingImage().setVisible(true);
        connector.getInput().setEnabled(false);
        //((MainMenuFirestore) connector.getFirestore()).getPlayers();
    }

    public void onPlayersFetched(DelayedRemovalArray<Player> players) {
        if (playerInBattle(players)) {
            ((MainMenuStuff) connector.getStuff()).getMessageLabel().setText("LEEEROOOOOY...!");
            // TODO Play Leeroy Jenkins sound here
            ((MainMenuStuff) connector.getStuff()).getLoadingImage().setVisible(false);
            ((MainMenuScreen) connector.getScreen()).joinBattle();
        } else {
            ((MainMenuFirestore) connector.getFirestore()).getRequests();
        }
    }

    public void onRequestsFetched(String[] additionRequests) {
        if (requestPending(additionRequests)) {
            requestSent = true;
            ((MainMenuStuff) connector.getStuff()).getMessageLabel().setText("Request sent, please wait");
        } else {
            ((MainMenuStuff) connector.getStuff()).getMessageLabel().setText("Choose a name and a color");
            ((MainMenuStuff) connector.getStuff()).getLoadingImage().setVisible(false);
            connector.getInput().setEnabled(true);
        }
    }

    private boolean playerInBattle(DelayedRemovalArray<Player> players) {
        for (Player p : players) {
            if (p.getPhoneID().equals(FarmingGame.phoneID)) {
                return true;
            }
        }
        return false;
    }

    public void colorButtonTapped(Button button) {
        this.selectedColor = button.getColor();
        for (Button b : ((MainMenuStuff) connector.getStuff()).getColorButtons()) {
            b.setSelected(b == button);
        }
    }

    public void joinButtonPressed() {
        if (selectedColor == null) {
            ((MainMenuStuff) connector.getStuff()).getMessageLabel().setText("Please select a color");
        } else {
            String chosenName = ((MainMenuStuff) connector.getStuff()).getPlayerNameButton().getText();
            if (chosenName.matches("^[-\\w.]+$") && chosenName.length() > 2) {
                requestToJoinBattle();
            } else {
                ((MainMenuStuff) connector.getStuff()).getMessageLabel().setText("Invalid name");
            }
        }
    }

    private void requestToJoinBattle() {
        System.out.println("MainMenuLogic-requestToJoinBattle");
        ((MainMenuStuff) connector.getStuff()).getMessageLabel().setText("Requesting to join...");
        ((MainMenuStuff) connector.getStuff()).getLoadingImage().setVisible(true);
        connector.getInput().setEnabled(false); // Disable input, check if working
        FirestoreDBConnection.getInstance().fetchRequests(
                new FetchListener<String[]>() {
                    @Override
                    public void onDataFetched(String[] additionRequests) {
                        if (requestPending(additionRequests)) {
                            requestSent = true;
                            ((MainMenuStuff) connector.getStuff()).getMessageLabel().setText("Request sent, please wait");
                        } else if (colorBeingRequested(additionRequests)) {
                            ((MainMenuStuff) connector.getStuff()).getMessageLabel().setText("Color being requested already");
                            ((MainMenuStuff) connector.getStuff()).getLoadingImage().setVisible(false);
                            connector.getInput().setEnabled(true);
                        } else {
                            requestColor();
                        }
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

    private boolean requestPending(String[] additionRequests) {
        for (String request : additionRequests) {
            if (request.contains(FarmingGame.phoneID)) {
                return true;
            }
        }
        return false;
    }

    private boolean colorBeingRequested(String[] additionRequests) {
        for (String request : additionRequests) {
            if (request.contains(Util.getStringFromColor(selectedColor))) {
                return true;
            }
        }
        return false;
    }

    private void requestColor() {
        System.out.println("MainMenuLogic-requestColor");
        ((MainMenuStuff) connector.getStuff()).getMessageLabel().setText("Requesting color...");
        FirestoreDBConnection.getInstance().fetchPlayers(new FetchListener<DelayedRemovalArray<Player>>() {
            @Override
            public void onDataFetched(DelayedRemovalArray<Player> players) {
                if (colorSelectedAvailable(players)) {
                    // The color is available, request it
                    String phoneID = FarmingGame.phoneID;
                    String name = ((MainMenuStuff) connector.getStuff()).getPlayerNameButton().getText();
                    String color = Util.getStringFromColor(selectedColor);
                    final String request = phoneID + "." + name + "." + color + ",";
                    FirestoreDBConnection.getInstance().playerAdditionRequest(request, new OnResultListener() {
                        @Override
                        public void onResult(boolean success) {
                            if (success) {
                                ((MainMenuStuff) connector.getStuff()).getMessageLabel().setText("Request sent, please wait");
                                ((MainMenuStuff) connector.getStuff()).getLoadingImage().setVisible(true);
                                connector.getInput().setEnabled(false);
                                requestSent = true;
                            } else {
                                FirestoreDBConnection.getInstance().playerAdditionRequest(request, this);
                            }
                        }
                    });
                } else {
                    ((MainMenuStuff) connector.getStuff()).getMessageLabel().setText("Color not available");
                    ((MainMenuStuff) connector.getStuff()).getLoadingImage().setVisible(false);
                    connector.getInput().setEnabled(true);
                }
            }
        });
    }

    private boolean colorSelectedAvailable(DelayedRemovalArray<Player> players) {
        for (Player p : players) {
            if (p.getColor() == selectedColor) {
                return false;
            }
        }
        return true;
    }
}
