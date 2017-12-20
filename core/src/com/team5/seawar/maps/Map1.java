package com.team5.seawar.maps;


import com.team5.seawar.objects.Element;

public class Map1 extends Map {

    public Map1(){
        super(13, 11);
        for (int i=0; i<colonne; i++){
            for (int j=0; j<ligne; j++){
                tab[i][j]= new Element(Element.Type.WATER, i, j);
            }
        }
        tab[0][0]= new Element(Element.Type.LIGHTHOUSE, 0, 0);
        tab[12][10]= new Element(Element.Type.LIGHTHOUSE, 12, 10);
        tab[6][5]= new Element(Element.Type.LIGHTHOUSE, 6, 5);

        tab[3][6]= new Element(Element.Type.DIRT, 3,6);
        tab[3][7]= new Element(Element.Type.DIRT, 3, 7);
        tab[4][7]= new Element(Element.Type.DIRT, 4, 7);
        tab[8][3]= new Element(Element.Type.DIRT, 8, 3);
        tab[9][2]= new Element(Element.Type.DIRT, 9, 2);
        tab[9][3]= new Element(Element.Type.DIRT, 9, 3);

    }

}
