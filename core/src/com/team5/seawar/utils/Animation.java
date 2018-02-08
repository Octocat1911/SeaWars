package com.team5.seawar.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created with love by Team 5
 */

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount; //number of frame
    private int frame; //current frame

    private float x;
    private float y;
    private float offsetX;
    private float offsetY;
    private float width;
    private float height;

    public Animation(TextureRegion region, int frameCount, float cycleTime, float width, float height, float offsetX, float offsetY){
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for (int i =0; i<frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = -1;
        this.width = width;
        this.height = height;
        currentFrameTime = 0;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public void update(float dt){
        if (frame >= 0) {
            currentFrameTime += dt;
            if (currentFrameTime > maxFrameTime) {
                frame++;
                currentFrameTime = 0;
            }
        }
        if (frame >= frameCount){
            frame = -1;
            currentFrameTime = 0;
        }
    }

    public void draw(SpriteBatch sb){
        if (frame>=0) {
            sb.draw(getFrame(), x + offsetX, y + offsetY, width, height);
        }
    }

    public void start(float x, float y){
        this.x = x;
        this.y = y;
        frame = 0;
    }

    public void startPosition(float x, float y){
        this.x = x * width *3/4;
        if (x%2==0){
            this.y = y * height;
        } else {
            this.y = y * height + height/2;
        }
        frame = 0;
    }

    public void init(){
        frame = -1;
        currentFrameTime = 0;
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}