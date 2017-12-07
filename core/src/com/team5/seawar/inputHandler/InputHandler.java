package com.team5.seawar.inputHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class InputHandler {

    private static InputHandler uniqueInstance;
    private static Touche HAUT = new Touche(Keys.Z);
    private static Touche BAS = new Touche(Keys.S);
    private static Touche GAUCHE = new Touche(Keys.Q);
    private static Touche DROITE = new Touche(Keys.D);
    private static Touche START = new Touche(Keys.ESCAPE);
    private static Touche  SELECT = new Touche (Keys.CONTROL_LEFT);
    private static Touche  A = new Touche(Keys.I);
    private static Touche B = new Touche(Keys.J);
    private static Touche  X = new Touche(Keys.K);
    private static Touche  Y = new Touche(Keys.L);
    private static Touche  CLIC_GAUCHE = new Touche(Buttons.LEFT);
    private static Touche  CLIC_DROITE = new Touche(Buttons.RIGHT);
    private static List<Touche> L = new ArrayList<Touche>(Arrays.asList(HAUT,BAS,GAUCHE,DROITE,START,SELECT,A,B,X,Y,CLIC_DROITE,CLIC_GAUCHE));
    private static Touche HAUT_2 = new Touche(Keys.Z);
    private static Touche BAS_2 = new Touche(Keys.S);
    private static Touche GAUCHE_2 = new Touche(Keys.Q);
    private static Touche DROITE_2 = new Touche(Keys.D);
    private static Touche START_2 = new Touche(Keys.ESCAPE);
    private static Touche  SELECT_2 = new Touche (Keys.CONTROL_LEFT);
    private static Touche  A_2 = new Touche(Keys.I);
    private static Touche B_2 = new Touche(Keys.J);
    private static Touche  X_2 = new Touche(Keys.K);
    private static Touche  Y_2 = new Touche(Keys.L);
    private static Touche  CLIC_GAUCHE_2 = new Touche(Buttons.LEFT);
    private static Touche  CLIC_DROITE_2 = new Touche(Buttons.RIGHT);
    private static List<Touche> L_2 = new ArrayList<Touche>(Arrays.asList(HAUT_2,BAS_2,GAUCHE_2,DROITE_2,START_2,SELECT_2,A_2,B_2,X_2,Y_2,CLIC_DROITE_2,CLIC_GAUCHE_2));


    private InputHandler(){
    }
    public static InputHandler getInstance(){
        if(uniqueInstance==null){
            uniqueInstance=new InputHandler();
        }
        return uniqueInstance;
    }

    public String toString(int bouton){
        return Input.Keys.toString(bouton);
    }

    public boolean isPressed(int bouton) {
        return Gdx.input.isKeyPressed(bouton);
    }

    public boolean isJustPressed(int bouton) {
        return Gdx.input.isKeyJustPressed(bouton);
    }
    /*
    public boolean isReleased(int bouton) {
        return Gdx.input.
    }
*/
    public boolean isMousePressed(int toucheSouris) {
        return Gdx.input.isButtonPressed(toucheSouris);
    }

    public void setHaut(int bouton){
        check(bouton);
        HAUT.setValeur(bouton);
    }

    public void setBas(int bouton){
        check(bouton);
        BAS.setValeur(bouton);
    }

    public void setGAUCHE(int bouton) {
        check(bouton);
        GAUCHE.setValeur(bouton);
    }

    public void setDROITE(int bouton) {
        check(bouton);
        DROITE.setValeur(bouton);
    }

    public void setSTART(int bouton) {
        check(bouton);
        START.setValeur(bouton);
    }

    public void setSELECT(int bouton) {
        check(bouton);
        SELECT.setValeur(bouton);
    }

    public void setA(int bouton) {
        check(bouton);
        A.setValeur(bouton);
    }

    public void setB(int bouton) {
        check(bouton);
        B.setValeur(bouton);
    }

    public void setX(int bouton) {
        check(bouton);
        X.setValeur(bouton);
    }

    public void setY(int bouton) {
        check(bouton);
        Y.setValeur(bouton);
    }

    public void setClicGauche(int bouton) {
        check(bouton);
        CLIC_GAUCHE.setValeur(bouton);
    }

    public void setClicDroite(int bouton) {
        check(bouton);
        CLIC_DROITE.setValeur(bouton);
    }

    public void setHaut2(int bouton){
        check(bouton);
        HAUT_2.setValeur(bouton);
    }

    public void setBas2(int bouton){
        check(bouton);
        BAS_2.setValeur(bouton);
    }

    public void setGauche2(int bouton) {
        check(bouton);
        GAUCHE_2.setValeur(bouton);
    }

    public void setDroite2(int bouton) {
        check(bouton);
        DROITE_2.setValeur(bouton);
    }

    public void setStart2(int bouton) {
        check(bouton);
        START_2.setValeur(bouton);
    }

    public void setSelect2(int bouton) {
        check(bouton);
        SELECT_2.setValeur(bouton);
    }

    public void setA2(int bouton) {
        check(bouton);
        A_2.setValeur(bouton);
    }

    public void setB2(int bouton) {
        check(bouton);
        B_2.setValeur(bouton);
    }

    public void setX2(int bouton) {
        check(bouton);
        X_2.setValeur(bouton);
    }

    public void setY2(int bouton) {
        check(bouton);
        Y_2.setValeur(bouton);
    }

    public void setClicGauche2(int bouton) {
        check(bouton);
        CLIC_GAUCHE_2.setValeur(bouton);
    }

    public void setClicDroite2(int bouton) {
        check(bouton);
        CLIC_DROITE_2.setValeur(bouton);
    }

    private void check(int bouton){
        for(int i=0;i<L.size();i++){
            if (L.get(i).getValeur()==bouton){
                L.get(i).setValeur(Keys.UNKNOWN);
            }
        }
    }

    public int getHaut(){
        return HAUT.getValeur();
    }
    public int getBas(){
        return BAS.getValeur();
    }
    public int getGauche(){
        return GAUCHE.getValeur();
    }
    public int getDroite(){
        return DROITE.getValeur();
    }
    public int getStart(){
        return START.getValeur();
    }
    public int getSelect(){
        return SELECT.getValeur();
    }
    public int getA(){
        return A.getValeur();
    }
    public int getB(){
        return B.getValeur();
    }
    public int getX(){
        return X.getValeur();
    }
    public int getY(){
        return Y.getValeur();
    }
    public int getClicGauche(){
        return CLIC_GAUCHE.getValeur();
    }
    public int getClicDroite(){
        return CLIC_DROITE.getValeur();
    }
    public int getHaut2(){
        return HAUT_2.getValeur();
    }
    public int getBas2(){
        return BAS_2.getValeur();
    }
    public int getGauche2(){
        return GAUCHE_2.getValeur();
    }
    public int getDroite2(){
        return DROITE_2.getValeur();
    }
    public int getStart2(){
        return START_2.getValeur();
    }
    public int getSelect2(){
        return SELECT_2.getValeur();
    }
    public int getA2(){
        return A_2.getValeur();
    }
    public int getB2(){
        return B_2.getValeur();
    }
    public int getX2(){
        return X_2.getValeur();
    }
    public int getY2(){
        return Y_2.getValeur();
    }
    public int getClicGauche2(){
        return CLIC_GAUCHE_2.getValeur();
    }
    public int getClicDroite2(){
        return CLIC_DROITE_2.getValeur();
    }

}
