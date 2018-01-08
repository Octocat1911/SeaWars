package com.team5.seawar.ship;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Canon {
    private Array<Vector2> range;
    private int damage;
    private int cooldown;


    public Canon(Array<Vector2> range, int damage, int cooldown){
        this.range = range;
        this.damage = damage;
        this.cooldown = cooldown;
    }

    public Canon(){

    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public Array<Vector2> getRange() {
        return range;
    }

    public void setRange(Array<Vector2> range) {
        this.range = range;
    }

}
