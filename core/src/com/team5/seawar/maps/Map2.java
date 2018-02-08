package com.team5.seawar.maps;


import com.team5.seawar.objects.Case;
import com.team5.seawar.objects.Element;
import com.team5.seawar.player.Player;
import com.team5.seawar.ship.Ship;
import com.team5.seawar.ship.ShipPosition;

/**
 * Created with love by Team 5
 */

public class Map2 extends Map {

    public Map2(){
        super(21, 21);
        for (int i=0; i<getColonne(); i++){
            for (int j=0; j<getLigne(); j++){
                tab[i][j]= new Case(Element.Type.WATER, i, j);
            }
        }
        int centre = 10;
        int i;
        int  k=5;
        while(k<10){
            for (int j=0; j<getColonne(); j++) {
                if(j%2==0){
                    for ( i = 0; i < centre - k; i++) {
                        tab[j][i] = new Case(Element.Type.VOID, j, i);
                    }
                    for (i = centre + 1; i < getLigne(); i++) {
                        if (i > centre + k) {
                            tab[j][i] = new Case(Element.Type.VOID, j, i);
                        }
                    }
                }else {
                    for (i = centre + 1; i < getLigne(); i++) {
                        if (i > centre + k) {
                            tab[j][i] = new Case(Element.Type.VOID, j, i);
                        }
                    }
                    k++;
                    for ( i = 0; i < centre - k; i++) {
                        tab[j][i] = new Case(Element.Type.VOID, j, i);
                    }
                }
            }
        }
        k = 5 ;
        while(k<10){
            for (int j=20; j>0; j--) {
                if(j%2==0){
                    for ( i = 0; i < centre - k; i++) {
                        tab[j][i] = new Case(Element.Type.VOID, j, i);
                    }
                    for (i = centre + 1; i < getLigne(); i++) {
                        if (i > centre + k) {
                            tab[j][i] = new Case(Element.Type.VOID, j, i);
                        }
                    }
                }else {

                    for (i = centre + 1; i < getLigne(); i++) {
                        if (i > centre + k) {
                            tab[j][i] = new Case(Element.Type.VOID, j, i);
                        }
                    }
                    k++;
                    for ( i = 0; i < centre - k; i++) {
                        tab[j][i] = new Case(Element.Type.VOID, j, i);
                    }
                }
            }
        }
        int min = 6;
        int max = 14;
        k = 2;
        centre =10;
        for (int j=min; j<=10; j++) {

            if(j%2==0){
                for ( i = centre-k; i <=centre + k; i++) {
                    tab[j][i] = new Case(Element.Type.DIRT, j, i);
                }

            }else {

                for (i = centre - k-1; i <=centre + k ; i++) {

                    tab[j][i] = new Case(Element.Type.DIRT, j, i);
                }
                k++;
            }
        }
        k=2;
        for (int j=max; j>10; j--) {

            if (j % 2 == 0) {
                for (i = centre - k; i <= centre + k; i++) {
                    tab[j][i] = new Case(Element.Type.DIRT, j, i);
                }

            } else {

                for (i = centre - k - 1; i <= centre + k; i++) {

                    tab[j][i] = new Case(Element.Type.DIRT, j, i);
                }
                k++;
            }
        }
        player1 = new Player();
        player2 = new Player();
        player1.getShips().add(new Ship(10,20, ShipPosition.Orientation.BOTTOM,Ship.Type.AMIRAL));
        player1.getShips().add(new Ship(9,19, ShipPosition.Orientation.BOTTOM,Ship.Type.FREGATE));
        player1.getShips().add(new Ship(10, 19, ShipPosition.Orientation.BOTTOM,Ship.Type.FREGATE));
        player1.getShips().add(new Ship(11, 19, ShipPosition.Orientation.BOTTOM,Ship.Type.FREGATE));
        player1.getShips().add(new Ship(0,15, ShipPosition.Orientation.BOTTOM_RIGHT,Ship.Type.AMIRAL));
        player1.getShips().add(new Ship(1, 15, ShipPosition.Orientation.BOTTOM_RIGHT,Ship.Type.FREGATE));
        player1.getShips().add(new Ship(1, 14, ShipPosition.Orientation.BOTTOM_RIGHT,Ship.Type.FREGATE));
        player1.getShips().add(new Ship(0, 14, ShipPosition.Orientation.BOTTOM_RIGHT,Ship.Type.FREGATE));
        player1.getShips().add(new Ship(20,15, ShipPosition.Orientation.BOTTOM_LEFT,Ship.Type.AMIRAL));
        player1.getShips().add(new Ship(19, 15, ShipPosition.Orientation.BOTTOM_LEFT,Ship.Type.FREGATE));
        player1.getShips().add(new Ship(19, 14, ShipPosition.Orientation.BOTTOM_LEFT,Ship.Type.FREGATE));
        player1.getShips().add(new Ship(20, 14, ShipPosition.Orientation.BOTTOM_LEFT,Ship.Type.FREGATE));
        player2.getShips().add(new Ship(10,0, ShipPosition.Orientation.TOP,Ship.Type.AMIRAL));
        player2.getShips().add(new Ship(9,0, ShipPosition.Orientation.TOP,Ship.Type.FREGATE));
        player2.getShips().add(new Ship(11, 0, ShipPosition.Orientation.TOP,Ship.Type.FREGATE));
        player2.getShips().add(new Ship(10, 1, ShipPosition.Orientation.TOP,Ship.Type.FREGATE));
        player2.getShips().add(new Ship(0,5, ShipPosition.Orientation.TOP_RIGHT,Ship.Type.AMIRAL));
        player2.getShips().add(new Ship(1,5, ShipPosition.Orientation.TOP_RIGHT,Ship.Type.FREGATE));
        player2.getShips().add(new Ship(1,4, ShipPosition.Orientation.TOP_RIGHT,Ship.Type.FREGATE));
        player2.getShips().add(new Ship(0,6, ShipPosition.Orientation.TOP_RIGHT,Ship.Type.FREGATE));
        player2.getShips().add(new Ship(20,5, ShipPosition.Orientation.TOP_LEFT,Ship.Type.AMIRAL));
        player2.getShips().add(new Ship(19,5, ShipPosition.Orientation.TOP_LEFT,Ship.Type.FREGATE));
        player2.getShips().add(new Ship(19,4, ShipPosition.Orientation.TOP_LEFT,Ship.Type.FREGATE));
        player2.getShips().add(new Ship(20,6, ShipPosition.Orientation.TOP_LEFT,Ship.Type.FREGATE));
        for (Ship ship : player1.getShips()){
            getCase(ship.getPosition().getColonne(), ship.getPosition().getLigne()).addShip(ship);
            ship.setJoueur(1);
        }

        for (Ship ship : player2.getShips()){
            getCase(ship.getPosition().getColonne(), ship.getPosition().getLigne()).addShip(ship);
            ship.setJoueur(2);
        }
        majNbLighthouses();
    }
}