package com.roadest.roadproredact.signs;

import com.roadest.roadproredact.borders.Border;
import com.roadest.roadproredact.models.Stripe;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class AwareSign extends Sign {
    Polygon awareSign = new Polygon();

    public AwareSign(Stripe stripe, SignType signType, double length) {
        super(stripe, signType, length);
        double coef=(Border.lengthLine(stripe.getPointStart(),stripe.getPointEnd())-length)/length;
        Point2D signPoint = new Point2D((stripe.getPointStart().getX()+coef*stripe.getPointEnd().getX())/(1+coef),
                (stripe.getPointStart().getY()+coef*stripe.getPointEnd().getY())/(1+coef));
        awareSign.getPoints().addAll(signPoint.getX(), signPoint.getY()+5,
                signPoint.getX()+5, signPoint.getY()-5,
                signPoint.getX()-5, signPoint.getY()-5);
        awareSign.setFill(Color.WHITE);
        awareSign.setStroke(Color.RED);
        awareSign.setStrokeWidth(3);
        getChildren().add(awareSign);
    }

    @Override
    public void move() {
        super.move();
        double coef=(Border.lengthLine(stripe.getPointStart(),stripe.getPointEnd())-length)/length;
        Point2D signPoint = new Point2D((stripe.getPointStart().getX()+coef*stripe.getPointEnd().getX())/(1+coef),
                (stripe.getPointStart().getY()+coef*stripe.getPointEnd().getY())/(1+coef));
        awareSign.getPoints().clear();
        awareSign.getPoints().addAll(signPoint.getX(), signPoint.getY()+5,
                signPoint.getX()+5, signPoint.getY()-5,
                signPoint.getX()-5, signPoint.getY()-5);
    }
}
