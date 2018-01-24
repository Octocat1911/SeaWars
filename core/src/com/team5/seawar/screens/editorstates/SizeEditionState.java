package com.team5.seawar.screens.editorstates;

import com.team5.seawar.cam.GlobalCam;
import com.team5.seawar.cam.ZoomCam;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.screens.MapEditorScreen;
import com.team5.seawar.screens.MenuScreen;
import com.team5.seawar.screens.PlayScreen;
import com.team5.seawar.screens.playstates.State;
import com.team5.seawar.utils.Assets;

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
        instance.mapEditorScreen.getMap().init();
        return instance;
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
            GlobalCam.getInstance().setCanGoToZoomCam(true);
            mapEditorScreen.setCamState(ZoomCam.getInstance());
            MapEditionState.setLightHouseNb(mapEditorScreen);
            PlayScreen.position.x = mapEditorScreen.getMap().getColonne() - 1;
            PlayScreen.position.y = mapEditorScreen.getMap().getLigne() - 1;
            mapEditorScreen.changeState(MapEditionState.getInstance());
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
