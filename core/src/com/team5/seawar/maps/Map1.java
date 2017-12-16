package com.team5.seawar.maps;


import com.team5.seawar.objects.Water;
import com.team5.seawar.objects.Lighthouse;
import com.team5.seawar.objects.Dirt;

public class Map1 extends Map {

    public Map1(){
        super(13, 11);
        for (int i=0; i<colonne; i++){
            for (int j=0; j<ligne; j++){
                tab[i][j]= Water.getInstance();
            }
        }
        tab[0][0]= new Lighthouse();
        tab[12][10]= new Lighthouse();
        tab[6][5]= new Lighthouse();

        tab[3][6]= Dirt.getInstance();
        tab[3][7]= Dirt.getInstance();
        tab[4][7]= Dirt.getInstance();
        tab[8][3]= Dirt.getInstance();
        tab[9][2]= Dirt.getInstance();
        tab[9][3]= Dirt.getInstance();

    }

}
