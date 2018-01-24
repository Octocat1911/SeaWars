package com.team5.seawar.maps;


import com.team5.seawar.objects.Case;
import com.team5.seawar.objects.Element;
import com.team5.seawar.player.Player;
import com.team5.seawar.ship.Ship;
import com.team5.seawar.ship.ShipPosition;

public class Map1 extends Map {

    public Map1(){
        super(13, 11);
        for (int i=0; i<colonne; i++){
            for (int j=0; j<ligne; j++){
                tab[i][j]= new Case(Element.Type.WATER, i, j);
            }
        }
        tab[0][0]= new Case(Element.Type.LIGHTHOUSE, 0, 0);
        tab[12][10]= new Case(Element.Type.LIGHTHOUSE, 12, 10);
        tab[6][5]= new Case(Element.Type.LIGHTHOUSE, 6, 5);

        tab[3][6]= new Case(Element.Type.DIRT, 3,6);
        tab[3][7]= new Case(Element.Type.DIRT, 3, 7);
        tab[4][7]= new Case(Element.Type.DIRT, 4, 7);
        tab[8][3]= new Case(Element.Type.DIRT, 8, 3);
        tab[9][2]= new Case(Element.Type.DIRT, 9, 2);
        tab[9][3]= new Case(Element.Type.DIRT, 9, 3);

        player1 = new Player();
        player2 = new Player();

        player1.getShips().add(new Ship(0,10, ShipPosition.Orientation.BOTTOM_RIGHT, Ship.Type.FREGATE));
        player1.getShips().add(new Ship(1,10, ShipPosition.Orientation.BOTTOM_RIGHT, Ship.Type.AMIRAL));
        player1.getShips().add(new Ship(3, 10, ShipPosition.Orientation.BOTTOM, Ship.Type.FREGATE));

        player2.getShips().add(new Ship(12,0, ShipPosition.Orientation.TOP_LEFT, Ship.Type.AMIRAL));
        player2.getShips().add(new Ship(12,1, ShipPosition.Orientation.TOP_LEFT, Ship.Type.FREGATE));
        player2.getShips().add(new Ship(10, 0, ShipPosition.Orientation.TOP, Ship.Type.FREGATE));

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
