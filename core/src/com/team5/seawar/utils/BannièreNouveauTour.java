package com.team5.seawar.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team5.seawar.game.GameApp;

public class BannièreNouveauTour {

    private Texture joueur;
    private Texture aToiDeJouer;

    private float positionJoueur;
    private float positionAToiDeJouer;

    private float speed;
    private float maxSpeed = 4000f;

    private boolean drawing = false;

    public BannièreNouveauTour(){
    }

    public void setTextures(Texture joueur, Texture aToiDeJouer){
        this.joueur = joueur;
        this.aToiDeJouer = aToiDeJouer;
    }

    public void update(float dt){
        if (drawing){
            if (Math.abs(positionJoueur + GameApp.WIDTH/2 - GameApp.WIDTH/2) > 150){
                speed = maxSpeed;
            } else {
                speed = 300;
            }
            positionJoueur += speed * dt;
            positionAToiDeJouer -= speed *dt;
            if (positionJoueur > GameApp.WIDTH && positionAToiDeJouer + GameApp.WIDTH < 0){
                drawing = false;
            }
        }
    }

    public void draw(SpriteBatch batch){
        if (drawing){
            batch.draw(joueur, positionJoueur, 0);
            batch.draw(aToiDeJouer, positionAToiDeJouer, 0);
        }
    }

    public void start(){
        positionJoueur = -GameApp.WIDTH+300;
        positionAToiDeJouer = GameApp.WIDTH-300;
        drawing = true;
    }

}
