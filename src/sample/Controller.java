package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import shape.Point;
import shape.RectangularPrism;
import shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    Canvas canvas;

    ShapeFactory factory = new ShapeFactory();
    Camera camera = new Camera();

    //plane
    double[] coord = { 1, 1, 0 };
//    double[] i = { 1, 0, 0 };
//    double[] j = { 0, -1, 0 };
//    double[] k = { 0, 0, 1 };
    double centerX;
    double centerY;
    double centerZ;
    double prevX, prevY;
    List<RectangularPrism> shapes = new ArrayList<>();

    //double[][][] rotation = { {}, {}, {}};

    public void init() {
        centerX = canvas.getWidth()/2;
        centerY = canvas.getHeight()/2;
        scaleX = 1/canvas.getWidth();
        scaleY = 1/canvas.getHeight();
        camera.updateZ(50);
        double len = 100;
        RectangularPrism shape = new RectangularPrism(centerX, centerY, centerZ, len, len, len);
        shapes.add(shape);
        rotateX(shape, 15);
        rotateY(shape, 0);
        rotateZ(shape, 15);
        paint();
    }

    public void rotate(double xStep, double yStep, double zStep) {
        RectangularPrism shape = shapes.get(0);
        //for(double i = 0; i < 360; i += step) {
            rotateX(shape, xStep);
            rotateY(shape, yStep);
            rotateZ(shape, zStep);
            paint();
        //}
//        for(double angle = 0; angle < 360; angle += step) {
//            for(int i = 0; i < shapes.size(); i++) {
//                Shape shape = shapes.get(i);
//                rotateX(shape, angle);
//                paint();
//            }
//        }
    }

//    public void scaleShape(Shape shape, double scale) {
//        for(Point point : shape) {
//            point.x *= scale;
//            point.y *= scale;
//            point.z *= scale;
//        }
//    }

    public void matrixMultiplication(Point point, double[][] matrix) {

    }

    public void rotateBasis(double degrees) {
        for(double i = 0; i < degrees; i+= 0.01) {

        }
    }
//    public void paintBasis() {
//        double scale = 50;
//        GraphicsContext g = canvas.getGraphicsContext2D();
//        g.setStroke(Color.BLUE);
//        g.strokeLine(centerX, centerY, i[0]*scale+centerX, i[1]*scale+centerY); //i
//        g.setStroke(Color.RED);
//        g.strokeLine(centerX, centerY, j[0]*scale+centerX, j[1]*scale+centerY); //j
//        g.setStroke(Color.GREEN);
//        g.strokeLine(centerX, centerY, k[0]*scale+centerX, k[1]*scale+centerY); //k
//    }

    public void paint() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g.setFill(Color.RED);
        g.fillRect(centerX-1, centerY-1,2, 2);
        for(RectangularPrism shape: shapes) {
            shape.display(g);
        }
    }



    public void rotateX(Shape shape, double t) {
        double
            cost = Math.cos(t),
            sint = Math.sin(t);
        for(int i = 0; i < shape.points.length; i++) {
            Point p = shape.points[i];
            double y = p.y;
            double z = p.z;
            p.y = y*cost - z*sint;
            p.z = y*sint + z*cost;
        }
    }
    public void rotateY(RectangularPrism shape, double t) {
        double
            cost = Math.cos(t),
            sint = Math.sin(t);
        for(int i = 0; i < shape.points.length; i++) {
            Point p = shape.points[i];
            double x = p.x;
            double z = p.z;
            p.x =  x*cost + z*sint;
            p.z = -x*sint + z*cost;
        }
    }
    public void rotateZ(RectangularPrism shape, double t) {
        double
            cost = Math.cos(t),
            sint = Math.sin(t);
        for(int i = 0; i < shape.points.length; i++) {
            Point p = shape.points[i];
            double x = p.x;
            double y = p.y;
            p.x = x*cost - y*sint;
            p.y = x*sint + y*cost;
        }
    }

    double scaleX;
    double scaleY;
    public void onMouseDragged(MouseEvent e) {
        double x = e.getX(); //+ -> movement 1 (ccw), - -> movement 3 (cw)
        double y = e.getY(); //+ -> movement 4 (ccw), - -> movement 2 (cw)
        double yDiff = x-prevX;
        double xDiff = y-prevY;
        System.out.printf("x: %.02f, y: %.02f\n", xDiff, yDiff);
//        camera.updateTheta(x);
//        camera.updatePhi(-y);

        rotate(xDiff*scaleX, yDiff*scaleY, 0);
        prevX = x;
        prevY = y;
    }

    public void onMousePressed(MouseEvent e) {
        prevX = e.getX();
        prevY = e.getY();
    }

    public void onMouseReleased(MouseEvent e) {

    }
}
