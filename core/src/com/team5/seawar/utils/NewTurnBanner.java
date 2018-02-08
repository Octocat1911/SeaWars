package com.team5.seawar.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team5.seawar.game.GameApp;

/**
 * Created with love by Team 5
 */

public class NewTurnBanner {

    private Texture player;
    private Texture play;

    private float playerPosition;
    private float playPosition;

    private float speed;
    private float maxSpeed = 4000f;

    private boolean drawing = false;

    public NewTurnBanner(){
    }

    public void setTextures(Texture player, Texture play){
        this.player = player;
        this.play = play;
    }

    public void update(float dt){
        if (drawing){
            if (Math.abs(playerPosition + GameApp.WIDTH/2 - GameApp.WIDTH/2) > 100){
                speed = maxSpeed;
            } else {
                speed = 200;
            }
            playerPosition += speed * dt;
            playPosition -= speed *dt;
            if (playerPosition > GameApp.WIDTH && playPosition + GameApp.WIDTH < 0){
                drawing = false;
            }
        }
    }

    public void draw(SpriteBatch batch){
        if (drawing){
            batch.draw(player, playerPosition, 0);
            batch.draw(play, playPosition, 0);
        }
    }

    public void start(){
        playerPosition = -GameApp.WIDTH+300;
        playPosition = GameApp.WIDTH-300;
        drawing = true;
    }

}
