package com.team5.seawar.maps;


import com.team5.seawar.objects.Eau;
import com.team5.seawar.objects.Phare;
import com.team5.seawar.objects.Terre;

public class Map1 extends Map {

    public Map1(){
        super(13, 11);
        for (int i=0; i<colonne; i++){
            for (int j=0; j<ligne; j++){
                tab[i][j]= Eau.getInstance();
            }
        }
        tab[0][0]= new Phare();
        tab[12][10]= new Phare();
        tab[6][5]= new Phare();

        tab[3][6]= Terre.getInstance();
        tab[3][7]= Terre.getInstance();
        tab[4][7]= Terre.getInstance();
        tab[8][3]= Terre.getInstance();
        tab[9][2]= Terre.getInstance();
        tab[9][3]= Terre.getInstance();

    }

}
