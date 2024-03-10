package com.roadest.roadproredact.signs;

import com.roadest.roadproredact.models.Stripe;
import javafx.scene.Group;

import java.lang.reflect.Type;

public class Sign extends Group {
    Stripe stripe;
    SignType signType;
    double length;
    public Sign(Stripe stripe,SignType signType, double length)
    {
        this.length=length;
        this.signType=signType;
        this.stripe=stripe;
    }
    public void move()
    {

    }

    public SignType getSignType() {
        return signType;
    }
}
