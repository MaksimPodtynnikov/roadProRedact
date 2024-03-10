package com.roadest.roadproredact.models;

import com.roadest.roadproredact.borders.Border;
import com.roadest.roadproredact.borders.BorderFactory;
import com.roadest.roadproredact.borders.BorderType;
import com.roadest.roadproredact.signs.Sign;
import com.roadest.roadproredact.signs.SignType;
import com.roadest.roadproredact.signs.SignsFactory;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;
import java.util.List;

public class Stripe extends Group {
    List<Sign> signs = new ArrayList<>();
    int number;
    boolean last;
    Point2D pointSt;
    Point2D pointEnd;
    double strokeWidth=20;
    BorderType borderType;
    Line stripe = new Line();
    Border border;
    PointDeflection stPoint;
    PointDeflection endPoint;
    RoadType type;
    public Stripe(int num, Point2D pointSt, Point2D pointEnd, BorderType borderType, RoadType roadtype, boolean last)
    {
        this.pointSt=pointSt;
        this.pointEnd=pointEnd;
        this.borderType=borderType;
        this.number=num;
        this.last=last;
        type=roadtype;
        borderConstruct(pointSt,pointEnd, roadtype);
        getChildren().add(stripe);
        getChildren().add(border=BorderFactory.create(borderType,pointSt,pointEnd,strokeWidth,number,last));
        createDeflection();
    }
    public Sign addSign(SignType type, double length)
    {
        Sign sign=SignsFactory.createSign(type,this,length);
        signs.add(sign);
        getChildren().add(sign);
        return sign;
    }
    public void createDeflection()
    {
        stPoint = new PointDeflection(pointSt, number,false,true);
        endPoint = new PointDeflection(pointEnd, number,true,true);
        getChildren().addAll(stPoint,endPoint);
    }
    public Point2D getPointStart()
    {
        return pointSt;
    }
    public Point2D getPointEnd()
    {
        return pointEnd;
    }
    public void borderConstruct(Point2D pointSt, Point2D pointEnd, RoadType roadtype)
    {
        stripe.setStrokeWidth(strokeWidth);
        if(roadtype==RoadType.GROUND)
            stripe.setStroke(Color.BROWN);
        else stripe.setStroke(Color.BLACK);
        stripe.setStartX(pointSt.getX());
        stripe.setStartY(pointSt.getY());
        stripe.setEndX(pointEnd.getX());
        stripe.setEndY(pointEnd.getY());
    }

    public List<Sign> getSigns() {
        return signs;
    }

    public void deleteSign(Sign sign)
    {
        getChildren().remove(sign);
        signs.remove(sign);
    }
    public void move(int num, Point2D pointSt, Point2D pointEnd) {
        borderConstruct(pointSt,pointEnd, this.type);
        borderMove(pointSt, pointEnd);
        signMove();
        this.pointSt=pointSt;
        this.pointEnd=pointEnd;
        moveDeflection(pointSt, pointEnd);
    }

    private void signMove() {
        for (Sign sign: signs) {
            sign.move();
        }
    }

    private void moveDeflection(Point2D pointSt, Point2D pointEnd) {
        stPoint.setPoint(pointSt);
        stPoint.setConnected(true);
        endPoint.setPoint(pointEnd);
        endPoint.setConnected(true);

    }

    private void borderMove(Point2D pointSt, Point2D pointEnd) {
        border.move(pointSt,pointEnd);
    }

    public Point2D getVector(boolean end) {
        if(end)
            return new Point2D(pointEnd.getX()-pointSt.getX(),pointEnd.getY()-pointSt.getY());
        else return new Point2D(pointSt.getX()-pointEnd.getX(),pointSt.getY()-pointEnd.getY());
    }
}
