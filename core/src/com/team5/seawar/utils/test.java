package com.team5.seawar.utils;

public class test {

    public static void rotationImpaire(float col, float row){
        float xx, yy, zz;

        xx = col;
        zz = row - (col + Math.abs(col%2)) /2;
        yy = -xx-zz;


        float tempx,tempy,tempz;
        tempx = xx;
        tempy = yy;
        tempz = zz;
        xx = -tempy;
        yy = -tempz;
        zz = -tempx;

        col = xx;
        row = zz + (xx - Math.abs(xx%2)) / 2;

        System.out.println(col + " " + row);
    }

    public static void rotationPaire(float col, float row){
        float xx, yy, zz;

        xx = col;
        zz = row - (col + Math.abs(col%2)) /2;
        yy = -xx-zz;


        float tempx,tempy,tempz;
        tempx = xx;
        tempy = yy;
        tempz = zz;
        xx = -tempy;
        yy = -tempz;
        zz = -tempx;

        col = xx;
        row = zz + (xx + Math.abs(xx%2)) / 2;

        System.out.println(col + " " + row);
    }


}
