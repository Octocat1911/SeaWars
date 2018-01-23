package com.team5.seawar.inputHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class InputHandler extends InputAdapter{

    public static final int A = Input.Keys.A;
    public static final int B = Input.Keys.Z;
    public static final int X = Input.Keys.X;
    public static final int Y = Input.Keys.E;
    public static final int L1 = Input.Keys.Q;
    public static final int L2 = Input.Keys.T;
    public static final int R1 = Input.Keys.D;
    public static final int R2 = Input.Keys.Y;
    public static final int SELECT = Input.Keys.TAB;
    public static final int START = Input.Keys.SPACE;
    public static final int UP = Input.Keys.UP;
    public static final int DOWN = Input.Keys.DOWN;
    public static final int LEFT = Input.Keys.LEFT;
    public static final int RIGHT = Input.Keys.RIGHT;

    public InputHandler(){
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        Inputs.mode = Inputs.Mode.INPUT_MODE;
        switch (keycode){
            case X:
                Inputs.X = Inputs.BUFFER;
                break;
            case Y:
                Inputs.Y = Inputs.BUFFER;
                break;
            case A:
                Inputs.A = Inputs.BUFFER;
                break;
            case B:
                Inputs.B = Inputs.BUFFER;
                break;
            case SELECT:
                Inputs.SELECT = Inputs.BUFFER;
                break;
            case START:
                Inputs.START = Inputs.BUFFER;
                break;
            case L1:
                Inputs.L1 = Inputs.BUFFER;
                break;
            case R1:
                Inputs.R1 = Inputs.BUFFER;
                break;
            case UP:
                Inputs.UP = Inputs.BUFFER;
                break;
            case DOWN:
                Inputs.DOWN = Inputs.BUFFER;
                break;
            case LEFT:
                Inputs.LEFT = Inputs.BUFFER;
                break;
            case RIGHT:
                Inputs.RIGHT = Inputs.BUFFER;
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Inputs.CLICK = Inputs.BUFFER;
        Inputs.mode = Inputs.Mode.MOUSE_MODE;
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Inputs.mode = Inputs.Mode.MOUSE_MODE;
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
