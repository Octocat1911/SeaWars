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
        manager.load("hexEau.png", Texture.class);
        manager.load("hexTerre.png", Texture.class);
        manager.load("hexPhare.png", Texture.class);
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
