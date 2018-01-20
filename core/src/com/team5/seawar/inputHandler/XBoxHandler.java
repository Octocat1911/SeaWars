package com.team5.seawar.inputHandler;

import com.badlogic.gdx.controllers.*;

public class XBoxHandler extends ControllerAdapter {

    public static final int BUTTON_X = 2;
    public static final int BUTTON_Y = 3;
    public static final int BUTTON_A = 0;
    public static final int BUTTON_B = 1;
    public static final int BUTTON_BACK = 6;
    public static final int BUTTON_START = 7;
    public static final PovDirection BUTTON_DPAD_UP = PovDirection.north;
    public static final PovDirection BUTTON_DPAD_DOWN = PovDirection.south;
    public static final PovDirection BUTTON_DPAD_RIGHT = PovDirection.east;
    public static final PovDirection BUTTON_DPAD_LEFT = PovDirection.west;
    public static final int BUTTON_LB = 4;
    public static final int BUTTON_L3 = 8;
    public static final int BUTTON_RB = 5;
    public static final int BUTTON_R3 = 9;
    public static final int AXIS_LEFT_X = 1; //-1 is left | +1 is right
    public static final int AXIS_LEFT_Y = 0; //-1 is up | +1 is down
    public static final int AXIS_LEFT_TRIGGER = 4; //value 0 to 1f
    public static final int AXIS_RIGHT_X = 3; //-1 is left | +1 is right
    public static final int AXIS_RIGHT_Y = 2; //-1 is up | +1 is down
    public static final int AXIS_RIGHT_TRIGGER = 4; //value 0 to -1f

    public XBoxHandler(){
        Controllers.addListener(this);
    }

    @Override
    public void connected(Controller controller) {

    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        Inputs.mode = Inputs.Mode.INPUT_MODE;
        switch (buttonCode){
            case BUTTON_X:
                Inputs.X = Inputs.BUFFER;
                break;
            case BUTTON_Y:
                Inputs.Y = Inputs.BUFFER;
                break;
            case BUTTON_A:
                Inputs.A = Inputs.BUFFER;
                break;
            case BUTTON_B:
                Inputs.B = Inputs.BUFFER;
                break;
            case BUTTON_BACK:
                Inputs.SELECT = Inputs.BUFFER;
                break;
            case BUTTON_START:
                Inputs.START = Inputs.BUFFER;
                break;
            case BUTTON_LB:
                Inputs.L1 = Inputs.BUFFER;
                break;
            case BUTTON_L3:
                //Inputs.X = Inputs.BUFFER;
                break;
            case BUTTON_RB:
                Inputs.R1 = Inputs.BUFFER;
                break;
            case BUTTON_R3:
                //Inputs.X = Inputs.BUFFER;
                break;
        }
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        Inputs.mode = Inputs.Mode.INPUT_MODE;
        if(value == BUTTON_DPAD_LEFT)
            Inputs.LEFT = Inputs.BUFFER;
        if(value == BUTTON_DPAD_RIGHT)
            Inputs.RIGHT = Inputs.BUFFER;
        if(value == BUTTON_DPAD_UP)
            Inputs.UP = Inputs.BUFFER;
        if(value == BUTTON_DPAD_DOWN)
            Inputs.DOWN = Inputs.BUFFER;
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {

        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

}
