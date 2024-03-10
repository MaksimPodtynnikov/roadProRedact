package com.roadest.roadproredact.models;

import com.roadest.roadproredact.borders.BorderType;
import javafx.geometry.Point2D;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class Road extends Group {
    RoadType type;
    Stripes stripeList;
    ConnectionArcs connectionArcs;
    List<ConnectionStripe> connectionStripes = new ArrayList<>();

    public Road(Point2D pointSt, Point2D pointEnd, int stripes, BorderType borderType,RoadType type) {
        this.type=type;
        stripeList = new Stripes(pointSt,pointEnd,borderType,type);
        for (int i=0;i<stripes;i++)
            stripeList.add();
        connectionArcs = new ConnectionArcs(stripeList.getList().get(0).pointSt,stripeList.getList().get(stripeList.getList().size()-1).pointSt,
                stripeList.getList().get(0).pointEnd,stripeList.getList().get(stripeList.getList().size()-1).pointEnd,
                stripeList.getList().size());
        getChildren().addAll(connectionArcs.arcs());
        getChildren().addAll(stripeList.getList());

    }

    public RoadType getType() {
        return type;
    }

    public double getLength()
    {
        return stripeList.pointSt.distance(stripeList.pointEnd)/5.3;
    }

    public Stripes getStripeList() {
        return stripeList;
    }

    public void move(Point2D pointSt, Point2D pointEnd, Point2D vector) {
        stripeList.moveAll(pointSt,pointEnd);
        connectionArcs.moveArcs(stripeList.getList().get(0).pointSt,stripeList.getList().get(stripeList.getList().size()-1).pointSt,
                stripeList.getList().get(0).pointEnd,stripeList.getList().get(stripeList.getList().size()-1).pointEnd,
                stripeList.getList().size());
        for (ConnectionStripe stripe: connectionStripes) {
            stripe.move(vector);
        }
    }
    public void addStripe()
    {
        stripeList.add();
    }

    public void addConnectionStripes(Road addedRoad,boolean endOld, boolean endNew)
    {
        Stripe temp = addedRoad.stripeList.getList().get(0);
        boolean closestStart;
        if(endOld && endNew) {
            if((addedRoad.stripeList.getList().get(0).pointEnd.distance(stripeList.getList().get(0).pointEnd)<
                    addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointEnd.distance(stripeList.getList().get(0).pointEnd)
            || addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointEnd.distance(stripeList.getList().get(stripeList.getList().size()-1).pointEnd)<
                    addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointEnd.distance(stripeList.getList().get(0).pointEnd))
            && (addedRoad.stripeList.getList().get(0).pointEnd.distance(stripeList.getList().get(0).pointEnd)<
                    addedRoad.stripeList.getList().get(0).pointEnd.distance(stripeList.getList().get(stripeList.getList().size()-1).pointEnd)
                    || addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointEnd.distance(stripeList.getList().get(stripeList.getList().size()-1).pointEnd)<
                    addedRoad.stripeList.getList().get(0).pointEnd.distance(stripeList.getList().get(stripeList.getList().size()-1).pointEnd)))
            {
                closestStart=(addedRoad.stripeList.getList().get(0).pointEnd.distance(stripeList.getList().get(0).pointEnd)<addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointEnd.distance(stripeList.getList().get(stripeList.getList().size()-1).pointEnd));
                for (int i=0;i<stripeList.stripeList.size();i++)
                {
                    if(addedRoad.stripeList.getList().size()>i)
                        temp = addedRoad.stripeList.getList().get(i);
                    ConnectionStripe connectionStripe = new ConnectionStripe(stripeList.getList().get(i),temp,endOld,endNew,Math.max(stripeList.stripeList.size(),addedRoad.stripeList.getList().size()),false,closestStart,type);
                    connectionStripes.add(connectionStripe);
                    getChildren().add(connectionStripe);

                }
            }
            else{
                temp = addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1);
                for (int i=0;i<stripeList.stripeList.size();i++)
                {
                    if(addedRoad.stripeList.getList().size()-i>0)
                        temp = addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-i-1);
                    ConnectionStripe connectionStripe = new ConnectionStripe(stripeList.getList().get(i),temp,endOld,endNew,Math.max(stripeList.stripeList.size(),addedRoad.stripeList.getList().size()),true,false,type);
                    connectionStripes.add(connectionStripe);
                    getChildren().add(connectionStripe);

                }
            }
        }
        else if(endOld && !endNew) {
            if((addedRoad.stripeList.getList().get(0).pointSt.distance(stripeList.getList().get(0).pointEnd)<
                    addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointSt.distance(stripeList.getList().get(0).pointEnd)
                    || addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointSt.distance(stripeList.getList().get(stripeList.getList().size()-1).pointEnd)<
                    addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointSt.distance(stripeList.getList().get(0).pointEnd))
            && (addedRoad.stripeList.getList().get(0).pointSt.distance(stripeList.getList().get(0).pointEnd)<
                    addedRoad.stripeList.getList().get(0).pointSt.distance(stripeList.getList().get(stripeList.getList().size()-1).pointEnd)
                    || addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointSt.distance(stripeList.getList().get(stripeList.getList().size()-1).pointEnd)<
                    addedRoad.stripeList.getList().get(0).pointSt.distance(stripeList.getList().get(stripeList.getList().size()-1).pointEnd)))
            {
                closestStart = addedRoad.stripeList.getList().get(0).pointSt.distance(stripeList.getList().get(0).pointEnd)<addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointSt.distance(stripeList.getList().get(stripeList.getList().size()-1).pointEnd);
                for (int i=0;i<stripeList.stripeList.size();i++)
                {
                    if(addedRoad.stripeList.getList().size()>i)
                        temp = addedRoad.stripeList.getList().get(i);
                    ConnectionStripe connectionStripe = new ConnectionStripe(stripeList.getList().get(i),temp,endOld,endNew,Math.max(stripeList.stripeList.size(),addedRoad.stripeList.getList().size()),false,closestStart,type);
                    connectionStripes.add(connectionStripe);
                    getChildren().add(connectionStripe);

                }
            }
            else{
                temp = addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1);
                for (int i=0;i<stripeList.stripeList.size();i++)
                {
                    if(addedRoad.stripeList.getList().size()-i>0)
                        temp = addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-i-1);
                    ConnectionStripe connectionStripe = new ConnectionStripe(stripeList.getList().get(i),temp,endOld,endNew,Math.max(stripeList.stripeList.size(),addedRoad.stripeList.getList().size()),true,false,type);
                    connectionStripes.add(connectionStripe);
                    getChildren().add(connectionStripe);

                }
            }
        }
        else if(!endOld && endNew){
            if((addedRoad.stripeList.getList().get(0).pointEnd.distance(stripeList.getList().get(0).pointSt)<
                    addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointEnd.distance(stripeList.getList().get(0).pointSt)
            || addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointEnd.distance(stripeList.getList().get(stripeList.getList().size()-1).pointSt)<
                    addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointEnd.distance(stripeList.getList().get(0).pointSt))
            && (addedRoad.stripeList.getList().get(0).pointEnd.distance(stripeList.getList().get(0).pointSt)<
                    addedRoad.stripeList.getList().get(0).pointEnd.distance(stripeList.getList().get(stripeList.getList().size()-1).pointSt)
                    || addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointEnd.distance(stripeList.getList().get(stripeList.getList().size()-1).pointSt)<
                    addedRoad.stripeList.getList().get(0).pointEnd.distance(stripeList.getList().get(stripeList.getList().size()-1).pointSt)))
            {
                closestStart = addedRoad.stripeList.getList().get(0).pointEnd.distance(stripeList.getList().get(0).pointSt)<addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointEnd.distance(stripeList.getList().get(stripeList.getList().size()-1).pointSt);
                for (int i=0;i<stripeList.stripeList.size();i++)
                {
                    if(addedRoad.stripeList.getList().size()>i)
                        temp = addedRoad.stripeList.getList().get(i);
                    ConnectionStripe connectionStripe = new ConnectionStripe(stripeList.getList().get(i),temp,endOld,endNew,Math.max(stripeList.stripeList.size(),addedRoad.stripeList.getList().size()),false,closestStart,type);
                    connectionStripes.add(connectionStripe);
                    getChildren().add(connectionStripe);

                }
            }
            else{
                temp = addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1);
                for (int i=0;i<stripeList.stripeList.size();i++)
                {
                    if(addedRoad.stripeList.getList().size()-i>0)
                        temp = addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-i-1);
                    ConnectionStripe connectionStripe = new ConnectionStripe(stripeList.getList().get(i),temp,endOld,endNew,Math.max(stripeList.stripeList.size(),addedRoad.stripeList.getList().size()),true,false,type);
                    connectionStripes.add(connectionStripe);
                    getChildren().add(connectionStripe);

                }
            }
        }
        else if(!endOld && !endNew){
            if((addedRoad.stripeList.getList().get(0).pointSt.distance(stripeList.getList().get(0).pointSt)<
                    addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointSt.distance(stripeList.getList().get(0).pointSt)
            || addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointSt.distance(stripeList.getList().get(stripeList.getList().size()-1).pointSt)<
                    addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointSt.distance(stripeList.getList().get(0).pointSt))
            && (addedRoad.stripeList.getList().get(0).pointSt.distance(stripeList.getList().get(0).pointSt)<
                    addedRoad.stripeList.getList().get(0).pointSt.distance(stripeList.getList().get(stripeList.getList().size()-1).pointSt)
                    || addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointSt.distance(stripeList.getList().get(stripeList.getList().size()-1).pointSt)<
                    addedRoad.stripeList.getList().get(0).pointSt.distance(stripeList.getList().get(stripeList.getList().size()-1).pointSt)))
            {
                closestStart = addedRoad.stripeList.getList().get(0).pointSt.distance(stripeList.getList().get(0).pointSt)<addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1).pointSt.distance(stripeList.getList().get(stripeList.getList().size()-1).pointSt);
                for (int i=0;i<stripeList.stripeList.size();i++)
                {
                    if(addedRoad.stripeList.getList().size()>i)
                        temp = addedRoad.stripeList.getList().get(i);
                    ConnectionStripe connectionStripe = new ConnectionStripe(stripeList.getList().get(i),temp,endOld,endNew,Math.max(stripeList.stripeList.size(),addedRoad.stripeList.getList().size()),false,closestStart,type);
                    connectionStripes.add(connectionStripe);
                    getChildren().add(connectionStripe);

                }
            }
            else{
                temp = addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-1);
                for (int i=0;i<stripeList.stripeList.size();i++)
                {
                    if(addedRoad.stripeList.getList().size()-i>0)
                        temp = addedRoad.stripeList.getList().get(addedRoad.stripeList.getList().size()-i-1);
                    ConnectionStripe connectionStripe = new ConnectionStripe(stripeList.getList().get(i),temp,endOld,endNew,Math.max(stripeList.stripeList.size(),addedRoad.stripeList.getList().size()),true,false,type);
                    connectionStripes.add(connectionStripe);
                    getChildren().add(connectionStripe);

                }
            }
        }

    }

    public ConnectionArcs getConnectionArcs() {
        return connectionArcs;
    }
}
