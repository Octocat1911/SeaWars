package com.team5.seawar.screens.editorstates;

import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.objects.Element;
import com.team5.seawar.screens.MapEditorScreen;
import com.team5.seawar.screens.playstates.State;
import com.team5.seawar.utils.Assets;

public class MapEditionState implements State {

    private MapEditorScreen mapEditorScreen;
    private int lightHouseNb = 3;

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

    public void handleInput(float dt){
        if(Inputs.isPressed(Inputs.SELECT)){
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
    }

    @Override
    public void draw() {
        mapEditorScreen.getMap().draw(mapEditorScreen.getBatch());
        mapEditorScreen.renderTexture(Assets.getInstance().getTexture("Maptextures/hexPointeur.png"), mapEditorScreen.position.x, mapEditorScreen.position.y);
    }
}
