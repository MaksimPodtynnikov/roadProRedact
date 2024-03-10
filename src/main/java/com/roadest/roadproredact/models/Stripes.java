package com.roadest.roadproredact.models;

import com.roadest.roadproredact.borders.BorderType;
import com.roadest.roadproredact.signs.Sign;
import com.roadest.roadproredact.signs.SignType;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.*;

public class Stripes {
    List<Stripe> stripeList = new ArrayList<>();
    Point2D pointSt;
    Point2D pointEnd;
    BorderType borderType;
    RoadType roadtype;
    public Stripes(Point2D pointSt, Point2D pointEnd, BorderType borderType, RoadType type)
    {
        this.pointSt=pointSt;
        this.pointEnd=pointEnd;
        this.roadtype=type;
        this.borderType = borderType;
    }
    public void add()
    {
        double dx = pointEnd.getX()-pointSt.getX();
        double dy= pointEnd.getY()-pointSt.getY();
        double len = Math.sqrt(Math.pow(pointEnd.getX()-pointSt.getX(),2) + Math.pow(pointEnd.getY()-pointSt.getY(),2));
        if(len==0)
            len =1;
        double udx = dx / len;
        double udy = dy / len;
        Point2D pointStNew = new Point2D(pointSt.getX() -udy * 20*stripeList.size(),pointSt.getY() + udx * 20*stripeList.size());
        Point2D pointEndNew = new Point2D(pointStNew.getX() + dx, pointStNew.getY() + dy);
        for (Stripe stripe:stripeList)
            stripe.last=false;
        Stripe stripe =new Stripe(stripeList.size(),pointStNew,pointEndNew, borderType,roadtype,true);
        stripeList.add(stripe);
        addConnect();
    }
    public String getSigns()
    {
        StringBuilder countAll= new StringBuilder("Дорога содержит ");
        Map<SignType,Integer> count=new HashMap<>();
        for (Stripe stripe: stripeList) {
            for (Sign sign: stripe.getSigns()) {
                count.merge(sign.getSignType(), 1, Integer::sum);
            }
        }
        for (SignType signType:count.keySet()) {
            countAll.append(" знак '").append(signType.toString()).append("' в количестве ").append(count.get(signType)).append(" шт.,");
        }
        countAll.deleteCharAt(countAll.lastIndexOf(","));
        return countAll.toString();
    }
    public int getSignsCount()
    {
        int count=0;
        for (Stripe stripe: stripeList) {
            for (Sign sign: stripe.getSigns()) {
                count++;
            }
        }
        return count;
    }


    public void moveAll(Point2D pointSt, Point2D pointEnd) {
        this.pointSt=pointSt;
        this.pointEnd=pointEnd;
        for (Stripe stripe: stripeList) {
            double dx = pointEnd.getX()-pointSt.getX();
            double dy= pointEnd.getY()-pointSt.getY();
            double len = Math.sqrt(Math.pow(pointEnd.getX()-pointSt.getX(),2) + Math.pow(pointEnd.getY()-pointSt.getY(),2));
            if(len==0)
                len =1;
            double udx = dx / len;
            double udy = dy / len;
            Point2D pointStNew = new Point2D(pointSt.getX() -udy * 20*stripeList.indexOf(stripe),pointSt.getY() + udx * 20*stripeList.indexOf(stripe));
            Point2D pointEndNew = new Point2D(pointStNew.getX() + dx, pointStNew.getY() + dy);
            stripe.move(stripeList.indexOf(stripe),pointStNew, pointEndNew);
        }
    }

    public Circle addConnect()
    {
        Circle endRoad = new Circle();
        endRoad.setCenterX((stripeList.get(0).pointEnd.getX()+stripeList.get(stripeList.size()-1).pointEnd.getX())/2);
        endRoad.setCenterY((stripeList.get(0).pointEnd.getY()+stripeList.get(stripeList.size()-1).pointEnd.getY())/2);
        endRoad.setRadius(22);
        endRoad.setFill(Color.BLACK);
        return endRoad;
    }
    public List<Stripe> getList() {
        return stripeList;
    }
}
