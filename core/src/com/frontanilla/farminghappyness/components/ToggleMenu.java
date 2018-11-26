package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Enums.MenuState;

import static com.frontanilla.farminghappyness.utils.Constants.AYARN_COST;
import static com.frontanilla.farminghappyness.utils.Constants.AYARN_ID;
import static com.frontanilla.farminghappyness.utils.Constants.ELSKA_COST;
import static com.frontanilla.farminghappyness.utils.Constants.ELSKA_ID;
import static com.frontanilla.farminghappyness.utils.Constants.ELSKER_COST;
import static com.frontanilla.farminghappyness.utils.Constants.ELSKER_ID;
import static com.frontanilla.farminghappyness.utils.Constants.GRA_COST;
import static com.frontanilla.farminghappyness.utils.Constants.GRA_ID;
import static com.frontanilla.farminghappyness.utils.Constants.KAERLIGHED_COST;
import static com.frontanilla.farminghappyness.utils.Constants.KAERLIGHED_ID;
import static com.frontanilla.farminghappyness.utils.Constants.KOCHAM_COST;
import static com.frontanilla.farminghappyness.utils.Constants.KOCHAM_ID;
import static com.frontanilla.farminghappyness.utils.Constants.MENU_ACTIVATION_BUTTON_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.MENU_ACTIVATION_BUTTON_Y;
import static com.frontanilla.farminghappyness.utils.Constants.MENU_ACTIVATION_TIME;
import static com.frontanilla.farminghappyness.utils.Constants.MENU_BUTTON_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.MENU_BUTTON_X_OFFSET;
import static com.frontanilla.farminghappyness.utils.Constants.MENU_BUTTON_Y_OFFSET;
import static com.frontanilla.farminghappyness.utils.Constants.MENU_BUTTON_Y_SPACING;
import static com.frontanilla.farminghappyness.utils.Constants.MILESTIBA_COST;
import static com.frontanilla.farminghappyness.utils.Constants.MILESTIBA_ID;
import static com.frontanilla.farminghappyness.utils.Constants.MINE_COST;
import static com.frontanilla.farminghappyness.utils.Constants.MINE_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.MINE_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.NULL_ID;
import static com.frontanilla.farminghappyness.utils.Constants.RAKKAUS_COST;
import static com.frontanilla.farminghappyness.utils.Constants.RAKKAUS_ID;
import static com.frontanilla.farminghappyness.utils.Constants.SEVIYORUM_COST;
import static com.frontanilla.farminghappyness.utils.Constants.SEVIYORUM_ID;
import static com.frontanilla.farminghappyness.utils.Constants.SZERELEM_COST;
import static com.frontanilla.farminghappyness.utils.Constants.SZERELEM_ID;
import static com.frontanilla.farminghappyness.utils.Constants.TOGGLE_MENU_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.TOGGLE_MENU_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.TRAP_ID;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_COST;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_ID;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_COST;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_ID;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_WIDTH;

public class ToggleMenu {

    private MenuState menuState;
    private float time, x;
    private NinePatcher panel;
    private Button defensesToggleButton, plantsToggleButton;
    private DelayedRemovalArray<ToggleMenuButton> defenseButtons, plantButtons;

    public ToggleMenu() {
        menuState = MenuState.DEACTIVATED;
        time = 0;
        x = -TOGGLE_MENU_WIDTH;
        panel = new NinePatcher(
                Assets.toggleMenuPanel,
                10f,
                1);
        panel.setWidth(TOGGLE_MENU_WIDTH);
        panel.setHeight(TOGGLE_MENU_HEIGHT);
        panel.setColor(Color.BROWN);
        initToggleButtons();
        initDefenseButtons();
        initPlantButtons();
    }

    private void initToggleButtons() {
        defensesToggleButton = new Button(
                Assets.testToggleButtonRight,
                0,
                MENU_ACTIVATION_BUTTON_Y,
                MENU_ACTIVATION_BUTTON_SIZE,
                MENU_ACTIVATION_BUTTON_SIZE);
        plantsToggleButton = new Button(
                Assets.testToggleButtonRight,
                0,
                MENU_ACTIVATION_BUTTON_Y - MENU_ACTIVATION_BUTTON_SIZE,
                MENU_ACTIVATION_BUTTON_SIZE,
                MENU_ACTIVATION_BUTTON_SIZE);
    }

