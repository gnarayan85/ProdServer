package com.str.engg.shape;

//TODO

//to update as in rectangle


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventType;
import static javafx.print.PrintColor.COLOR;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class GeneralShape extends Application {
//

    private double[] xCoords = {20, 120, 120, 90, 90, 50, 50, 20, 20};
    private double[] yCoords = {20, 20, 50, 50, 110, 110, 50, 50, 20};
    //       private double[] xCoords={20, 120, 120, 20,20};
    //      private double[] yCoords ={20,20,80,80,20};
    GraphicsContext gc;

//    public static void main(String[] args) {
//        launch(args);
//    }

    public GeneralShape() {

    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(300, 250);

        gc = canvas.getGraphicsContext2D();
        drawShapes();
        calculate(xCoords, yCoords);
        trial(70);
        root.getChildren().addAll(canvas);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void drawShapes() {
        gc.setStroke(Color.RED);
        gc.strokeLine(1, 1, 1, 249);
        gc.strokeLine(1, 1, 299, 1);

        gc.setStroke(Color.BLACK);
        gc.strokePolygon(xCoords,
                yCoords, xCoords.length - 1);

    }

    private void calculate(double[] xCoords, double[] yCoords) {

        int j = xCoords.length - 1;
        double area = 0;
        double cX = 0, cY = 0;
        for (int i = 0; i < j; i++) {
            area = area + ((xCoords[i] * yCoords[i + 1]) - (xCoords[i + 1] * yCoords[i]));
            cX = cX + ((xCoords[i] + xCoords[i + 1]) * ((xCoords[i] * yCoords[i + 1]) - (xCoords[i + 1] * yCoords[i])));
            cY = cY + ((yCoords[i] + yCoords[i + 1]) * ((xCoords[i] * yCoords[i + 1]) - (xCoords[i + 1] * yCoords[i])));
        }
        area /= 2;

        cX = cX / (area * 6);
        cY = cY / (area * 6);
        System.out.println("area = " + area + "\ncentroid X = " + cX + "\ncentroid Y = " + cY);

    }

    private void trial(double j) {
        int i;
        double x = 0, y = 0, temp;
        int k = 0;
        double[] newxArray = new double[25];
        double[] newyArray = new double[25];
        double smally = yCoords[0], bigy = yCoords[0];
        for (i = 0; i < yCoords.length - 1; i++) {
            if (smally >= yCoords[i + 1]) {
                smally = yCoords[i + 1];
            } else if (bigy <= yCoords[i + 1]) {
                bigy = yCoords[i + 1];
            }

        }
        
       for (i = 0; i < yCoords.length - 1; i++) {
            if (j == yCoords[i] || yCoords[i] < j) {
                newyArray[k] = yCoords[i];
                newxArray[k] = xCoords[i];
                k++;

            }
            if ((yCoords[i] < j && yCoords[i + 1] > j) || (yCoords[i] > j && yCoords[i + 1] < j)) {

                if (xCoords[i] == xCoords[i + 1]) {
                    newyArray[k] = j;
                    newxArray[k] = xCoords[i];
                    k++;
                } else {
                    newyArray[k] = yCoords[i];
                    newxArray[k] = ((j - yCoords[i]) * (xCoords[i + 1] - xCoords[i]) / (yCoords[i + 1] - yCoords[i])) + xCoords[i];
                    k++;
                }

            }

        }
        newyArray[k] = newyArray[0];
        newxArray[k] = newxArray[0];
        gc.setStroke(Color.BLUE);
        gc.strokePolygon(newxArray, newyArray, k);
        calculate(newxArray, newyArray);

    }
}
