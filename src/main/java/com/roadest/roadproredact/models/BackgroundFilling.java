package com.roadest.roadproredact.models;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class BackgroundFilling {
    Background bg;
    public BackgroundFilling()
    {
        bg = new Background(new BackgroundImage(new Image("background1.jpg",100,100,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT));
    }

    public Background getBackGround() {
        return bg;
    }
}
