package com.team5.seawar.screens.editorstates;

import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.screens.MapEditorScreen;
import com.team5.seawar.screens.playstates.State;

public class MapEditionState implements State {

    private MapEditorScreen mapEditorScreen;

    private static MapEditionState instance;

    private MapEditionState(MapEditorScreen mapEditorScreen){
        this.mapEditorScreen = mapEditorScreen;
    }


    public static void init(MapEditorScreen mapEditorScreen){
        if(instance ==null){
            instance = new MapEditionState(mapEditorScreen);
        }
    }

    public static MapEditionState getInstance(){
        return instance;
    }

    public void handleInput(float dt){
        if(Inputs.isPressed(Inputs.SELECT)){
            mapEditorScreen.changeState(SizeEditionState.getInstance());
        }
    }

    @Override
    public void update(float dt) {
        handleInput(dt);
    }

    @Override
    public void draw() {
        mapEditorScreen.getMap().draw(mapEditorScreen.getBatch());
    }
}
