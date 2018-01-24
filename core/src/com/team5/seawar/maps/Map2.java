package com.team5.seawar.maps;

import com.team5.seawar.objects.Case;
import com.team5.seawar.objects.Element;
import com.team5.seawar.player.Player;
import com.team5.seawar.ship.Amiral;
import com.team5.seawar.ship.Fregate;
import com.team5.seawar.ship.Ship;
import com.team5.seawar.ship.ShipPosition;

public class Map2 extends Map {

    public Map2(){
        super(21, 21);
        for (int i=0; i<colonne; i++){
            for (int j=0; j<ligne; j++){
                tab[i][j]= new Case(Element.Type.WATER, i, j);
            }
        }
        int centre = 10;
        int i;
        int  k=5;
        while(k<10){
            for (int j=0; j<colonne; j++) {
                if(j%2==0){
                    for ( i = 0; i < centre - k; i++) {
                        tab[j][i] = new Case(Element.Type.VOID, j, i);
                    }
                    for (i = centre + 1; i < ligne; i++) {
                        if (i > centre + k) {
                            tab[j][i] = new Case(Element.Type.VOID, j, i);
                        }
                    }
                }else {
                    for (i = centre + 1; i < ligne; i++) {
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
                    for (i = centre + 1; i < ligne; i++) {
                        if (i > centre + k) {
                            tab[j][i] = new Case(Element.Type.VOID, j, i);
                        }
                    }
                }else {
                    for (i = centre + 1; i < ligne; i++) {
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
        tab[10][10] = new Case(Element.Type.DIRT, 10, 10);

        // min = 6 ; max = 14 ; k =2   ( min = 8 max 12 k 1 )

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

        player1 = new Player();
        player2 = new Player();

        player1.getShips().add(new Fregate(0,10, ShipPosition.Orientation.BOTTOM_RIGHT));
        player1.getShips().add(new Amiral(1,10, ShipPosition.Orientation.BOTTOM_RIGHT));
        player1.getShips().add(new Fregate(3, 10, ShipPosition.Orientation.BOTTOM));

        player2.getShips().add(new Amiral(12,0, ShipPosition.Orientation.TOP_LEFT));
        player2.getShips().add(new Fregate(12,1, ShipPosition.Orientation.TOP_LEFT));
        player2.getShips().add(new Fregate(10, 0, ShipPosition.Orientation.TOP));

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