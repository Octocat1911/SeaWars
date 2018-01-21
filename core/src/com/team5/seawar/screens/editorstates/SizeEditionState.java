package com.team5.seawar.screens.editorstates;

import com.team5.seawar.cam.ZoomCam;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.screens.MapEditorScreen;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.screens.playstates.State;

public class SizeEditionState implements State {

    private MapEditorScreen mapEditorScreen;

    private static SizeEditionState instance;

    private SizeEditionState(MapEditorScreen mapEditorScreen){
        this.mapEditorScreen = mapEditorScreen;
    }


    public static void init(MapEditorScreen mapEditorScreen){
        if(instance ==null){
            instance = new SizeEditionState(mapEditorScreen);
        }
    }

    public static SizeEditionState getInstance(){
        return instance;
    }


    public void handleInput(float dt){
        if(Inputs.isPressed(Inputs.UP)){
            this.mapEditorScreen.getMap().setLigne(this.mapEditorScreen.getMap().getLigne() + 1);
            this.mapEditorScreen.getMap().init();
            PlayScreen.position.y++;
        }
        if(Inputs.isPressed(Inputs.RIGHT)){
            this.mapEditorScreen.getMap().setColonne(this.mapEditorScreen.getMap().getColonne() + 1);
            this.mapEditorScreen.getMap().init();
            PlayScreen.position.x++;
        }
        if(Inputs.isPressed(Inputs.DOWN)){
            this.mapEditorScreen.getMap().setLigne(this.mapEditorScreen.getMap().getLigne() - 1);
            this.mapEditorScreen.getMap().init();
            if(!(this.mapEditorScreen.getMap().getLigne()<6)){
                PlayScreen.position.y--;
            }

        }
        if(Inputs.isPressed(Inputs.LEFT)){
            this.mapEditorScreen.getMap().setColonne(this.mapEditorScreen.getMap().getColonne() - 1);
            this.mapEditorScreen.getMap().init();
            if(!(this.mapEditorScreen.getMap().getColonne()<6)){
                PlayScreen.position.x--;
            }
        }
        if(Inputs.isPressed(Inputs.START)){
            mapEditorScreen.changeState(MapEditionState.getInstance());
            mapEditorScreen.setCamState(ZoomCam.getInstance());
        }
        mapEditorScreen.getMap().handleInput(mapEditorScreen.getCam(),mapEditorScreen.getViewport());
    }

    @Override
    public void update(float dt) {
        handleInput(dt);
        mapEditorScreen.getCamState().update(dt);
    }

    @Override
    public void draw() {
        mapEditorScreen.getMap().draw(mapEditorScreen.getBatch());
    }
}
