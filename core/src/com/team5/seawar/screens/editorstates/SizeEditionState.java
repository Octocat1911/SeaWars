package com.team5.seawar.screens.editorstates;

import com.team5.seawar.cam.GlobalCam;
import com.team5.seawar.cam.ZoomCam;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.screens.MapEditorScreen;
import com.team5.seawar.screens.MenuScreen;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.screens.playstates.State;
import com.team5.seawar.utils.Assets;

/**
 * Created with love by Team 5
 */

public class SizeEditionState implements State {

    private MapEditorScreen mapEditorScreen;

    public SizeEditionState(MapEditorScreen mapEditorScreen){
        GlobalCam.getInstance().setSetZoomCamActive(false);
        this.mapEditorScreen = mapEditorScreen;
    }

    public void handleInput(float dt){
        if(Inputs.isPressed(Inputs.UP)){
            this.mapEditorScreen.getMap().setLigne(this.mapEditorScreen.getMap().getLigne() + 1);
            this.mapEditorScreen.getMap().init();
        }
        if(Inputs.isPressed(Inputs.RIGHT)){
            this.mapEditorScreen.getMap().setColonne(this.mapEditorScreen.getMap().getColonne() + 1);
            this.mapEditorScreen.getMap().init();
        }
        if(Inputs.isPressed(Inputs.DOWN)){
            this.mapEditorScreen.getMap().setLigne(this.mapEditorScreen.getMap().getLigne() - 1);
            this.mapEditorScreen.getMap().init();
        }
        if(Inputs.isPressed(Inputs.LEFT)){
            this.mapEditorScreen.getMap().setColonne(this.mapEditorScreen.getMap().getColonne() - 1);
            this.mapEditorScreen.getMap().init();
        }
        if(Inputs.isPressed(Inputs.START)){
            GlobalCam.getInstance().setSetZoomCamActive(true);
            mapEditorScreen.setCamState(ZoomCam.getInstance());
            MapEditionState.setLightHouseNb(mapEditorScreen);
            PlayScreen.position.x = mapEditorScreen.getMap().getColonne() - 1;
            PlayScreen.position.y = mapEditorScreen.getMap().getLigne() - 1;
            mapEditorScreen.changeState(new MapEditionState(mapEditorScreen));
        }
        if (Inputs.isPressed(Inputs.SELECT)){
            mapEditorScreen.getGameApp().setScreen(MenuScreen.getInstance());
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

    public void drawUI(){
        mapEditorScreen.getBatch().draw(Assets.getInstance().getTexture("UI/SizeEditionState.png"), 870, 50, 1683/5, 724/5);
    }
}