    private void initDefenseButtons() {
        defenseButtons = new DelayedRemovalArray<>();
        int yMultiplier = 1;
        defenseButtons.add(new ToggleMenuButton(
                TURRET_COST,
                Assets.testFrame,
                Assets.turret,
                x,
                TOGGLE_MENU_HEIGHT - MENU_BUTTON_Y_OFFSET - MENU_BUTTON_Y_SPACING * yMultiplier++,
                MENU_BUTTON_SIZE,
                MENU_BUTTON_SIZE,
                TURRET_WIDTH,
                TURRET_HEIGHT,
                TURRET_ID));
        defenseButtons.add(new ToggleMenuButton(
                WALL_COST,
                Assets.testFrame,
                Assets.wall,
                x,
                TOGGLE_MENU_HEIGHT - MENU_BUTTON_Y_OFFSET - MENU_BUTTON_Y_SPACING * yMultiplier++,
                MENU_BUTTON_SIZE,
                MENU_BUTTON_SIZE,
                WALL_WIDTH,
                WALL_HEIGHT,
                WALL_ID));
        defenseButtons.add(new ToggleMenuButton(
                MINE_COST,
                Assets.testFrame,
                Assets.mine,
                x,
                TOGGLE_MENU_HEIGHT - MENU_BUTTON_Y_OFFSET - MENU_BUTTON_Y_SPACING * yMultiplier,
                MENU_BUTTON_SIZE,
                MENU_BUTTON_SIZE,
                MINE_WIDTH,
                MINE_HEIGHT,
                TRAP_ID));
    }

