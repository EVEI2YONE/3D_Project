package sample;

public class Camera {
    CoordinateConversion coordsConversion = new CoordinateConversion();
    //view/camera
    private double[] cartesian = { 0, 0, 0 }; //x, y, z
    private double[] polar = coordsConversion.rectToPolar(cartesian[0], cartesian[1], cartesian[2]); //theta, r, z
    private double[] spherical = coordsConversion.polarToSpherical(polar[0], polar[1], polar[2]); //theta, phi, rho

    public double getX() { return cartesian[0]; }
    public double getY() { return cartesian[1]; }
    public double getZ() { return cartesian[2]; }
    public double getTheta() { return polar[0]; }
    public double getR() { return polar[1]; }
    public double getPhi() { return spherical[1]; }
    public double getRho() { return spherical[2]; }

    public void updateX(double step) {
        cartesian[0] += step;
        polar = coordsConversion.rectToPolar(cartesian[0], cartesian[1], cartesian[2]);
        spherical = coordsConversion.rectToSpherical(cartesian[0], cartesian[1], cartesian[2]);
    }

    public void updateY(double step) {
        cartesian[1] += step;
        polar = coordsConversion.rectToPolar(cartesian[0], cartesian[1], cartesian[2]);
        spherical = coordsConversion.rectToSpherical(cartesian[0], cartesian[1], cartesian[2]);
    }

    public void updateZ(double step) {
        cartesian[2] += step;
        polar = coordsConversion.rectToPolar(cartesian[0], cartesian[1], cartesian[2]);
        spherical = coordsConversion.rectToSpherical(cartesian[0], cartesian[1], cartesian[2]);
    }

    public void updateTheta(double angle) {
        polar[0] += angle;
        cartesian = coordsConversion.polarToRect(polar[0], polar[1], polar[2]);
        spherical = coordsConversion.polarToSpherical(polar[0], polar[1], polar[2]);
    }

    public void updateRadius(double growth) {
        polar[1] += growth;
        cartesian = coordsConversion.polarToRect(polar[0], polar[1], polar[2]);
        spherical = coordsConversion.polarToSpherical(polar[0], polar[1], polar[2]);
    }

    public void updatePhi(double angle) {
        spherical[1] += angle;
        polar = coordsConversion.sphericalToPolar(spherical[0], spherical[1], spherical[2]);
        cartesian = coordsConversion.sphericalToRect(spherical[0], spherical[1], spherical[2]);
    }

    public void updateRho(double growth) {
        spherical[2] += growth;
        polar = coordsConversion.sphericalToPolar(spherical[0], spherical[1], spherical[2]);
        cartesian = coordsConversion.sphericalToRect(spherical[0], spherical[1], spherical[2]);
    }
}
