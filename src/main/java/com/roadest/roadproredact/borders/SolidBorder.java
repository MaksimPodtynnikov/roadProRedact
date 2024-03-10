package com.roadest.roadproredact.borders;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class SolidBorder extends Border{
    Line second = new Line();
    Line first = new Line();
    public SolidBorder(Point2D pointSt, Point2D pointEnd, double strWidth, int num, boolean last)
    {
        super(strWidth,num);

        second.setStrokeWidth(3);
        second.setStroke(Color.ORANGE);
        second.setStartX(pointSt.getX()+borderWidth(pointSt,pointEnd));
        second.setStartY(pointSt.getY()+borderHeight(pointSt,pointEnd));
        second.setEndX(pointEnd.getX()+borderWidth(pointSt,pointEnd));
        second.setEndY(pointEnd.getY()+borderHeight(pointSt,pointEnd));
        secondLine.getChildren().add(second);
        secondLineStart = new Point2D(second.getStartX(),second.getStartY());
        secondLineEnd = new Point2D(second.getEndX(),second.getEndY());


        first.setStrokeWidth(3);
        first.setStroke(Color.ORANGE);
        first.setStartX(pointSt.getX()-borderWidth(pointSt,pointEnd));
        first.setStartY(pointSt.getY()-borderHeight(pointSt,pointEnd));
        first.setEndX(pointEnd.getX()-borderWidth(pointSt,pointEnd));
        first.setEndY(pointEnd.getY()-borderHeight(pointSt,pointEnd));
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
        secondLineStart = new Point2D(second.getStartX(),second.getStartY());
        secondLineEnd = new Point2D(second.getEndX(),second.getEndY());
        firstLineStart = new Point2D(first.getStartX(),first.getStartY());
        firstLineEnd = new Point2D(first.getEndX(),first.getEndY());
    }
}
