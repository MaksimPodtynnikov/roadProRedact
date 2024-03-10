package com.roadest.roadproredact.models;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Roads {
    List<Road> roads = new ArrayList<>();
    public Roads()
    {

    }

    public List<Road> getRoads() {
        return roads;
    }

    public Road addRoad(Road road)
    {
        roads.add(road);
        return roads.get(roads.indexOf(road));
    }
    public void delete(Road road)
    {
        roads.remove(road);
    }

    public void move(Point2D vector) {
        for (Road road: roads) {
            road.move(new Point2D(road.stripeList.pointSt.getX()+vector.getX(),road.stripeList.pointSt.getY()+vector.getY()),
                    new Point2D(road.stripeList.pointEnd.getX()+vector.getX(),road.stripeList.pointEnd.getY()+vector.getY()),vector);
        }
    }
    public double getLength()
    {
        double length=0;
        for (Road road: roads) {
            length+= road.getLength();
        }
        return length;
    }
    public int getSignsCount()
    {
        int count=0;
        for (Road road: roads) {
            count+= road.getStripeList().getSignsCount();
        }
        return count;
    }
    public void clear() {
        roads.clear();
    }
}
