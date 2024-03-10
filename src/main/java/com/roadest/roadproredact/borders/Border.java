package com.roadest.roadproredact.borders;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.shape.Line;

public class Border extends Group {
    double strokeWidth=20;
    Group firstLine=new Group();
    Group secondLine = new Group();
    Point2D firstLineStart;
    Point2D firstLineEnd;
    Point2D secondLineStart;
    Point2D secondLineEnd;
    public Border(double strWidth,int number)
    {
        strokeWidth=strWidth;
        getChildren().add(firstLine);
        getChildren().add(secondLine);
    }

    public Group getFirstLine() {
        return firstLine;
    }

    public Group getSecondLine() {
        return secondLine;
    }

    public Point2D getFirstLineEnd() {
        return firstLineEnd;
    }

    public Point2D getFirstLineStart() {
        return firstLineStart;
    }

    public Point2D getSecondLineEnd() {
        return secondLineEnd;
    }

    public Point2D getSecondLineStart() {
        return secondLineStart;
    }

    protected double borderHeight(Point2D pointSt, Point2D pointEnd)
    {
        return (pointSt.getX()-pointEnd.getX()) * coefLine(pointSt, pointEnd);
    }
    protected double borderWidth(Point2D pointSt, Point2D pointEnd)
    {
        return (pointEnd.getY()-pointSt.getY()) * coefLine(pointSt, pointEnd);
    }
    protected double coefLine(Point2D pointSt, Point2D pointEnd)
    {
        return (strokeWidth/2)/lengthLine(pointSt, pointEnd);
    }
    public static double lengthLine(Point2D pointSt, Point2D pointEnd)
    {
        return Math.sqrt(Math.pow(pointEnd.getX()-pointSt.getX(),2) + Math.pow(pointEnd.getY()-pointSt.getY(),2));
    }
    protected double lengthBorder(Point2D pointSt, Point2D pointEnd)
    {
        return Math.sqrt(Math.pow(pointEnd.getX()-pointSt.getX(),2) + Math.pow(pointEnd.getY()-pointSt.getY(),2));
    }

    public void move(Point2D pointSt, Point2D pointEnd) {

    }
}
