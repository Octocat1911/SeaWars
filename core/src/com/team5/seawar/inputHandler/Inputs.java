package com.team5.seawar.inputHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.objects.Case;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.utils.Action2DSprite;

/**
 * Created with love by Team 5
 */

public class Inputs {

    public static final int BUFFER = 2;

    public enum Mode {INPUT_MODE, MOUSE_MODE}
    public static Mode mode = Mode.INPUT_MODE;

    public static int A = 0;
    public static int B = 0;
    public static int X = 0;
    public static int Y = 0;
    public static int L1 = 0;
    public static int R1 = 0;
    public static int R3 = 0;
    public static int SELECT = 0;
    public static int START = 0;
    public static int UP = 0;
    public static int DOWN = 0;
    public static int LEFT = 0;
    public static int RIGHT = 0;
    public static int CLICK = 0;


    public static Vector3 mouse = new Vector3();

    public static void update() {
        if (A > 0)
            A--;
        if (B > 0)
            B--;
        if (X > 0)
            X--;
        if (Y > 0)
            Y--;
        if (L1 > 0)
            L1--;
        if (R1 > 0)
            R1--;
        if (R3 > 0)
            R3--;
        if (SELECT > 0)
            SELECT--;
        if (START > 0)
            START--;
        if (UP > 0)
            UP--;
        if (DOWN > 0)
            DOWN--;
        if (LEFT > 0)
            LEFT--;
        if (RIGHT > 0)
            RIGHT--;
        if (CLICK > 0)
            CLICK--;
    }

    public static void isTouched(Action2DSprite sprite, Camera camera, Viewport viewport) {
        switch (mode) {
            case MOUSE_MODE:
                camera.unproject(mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
                if (sprite.getSprite().getBoundingRectangle().contains(mouse.x, mouse.y)) {
                    sprite.touchAction();
                } else {
                    sprite.defaultAction();
                }
                break;
            case INPUT_MODE:
                sprite.defaultAction();
                break;
        }
    }

    public static void isJustClicked(Action2DSprite sprite, Camera camera, Viewport viewport) {
        if (mode == Mode.MOUSE_MODE) {
            if (Gdx.input.justTouched()) {
                camera.unproject(mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
                if (sprite.getSprite().getBoundingRectangle().contains(mouse.x, mouse.y)) {
                    sprite.clickedAction();
                }
            }
        }
    }

    public static void updateCase(Case myCase, Camera camera, Viewport viewport) {
        if (mode == Mode.MOUSE_MODE) {
            camera.unproject(mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
            if (myCase.getBounds().contains(mouse.x, mouse.y)) {
                PlayScreen.position.set(myCase.getPosition());
            } else {
                //coucou
                // If u don't know the meaning of this, think harder !........oh wait no, this is actually useless :D
            }
        }
    }

    public static boolean isPressed(int touche) {
        return touche > 0;
    }

}