    private void initPlantButtons() {
        plantButtons = new DelayedRemovalArray<>();
        int yMultiplier = 1;
        plantButtons.add(new ToggleMenuButton(
                ELSKER_COST,
                Assets.testFrame,
                Assets.elsker,
                x,
                TOGGLE_MENU_HEIGHT - MENU_BUTTON_Y_OFFSET - MENU_BUTTON_Y_SPACING * yMultiplier++,
                MENU_BUTTON_SIZE,
                MENU_BUTTON_SIZE,
                Assets.elsker.getRegionWidth(),
                Assets.elsker.getRegionHeight(),
                ELSKER_ID));
        plantButtons.add(new ToggleMenuButton(
                GRA_COST,
                Assets.testFrame,
                Assets.gra,
                x,
                TOGGLE_MENU_HEIGHT - MENU_BUTTON_Y_OFFSET - MENU_BUTTON_Y_SPACING * yMultiplier++,
                MENU_BUTTON_SIZE,
                MENU_BUTTON_SIZE,
                Assets.gra.getRegionWidth(),
                Assets.gra.getRegionHeight(),
                GRA_ID));
        plantButtons.add(new ToggleMenuButton(
                KOCHAM_COST,
                Assets.testFrame,
                Assets.kocham,
                x,
                TOGGLE_MENU_HEIGHT - MENU_BUTTON_Y_OFFSET - MENU_BUTTON_Y_SPACING * yMultiplier++,
                MENU_BUTTON_SIZE,
                MENU_BUTTON_SIZE,
                Assets.kocham.getRegionWidth(),
                Assets.kocham.getRegionHeight(),
                KOCHAM_ID));
        plantButtons.add(new ToggleMenuButton(
                SZERELEM_COST,
                Assets.testFrame,
                Assets.szerelem,
                x,
                TOGGLE_MENU_HEIGHT - MENU_BUTTON_Y_OFFSET - MENU_BUTTON_Y_SPACING * yMultiplier++,
                MENU_BUTTON_SIZE,
                MENU_BUTTON_SIZE,
                Assets.szerelem.getRegionWidth(),
                Assets.szerelem.getRegionHeight(),
                SZERELEM_ID));
        plantButtons.add(new ToggleMenuButton(
                ELSKA_COST,
                Assets.testFrame,
                Assets.elska,
                x,
                TOGGLE_MENU_HEIGHT - MENU_BUTTON_Y_OFFSET - MENU_BUTTON_Y_SPACING * yMultiplier++,
                MENU_BUTTON_SIZE,
                MENU_BUTTON_SIZE,
                Assets.elska.getRegionWidth(),
                Assets.elska.getRegionHeight(),
                ELSKA_ID));
        plantButtons.add(new ToggleMenuButton(
                AYARN_COST,
                Assets.testFrame,
                Assets.ayarn,
                x,
                TOGGLE_MENU_HEIGHT - MENU_BUTTON_Y_OFFSET - MENU_BUTTON_Y_SPACING * yMultiplier++,
                MENU_BUTTON_SIZE,
                MENU_BUTTON_SIZE,
                Assets.ayarn.getRegionWidth(),
                Assets.ayarn.getRegionHeight(),
                AYARN_ID));
        plantButtons.add(new ToggleMenuButton(
                SEVIYORUM_COST,
                Assets.testFrame,
                Assets.seviyorum,
                x,
                TOGGLE_MENU_HEIGHT - MENU_BUTTON_Y_OFFSET - MENU_BUTTON_Y_SPACING * yMultiplier++,
                MENU_BUTTON_SIZE,
                MENU_BUTTON_SIZE,
                Assets.seviyorum.getRegionWidth(),
                Assets.seviyorum.getRegionHeight(),
                SEVIYORUM_ID));
        plantButtons.add(new ToggleMenuButton(
                MILESTIBA_COST,
                Assets.testFrame,
                Assets.milestiba,
                x,
                TOGGLE_MENU_HEIGHT - MENU_BUTTON_Y_OFFSET - MENU_BUTTON_Y_SPACING * yMultiplier++,
                MENU_BUTTON_SIZE,
                MENU_BUTTON_SIZE,
                Assets.milestiba.getRegionWidth(),
                Assets.milestiba.getRegionHeight(),
                MILESTIBA_ID));
        plantButtons.add(new ToggleMenuButton(
                RAKKAUS_COST,
                Assets.testFrame,
                Assets.rakkaus,
                x,
                TOGGLE_MENU_HEIGHT - MENU_BUTTON_Y_OFFSET - MENU_BUTTON_Y_SPACING * yMultiplier++,
                MENU_BUTTON_SIZE,
                MENU_BUTTON_SIZE,
                Assets.rakkaus.getRegionWidth(),
                Assets.rakkaus.getRegionHeight(),
                RAKKAUS_ID));
        plantButtons.add(new ToggleMenuButton(
                KAERLIGHED_COST,
                Assets.testFrame,
                Assets.kaerlighed,
                x,
                TOGGLE_MENU_HEIGHT - MENU_BUTTON_Y_OFFSET - MENU_BUTTON_Y_SPACING * yMultiplier,
                MENU_BUTTON_SIZE,
                MENU_BUTTON_SIZE,
                Assets.kaerlighed.getRegionWidth(),
                Assets.kaerlighed.getRegionHeight(),
                KAERLIGHED_ID));
    }

    public void update(float delta, int money, int availableWorkers) {
        // Activating
        if (menuState == MenuState.ACTIVATING_DEFENSES_MENU || menuState == MenuState.ACTIVATING_PLANTS_MENU) {
            time += delta;
            if (time >= MENU_ACTIVATION_TIME) {
                time = MENU_ACTIVATION_TIME;
                if (menuState == MenuState.ACTIVATING_DEFENSES_MENU) {
                    menuState = MenuState.SHOWING_DEFENSES_MENU;
                    defensesToggleButton.setTexture(Assets.testToggleButtonLeft);
                }
                if (menuState == MenuState.ACTIVATING_PLANTS_MENU) {
                    menuState = MenuState.SHOWING_PLANTS_MENU;
                    plantsToggleButton.setTexture(Assets.testToggleButtonLeft);
                }
            }
            x = Math.min((time * TOGGLE_MENU_WIDTH) / MENU_ACTIVATION_TIME - TOGGLE_MENU_WIDTH, 0);
            defensesToggleButton.bounds.x = x + TOGGLE_MENU_WIDTH;
            plantsToggleButton.bounds.x = x + TOGGLE_MENU_WIDTH;
        }
        // Deactivating
        if (menuState == MenuState.DEACTIVATING) {
            time -= delta;
            if (time <= 0) {
                menuState = MenuState.DEACTIVATED;
                time = 0;
                defensesToggleButton.setTexture(Assets.testToggleButtonRight);
                plantsToggleButton.setTexture(Assets.testToggleButtonRight);
            }
            x = Math.max(-TOGGLE_MENU_WIDTH, (time * TOGGLE_MENU_WIDTH) / MENU_ACTIVATION_TIME - TOGGLE_MENU_WIDTH);
            defensesToggleButton.bounds.x = x + TOGGLE_MENU_WIDTH;
            plantsToggleButton.bounds.x = x + TOGGLE_MENU_WIDTH;
        }
        // Update defense buttons to show availability
        for (ToggleMenuButton button : defenseButtons) {
            button.setX(x + MENU_BUTTON_X_OFFSET);
            if (button.getPlantCost() > money) {
                button.setColor(Color.GRAY);
            } else {
                if (button.getColor() == Color.GRAY) {
                    button.setColor(Color.SKY);
                }
            }
        }
        // Update plant buttons to show availability
        for (ToggleMenuButton plantButton : plantButtons) {
            plantButton.setX(x + MENU_BUTTON_X_OFFSET);
            if (plantButton.getPlantCost() > availableWorkers) {
                plantButton.setColor(Color.GRAY);
            } else {
                if (plantButton.getColor() == Color.GRAY) {
                    plantButton.setColor(Color.SKY);
                }
            }
        }
        // Update panel position
        panel.setPosition(x, 0);
    }

