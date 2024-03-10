package com.roadest.roadproredact.signs;

import com.roadest.roadproredact.models.Stripe;

public class SignsFactory {
    public static Sign createSign(SignType type, Stripe stripe, double length) {
        Sign sign = null;

        switch (type) {
            case STOP:
                sign =  new StopSign(stripe,type,length);
                break;
            case SPEED:
                sign = new SpeedSign(stripe,type,length);
                break;
            case AWARE:
                sign =  new AwareSign(stripe,type,length);
                break;
            case MAIN:
                sign =  new MainSign(stripe,type,length);
                break;
        }

        return sign;
    }
}
