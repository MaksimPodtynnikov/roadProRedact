package com.roadest.roadproredact.signs;

import com.roadest.roadproredact.borders.Border;
import com.roadest.roadproredact.models.Stripe;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.lang.reflect.Type;

public class StopSign extends Sign {
    Polygon stopSign;
    Text stop;
    public StopSign(Stripe stripe, SignType signType, double length) {
        super(stripe, signType, length);
        double coef=(Border.lengthLine(stripe.getPointStart(),stripe.getPointEnd())-length)/length;
        Point2D signPoint = new Point2D((stripe.getPointStart().getX()+coef*stripe.getPointEnd().getX())/(1+coef),
                (stripe.getPointStart().getY()+coef*stripe.getPointEnd().getY())/(1+coef));
        stopSign=new Polygon(signPoint.getX()+3,signPoint.getY()-6,signPoint.getX()+6,signPoint.getY()-3,signPoint.getX()+6,signPoint.getY()+3,signPoint.getX()+3,signPoint.getY()+6,
                signPoint.getX()-3,signPoint.getY()+6,signPoint.getX()-6,signPoint.getY()+3,signPoint.getX()-6,signPoint.getY()-3,signPoint.getX()-3,signPoint.getY()-6);
        stop = new Text(signPoint.getX()-5,signPoint.getY()+1,"STOP");
        stop.setStyle("-fx-font-size: 4px;");
        stop.setFill(Color.WHITE);
        stopSign.setFill(Color.RED);
        stopSign.setStroke(Color.WHITE);
        stopSign.setStrokeWidth(2);
        getChildren().add(stopSign);
        getChildren().add(stop);
    }

    @Override
    public void move() {
        super.move();
        double coef=(Border.lengthLine(stripe.getPointStart(),stripe.getPointEnd())-length)/length;
        Point2D signPoint = new Point2D((stripe.getPointStart().getX()+coef*stripe.getPointEnd().getX())/(1+coef),
                (stripe.getPointStart().getY()+coef*stripe.getPointEnd().getY())/(1+coef));
        stopSign.getPoints().clear();
        stopSign.getPoints().addAll(signPoint.getX()+3,signPoint.getY()-6,signPoint.getX()+6,signPoint.getY()-3,signPoint.getX()+6,signPoint.getY()+3,signPoint.getX()+3,signPoint.getY()+6,
                signPoint.getX()-3,signPoint.getY()+6,signPoint.getX()-6,signPoint.getY()+3,signPoint.getX()-6,signPoint.getY()-3,signPoint.getX()-3,signPoint.getY()-6);
        stop.setX(signPoint.getX()-5);
        stop.setY(signPoint.getY()+1);
    }
}
