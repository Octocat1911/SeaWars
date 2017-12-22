package com.team5.seawar.maps;

import com.team5.seawar.objects.Element;

public abstract class Map {

    protected int colonne;
    protected int ligne;
    protected Element tab[][];


    public Map(int colonne, int ligne) {
        this.ligne = ligne;
        this.colonne = colonne;
        tab = new Element[colonne][ligne];
    }

    public int getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public Element getElement(int j, int i) {
        return tab[j][i];
    }
}