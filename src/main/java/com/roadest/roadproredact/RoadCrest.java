package com.roadest.roadproredact;

import com.roadest.roadproredact.models.PointDeflection;
import com.roadest.roadproredact.models.PointsOfDeflections;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RoadCrest extends Group {
    PointsOfDeflections pointsOfDeflections = new PointsOfDeflections();
    Rectangle crest;
    public RoadCrest(int stripes, Point2D pointCenter)
    {
        crest=new Rectangle(pointCenter.getX() - 10*stripes,pointCenter.getY()-10*stripes,20*stripes,20*stripes);
        crest.setFill(Color.BLACK);
        for (int i=1;i<=stripes*2-1;i+=2)
            pointsOfDeflections.add(new Point2D((pointCenter.getX() - 10*stripes)+10*i,pointCenter.getY()-10*stripes),true,false);
        for (int i=1;i<=stripes*2-1;i+=2)
            pointsOfDeflections.add(new Point2D((pointCenter.getX() - 10*stripes)+10*i,pointCenter.getY()+10*stripes),true,false);
        for (int i=1;i<=stripes*2-1;i+=2)
            pointsOfDeflections.add(new Point2D(pointCenter.getX() - 10*stripes,(pointCenter.getY()-10*stripes)+10*i),true,false);
        for (int i=1;i<=stripes*2-1;i+=2)
            pointsOfDeflections.add(new Point2D(pointCenter.getX() + 10*stripes,(pointCenter.getY()-10*stripes)+10*i),true,false);
        getChildren().add(crest);
        getChildren().addAll(pointsOfDeflections.getPointDeflections());
    }
    public void move(Point2D vector)
    {
        crest.setX(crest.getX()+vector.getX());
        crest.setY(crest.getY()+vector.getY());
        for (PointDeflection point: pointsOfDeflections.getPointDeflections()) {
            point.setPoint(new Point2D(point.getPoint().getX()+vector.getX(),point.getPoint().getY()+vector.getY()));
        }
    }
}