    public void render(SpriteBatch batch) {
        renderPanel(batch);
        renderToggleButtons(batch);
        renderEntityButtons(batch);
    }

    private void renderPanel(SpriteBatch batch) {
        panel.render(batch);
    }

    private void renderToggleButtons(SpriteBatch batch) {
        defensesToggleButton.render(batch);
        plantsToggleButton.render(batch);
    }

    private void renderEntityButtons(SpriteBatch batch) {
        if (menuState == MenuState.SHOWING_DEFENSES_MENU) {
            for (ToggleMenuButton b : defenseButtons) {
                b.render(batch);
            }
        } else if (menuState == MenuState.SHOWING_PLANTS_MENU) {
            // TODO render only 4 for now
            for (int i = 0; i < 4; i++) {
                plantButtons.get(i).render(batch);
            }
//            for (ToggleMenuButton b : plantButtons) {
//                b.render(batch);
//            }
        }
    }

    public void activate(boolean defensesMenu) {
        if (defensesMenu) {
            menuState = MenuState.ACTIVATING_DEFENSES_MENU;
        } else {
            menuState = MenuState.ACTIVATING_PLANTS_MENU;
        }
        time = 0;
    }

    public void deactivate() {
        menuState = MenuState.DEACTIVATING;
        time = MENU_ACTIVATION_TIME;
    }

    public int getSelectedDefenseButtonID() {
        for (ToggleMenuButton button : defenseButtons) {
            if (button.color == Color.YELLOW) {
                return button.getID();
            }
        }
        return NULL_ID;
    }

    public int getSelectedPlantButtonID() {
        for (ToggleMenuButton button : plantButtons) {
            if (button.color == Color.YELLOW) {
                return button.getID();
            }
        }
        return NULL_ID;
    }

    //----------------------------
    //      GETTERS & SETTERS
    //----------------------------

    public MenuState getMenuState() {
        return menuState;
    }

    public void setMenuState(MenuState menuState) {
        this.menuState = menuState;
        if (menuState == MenuState.SHOWING_DEFENSES_MENU) {
            defensesToggleButton.setTexture(Assets.testToggleButtonLeft);
            plantsToggleButton.setTexture(Assets.testToggleButtonRight);
        } else if (menuState == MenuState.SHOWING_PLANTS_MENU) {
            defensesToggleButton.setTexture(Assets.testToggleButtonRight);
            plantsToggleButton.setTexture(Assets.testToggleButtonLeft);
        }
    }

    public Button getDefensesToggleButton() {
        return defensesToggleButton;
    }

    public Button getPlantsToggleButton() {
        return plantsToggleButton;
    }

    public DelayedRemovalArray<ToggleMenuButton> getActiveButtons() {
        if (menuState == MenuState.SHOWING_DEFENSES_MENU) {
            return defenseButtons;
        } else if (menuState == MenuState.SHOWING_PLANTS_MENU) {
            return plantButtons;
        }
        return null;
    }
}
