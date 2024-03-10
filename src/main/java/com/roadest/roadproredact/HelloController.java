package com.roadest.roadproredact;

import com.roadest.roadproredact.borders.BorderType;
import com.roadest.roadproredact.models.*;
import com.roadest.roadproredact.signs.Sign;
import com.roadest.roadproredact.signs.SignType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public BorderPane borderPane;
    public ToggleButton RoadButton;
    public ToolBar propertyBar;
    public MenuBar menuBar;
    public ToggleButton signButton;
    public ToggleButton roadConnect;
    public VBox roadAttrs;
    public VBox stripeAttrs;
    public VBox signAttrs;
    public Button save;
    public ToggleButton delete;
    public ToggleButton select;
    public ToggleButton movement;
    public ComboBox<BorderType> borderType;
    public ComboBox<SignType> signType;
    public Slider stripesCount;
    public ComboBox<RoadType> roadType;
    public Pane pane;
    public ToggleGroup creatings;
    Roads roads = new Roads();
    Road roadTemp = null;
    Point2D lastPointOfConnection;
    Point2D lastPoint;
    PointDeflection lastPointDef;
    boolean endOld;
    List<RoadCrest> roadCrestList=new ArrayList<>();
    boolean endNew;
    Road connectRoad;
    Stripe stripeMoused;
    Road selectedRoad =null;
    boolean deleteSign=false;
    Sign selectedSign = null;
    Point2D dragStartPoint=new Point2D(0,0);
    boolean onConnectZone=false;
    BackgroundFilling bg = new BackgroundFilling();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.setBackground(bg.getBackGround());
        borderPane.getLeft().setTranslateZ(2);
        borderPane.getCenter().setTranslateZ(1);
        signType.getItems().setAll(SignType.values());
        signType.getSelectionModel().select(0);
        roadType.getItems().setAll(RoadType.values());
        roadType.getSelectionModel().select(0);
        borderType.getItems().setAll(BorderType.values());
        borderType.getSelectionModel().select(0);
        creatings.selectedToggleProperty().addListener(e->{if (movement.isSelected())
            pane.getScene().setCursor(Cursor.OPEN_HAND);
        else pane.getScene().setCursor(Cursor.DEFAULT);});
        pane.setOnMouseDragged(e1->{
            if (movement.isSelected()) {
                pane.getScene().setCursor(Cursor.CLOSED_HAND);
                Point2D vector = new Point2D(e1.getSceneX()-dragStartPoint.getX()-propertyBar.getWidth(), e1.getSceneY()-dragStartPoint.getY()-menuBar.getHeight());
                roads.move(vector);
                for (RoadCrest roadCrest:roadCrestList) {
                    roadCrest.move(vector);
                }
                dragStartPoint = new Point2D(e1.getSceneX()-propertyBar.getWidth(), e1.getSceneY()-menuBar.getHeight());
            }
        });
        pane.setOnMousePressed(e1->{
            dragStartPoint=new Point2D(e1.getSceneX()-propertyBar.getWidth(), e1.getSceneY()-menuBar.getHeight());
        });
        pane.setOnMouseReleased(e1->{
            if(movement.isSelected())
                pane.getScene().setCursor(Cursor.OPEN_HAND);
        });
        pane.setOnMouseMoved(e3->{
            if(!onConnectZone)
                lastPoint=new Point2D(e3.getSceneX()-propertyBar.getWidth(), e3.getSceneY()-menuBar.getHeight());
            if(RoadButton.isSelected() && roadTemp!=null)
            {
                    pane.getChildren().remove(roadTemp);
                    roadTemp.move(lastPointOfConnection, lastPoint, new Point2D(0, 0));
                    pane.getChildren().add(roadTemp);
                    roadTemp.toBack();
            }
        });
        EventHandler<MouseEvent> click = e -> {
             if(RoadButton.isSelected() && roadTemp==null) {
                roadTemp = new Road(lastPointOfConnection=lastPoint,lastPoint, (int) stripesCount.getValue(),borderType.getValue(),roadType.getValue());
                roadTemp.toBack();
                if(connectRoad!=null) {
                    connectRoad.addConnectionStripes(roadTemp, endOld, endNew);
                }
                pane.getChildren().add(roadTemp);
            }
            else if(RoadButton.isSelected() && roadTemp!=null){
                if(onConnectZone)
                    lastPointDef.setConnected(true);
               Road created=roads.addRoad(roadTemp);
               created.setOnMouseClicked(e1->{
                   created.setEffect(null);
                   if(delete.isSelected() && !deleteSign) {
                       pane.getChildren().remove(created);
                       roads.delete(created);
                   }
                   else deleteSign=false;
               });
                 created.setOnMouseEntered(e1-> {
                   selectedRoad =created;
                   if(!movement.isSelected())
                    created.setEffect(new Glow(10));
               });
                 created.setOnMouseExited(e1-> {
                     selectedRoad =null;
                    created.setEffect(null);
               });

               for(Stripe stripe: roadTemp.getStripeList().getList()) {
                   stripe.setOnMouseEntered(e1->{
                       stripeMoused=stripe;
                   });
                   stripe.setOnMouseExited(e1->{
                       stripeMoused=null;
                   });
               }
               if(connectRoad!=null) {
                   connectRoad.addConnectionStripes(roadTemp, endOld, endNew);
                   connectRoad = null;
               }
               roadTemp.getConnectionArcs().getFinishArc().setOnMouseClicked(e1->{
                     if(RoadButton.isSelected() && roadTemp!=null)
                     {
                         created.addConnectionStripes(roadTemp,true,true);
                     } else if (RoadButton.isSelected() && roadTemp==null) {
                         connectRoad=created;
                         endNew = false;
                         endOld=true;
                     }
                });
                 roadTemp.getConnectionArcs().getStartArc().setOnMouseClicked(e1->{
                     if(RoadButton.isSelected() && roadTemp!=null)
                     {

                         created.addConnectionStripes(roadTemp,false,true);
                     } else if (RoadButton.isSelected() && roadTemp==null) {
                         connectRoad=created;
                         endOld=false;
                         endNew=false;
                     }
                 });
                roadTemp=null;
            }
            else if(signButton.isSelected() && stripeMoused!=null)
            {
                double length=new Point2D(e.getSceneX()-propertyBar.getWidth(),
                        e.getSceneY()-menuBar.getHeight()).distance(stripeMoused.getPointEnd());
                for (Road road: roads.getRoads() ) {
                    road.setEffect(null);
                }
                Sign sign=stripeMoused.addSign(signType.getValue(),length);

                sign.setOnMouseClicked(e1->{
                    if(delete.isSelected()) {
                        stripeMoused.deleteSign(sign);
                        deleteSign=true;
                    }
                });
                sign.setOnMouseEntered(e1->{
                    selectedSign =sign;
                    sign.setEffect(new Glow(10));
                });
                sign.setOnMouseExited(e1->{
                    selectedSign =null;
                    sign.setEffect(null);
                });
            } else if (roadConnect.isSelected()) {
                RoadCrest roadCrest;
                roadCrestList.add(roadCrest=new RoadCrest((int) stripesCount.getValue(),new Point2D(e.getSceneX()-propertyBar.getWidth(),
                        e.getSceneY()-menuBar.getHeight())));
                pane.getChildren().add(roadCrest);
                 for (PointDeflection point:roadCrest.pointsOfDeflections.getPointDeflections()) {
                     point.setOnMouseEntered(e1->{
                        onConnectZone=true;
                        lastPoint = point.getPoint();
                        lastPointDef=point;
                     });
                     point.setOnMouseExited(e1->{onConnectZone=false;});
                 }
                 roadCrest.setOnMouseEntered(e1->{roadCrest.setEffect(new Glow(10));});
                 roadCrest.setOnMouseExited(e1->{roadCrest.setEffect(null);});
                 roadCrest.setOnMouseClicked(e1->{
                     roadCrest.setEffect(null);
                     if(delete.isSelected()) {
                         pane.getChildren().remove(roadCrest);
                         roadCrestList.remove(roadCrest);
                     }
                 });
            }
        };
        pane.setOnMouseClicked(click);
    }
    @FXML
    private void helpItem()
    {
        new Alert(Alert.AlertType.INFORMATION,"Программа для проектирования дорожного полотна, а также для размещения знаков и разметки на нем",ButtonType.APPLY).show();
    }
    @FXML
    private void clearItem()
    {
        for (Road road:roads.getRoads())
            pane.getChildren().remove(road);
        for (RoadCrest roadCrest:roadCrestList)
            pane.getChildren().remove(roadCrest);
       roads.clear();
       roadCrestList.clear();
    }
    @FXML
    private void saveToWord() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбор папки для сохранения");
        fileChooser.setInitialFileName("");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("document", "*.docx"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null)
            WordExport.exportToWord(file.getAbsolutePath(),roads,roadCrestList);
    }
}