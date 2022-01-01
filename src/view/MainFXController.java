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
    Circle fig;
    int[][] indexy = new int[2][1];
    int hod;




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
                        fig = kol;
                        indexy[0][0] = (int) kol.getLayoutX();
                        indexy[1][0] = (int) kol.getLayoutY();
                        pohyb();
                    }
                });
            }
        }
    }



    public void pohyb(){
        if(hod == 6){
            if(vDomecku()==true){
                nasadFig();
            }
            posunFig();
        }
        else{
            posunFig();
        }
    }

    public void nasadFig(){
        if(fig.getFill()==Color.BLUE){
            fig.setLayoutX(HraciPlocha.indexPole(0)[0]);
            fig.setLayoutY(HraciPlocha.indexPole(0)[1]);
        }
        else if(fig.getFill()==Color.GREEN){
            fig.setLayoutX(HraciPlocha.indexPole(10)[0]);
            fig.setLayoutY(HraciPlocha.indexPole(10)[1]);
        }
        else if(fig.getFill()==Color.RED){
            fig.setLayoutX(HraciPlocha.indexPole(20)[0]);
            fig.setLayoutY(HraciPlocha.indexPole(20)[1]);
        }
        else {
            fig.setLayoutX(HraciPlocha.indexPole(30)[0]);
            fig.setLayoutY(HraciPlocha.indexPole(30)[1]);
        }


    }

    public void posunFig() {

        int x = 0;

        while(indexy[0][0] != HraciPlocha.indexPole(x)[0] && indexy[1][0] != HraciPlocha.indexPole(x)[1]){
            x++;
        }
        int kamIndex;
        if(x+hod < 40){
            kamIndex = x+hod;
        }
        else{
            kamIndex = x+hod - 40;
        }
        fig.setLayoutX(HraciPlocha.indexPole(kamIndex)[0]);
        fig.setLayoutY(HraciPlocha.indexPole(kamIndex)[1]);
    }

    public void hod(){
        int a = HraciPlocha.hod();
        String b = Integer.toString(a);
        kostkaLabel.setFont(Font.font(40));
        kostkaLabel.setText(b);
        hod = a;
    }


    public boolean vDomecku(){
        if(fig.getLayoutX() == 535 || fig.getLayoutX() == 35){
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