package com.team5.seawar.inputHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.utils.Action2DSprite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputHandler {

    public static final int A = Keys.A;
    public static final int B = Keys.B;
    public static final int X = Keys.X;
    public static final int Y = Keys.Y;
    public static final int L1 = Keys.L;
    public static final int L2 = Keys.L;
    public static final int R1 = Keys.R;
    public static final int R2 = Keys.R;
    public static final int SELECT = Keys.ESCAPE;
    public static final int START = Keys.ENTER;
    public static final int UP = Keys.UP;
    public static final int DOWN = Keys.DOWN;
    public static final int LEFT = Keys.LEFT;
    public static final int RIGHT = Keys.RIGHT;

    private static Vector3 mouse = new Vector3();

    public static void isTouched(Action2DSprite sprite, Camera camera, Viewport viewport) {
        camera.unproject(mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
        if (sprite.getSprite().getBoundingRectangle().contains(mouse.x, mouse.y)) {
            sprite.touchAction();
        } else {
            sprite.defaultAction();
        }
    }

    public static void isJustClicked(Action2DSprite sprite, Camera camera, Viewport viewport) {
        if (Gdx.input.justTouched()) {
            camera.unproject(mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
            if (sprite.getSprite().getBoundingRectangle().contains(mouse.x, mouse.y)) {
                sprite.clickedAction();
            }
        }
    }

    public static boolean isPressed(int touche){
        return Gdx.input.isKeyPressed(touche);
    }

    public static boolean isJustPressed(int touche){
        return Gdx.input.isKeyJustPressed(touche);
    }

    public static void test(){
        if (Gdx.input.getDeltaX() != 0)
            System.out.println(Gdx.input.getDeltaX() + " " + Gdx.input.getDeltaY());
    }

}

