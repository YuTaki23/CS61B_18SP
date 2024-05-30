public class NBody {
    public static double readRadius(String s) {
        In in = new In(s);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String s) {
        In in = new In(s);

        int n = in.readInt();
        in.readDouble();

        Planet[] allPlanets = new Planet[n];
        for (int i = 0; i < n; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            allPlanets[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return allPlanets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Planet planet : allPlanets) {
            planet.draw();
        }

        StdDraw.enableDoubleBuffering();

        int n = allPlanets.length;
        double time = 0;
        double[] xForces = new double[n];
        double[] yForces = new double[n];

        while (time < T) {
            for (int i = 0; i < n; i++) {
                double fx = allPlanets[i].calcForceExertedByX(allPlanets[i]);
                double fy = allPlanets[i].calcForceExertedByY(allPlanets[i]);
                xForces[i] = fx;
                yForces[i] = fy;
            }

            for (int i = 0; i < n; i++) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (int i = 0; i < n; i++) {
                allPlanets[i].draw();
            }

            StdDraw.show();

            StdDraw.pause(10);

            time += dt;
        }

        StdOut.printf("%d\n", n);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                    allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }
    }
}
