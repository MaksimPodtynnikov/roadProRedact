package com.roadest.roadproredact.borders;

import javafx.geometry.Point2D;

public class BorderFactory {

    public static Border create(BorderType borderType, Point2D pointSt, Point2D pointEnd,double width,int num,boolean last) {
        Border border;
        switch (borderType)
        {
            case Dotted: border= new DottedBorder(pointSt,pointEnd,width,num, last);
                break;
            case DoubleSolid: border= new DoubleSolidBorder(pointSt,pointEnd,width,num,last);
                break;
            default: border= new SolidBorder(pointSt,pointEnd,width,num,last);
                break;
        }
        return border;
    }
}
