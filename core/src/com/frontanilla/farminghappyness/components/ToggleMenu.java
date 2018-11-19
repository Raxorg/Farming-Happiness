package com.frontanilla.farminghappyness.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.farminghappyness.utils.Assets;
import com.frontanilla.farminghappyness.utils.Enums.MenuState;

import static com.frontanilla.farminghappyness.utils.Constants.MENU_ACTIVATION_BUTTON_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.MENU_ACTIVATION_BUTTON_Y;
import static com.frontanilla.farminghappyness.utils.Constants.MENU_ACTIVATION_TIME;
import static com.frontanilla.farminghappyness.utils.Constants.MENU_BUTTON_IMAGE_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.MENU_BUTTON_SIZE;
import static com.frontanilla.farminghappyness.utils.Constants.MENU_BUTTON_X_OFFSET;
import static com.frontanilla.farminghappyness.utils.Constants.MENU_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.MENU_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.MINE_COST;
import static com.frontanilla.farminghappyness.utils.Constants.MINE_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.MINE_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.NULL_ID;
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
        x = -MENU_WIDTH;
        panel = new NinePatcher(
                Assets.testPanel,
                10f,
                1);
        panel.setWidth(MENU_WIDTH);
        panel.setHeight(MENU_HEIGHT);
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
        defenseButtons.add(new ToggleMenuButton(
                TURRET_COST,
                Assets.testFrame,
                Assets.turret,
                x,
                MENU_HEIGHT - MENU_BUTTON_IMAGE_WIDTH * 2,
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
                MENU_HEIGHT - MENU_BUTTON_IMAGE_WIDTH * 4,
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
                MENU_HEIGHT - MENU_BUTTON_IMAGE_WIDTH * 6,
                MENU_BUTTON_SIZE,
                MENU_BUTTON_SIZE,
                MINE_WIDTH,
                MINE_HEIGHT,
                TRAP_ID));
    }

    private void initPlantButtons() {
        plantButtons = new DelayedRemovalArray<>();

    }

    public void update(float delta, int money) {
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
            x = Math.min((time * MENU_WIDTH) / MENU_ACTIVATION_TIME - MENU_WIDTH, 0);
            defensesToggleButton.bounds.x = x + MENU_WIDTH;
            plantsToggleButton.bounds.x = x + MENU_WIDTH;
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
            x = Math.max(-MENU_WIDTH, (time * MENU_WIDTH) / MENU_ACTIVATION_TIME - MENU_WIDTH);
            defensesToggleButton.bounds.x = x + MENU_WIDTH;
            plantsToggleButton.bounds.x = x + MENU_WIDTH;
        }
        // Update images to show availability
        for (ToggleMenuButton button : defenseButtons) {
            button.setX(x + MENU_BUTTON_X_OFFSET);
            if (button.getCost() > money) {
                button.setColor(Color.GRAY);
            }
        }
        for (ToggleMenuButton button : plantButtons) {
            button.setX(x + MENU_BUTTON_X_OFFSET);
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
            for (ToggleMenuButton b : plantButtons) {
                b.render(batch);
            }
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

    public int getSelectedButtonID() {
        for (ToggleMenuButton button : defenseButtons) {
            if (button.color == Color.YELLOW) {
                return button.getID();
            }
        }
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

    // TODO use ConstructionState enum
}
