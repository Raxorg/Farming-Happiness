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
import static com.frontanilla.farminghappyness.utils.Constants.TRAP_COST;
import static com.frontanilla.farminghappyness.utils.Constants.TRAP_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.TRAP_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_COST;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.TURRET_WIDTH;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_COST;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_HEIGHT;
import static com.frontanilla.farminghappyness.utils.Constants.WALL_WIDTH;

public class ToggleMenu {

    private MenuState menuState;
    private float time, x;
    private NinePatcher panel;
    private Button defensesButton, plantsButton;
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
        defensesButton = new Button(
                Assets.testToggleButtonRight,
                -MENU_ACTIVATION_BUTTON_SIZE / 2,
                MENU_ACTIVATION_BUTTON_Y,
                MENU_ACTIVATION_BUTTON_SIZE,
                MENU_ACTIVATION_BUTTON_SIZE);
        plantsButton = new Button(
                Assets.testToggleButtonRight,
                -MENU_ACTIVATION_BUTTON_SIZE / 2,
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
                TURRET_HEIGHT));
        defenseButtons.add(new ToggleMenuButton(
                WALL_COST,
                Assets.testFrame,
                Assets.wall,
                x,
                MENU_HEIGHT - MENU_BUTTON_IMAGE_WIDTH * 4,
                MENU_BUTTON_SIZE,
                MENU_BUTTON_SIZE,
                WALL_WIDTH,
                WALL_HEIGHT));
        defenseButtons.add(new ToggleMenuButton(
                TRAP_COST,
                Assets.testFrame,
                Assets.trap,
                x,
                MENU_HEIGHT - MENU_BUTTON_IMAGE_WIDTH * 6,
                MENU_BUTTON_SIZE,
                MENU_BUTTON_SIZE,
                TRAP_WIDTH,
                TRAP_HEIGHT));
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
                    defensesButton.setTexture(Assets.testToggleButtonLeft);
                }
                if (menuState == MenuState.ACTIVATING_PLANTS_MENU) {
                    menuState = MenuState.SHOWING_PLANTS_MENU;
                    plantsButton.setTexture(Assets.testToggleButtonLeft);
                }
            }
            x = Math.min((time * MENU_WIDTH) / MENU_ACTIVATION_TIME - MENU_WIDTH, 0);
            defensesButton.bounds.x = x + MENU_WIDTH - MENU_ACTIVATION_BUTTON_SIZE / 2;
            plantsButton.bounds.x = x + MENU_WIDTH - MENU_ACTIVATION_BUTTON_SIZE / 2;
        }
        // Deactivating
        if (menuState == MenuState.DEACTIVATING) {
            time -= delta;
            if (time <= 0) {
                menuState = MenuState.DEACTIVATED;
                time = 0;
                defensesButton.setTexture(Assets.testToggleButtonRight);
                plantsButton.setTexture(Assets.testToggleButtonRight);
            }
            x = Math.max(-MENU_WIDTH, (time * MENU_WIDTH) / MENU_ACTIVATION_TIME - MENU_WIDTH);
            defensesButton.bounds.x = x + MENU_WIDTH - MENU_ACTIVATION_BUTTON_SIZE / 2;
            plantsButton.bounds.x = x + MENU_WIDTH - MENU_ACTIVATION_BUTTON_SIZE / 2;
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
        renderIcons(batch);
    }

    private void renderPanel(SpriteBatch batch) {
        panel.render(batch);
    }

    private void renderToggleButtons(SpriteBatch batch) {
        defensesButton.render(batch);
        plantsButton.render(batch);
    }

    private void renderIcons(SpriteBatch batch) {
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

    //----------------------------
    //      GETTERS & SETTERS
    //----------------------------

    public MenuState getMenuState() {
        return menuState;
    }

    public Button getDefensesButton() {
        return defensesButton;
    }

    public Button getPlantsButton() {
        return plantsButton;
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
