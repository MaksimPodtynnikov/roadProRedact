package com.roadest.roadproredact.models;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class PointsOfDeflections {
    List<PointDeflection> pointDeflections = new ArrayList<>();
    public PointsOfDeflections()
    {

    }
    public void add(Point2D point, boolean end,boolean connected)
    {
        pointDeflections.add(new PointDeflection(point, pointDeflections.size()-1,end,connected));
    }

    public List<PointDeflection> getPointDeflections() {
        return pointDeflections;
    }

    public void move(Point2D pointSt,Point2D pointEnd) {
        for (PointDeflection point: pointDeflections) {
            if(point.end)
            {
                point.setPoint(pointEnd);
            }
            else point.setPoint(pointSt);
        }
    }
}
