package com.team5.seawar.screens.editorstates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;
import com.team5.seawar.cam.GlobalCam;
import com.team5.seawar.game.GameApp;
import com.team5.seawar.inputHandler.Inputs;
import com.team5.seawar.objects.Element;
import com.team5.seawar.screens.MapEditorScreen;
import com.team5.seawar.screens.MenuScreen;
import com.team5.seawar.screens.menustates.MenuState;
import com.team5.seawar.screens.playstates.State;
import com.team5.seawar.ship.Ship;
import com.team5.seawar.ship.ShipPosition;
import com.team5.seawar.utils.Assets;
import com.team5.seawar.utils.Save;

import static com.team5.seawar.screens.PlayScreen.position;

/**
 * Created with love by Team 5
 */

public class ShipEditionState implements State {

    private MapEditorScreen mapEditorScreen;

    public ShipEditionState(MapEditorScreen mapEditorScreen) {
        GlobalCam.getInstance().setSetZoomCamActive(true);
        this.mapEditorScreen = mapEditorScreen;
    }

    public void handleInput(float dt){
        if(Inputs.isPressed(Inputs.SELECT)) {
            mapEditorScreen.setCamState(GlobalCam.getInstance());
            mapEditorScreen.changeState(new MapEditionState(mapEditorScreen));
        }
        if (Inputs.isPressed(Inputs.START) && mapEditorScreen.getMap().getPlayer1().getShips().size > 0 && mapEditorScreen.getMap().getPlayer2().getShips().size > 0) {
            Json json = new Json();
            json.toJson(new Save(mapEditorScreen.getMap()), Gdx.files.local("map"+ (GameApp.NBMAP + 1) +".txt"));
            GameApp.NBMAP =+ 1;
            MenuState.init(MenuScreen.getInstance());
            mapEditorScreen.getGameApp().setScreen(MenuScreen.getInstance());
        }
        if (Inputs.isPressed(Inputs.LEFT) && position.x>0){
            position.x--;
        }
        if (Inputs.isPressed(Inputs.RIGHT) && position.x<mapEditorScreen.getMap().getColonne()-1){
            position.x++;
        }
        if (Inputs.isPressed(Inputs.UP) && position.y<mapEditorScreen.getMap().getLigne()-1){
            position.y++;
        }
        if (Inputs.isPressed(Inputs.DOWN) && position.y>0){
            position.y--;
        }
        if(mapEditorScreen.getMap().getCase((int)mapEditorScreen.getPosition().x, (int)mapEditorScreen.getPosition().y).getElement().getType() != Element.Type.VOID && mapEditorScreen.getMap().getCase((int)mapEditorScreen.getPosition().x, (int)mapEditorScreen.getPosition().y).getElement().getType() != Element.Type.DIRT) {
            if (Inputs.isPressed(Inputs.A)) {
                if (mapEditorScreen.getMap().getCase((int) mapEditorScreen.getPosition().x, (int) mapEditorScreen.getPosition().y).getShip() == null) {
                    mapEditorScreen.getMap().getPlayer1().getShips().add(new Ship((int) position.x, (int) position.y, ShipPosition.Orientation.TOP, Ship.Type.AMIRAL));
                } else {
                    mapEditorScreen.getMap().getPlayer1().getShips().removeValue(mapEditorScreen.getMap().getCase((int)position.x, (int)position.y).getShip(), true);
                    mapEditorScreen.getMap().getPlayer2().getShips().removeValue(mapEditorScreen.getMap().getCase((int)position.x, (int)position.y).getShip(), true);
                    mapEditorScreen.getMap().getCase((int) mapEditorScreen.getPosition().x, (int) mapEditorScreen.getPosition().y).deleteShip();
                }
                mapEditorScreen.getMap().load();
            }
            if (Inputs.isPressed(Inputs.B)) {
                if (mapEditorScreen.getMap().getCase((int) mapEditorScreen.getPosition().x, (int) mapEditorScreen.getPosition().y).getShip() == null) {
                    mapEditorScreen.getMap().getPlayer1().getShips().add(new Ship((int) position.x, (int) position.y, ShipPosition.Orientation.TOP, Ship.Type.FREGATE));
                } else {
                    mapEditorScreen.getMap().getPlayer1().getShips().removeValue(mapEditorScreen.getMap().getCase((int)position.x, (int)position.y).getShip(), true);
                    mapEditorScreen.getMap().getPlayer2().getShips().removeValue(mapEditorScreen.getMap().getCase((int)position.x, (int)position.y).getShip(), true);
                    mapEditorScreen.getMap().getCase((int) mapEditorScreen.getPosition().x, (int) mapEditorScreen.getPosition().y).deleteShip();
                }
                mapEditorScreen.getMap().load();
            }
            if (Inputs.isPressed(Inputs.X)) {
                if (mapEditorScreen.getMap().getCase((int) mapEditorScreen.getPosition().x, (int) mapEditorScreen.getPosition().y).getShip() == null) {
                    mapEditorScreen.getMap().getPlayer2().getShips().add(new Ship((int) position.x, (int) position.y, ShipPosition.Orientation.TOP, Ship.Type.AMIRAL));
                } else {
                    mapEditorScreen.getMap().getPlayer1().getShips().removeValue(mapEditorScreen.getMap().getCase((int)position.x, (int)position.y).getShip(), true);
                    mapEditorScreen.getMap().getPlayer2().getShips().removeValue(mapEditorScreen.getMap().getCase((int)position.x, (int)position.y).getShip(), true);
                    mapEditorScreen.getMap().getCase((int) mapEditorScreen.getPosition().x, (int) mapEditorScreen.getPosition().y).deleteShip();
                }
                mapEditorScreen.getMap().load();
            }
            if (Inputs.isPressed(Inputs.Y)) {
                if (mapEditorScreen.getMap().getCase((int) mapEditorScreen.getPosition().x, (int) mapEditorScreen.getPosition().y).getShip() == null) {
                    mapEditorScreen.getMap().getPlayer2().getShips().add(new Ship((int) position.x, (int) position.y, ShipPosition.Orientation.TOP, Ship.Type.FREGATE));
                } else {
                    mapEditorScreen.getMap().getPlayer1().getShips().removeValue(mapEditorScreen.getMap().getCase((int)position.x, (int)position.y).getShip(), true);
                    mapEditorScreen.getMap().getPlayer2().getShips().removeValue(mapEditorScreen.getMap().getCase((int)position.x, (int)position.y).getShip(), true);
                    mapEditorScreen.getMap().getCase((int) mapEditorScreen.getPosition().x, (int) mapEditorScreen.getPosition().y).deleteShip();
                }
                mapEditorScreen.getMap().load();
            }
            if (Inputs.isPressed(Inputs.L1) && mapEditorScreen.getMap().getCase((int) mapEditorScreen.getPosition().x, (int) mapEditorScreen.getPosition().y).getShip() != null) {
                mapEditorScreen.getMap().getCase((int) mapEditorScreen.getPosition().x, (int) mapEditorScreen.getPosition().y).getShip().rotateLeft();
                mapEditorScreen.getMap().getCase((int) mapEditorScreen.getPosition().x, (int) mapEditorScreen.getPosition().y).getShip().setHasFinished(false);
            }
            if (Inputs.isPressed(Inputs.R1) && mapEditorScreen.getMap().getCase((int) mapEditorScreen.getPosition().x, (int) mapEditorScreen.getPosition().y).getShip() != null) {
                mapEditorScreen.getMap().getCase((int) mapEditorScreen.getPosition().x, (int) mapEditorScreen.getPosition().y).getShip().rotateRight();
                mapEditorScreen.getMap().getCase((int) mapEditorScreen.getPosition().x, (int) mapEditorScreen.getPosition().y).getShip().setHasFinished(false);
            }
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
        mapEditorScreen.renderTexture(Assets.getInstance().getTexture("Maptextures/hexPointeur.png"), position.x, position.y);
    }

    public void drawUI(){
        mapEditorScreen.getBatch().draw(Assets.getInstance().getTexture("UI/shipCreation.png"), 870, 50, 965/3, 1078/3);
    }
}
