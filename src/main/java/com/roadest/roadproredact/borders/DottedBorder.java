package com.roadest.roadproredact.borders;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class DottedBorder extends Border{
Line second=new Line();
Line first = new Line();
int number;
    public DottedBorder(Point2D pointSt, Point2D pointEnd, double strWidth, int number, boolean last) {
        super(strWidth, number);
        this.number=number;
        second.setStrokeWidth(3);
        second.setStroke(Color.ORANGE);
        second.setStartX(pointSt.getX()+borderWidth(pointSt,pointEnd));
        second.setStartY(pointSt.getY()+borderHeight(pointSt,pointEnd));
        second.setEndX(pointEnd.getX()+borderWidth(pointSt,pointEnd));
        second.setEndY(pointEnd.getY()+borderHeight(pointSt,pointEnd));
        if(number>0)
            second.getStrokeDashArray().addAll(20.0);
        secondLine.getChildren().add(second);
        secondLineStart = new Point2D(second.getStartX(),second.getStartY());
        secondLineEnd = new Point2D(second.getEndX(),second.getEndY());

        first.setStrokeWidth(3);
        first.setStroke(Color.ORANGE);
        first.setStartX(pointSt.getX()-borderWidth(pointSt,pointEnd));
        first.setStartY(pointSt.getY()-borderHeight(pointSt,pointEnd));
        first.setEndX(pointEnd.getX()-borderWidth(pointSt,pointEnd));
        first.setEndY(pointEnd.getY()-borderHeight(pointSt,pointEnd));
        if(!last)
            first.getStrokeDashArray().addAll(20.0);
        firstLine.getChildren().add(first);
        firstLineStart = new Point2D(first.getStartX(),first.getStartY());
        firstLineEnd = new Point2D(first.getEndX(),first.getEndY());
    }

    @Override
    public void move(Point2D pointSt, Point2D pointEnd) {
        super.move(pointSt, pointEnd);
        second.setStartX(pointSt.getX()+borderWidth(pointSt,pointEnd));
        second.setStartY(pointSt.getY()+borderHeight(pointSt,pointEnd));
        second.setEndX(pointEnd.getX()+borderWidth(pointSt,pointEnd));
        second.setEndY(pointEnd.getY()+borderHeight(pointSt,pointEnd));
        first.setStartX(pointSt.getX()-borderWidth(pointSt,pointEnd));
        first.setStartY(pointSt.getY()-borderHeight(pointSt,pointEnd));
        first.setEndX(pointEnd.getX()-borderWidth(pointSt,pointEnd));
        first.setEndY(pointEnd.getY()-borderHeight(pointSt,pointEnd));
        firstLineStart = new Point2D(first.getStartX(),first.getStartY());
        firstLineEnd = new Point2D(first.getEndX(),first.getEndY());
    }
}
