package com.team5.seawar.inputHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team5.seawar.utils.Action2DSprite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputHandler {

    private static Vector3 mouse;
    private static InputHandler uniqueInstance;
    private static Input HAUT;
    private static Input BAS;
    private static Input GAUCHE;
    private static Input DROITE;
    private static Input START;
    private static Input SELECT;
    private static Input A;
    private static Input B;
    private static Input X;
    private static Input Y;
    private static Input CLIC_GAUCHE;
    private static Input CLIC_DROITE;
    private static List<Input> L;
    private static Input HAUT_2;
    private static Input BAS_2;
    private static Input GAUCHE_2;
    private static Input DROITE_2;
    private static Input START_2;
    private static Input SELECT_2;
    private static Input A_2;
    private static Input B_2;
    private static Input X_2;
    private static Input Y_2;
    private static Input CLIC_GAUCHE_2;
    private static Input CLIC_DROITE_2;
    private static List<Input> L_2;


    private InputHandler(){
        mouse = new Vector3();
        HAUT = new Input(Keys.Z);
        BAS = new Input(Keys.S);
        GAUCHE = new Input(Keys.Q);
        DROITE = new Input(Keys.D);
        START = new Input(Keys.ESCAPE);
        SELECT = new Input(Keys.CONTROL_LEFT);
        A = new Input(Keys.I);
        B = new Input(Keys.J);
        X = new Input(Keys.K);
        Y = new Input(Keys.L);
        CLIC_GAUCHE = new Input(Buttons.LEFT);
        CLIC_DROITE = new Input(Buttons.RIGHT);
        L = new ArrayList<Input>(Arrays.asList(HAUT,BAS,GAUCHE,DROITE,START,SELECT,A,B,X,Y,CLIC_DROITE,CLIC_GAUCHE));
        HAUT_2 = new Input(Keys.Z);
        BAS_2 = new Input(Keys.S);
        GAUCHE_2 = new Input(Keys.Q);
        DROITE_2 = new Input(Keys.D);
        START_2 = new Input(Keys.ESCAPE);
        SELECT_2 = new Input(Keys.CONTROL_LEFT);
        A_2 = new Input(Keys.I);
        B_2 = new Input(Keys.J);
        X_2 = new Input(Keys.K);
        Y_2 = new Input(Keys.L);
        CLIC_GAUCHE_2 = new Input(Buttons.LEFT);
        CLIC_DROITE_2 = new Input(Buttons.RIGHT);
        L_2 = new ArrayList<Input>(Arrays.asList(HAUT_2,BAS_2,GAUCHE_2,DROITE_2,START_2,SELECT_2,A_2,B_2,X_2,Y_2,CLIC_DROITE_2,CLIC_GAUCHE_2));
    }

    public static InputHandler getInstance(){
        if(uniqueInstance==null){
            uniqueInstance=new InputHandler();
        }
        return uniqueInstance;
    }

    public String toString(int bouton){
        return com.badlogic.gdx.Input.Keys.toString(bouton);
    }

    public void isTouched(Action2DSprite sprite, Camera camera, Viewport viewport){
        camera.unproject(mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
        if(sprite.getSprite().getBoundingRectangle().contains(mouse.x, mouse.y)){
            sprite.touchAction();
        }else {
            sprite.defaultAction();
        }
    }

    public void isJustClicked(Action2DSprite sprite, Camera camera, Viewport viewport){
        // a modifier !!!
        if (Gdx.input.justTouched()) {
            camera.unproject(mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0), viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight());
            if (sprite.getSprite().getBoundingRectangle().contains(mouse.x, mouse.y)){
                sprite.clickedAction();
            }
        }
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
        HAUT.setValue(bouton);
    }

    public void setBas(int bouton){
        check(bouton);
        BAS.setValue(bouton);
    }

    public void setGAUCHE(int bouton) {
        check(bouton);
        GAUCHE.setValue(bouton);
    }

    public void setDROITE(int bouton) {
        check(bouton);
        DROITE.setValue(bouton);
    }

    public void setSTART(int bouton) {
        check(bouton);
        START.setValue(bouton);
    }

    public void setSELECT(int bouton) {
        check(bouton);
        SELECT.setValue(bouton);
    }

    public void setA(int bouton) {
        check(bouton);
        A.setValue(bouton);
    }

    public void setB(int bouton) {
        check(bouton);
        B.setValue(bouton);
    }

    public void setX(int bouton) {
        check(bouton);
        X.setValue(bouton);
    }

    public void setY(int bouton) {
        check(bouton);
        Y.setValue(bouton);
    }

    public void setClicGauche(int bouton) {
        check(bouton);
        CLIC_GAUCHE.setValue(bouton);
    }

    public void setClicDroite(int bouton) {
        check(bouton);
        CLIC_DROITE.setValue(bouton);
    }

    public void setHaut2(int bouton){
        check(bouton);
        HAUT_2.setValue(bouton);
    }

    public void setBas2(int bouton){
        check(bouton);
        BAS_2.setValue(bouton);
    }

    public void setGauche2(int bouton) {
        check(bouton);
        GAUCHE_2.setValue(bouton);
    }

    public void setDroite2(int bouton) {
        check(bouton);
        DROITE_2.setValue(bouton);
    }

    public void setStart2(int bouton) {
        check(bouton);
        START_2.setValue(bouton);
    }

    public void setSelect2(int bouton) {
        check(bouton);
        SELECT_2.setValue(bouton);
    }

    public void setA2(int bouton) {
        check(bouton);
        A_2.setValue(bouton);
    }

    public void setB2(int bouton) {
        check(bouton);
        B_2.setValue(bouton);
    }

    public void setX2(int bouton) {
        check(bouton);
        X_2.setValue(bouton);
    }

    public void setY2(int bouton) {
        check(bouton);
        Y_2.setValue(bouton);
    }

    public void setClicGauche2(int bouton) {
        check(bouton);
        CLIC_GAUCHE_2.setValue(bouton);
    }

    public void setClicDroite2(int bouton) {
        check(bouton);
        CLIC_DROITE_2.setValue(bouton);
    }

    private void check(int bouton){
        for (Input input : L){
            if (input.getValue() == bouton){
                input.setValue(Keys.UNKNOWN);
            }
        }
    }

    public int getHaut(){
        return HAUT.getValue();
    }
    public int getBas(){
        return BAS.getValue();
    }
    public int getGauche(){
        return GAUCHE.getValue();
    }
    public int getDroite(){
        return DROITE.getValue();
    }
    public int getStart(){
        return START.getValue();
    }
    public int getSelect(){
        return SELECT.getValue();
    }
    public int getA(){
        return A.getValue();
    }
    public int getB(){
        return B.getValue();
    }
    public int getX(){
        return X.getValue();
    }
    public int getY(){
        return Y.getValue();
    }
    public int getClicGauche(){
        return CLIC_GAUCHE.getValue();
    }
    public int getClicDroite(){
        return CLIC_DROITE.getValue();
    }
    public int getHaut2(){
        return HAUT_2.getValue();
    }
    public int getBas2(){
        return BAS_2.getValue();
    }
    public int getGauche2(){
        return GAUCHE_2.getValue();
    }
    public int getDroite2(){
        return DROITE_2.getValue();
    }
    public int getStart2(){
        return START_2.getValue();
    }
    public int getSelect2(){
        return SELECT_2.getValue();
    }
    public int getA2(){
        return A_2.getValue();
    }
    public int getB2(){
        return B_2.getValue();
    }
    public int getX2(){
        return X_2.getValue();
    }
    public int getY2(){
        return Y_2.getValue();
    }
    public int getClicGauche2(){
        return CLIC_GAUCHE_2.getValue();
    }
    public int getClicDroite2(){
        return CLIC_DROITE_2.getValue();
    }

}
