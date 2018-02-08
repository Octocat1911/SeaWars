package com.team5.seawar.screens.editorstates;

import com.team5.seawar.cam.GlobalCam;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.objects.Element;
import com.team5.seawar.screens.MapEditorScreen;
import com.team5.seawar.screens.playstates.State;
import com.team5.seawar.utils.Assets;

/**
 * Created with love by Team 5
 */

public class MapEditionState implements State {

    private MapEditorScreen mapEditorScreen;
    private static int lightHouseNb = 3;

    public MapEditionState(MapEditorScreen mapEditorScreen) {
        this.mapEditorScreen = mapEditorScreen;
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
        if(Inputs.isPressed(Inputs.SELECT)){
            GlobalCam.getInstance().setSetZoomCamActive(false);
            mapEditorScreen.setCamState(GlobalCam.getInstance());
            mapEditorScreen.getMap().init();
            mapEditorScreen.changeState(new SizeEditionState(mapEditorScreen));
        }
        if (Inputs.isPressed(Inputs.START)){
            mapEditorScreen.changeState(new ShipEditionState(mapEditorScreen));
        }
        if (Inputs.isPressed(Inputs.LEFT) && mapEditorScreen.position.x>0){
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
        if(Inputs.isPressed(Inputs.X)){
            if(lightHouseNb > 0){
                lightHouseNb --;
                mapEditorScreen.getCurrentCase().setElement(new Element(Element.Type.LIGHTHOUSE,mapEditorScreen.position.x,mapEditorScreen.position.y));
            }

        }
        if(Inputs.isPressed(Inputs.B)){
            if(mapEditorScreen.getCurrentCase().getElement().getType() == Element.Type.LIGHTHOUSE){
                lightHouseNb ++;
            }
            mapEditorScreen.getCurrentCase().setElement(new Element(Element.Type.WATER,mapEditorScreen.position.x,mapEditorScreen.position.y));
        }
        if (Inputs.isPressed(Inputs.Y)){
            if(mapEditorScreen.getCurrentCase().getElement().getType() == Element.Type.LIGHTHOUSE){
                lightHouseNb ++;
            }
            mapEditorScreen.getCurrentCase().setElement(new Element(Element.Type.VOID,mapEditorScreen.position.x,mapEditorScreen.position.y));
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
        mapEditorScreen.getBatch().draw(Assets.getInstance().getTexture("UI/MapEditionState.png"), 880, 40, 448/2, 725/2);
    }
}
