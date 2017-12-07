package com.team5.seawar.inputHandler;

public class Touche {
    int valeur;
    public Touche (int bouton){
        valeur = bouton;
    }

    public void setValeur(int bouton) {
        valeur = bouton;
    }

    public int getValeur() {
        return valeur;
    }
}
