package com.roadest.roadproredact.borders;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class DoubleSolidBorder extends Border{
    Line second1 = new Line();
    Line second2 = new Line();
    Line first1 = new Line();
    Line first2 = new Line();
    int number;
    boolean last;
    public DoubleSolidBorder(Point2D pointSt, Point2D pointEnd, double strWidth, int number,boolean last) {
        super(strWidth, number);
        this.number=number;
        this.last=last;
        if(number>0) {

            Point2D startSecond = new Point2D(pointSt.getX() + borderWidth(pointSt, pointEnd), pointSt.getY() + borderHeight(pointSt, pointEnd));
            Point2D endSecond = new Point2D(pointEnd.getX() + borderWidth(pointSt, pointEnd), pointEnd.getY() + borderHeight(pointSt, pointEnd));
            second1.setStrokeWidth(2);
            second1.setStroke(Color.ORANGE);
            second1.setStartX(getPerpendLineStart(startSecond, endSecond).getX());
            second1.setStartY(getPerpendLineStart(startSecond, endSecond).getY());
            second1.setEndX(getPerpendLineEnd(startSecond, endSecond).getX());
            second1.setEndY(getPerpendLineEnd(startSecond, endSecond).getY());
            secondLine.getChildren().add(second1);
        }

        second2.setStrokeWidth(2);
        second2.setStroke(Color.ORANGE);
        second2.setStartX(pointSt.getX()+borderWidth(pointSt,pointEnd));
        second2.setStartY(pointSt.getY()+borderHeight(pointSt,pointEnd));
        second2.setEndX(pointEnd.getX()+borderWidth(pointSt,pointEnd));
        second2.setEndY(pointEnd.getY()+borderHeight(pointSt,pointEnd));
        secondLine.getChildren().add(second2);
        secondLineStart = new Point2D(second2.getStartX(),second2.getStartY());
        secondLineEnd = new Point2D(second2.getEndX(),second2.getEndY());
        if(!last) {
            Point2D startFirst = new Point2D(pointSt.getX() - borderWidth(pointSt, pointEnd), pointSt.getY() - borderHeight(pointSt, pointEnd));
            Point2D endFirst = new Point2D(pointEnd.getX() - borderWidth(pointSt, pointEnd), pointEnd.getY() - borderHeight(pointSt, pointEnd));

            first1.setStrokeWidth(2);
            first1.setStroke(Color.ORANGE);
            first1.setStartX(getPerpendLineStart(startFirst, endFirst).getX());
            first1.setStartY(getPerpendLineStart(startFirst, endFirst).getY());
            first1.setEndX(getPerpendLineEnd(startFirst, endFirst).getX());
            first1.setEndY(getPerpendLineEnd(startFirst, endFirst).getY());
            firstLine.getChildren().add(first1);
        }

        first2.setStrokeWidth(2);
        first2.setStroke(Color.ORANGE);
        first2.setStartX(pointSt.getX()-borderWidth(pointSt,pointEnd));
        first2.setStartY(pointSt.getY()-borderHeight(pointSt,pointEnd));
        first2.setEndX(pointEnd.getX()-borderWidth(pointSt,pointEnd));
        first2.setEndY(pointEnd.getY()-borderHeight(pointSt,pointEnd));
        firstLine.getChildren().add(first2);
        firstLineStart = new Point2D(first2.getStartX(),first2.getStartY());
        firstLineEnd = new Point2D(first2.getEndX(),first2.getEndY());
    }
    private Point2D getPerpendLineEnd(Point2D start,Point2D end)
    {
        return new Point2D((end.getY()-start.getY())/lengthLine(end,start)*5+start.getX(),
                (-(end.getX()-start.getX()))/lengthLine(end,start)*5+start.getY());
    }
    private Point2D getPerpendLineStart(Point2D start,Point2D end)
    {
        return new Point2D(-(start.getY()-end.getY())/lengthLine(start,end)*5+end.getX(),
                ((start.getX()-end.getX()))/lengthLine(start,end)*5+end.getY());
    }

    @Override
    public void move(Point2D pointSt, Point2D pointEnd) {
        super.move(pointSt, pointEnd);
        if(number>0) {
            Point2D startSecond = new Point2D(pointSt.getX() + borderWidth(pointSt, pointEnd), pointSt.getY() + borderHeight(pointSt, pointEnd));
            Point2D endSecond = new Point2D(pointEnd.getX() + borderWidth(pointSt, pointEnd), pointEnd.getY() + borderHeight(pointSt, pointEnd));
            second1.setStartX(getPerpendLineStart(startSecond, endSecond).getX());
            second1.setStartY(getPerpendLineStart(startSecond, endSecond).getY());
            second1.setEndX(getPerpendLineEnd(startSecond, endSecond).getX());
            second1.setEndY(getPerpendLineEnd(startSecond, endSecond).getY());
        }
        second2.setStartX(pointSt.getX()+borderWidth(pointSt,pointEnd));
        second2.setStartY(pointSt.getY()+borderHeight(pointSt,pointEnd));
        second2.setEndX(pointEnd.getX()+borderWidth(pointSt,pointEnd));
        second2.setEndY(pointEnd.getY()+borderHeight(pointSt,pointEnd));
        secondLineStart = new Point2D(second2.getStartX(),second2.getStartY());
        secondLineEnd = new Point2D(second2.getEndX(),second2.getEndY());
        if(!last) {
            Point2D startFirst = new Point2D(pointSt.getX() - borderWidth(pointSt, pointEnd), pointSt.getY() - borderHeight(pointSt, pointEnd));
            Point2D endFirst = new Point2D(pointEnd.getX() - borderWidth(pointSt, pointEnd), pointEnd.getY() - borderHeight(pointSt, pointEnd));
            first1.setStartX(getPerpendLineStart(startFirst, endFirst).getX());
            first1.setStartY(getPerpendLineStart(startFirst, endFirst).getY());
            first1.setEndX(getPerpendLineEnd(startFirst, endFirst).getX());
            first1.setEndY(getPerpendLineEnd(startFirst, endFirst).getY());
        }
        first2.setStartX(pointSt.getX()-borderWidth(pointSt,pointEnd));
        first2.setStartY(pointSt.getY()-borderHeight(pointSt,pointEnd));
        first2.setEndX(pointEnd.getX()-borderWidth(pointSt,pointEnd));
        first2.setEndY(pointEnd.getY()-borderHeight(pointSt,pointEnd));
        firstLineStart = new Point2D(first2.getStartX(),first2.getStartY());
        firstLineEnd = new Point2D(first2.getEndX(),first2.getEndY());
    }
}
