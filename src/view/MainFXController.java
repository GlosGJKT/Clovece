package view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import model.Figurka;
import model.HraciPlocha;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainFXController {

    @FXML
    Canvas platno;

    @FXML
    AnchorPane plocha;

    @FXML
    Label kostkaLabel;

    LinkedList<Circle> figZ = new LinkedList<>();
    LinkedList<Color> barvy = new LinkedList<>();
    Circle circle;
    int[] indexy = new int[2];
    int hod;
    int[] domB = new int[4];
    int[] domG = new int[4];
    int[] domR = new int[4];
    int[] domY = new int[4];




    public void hriste(){

        barvy.add(Color.BLUE);
        barvy.add(Color.GREEN);
        barvy.add(Color.RED);
        barvy.add(Color.YELLOW);


        int b = 50;
        platno.getGraphicsContext2D().setFill(Color.MEDIUMPURPLE);
        for(int d=0;d<40;d++){
            platno.getGraphicsContext2D().fillOval(HraciPlocha.indexPole(d)[0],HraciPlocha.indexPole(d)[1],30,30);
        }
        platno.getGraphicsContext2D().setFill(Color.PURPLE); //startovni policka
        platno.getGraphicsContext2D().fillOval(19,215,30,30);
        platno.getGraphicsContext2D().fillOval(519,316,30,30);
        platno.getGraphicsContext2D().fillOval(319,15,30,30);
        platno.getGraphicsContext2D().fillOval(219,515,30,30);


        //domecky
        platno.getGraphicsContext2D().setFill(Color.BLUE); //leva
        for(int i = 69; i < 250; i += 50){
            platno.getGraphicsContext2D().fillOval(i, 265, 30, 30);
        }


        platno.getGraphicsContext2D().setFill(Color.RED); //prava
        for(int i = 319; i < 500; i += 50){
            platno.getGraphicsContext2D().fillOval(i, 265, 30, 30);
        }

        platno.getGraphicsContext2D().setFill(Color.GREEN); //horni
        for(int i = 65; i < 250; i += 50){
            platno.getGraphicsContext2D().fillOval(268, i, 30, 30);
        }

        platno.getGraphicsContext2D().setFill(Color.YELLOW); //dolni
        for(int i = 315; i < 500; i += 50){
            platno.getGraphicsContext2D().fillOval(268, i, 30, 30);
        }


        /*LinkedList<Color> barvy = new LinkedList<>();
        barvy.add(Color.BLUE);
        barvy.add(Color.GREEN);
        barvy.add(Color.RED);
        barvy.add(Color.YELLOW);*/


        //FIGURKY
        for(int x = 0; x < 4; x++){
            for(int y = 0; y < 4; y++) {
                Circle kol = new Circle();
                kol.setFill(barvy.get(x));
                kol.setRadius(15);
                figZ.add(kol);
                plocha.getChildren().add(kol);
                kol.setLayoutX(HraciPlocha.indexDomecku(x)[0]);
                kol.setLayoutY(HraciPlocha.indexDomecku(x)[1]);
                kol.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        pohyb(kol);
                    }
                });
            }
        }
    }

    public void domecek(Circle circle){
        if(circle.getFill()==Color.BLUE){

        }
        else if(circle.getFill()==Color.GREEN){

        }
        else if(circle.getFill()==Color.RED){

        }
        else {

        }
    }

    public void pohyb(Circle circle){
        if(hod == 6){
            if(vDomecku(circle)){
                nasadFig(circle);
            }
            else {
                posunFig(circle);
            }
        }
        else{
            posunFig(circle);
        }
    }

    public void nasadFig(Circle circle){
        if(circle.getFill()==Color.BLUE){
            circle.setLayoutX(HraciPlocha.indexPole(0)[0]);
            circle.setLayoutY(HraciPlocha.indexPole(0)[1]);
        }
        else if(circle.getFill()==Color.GREEN){
            circle.setLayoutX(HraciPlocha.indexPole(10)[0]);
            circle.setLayoutY(HraciPlocha.indexPole(10)[1]);
        }
        else if(circle.getFill()==Color.RED){
            circle.setLayoutX(HraciPlocha.indexPole(20)[0]);
            circle.setLayoutY(HraciPlocha.indexPole(20)[1]);
        }
        else {
            circle.setLayoutX(HraciPlocha.indexPole(30)[0]);
            circle.setLayoutY(HraciPlocha.indexPole(30)[1]);
        }


    }


    public void posunFig(Circle circle) {
        int kamIndex;
        int kde = souradnicePolicka(circle.getLayoutX(), circle.getLayoutY());

        if (kde + hod < 40) {
            kamIndex = kde + hod;
        } else {
            kamIndex = kde + hod - 40;
        }


        if (circle.getFill() == Color.BLUE) {
            if (kamIndex > 39) {
                if (kamIndex - 40 > 3) {
                    System.out.println("Figurkou nelze táhnout");
                } else {
                    domB[kamIndex - 40] = 1;
                }
            }
            else if (circle.getFill() == Color.GREEN) {
                if (kde >= 10) {
                } else {
                    if (kamIndex > 9 && kamIndex - 9 > 3) {
                        System.out.println("Figurkou nelze táhnout");
                    } else if (kamIndex > 9 && kamIndex - 9 < 4) {
                        domG[kamIndex - 9] = 1;
                    } else {
                    }
                }
            }
        }

        else if(circle.getFill()==Color.RED){

        }
        else {

        }

        circle.setLayoutX(HraciPlocha.indexPole(kamIndex)[0]);
        circle.setLayoutY(HraciPlocha.indexPole(kamIndex)[1]);
    }


    public void hod(){
        int a = HraciPlocha.hod();
        String b = Integer.toString(a);
        kostkaLabel.setFont(Font.font(40));
        kostkaLabel.setText(b);
        hod = a;
    }

    public int souradnicePolicka(double X, double Y) {

        int vysledek = 0;
        for (int j = 0; j < 40; j++) {
            if (X == HraciPlocha.indexPole(j)[0] && Y == HraciPlocha.indexPole(j)[1]) {
                vysledek = j;
                break;
            }
        }
        return vysledek;
    }

    public boolean vDomecku(Circle circle){
        if(circle.getLayoutX() == 535 || circle.getLayoutX() == 35){
            return true;
        }
        return false;
    }


    /*hod = HraciPlocha.hod();
        String b = Integer.toString(hod);
        platno.getGraphicsContext2D().setFill(Color.LIGHTPINK);
        platno.getGraphicsContext2D().fillRect(263,258,40,40);
        platno.getGraphicsContext2D().setFont(Font.font(40));
        platno.getGraphicsContext2D().setFill(Color.BLACK);
        platno.getGraphicsContext2D().fillText(b,273,297);*/


}