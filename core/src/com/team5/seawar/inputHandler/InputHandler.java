package com.team5.seawar.inputHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;
import com.sun.org.apache.xpath.internal.SourceTree;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class InputHandler {

    private static InputHandler uniqueInstance;
    private static int HAUT = Keys.Z;
    private static int BAS = Keys.S;
    private static int GAUCHE = Keys.Q;
    private static int DROITE = Keys.D;
    private static int START = Keys.ESCAPE;
    private static int SELECT = Keys.CONTROL_LEFT;
    private static int A = Keys.I;
    private static int B = Keys.J;
    private static int X = Keys.K;
    private static int Y = Keys.L;
    private static int CLIC_GAUCHE = Buttons.LEFT;
    private static int CLIC_DROITE = Buttons.RIGHT;
    private static int nb = 12;
    private static List<Integer> L = new ArrayList<Integer>(Arrays.asList(HAUT,BAS,GAUCHE,DROITE,START,SELECT,A,B,X,Y,CLIC_DROITE,CLIC_GAUCHE));
    private InputHandler(){
    }
    public static InputHandler getInstance(){
        if(uniqueInstance==null){
            uniqueInstance=new InputHandler();
        }
        return uniqueInstance;
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
        L.set(0,bouton);
    }
    public void setBas(int bouton){
        check(bouton);
        L.set(1,bouton);
    }
    private void check(int bouton){
        for(int i=0;i<L.size();i++){
            if (L.get(i)==bouton){
                L.set(i,-1);
            }
        }
    }
    public int getHaut(){
        return L.get(0);
    }
    public int getBas(){
        return L.get(1);
    }
    public int getGauche(){
        return GAUCHE;
    }
    public int getDroite(){
        return DROITE;
    }
    public int getStart(){
        return START;
    }
    public int getSelect(){
        return SELECT;
    }
    public int getA(){
        return A;
    }
    public int getB(){
        return B;
    }
    public int getX(){
        return X;
    }
    public int getY(){
        return Y;
    }
    public int getClicGauche(){
        return CLIC_GAUCHE;
    }
    public int getClicDroite(){
        return CLIC_DROITE;
    }
}
