package com.roadest.roadproredact.models;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PointDeflection extends Group {
    Boolean end;
    int number;
    Point2D point;
    Circle visibleCircle;
    Circle circleDetection;
    boolean connected;
    public PointDeflection(Point2D point, int number, boolean end,boolean connected) {
        this.point = point;
        this.number = number;
        this.end = end;
        visibleCircle=new Circle(point.getX(),point.getY(),3,Color.BLUE);
        visibleCircle.setStroke(Color.BLUE);
        circleDetection = new Circle(point.getX(),point.getY(),16,Color.TRANSPARENT);
        circleDetection.setStroke(Color.TRANSPARENT);
        setConnected(connected);
        getChildren().add(visibleCircle);
        getChildren().add(circleDetection);
    }

    public Circle getVisibleCircle() {
        return visibleCircle;
    }

    public Point2D getPoint() {
        return point;
    }

    public Circle getCircleDetection() {
        return circleDetection;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
        if(connected) {
            visibleCircle.setFill(Color.TRANSPARENT);
            visibleCircle.setStroke(Color.TRANSPARENT);
            circleDetection.setRadius(0);

        }
        else {
            visibleCircle.setFill(Color.BLUE);
            visibleCircle.setStroke(Color.BLUE);
            circleDetection.setRadius(16);
        }
    }

    public void setPoint(Point2D pointEnd) {
        this.point=pointEnd;
        visibleCircle.setCenterX(point.getX());
        visibleCircle.setCenterY(point.getY());
        circleDetection.setCenterX(point.getX());
        circleDetection.setCenterY(point.getY());
    }

}
