package com.team5.seawar.screens.editorstates;

import com.team5.seawar.cam.GlobalCam;
import com.team5.seawar.cam.ZoomCam;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.objects.Element;
import com.team5.seawar.screens.MapEditorScreen;
import com.team5.seawar.screens.playstates.State;
import com.team5.seawar.utils.Assets;

public class MapEditionState implements State {

    private MapEditorScreen mapEditorScreen;
    private static int lightHouseNb = 3;

    private static MapEditionState instance;

    private MapEditionState(MapEditorScreen mapEditorScreen) {
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

    public static void setLightHouseNb(MapEditorScreen mapEditorScreen){
        int mapSize = mapEditorScreen.getMap().getColonne() * mapEditorScreen.getMap().getLigne();
        if(mapSize < 50){
            lightHouseNb = 3;
        }else if(mapSize > 50 && mapSize < 500){
            lightHouseNb = 6;
        }else if(mapSize > 500 && mapSize < 900){
            lightHouseNb = 9;
        }else if(mapSize> 900){
            lightHouseNb = 12;
        }
    }

    public void handleInput(float dt){
        if(Inputs.isPressed(Inputs.START)){
            GlobalCam.getInstance().setCanGoToZoomCam(false);
            mapEditorScreen.setCamState(GlobalCam.getInstance());
            mapEditorScreen.getMap().init();
            mapEditorScreen.changeState(SizeEditionState.getInstance());
        } if (Inputs.isPressed(Inputs.LEFT) && mapEditorScreen.position.x>0){
            mapEditorScreen.position.x--;
        }
        if (Inputs.isPressed(Inputs.RIGHT) && mapEditorScreen.position.x<mapEditorScreen.getMap().getColonne()-1){
            mapEditorScreen.position.x++;
        }
        if (Inputs.isPressed(Inputs.UP) && mapEditorScreen.position.y<mapEditorScreen.getMap().getLigne()-1){
            mapEditorScreen.position.y++;
        }
        if (Inputs.isPressed(Inputs.DOWN) && mapEditorScreen.position.y>0){
            mapEditorScreen.position.y--;
        }
        if(Inputs.isPressed(Inputs.A)){
            if(mapEditorScreen.getCurrentCase().getElement().getType() == Element.Type.LIGHTHOUSE){
                lightHouseNb ++;
            }
            mapEditorScreen.getCurrentCase().setElement(new Element(Element.Type.DIRT,mapEditorScreen.position.x,mapEditorScreen.position.y));
        }
        if(Inputs.isPressed(Inputs.B)){
            if(lightHouseNb > 0){
                lightHouseNb --;
                mapEditorScreen.getCurrentCase().setElement(new Element(Element.Type.LIGHTHOUSE,mapEditorScreen.position.x,mapEditorScreen.position.y));
            }

        }
        if(Inputs.isPressed(Inputs.X)){
            if(mapEditorScreen.getCurrentCase().getElement().getType() == Element.Type.LIGHTHOUSE){
                lightHouseNb ++;
            }
            mapEditorScreen.getCurrentCase().setElement(new Element(Element.Type.WATER,mapEditorScreen.position.x,mapEditorScreen.position.y));
        }
        mapEditorScreen.getMap().handleInput(mapEditorScreen.getCam(), mapEditorScreen.getViewport());
    }

    @Override
    public void update(float dt) {
        handleInput(dt);
        mapEditorScreen.getCamState().update(dt);
    }

    @Override
    public void draw() {
        mapEditorScreen.getMap().draw(mapEditorScreen.getBatch());
        mapEditorScreen.renderTexture(Assets.getInstance().getTexture("Maptextures/hexPointeur.png"), mapEditorScreen.position.x, mapEditorScreen.position.y);
    }

    public void drawUI(){

    }
}
