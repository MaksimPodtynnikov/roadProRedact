package com.roadest.roadproredact.models;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class ConnectionArcs {
    Arc startArc = new Arc();
    Arc finishArc = new Arc();
    public ConnectionArcs(Point2D st1,Point2D st2, Point2D end1, Point2D end2,int stripesCount)
    {
        finishArc.setCenterX((end1.getX()+end2.getX())/2);
        finishArc.setCenterY((end1.getY()+end2.getY())/2);
        finishArc.setRadiusX(20*stripesCount);
        finishArc.setRadiusY(20*stripesCount);
        finishArc.setFill(Color.TRANSPARENT);
        finishArc.setStartAngle(new Point2D(1, 0).angle(end1.subtract(end2)));
        finishArc.setLength(180);

        startArc.setCenterX((st1.getX()+st2.getX())/2);
        startArc.setCenterY((st1.getY()+st2.getY())/2);
        startArc.setRadiusX(10*stripesCount);
        startArc.setRadiusY(10*stripesCount);
        startArc.setFill(Color.TRANSPARENT);
        startArc.setStartAngle(new Point2D(1, 0).angle(st1.subtract(st2)));
        startArc.setLength(180);
    }

    public Arc getFinishArc() {
        return finishArc;
    }

    public List<Arc> arcs()
    {
        List<Arc> arcs= new ArrayList<>();
        arcs.add(startArc);
        arcs.add(finishArc);
        return arcs;
    }
    public Arc getStartArc() {
        return startArc;
    }
    private double angle(Point2D center, Point2D end)
    {
        Point2D start = new Point2D(center.getX()+Math.abs(center.distance(end)),center.getY());

        Point2D vec1 = new Point2D(start.getX()-center.getX(),start.getY()-center.getY());
        Point2D vec2 = new Point2D(end.getX()-center.getX(),end.getY()-center.getY());
        double pr = vec1.getX()*vec2.getX()+vec1.getY()*vec2.getY();
        double len1 = Math.sqrt(Math.pow(vec1.getX(),2)+Math.pow(vec1.getY(),2));
        double len2 = Math.sqrt(Math.pow(vec2.getX(),2)+Math.pow(vec2.getY(),2));

        double degress=-1;
        if (end.getY()>center.getY())
            degress=1;
        return 270-(Math.acos(pr/(len2*len1))*180.0d/Math.PI)*degress;
    }
    public void moveArcs(Point2D st1,Point2D st2, Point2D end1, Point2D end2,int stripesCount) {
        finishArc.setCenterX((end1.getX()+end2.getX())/2);
        finishArc.setCenterY((end1.getY()+end2.getY())/2);
        finishArc.setRadiusX(25*stripesCount);
        finishArc.setRadiusY(25*stripesCount);
        finishArc.setStartAngle(angle(st1,end1));

        startArc.setCenterX((st1.getX()+st2.getX())/2);
        startArc.setCenterY((st1.getY()+st2.getY())/2);
        startArc.setRadiusX(25*stripesCount);
        startArc.setRadiusY(25*stripesCount);
        startArc.setStartAngle(angle(end1,st1));
    }
}
