package com.roadest.roadproredact.signs;

import com.roadest.roadproredact.borders.Border;
import com.roadest.roadproredact.models.Stripe;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.lang.reflect.Type;

public class SpeedSign extends Sign   {
    Circle speedSign;
    Text speed;
    public SpeedSign(Stripe stripe, SignType signType, double length) {
        super(stripe, signType, length);
        double coef=(Border.lengthLine(stripe.getPointStart(),stripe.getPointEnd())-length)/length;
        Point2D signPoint = new Point2D((stripe.getPointStart().getX()+coef*stripe.getPointEnd().getX())/(1+coef),
                (stripe.getPointStart().getY()+coef*stripe.getPointEnd().getY())/(1+coef));
        speedSign=new Circle(signPoint.getX(),signPoint.getY(),10,Color.WHITE);
        speed = new Text(signPoint.getX()-6,signPoint.getY()+4,"60");
        speed.setStyle("-fx-font-size: 11px;");
        speedSign.setStroke(Color.RED);
        speedSign.setStrokeWidth(2);
        getChildren().add(speedSign);
        getChildren().add(speed);
    }

    @Override
    public void move() {
        super.move();
        double coef=(Border.lengthLine(stripe.getPointStart(),stripe.getPointEnd())-length)/length;
        Point2D signPoint = new Point2D((stripe.getPointStart().getX()+coef*stripe.getPointEnd().getX())/(1+coef),
                (stripe.getPointStart().getY()+coef*stripe.getPointEnd().getY())/(1+coef));
        speedSign.setCenterX(signPoint.getX());
        speedSign.setCenterY(signPoint.getY());
        speed.setX(signPoint.getX()-6);
        speed.setY(signPoint.getY()+4);
    }
}
