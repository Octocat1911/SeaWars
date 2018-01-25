package com.team5.seawar.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.team5.seawar.maps.Map;
import com.team5.seawar.ship.Ship;
import com.team5.seawar.ship.ShipPosition;

public class CanonCalcul {

    //Pour canon

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

    //Pour déplacement bateau

    public static Array<Vector2> reachableATM(Vector2 position, ShipPosition.Orientation orientation){
        Array<Vector2> l = new Array<Vector2>();
        if(position.x % 2 == 0){
            if(orientation == ShipPosition.Orientation.TOP){
                Vector2 v1 = new Vector2(0,1);
                Vector2 v2 = new Vector2(1,0);
                Vector2 v3 = new Vector2(-1,0);
                l.add(v1);
                l.add(v2);
                l.add(v3);
            }
            if(orientation == ShipPosition.Orientation.BOTTOM){
                Vector2 v1 = new Vector2(0,-1);
                Vector2 v2 = new Vector2(-1,-1);
                Vector2 v3 = new Vector2(1,-1);
                l.add(v1);
                l.add(v2);
                l.add(v3);
            }
            if(orientation == ShipPosition.Orientation.TOP_RIGHT){
                Vector2 v1 = new Vector2(0,1);
                Vector2 v2 = new Vector2(1,0);
                Vector2 v3 = new Vector2(1,-1);
                l.add(v1);
                l.add(v2);
                l.add(v3);
            }
            if(orientation == ShipPosition.Orientation.BOTTOM_RIGHT){
                Vector2 v1 = new Vector2(1,0);
                Vector2 v2 = new Vector2(1,-1);
                Vector2 v3 = new Vector2(0,-1);
                l.add(v1);
                l.add(v2);
                l.add(v3);
            }
            if(orientation == ShipPosition.Orientation.TOP_LEFT){
                Vector2 v1 = new Vector2(0,1);
                Vector2 v2 = new Vector2(-1,0);
                Vector2 v3 = new Vector2(-1,-1);
                l.add(v1);
                l.add(v2);
                l.add(v3);
            }
            if(orientation == ShipPosition.Orientation.BOTTOM_LEFT){
                Vector2 v1 = new Vector2(-1,0);
                Vector2 v2 = new Vector2(-1,-1);
                Vector2 v3 = new Vector2(0,-1);
                l.add(v1);
                l.add(v2);
                l.add(v3);
            }
        }else{
            if(orientation == ShipPosition.Orientation.TOP){
                Vector2 v1 = new Vector2(0,1);
                Vector2 v2 = new Vector2(1,1);
                Vector2 v3 = new Vector2(-1,1);
                l.add(v1);
                l.add(v2);
                l.add(v3);
            }
            if(orientation == ShipPosition.Orientation.BOTTOM){
                Vector2 v1 = new Vector2(0,-1);
                Vector2 v2 = new Vector2(-1,0);
                Vector2 v3 = new Vector2(1,0);
                l.add(v1);
                l.add(v2);
                l.add(v3);
            }
            if(orientation == ShipPosition.Orientation.TOP_RIGHT){
                Vector2 v1 = new Vector2(0,1);
                Vector2 v2 = new Vector2(1,1);
                Vector2 v3 = new Vector2(1,0);
                l.add(v1);
                l.add(v2);
                l.add(v3);

            }
            if(orientation == ShipPosition.Orientation.BOTTOM_RIGHT){
                Vector2 v1 = new Vector2(1,1);
                Vector2 v2 = new Vector2(1,0);
                Vector2 v3 = new Vector2(0,-1);
                l.add(v1);
                l.add(v2);
                l.add(v3);

            }
            if(orientation == ShipPosition.Orientation.TOP_LEFT){
                Vector2 v1 = new Vector2(0,1);
                Vector2 v2 = new Vector2(-1,1);
                Vector2 v3 = new Vector2(-1,0);
                l.add(v1);
                l.add(v2);
                l.add(v3);
            }
            if(orientation == ShipPosition.Orientation.BOTTOM_LEFT){
                Vector2 v1 = new Vector2(-1,1);
                Vector2 v2 = new Vector2(-1,0);
                Vector2 v3 = new Vector2(0,-1);
                l.add(v1);
                l.add(v2);
                l.add(v3);
            }
        }
        return l;
    }

