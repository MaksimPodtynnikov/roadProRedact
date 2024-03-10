package com.roadest.roadproredact.signs;

import com.roadest.roadproredact.borders.Border;
import com.roadest.roadproredact.models.Stripe;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.lang.reflect.Type;

public class MainSign extends Sign {
    Rectangle mainSign;
    public MainSign(Stripe stripe, SignType signType, double length) {
        super(stripe, signType, length);
        double coef=(Border.lengthLine(stripe.getPointStart(),stripe.getPointEnd())-length)/length;
        Point2D signPoint = new Point2D((stripe.getPointStart().getX()+coef*stripe.getPointEnd().getX())/(1+coef),
                (stripe.getPointStart().getY()+coef*stripe.getPointEnd().getY())/(1+coef));
        mainSign=new Rectangle(signPoint.getX(),signPoint.getY(),10,10);
        mainSign.setFill(Color.ORANGE);
        mainSign.setRotate(45);
        mainSign.setStroke(Color.WHITE);
        mainSign.setArcHeight(2);
        mainSign.setStrokeWidth(4);
        getChildren().add(mainSign);
    }

    @Override
    public void move() {
        super.move();
        double coef=(Border.lengthLine(stripe.getPointStart(),stripe.getPointEnd())-length)/length;
        Point2D signPoint = new Point2D((stripe.getPointStart().getX()+coef*stripe.getPointEnd().getX())/(1+coef),
                (stripe.getPointStart().getY()+coef*stripe.getPointEnd().getY())/(1+coef));
        mainSign.setX(signPoint.getX());
        mainSign.setY(signPoint.getY());
    }
}
