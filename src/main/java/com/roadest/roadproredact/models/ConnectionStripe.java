package com.roadest.roadproredact.models;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class ConnectionStripe extends Group {
    Path path = new Path();
    Path border1 = new Path();
    Path border2 = new Path();
    //Moving to the starting point
    MoveTo moveTo = new MoveTo();
    MoveTo moveTo1 = new MoveTo();
    MoveTo moveTo2 = new MoveTo();
    ArcTo arcTo = new ArcTo();
    ArcTo arcTo1 = new ArcTo();
    ArcTo arcTo2 = new ArcTo();
    Stripe stripeOld;
    Stripe stripeNew;
    boolean endOld, endNew,closestStart,endToStart;
    int stripesCount;
    public ConnectionStripe(Stripe stripeOld, Stripe stripeNew, boolean endOld, boolean endNew, int stripesCount, boolean endToStart, boolean closestStart,RoadType type) {

        this.endOld=endOld;
        this.endNew=endNew;
        this.closestStart=closestStart;
        this.endToStart=endToStart;
        this.stripesCount=stripesCount;
        Point2D border1left,border2left,border1right,border2right;
        if(endOld) {
            moveTo.setX(stripeOld.pointEnd.getX());
            moveTo.setY(stripeOld.pointEnd.getY());
            border1left=stripeOld.border.getFirstLineEnd();
            border1right=stripeOld.border.getSecondLineEnd();
            moveTo1.setX(stripeOld.border.getFirstLineEnd().getX());
            moveTo1.setY(stripeOld.border.getFirstLineEnd().getY());
            moveTo2.setX(stripeOld.border.getSecondLineEnd().getX());
            moveTo2.setY(stripeOld.border.getSecondLineEnd().getY());
        }
        else{
            moveTo.setX(stripeOld.pointSt.getX());
            moveTo.setY(stripeOld.pointSt.getY());
            if (endToStart) {
                border1left=stripeOld.border.getFirstLineStart();
                border1right=stripeOld.border.getSecondLineStart();
                moveTo1.setX(stripeOld.border.getFirstLineStart().getX());
                moveTo1.setY(stripeOld.border.getFirstLineStart().getY());
                moveTo2.setX(stripeOld.border.getSecondLineStart().getX());
                moveTo2.setY(stripeOld.border.getSecondLineStart().getY());
            }
            else{
                border1left=stripeOld.border.getSecondLineStart();
                border1right=stripeOld.border.getFirstLineStart();
                moveTo1.setX(stripeOld.border.getSecondLineStart().getX());
                moveTo1.setY(stripeOld.border.getSecondLineStart().getY());
                moveTo2.setX(stripeOld.border.getFirstLineStart().getX());
                moveTo2.setY(stripeOld.border.getFirstLineStart().getY());
            }
        }

        //Instantiating the arcTo class

        //setting properties of the path element arc
        if(endNew) {
            arcTo.setX(stripeNew.pointEnd.getX());
            arcTo.setY(stripeNew.pointEnd.getY());
            border2left=stripeNew.border.getSecondLineEnd();
            border2right=stripeNew.border.getFirstLineEnd();
            arcTo1.setX(stripeNew.border.getSecondLineEnd().getX());
            arcTo1.setY(stripeNew.border.getSecondLineEnd().getY());
            arcTo2.setX(stripeNew.border.getFirstLineEnd().getX());
            arcTo2.setY(stripeNew.border.getFirstLineEnd().getY());
        }
        else{
            arcTo.setX(stripeNew.pointSt.getX());
            arcTo.setY(stripeNew.pointSt.getY());
            border2left=stripeNew.border.getFirstLineStart();
            border2right=stripeNew.border.getSecondLineStart();
            arcTo1.setX(stripeNew.border.getFirstLineStart().getX());
            arcTo1.setY(stripeNew.border.getFirstLineStart().getY());
            arcTo2.setX(stripeNew.border.getSecondLineStart().getX());
            arcTo2.setY(stripeNew.border.getSecondLineStart().getY());

        }
        arcTo.setSweepFlag((getPoint(stripeOld,endOld).getX()<getPoint(stripeNew,endNew).getX() &&
                getPoint(stripeOld,endOld).getY()<getPoint(stripeNew,endNew).getY() && stripeNew.getVector(endNew).getY()<0)
        || (getPoint(stripeOld,endOld).getX()>getPoint(stripeNew,endNew).getX() &&
                getPoint(stripeOld,endOld).getY()>getPoint(stripeNew,endNew).getY() && stripeNew.getVector(endNew).getY()>0));
        arcTo1.setSweepFlag(arcTo.isSweepFlag());
        arcTo2.setSweepFlag(arcTo.isSweepFlag());
        double radiusX=Math.abs(getPoint(stripeOld,endOld).getX()-getPoint(stripeNew,endNew).getX());
        double radiusY=Math.abs(getPoint(stripeOld,endOld).getY()-getPoint(stripeNew,endNew).getY());
        arcTo.setRadiusX(radiusX);
        arcTo.setRadiusY(radiusY);
        path.setStrokeWidth(19);
        arcTo1.setRadiusX(Math.abs(border1left.getX()-border2left.getX()));
        arcTo1.setRadiusY(Math.abs(border1left.getY()-border2left.getY()));
        arcTo2.setRadiusX(Math.abs(border1right.getX()-border2right.getX()));
        arcTo2.setRadiusY(Math.abs(border1right.getY()-border2right.getY()));
        if(RoadType.ASPHALT ==type)
            path.setStroke(Color.BLACK);
        else
            path.setStroke(Color.BROWN);
        border1.setStroke(Color.ORANGE);
        border2.setStroke(Color.ORANGE);
        border1.setStrokeWidth(3);
        border2.setStrokeWidth(3);
        //Adding the path elements to Observable list of the Path class
        path.getElements().add(moveTo);
        path.getElements().add(arcTo);
        border1.getElements().add(moveTo1);
        border1.getElements().add(arcTo1);
        border2.getElements().add(moveTo2);
        border2.getElements().add(arcTo2);

        getChildren().addAll(path,border1,border2);
        border1.toFront();
        border2.toFront();
        path.toBack();

    }

    public void move(Point2D vector) {
        if(endOld) {
            moveTo.setX(moveTo.getX()+vector.getX());
            moveTo.setY(moveTo.getY()+vector.getY());
            moveTo1.setX(moveTo1.getX()+vector.getX());
            moveTo1.setY(moveTo1.getY()+vector.getY());
            moveTo2.setX(moveTo2.getX()+vector.getX());
            moveTo2.setY(moveTo2.getY()+vector.getY());
        }
        else{
            moveTo.setX(moveTo.getX()+vector.getX());
            moveTo.setY(moveTo.getY()+vector.getY());
            if (endToStart) {
                moveTo1.setX(moveTo1.getX()+vector.getX());
                moveTo1.setY(moveTo1.getY()+vector.getY());
                moveTo2.setX(moveTo2.getX()+vector.getX());
                moveTo2.setY(moveTo2.getY()+vector.getY());
            }
            else{
                moveTo1.setX(moveTo1.getX()+vector.getX());
                moveTo1.setY(moveTo1.getY()+vector.getY());
                moveTo2.setX(moveTo2.getX()+vector.getX());
                moveTo2.setY(moveTo2.getY()+vector.getY());
            }
        }

        //Instantiating the arcTo class

        //setting properties of the path element arc
        if(endNew) {
            arcTo.setX(arcTo.getX()+vector.getX());
            arcTo.setY(arcTo.getY()+vector.getY());
            arcTo1.setX(arcTo1.getX()+vector.getX());
            arcTo1.setY(arcTo1.getY()+vector.getY());
            arcTo2.setX(arcTo2.getX()+vector.getX());
            arcTo2.setY(arcTo2.getY()+vector.getY());
        }
        else{
            arcTo.setX(arcTo.getX()+vector.getX());
            arcTo.setY(arcTo.getY()+vector.getY());
            arcTo1.setX(arcTo1.getX()+vector.getX());
            arcTo1.setY(arcTo1.getY()+vector.getY());
            arcTo2.setX(arcTo2.getX()+vector.getX());
            arcTo2.setY(arcTo2.getY()+vector.getY());

        }
    }
    private Point2D getPoint(Stripe stripe,boolean end)
    {
        if(end)
            return stripe.pointEnd;
        else return stripe.pointSt;
    }
}
