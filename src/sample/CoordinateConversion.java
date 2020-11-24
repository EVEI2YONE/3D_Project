package sample;

public class CoordinateConversion {

    public double[] rectToPolar(double x, double y, double z) {
        return new double[]{ normalizeAngle(x, y), mag(x,y), z };
    }

    public double[] polarToSpherical(double theta, double r, double z) {
        return new double[]{ theta, 90.0-normalizeAngle(r, z), mag(r, z)};
    }

    public double[] rectToSpherical(double x, double y, double z) {
        double[] polar = rectToPolar(x, y, z);
        return polarToSpherical(polar[0], polar[1], polar[2]);
    }

    public double[] sphericalToPolar(double theta, double phi, double rho) {
        phi -= 90;
        double r = rho*Math.cos(phi);
        double z = rho*Math.sin(phi);
        return new double[]{ theta, r, z};
    }

    public double[] polarToRect(double theta, double r, double z) {
        double x = r*Math.cos(theta);
        double y = r*Math.sin(theta);
        return new double[]{ x, y, z };
    }

    public double[] sphericalToRect(double theta, double phi, double rho) {
        double[] polar = sphericalToPolar(theta, phi, rho);
        return new double[]{ polar[0], polar[1], polar[2] };
    }

    public double normalizeAngle(double x, double y) {
        double RadToDeg = (Math.PI*2)/360;
        return Math.atan2(y, x) * RadToDeg;
    }
    public double mag(double a, double b) {
        return Math.sqrt(a*a+b*b);
    }
    public double mag(double a, double b, double c) {
        return Math.sqrt(a*a+b*b+c*c);
    }

}
