package shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangularPrism extends Shape{
    public RectangularPrism(double x, double y, double z, double l, double w, double d) {
        this.x = x;
        this.y = y;
        this.z = z;

        //create points/vertices of the shape
        points = new Point[8];
        points[0] = new Point(-w/2, -l/2, +d/2);
        points[1] = new Point(-w/2, +l/2, +d/2);
        points[2] = new Point(+w/2, +l/2, +d/2);
        points[3] = new Point(+w/2, -l/2, +d/2);
        points[4] = new Point(+w/2, +l/2, -d/2);
        points[5] = new Point(+w/2, -l/2, -d/2);
        points[6] = new Point(-w/2, -l/2, -d/2);
        points[7] = new Point(-w/2, +l/2, -d/2);

        //draw the connections between the points
        edges = new int[12][];
        edges[0] = new int[]{0, 1};
        edges[1] = new int[]{1, 2};
        edges[2] = new int[]{2, 3};
        edges[3] = new int[]{3, 0};
        edges[4] = new int[]{4, 5};
        edges[5] = new int[]{5, 6};
        edges[6] = new int[]{6, 7};
        edges[7] = new int[]{7, 4};
        edges[8] = new int[]{2, 4};
        edges[9] = new int[]{3, 5};
        edges[10] = new int[]{0, 6};
        edges[11] = new int[]{1, 7};
    }

    @Override
    public void display(GraphicsContext gc) {
        int r = 0;
        int g = r+10;
        int b = g+10;

        int radius = 2;
        //color points
        for(int i = 0; i < points.length; i++) {
            gc.setFill(Color.rgb(r, g, b));
            Point p = points[i];

            gc.fillOval(x+p.x-radius, y+p.y-radius, 4, 4);
            r = (r + 15) % 255;
            g = (g + r + 20) % 255;
            b = (b + g + 25) % 255;
        }

        gc.setLineWidth(2);
        //color edges
        for(int i = 0; i < edges.length; i++) {
            gc.setStroke(Color.rgb(r, g, b));
            int e1 = edges[i][0];
            int e2 = edges[i][1];
            Point p1 = points[e1];
            Point p2 = points[e2];

            gc.strokeLine(x+p1.x, y+p1.y, x+p2.x, y+p2.y);
            r = (r + 15) % 255;
            g = (g + r + 20) % 255;
            b = (b + g + 25) % 255;
        }
    }
}
