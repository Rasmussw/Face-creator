package com.example.facecreator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.skin.RadioButtonSkin;
import javafx.scene.effect.ColorInput;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class HelloApplication extends Application {
    static int width = 600;
    static int height = 600;
    static Canvas canvas = new Canvas(width, height);
    static GraphicsContext gc = canvas.getGraphicsContext2D();



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Group root = new Group();
        Scene scene = new Scene(root, width, height);
        stage.setTitle("Face");
        stage.setScene(scene);

        gc.clearRect(0, 0, width, height);
        root.getChildren().add(canvas);

        //til her

        drawPrimitiveFace();

        stage.show();

    }

    public static void drawPrimitiveFace() {

       //baggrundsfarve (en rektangel der fylder hele canvas)
        gc.setFill(Color.PINK);
        gc.fillRect(0,0,600,600);

        drawEars(50,10);
        drawShape(300,300);
        drawMouth(100);
        drawEyes(20, 5);

        int distanceBetweenEyes = distanceBetweenEyes(360,220);
        String distanceBetweenEyesMessage = "The distance between the eyes is ";
        System.out.println(distanceBetweenEyesMessage + distanceBetweenEyes);

        drawNose(  20);
        drawGlasses();
        drawEyeBrows(40,10);
        drawHappyOrSad();
        System.out.println("this is what you got, hope you like it😁");
    }

   //ansigtets ører
    public static void drawEars(int earSize, int earChanal){

        gc.setFill(Color.ORANGE);
        gc.fillOval(150, 200, earSize, earSize);
        gc.fillOval(width-200, 200, earSize, earSize);
        gc.setFill(Color.BLACK);
        gc.fillOval(160, 210, earChanal,earChanal);
        gc.fillOval(width-175, 210, earChanal,earChanal);

    }

    //return metode der retunere afstanden imellem øjnene
    public static int distanceBetweenEyes(int leftEyeXCoordinate, int rightEyeXCoordinate){
        int distanceBetweenEyes = leftEyeXCoordinate - rightEyeXCoordinate;
        return distanceBetweenEyes;
    }

    //skal ansigtet være happy eller sad
    public static void drawHappyOrSad() {

       System.out.println("Do you want a happy or sad face?");
        Scanner scanner = new Scanner(System.in);
        String happyOrSad = scanner.next().toLowerCase(Locale.ROOT);

        if (happyOrSad.equals("sad")) {
            System.out.println("that was sad");
            gc.setFill(Color.BLUE);
            int startYValueForTears = 230;
            for (int i = startYValueForTears; i < 600; i = i+25) {
                gc.fillOval(220,i,5,10);
                gc.fillOval(375, i,5,10);
            }
        } else if (happyOrSad.equals("happy")) {
            System.out.println("WUUUHUU🎉");
        }

    }

    //formen på ansigtet
    public static void drawShape(int widhtDiameter, int hightDiameter) {
        gc.setFill(Color.ORANGE);
        gc.fillOval(150, 150, widhtDiameter, hightDiameter);
    }

    //munden
    public static void drawMouth(int mouthSize) {
        gc.setFill(Color.RED);
        gc.fillOval(250,325,mouthSize,30);

    }

    //vælge hvilken form næsen skal have
    public static void drawNose(int noseSize){

        System.out.println("What shape do you want your nose to be? Chose between oval, square or a mix");
        Scanner scanner = new Scanner(System.in);
        String noseShape = scanner.next().toLowerCase(Locale.ROOT);

        if (noseShape.equals("square")){
            System.out.println("Thats gonna look cool!");
            gc.setFill(Color.BLACK);
            gc.fillRect(290,270,noseSize,noseSize);

        } else if (noseShape.equals("mix")){
            System.out.println("Thats gonna look cool!");
            gc.setFill(Color.BLACK);
            gc.fillRoundRect(290,270,noseSize,noseSize,10,10);

        } else if (noseShape.equals("oval")){
            gc.setFill(Color.BLACK);
            gc.fillOval(290, 270,noseSize,noseSize);
            System.out.println("Thats gonna look cool!");
        }

    }

   //Vælg om ansigtet skal have øjne
    public static void drawEyes(int eyesSize, int pupilSize) {

        //eyesColor.equals(Color.GREEN);

        System.out.println("Do you want Your face to have eyes?");
        Scanner scanner = new Scanner(System.in);
        String eyesYesOrNo;
        eyesYesOrNo = scanner.next().toLowerCase(Locale.ROOT);

        if (eyesYesOrNo.equals("yes")) {
            System.out.println("Exellent choice");

            gc.setFill(Color.GREEN);
            gc.fillOval(220,220,eyesSize,eyesSize);
            gc.setFill(Color.GREEN);
            gc.fillOval(360,220,eyesSize,eyesSize);
            gc.setFill(Color.BLACK);
            gc.fillOval(227, 227, pupilSize, pupilSize);
            gc.setFill(Color.BLACK);
            gc.fillOval(367,227, pupilSize, pupilSize);

        }else if (eyesYesOrNo.equals("no")){
            System.out.println("Thats gonna look weird, but here you go");
        }
    }

    //Vælg hvilken farve øjenbrynene skal være (mellem sort og hvid)
    public static void drawEyeBrows(int widthDiameter, int hightDiameter) {

        //System.out.println("What color do you want your eyebrows to be? chose between white and black");
        Scanner scanner = new Scanner(System.in);
        String blackEyeBrows = "black";
        String whiteEyeBrows = "white";

        String eyeBrowsColor;
        do{
            System.out.println("What color do you want your eyebrows to be? chose between white and black");
            while (scanner.hasNextLine()){
                String input = scanner.nextLine();
                System.out.println(input + " is not an option, you have to chose black or white");
            } eyeBrowsColor = scanner.nextLine();
        } while (eyeBrowsColor.equals(blackEyeBrows) & eyeBrowsColor.equals(whiteEyeBrows));

       //String eyeBrowsColor = scanner.next().toLowerCase(Locale.ROOT);


        if (eyeBrowsColor.equals(blackEyeBrows)) {
            System.out.println("Black it is");

            gc.setFill(Color.BLACK);
            gc.fillOval(210, 190, widthDiameter, hightDiameter);
            gc.setFill(Color.BLACK);
            gc.fillOval(350, 190, widthDiameter, hightDiameter);


        } else if (eyeBrowsColor.equals(whiteEyeBrows)) {
            System.out.println("White it is");

            gc.setFill(Color.WHITE);
            gc.fillOval(210, 190, widthDiameter, hightDiameter);
            gc.setFill(Color.WHITE);
            gc.fillOval(350, 190, widthDiameter, hightDiameter);
           }
        }

        //Vælg om der skal være briller
        public static void drawGlasses() {

        System.out.println("Do you want your face to have glasses?🤓");
        Scanner scanner = new Scanner(System.in);
        String glassesYesOrNo = scanner.next().toLowerCase(Locale.ROOT);

        if (glassesYesOrNo.equals("yes")) {
            System.out.println("nice🥸");

            gc.setLineWidth(5);
            gc.strokeOval(206, 206, 50, 50);
            gc.strokeOval(346, 206, 50, 50);
            gc.strokeLine(185, 206, 206, 225);
            gc.strokeLine(255, 225, 346, 225);
            gc.strokeLine(417, 206, 396, 225);

        }else if (glassesYesOrNo.equals("no")){
            System.out.println("No glasses it is");
        }
    }

    public static void main(String[] args) {
        String welcomeText = "Welcome to the face-machine🤟";
        System.out.println(welcomeText);

        launch();


    }
}