    public static ShipPosition.Orientation orientation(Vector2 depart, Vector2 arrive){
        ShipPosition.Orientation orientation = ShipPosition.Orientation.TOP;
        float deltaX = arrive.x - depart.x;
        float deltaY = arrive.y - depart.y;
        if (arrive.x%2==0){
            if (deltaX == 0 && deltaY == 1)
                orientation = ShipPosition.Orientation.TOP;
            if (deltaX == 1 && deltaY == 1)
                orientation = ShipPosition.Orientation.TOP_RIGHT;
            if (deltaX == 1 && deltaY == 0)
                orientation = ShipPosition.Orientation.BOTTOM_RIGHT;
            if (deltaX == 0 && deltaY == -1)
                orientation = ShipPosition.Orientation.BOTTOM;
            if (deltaX == -1 && deltaY == 0)
                orientation = ShipPosition.Orientation.BOTTOM_LEFT;
            if (deltaX == -1 && deltaY == 1)
                orientation = ShipPosition.Orientation.TOP_LEFT;
        } else {
            if (deltaX == 0 && deltaY == 1)
                orientation = ShipPosition.Orientation.TOP;
            if (deltaX == 1 && deltaY == 0)
                orientation = ShipPosition.Orientation.TOP_RIGHT;
            if (deltaX == 1 && deltaY == -1)
                orientation = ShipPosition.Orientation.BOTTOM_RIGHT;
            if (deltaX == 0 && deltaY == -1)
                orientation = ShipPosition.Orientation.BOTTOM;
            if (deltaX == -1 && deltaY == -1)
                orientation = ShipPosition.Orientation.BOTTOM_LEFT;
            if (deltaX == -1 && deltaY == 0)
                orientation = ShipPosition.Orientation.TOP_LEFT;
        }
        return orientation;
    }

    public static Array<Vector2> reachablePosition(Ship ship, Map map){
        int nbmove = ship.getMovements();
        Array<Vector2> l = new Array<Vector2>();
        Array<Vector2> res = new Array<Vector2>();
        Array<Vector2> i = new Array<Vector2>();//A tester
        Array<Vector2> i2 = new Array<Vector2>();//Origine de i
        Array<Vector2> i3 = new Array<Vector2>();//Origine
        Array<Vector2> i4 = new Array<Vector2>();//Obtenu
        Array<Vector2> troisPremiers;
        Array<Vector2>tmp;

        l.add(new Vector2(ship.getShipPosition().getColonne(),ship.getShipPosition().getLigne()));
        troisPremiers = reachableATM(l.get(0),ship.getPosition().getOrientation());

        for(int j=0;j<troisPremiers.size;j++){
            Vector2 c = new Vector2(troisPremiers.get(j).add(l.get(0)));
            if(c.x >= 0 && c.x < map.getColonne() && c.y >= 0 && c.y < map.getLigne() && map.getCase((int)c.x, (int)c.y).isNavigable()) {
                l.add(c);
                i.add(c);
                i2.add(l.get(0));
            }
        }

        for(int j=1;j<nbmove;j++){
            for (int p=0;p<i.size;p++) {
                Vector2 a = new Vector2(i2.get(p));//Origine
                Vector2 b = new Vector2(i.get(p));//Destination
                ShipPosition.Orientation orientation = orientation(a, b);
                tmp = reachableATM(i.get(p),orientation);
                for(int q=0;q<tmp.size;q++){
                    Vector2 c = new Vector2(tmp.get(q).add(i.get(p)));
                    if (c.x >= 0 && c.x < map.getColonne() && c.y >= 0 && c.y < map.getLigne() && map.getCase((int)c.x, (int)c.y).isNavigable()){
                        l.add(c);
                        i4.add(c);
                        i3.add(i.get(p));
                    }
                }
            }
            i=new Array<Vector2>(i4);
            i2=new Array<Vector2>(i3);
            i3.clear();
            i4.clear();
        }

        for(int j=0;j<l.size;j++){
            if(!res.contains(l.get(j),false)){
                res.add(l.get(j));
            }
        }


        res.removeIndex(0);
        for (Vector2 k:troisPremiers){
            res.removeValue(k,false);
        }

        return res;
    }
}
