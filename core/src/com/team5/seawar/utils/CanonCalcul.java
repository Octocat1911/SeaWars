package com.team5.seawar.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.team5.seawar.ship.ShipPosition;

public class CanonCalcul {

    public static Vector2 rotation(Vector2 pos, ShipPosition.Orientation orientation, boolean impaire){
        float xx, yy, zz;
        xx = pos.x;
        if (!impaire) {
            zz = pos.y - (pos.x - Math.abs(pos.x % 2)) / 2;
        } else {
            zz = pos.y - (pos.x + Math.abs(pos.x % 2)) / 2;
        }
        yy = -xx-zz;
        Vector3 pos3d = new Vector3(xx,yy,zz);

        int nbRotation = 0;
        switch (orientation){
            case TOP:
                nbRotation = 0;
                break;
            case TOP_RIGHT:
                nbRotation = 1;
                break;
            case BOTTOM_RIGHT:
                nbRotation = 2;
                break;
            case BOTTOM:
                nbRotation = 3;
                break;
            case BOTTOM_LEFT:
                nbRotation = 4;
                break;
            case TOP_LEFT:
                nbRotation = 5;
                break;
        }
        for (int i = 0; i < nbRotation; i++){
            pos3d = rotation3D(pos3d);
        }
        if (!impaire) {
            return new Vector2(pos3d.x, pos3d.z + (pos3d.x - Math.abs(pos3d.x % 2)) / 2);
        } else {
            return new Vector2(pos3d.x, pos3d.z + (pos3d.x + Math.abs(pos3d.x % 2)) / 2);
        }
    }

    public static Vector3 rotation3D(Vector3 pos){
        return new Vector3(-pos.y, -pos.z, -pos.x);
    }

    public static Vector2 even2odd(Vector2 vector2){
        if (vector2.x%2 == 0){
            return vector2;
        } else {
            return new Vector2(vector2.x, vector2.y+1);
        }
    }

}
