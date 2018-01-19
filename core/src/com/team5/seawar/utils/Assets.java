package com.team5.seawar.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
    private AssetManager manager = new AssetManager();

    private static Assets instance = null;

    protected Assets(){

    }

    public static Assets getInstance() {
        if(instance == null) {
            instance = new Assets();
        }
        return instance;
    }

    public void load() {
        // MenuScreen
        manager.load("Menutextures/background.png", Texture.class);
        manager.load("Menutextures/play.png", Texture.class);
        manager.load("Menutextures/exit.png", Texture.class);
        manager.load("Menutextures/play2.png",Texture.class);

        // PlayScreen
        manager.load("Maptextures/hexPointeur.png", Texture.class);
        manager.load("Maptextures/hexPortee.png", Texture.class);
        manager.load("Maptextures/cible.png", Texture.class);

        manager.load("Maptextures/hexEau.png", Texture.class);
        manager.load("Maptextures/hexTerre.png", Texture.class);
        manager.load("Maptextures/hexPhare.png", Texture.class);
        manager.load("Maptextures/void.png", Texture.class);

        manager.load("Shiptextures/ShipH.png", Texture.class);
        manager.load("Shiptextures/ShipHD.png", Texture.class);
        manager.load("Shiptextures/ShipBD.png", Texture.class);
        manager.load("Shiptextures/ShipB.png", Texture.class);
        manager.load("Shiptextures/ShipBG.png", Texture.class);
        manager.load("Shiptextures/ShipHG.png", Texture.class);

        manager.load("Shiptextures/ShipH2.png", Texture.class);
        manager.load("Shiptextures/ShipHD2.png", Texture.class);
        manager.load("Shiptextures/ShipBD2.png", Texture.class);
        manager.load("Shiptextures/ShipB2.png", Texture.class);
        manager.load("Shiptextures/ShipBG2.png", Texture.class);
        manager.load("Shiptextures/ShipHG2.png", Texture.class);

    }

    public Texture getTexture(String fileName){
        return manager.get(fileName, Texture.class);
    }

    public void finishLoading(){
        manager.finishLoading();
    }

    public void dispose(){
        manager.dispose();
    }
}
