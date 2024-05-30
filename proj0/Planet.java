public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p1){
        double dx = xxPos - p1.xxPos;
        double dy = yyPos - p1.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p2){
        return G * mass * p2.mass / (calcDistance(p2) * calcDistance(p2));
    }

    public double calcForceExertedByX(Planet p3){
        double F = calcForceExertedBy(p3);
        double r = calcDistance(p3);
        double dx = p3.xxPos - xxPos;
        return F * dx / r;
    }

    public double calcForceExertedByY(Planet p4){
        double F = calcForceExertedBy(p4);
        double r = calcDistance(p4);
        double dy = p4.yyPos - yyPos;
        return F * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double allForce = 0;
        for(int i = 0; i < allPlanets.length; i++){
            if (this.equals(allPlanets[i])){
               continue;
            }
            allForce += calcForceExertedByX(allPlanets[i]);
        }
        return allForce;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        double allForce = 0;
        for(Planet allPlanet : allPlanets){
            if(this.equals(allPlanet)){
                continue;
            }
            allForce += calcForceExertedByY(allPlanet);
        }
        return allForce;
    }

    public void update(double dt, double fX, double fY){
        double FX = fX / this.mass;
        double FY = fY / this.mass;

        this.xxVel += dt * FX;
        this.yyVel += dt * FY;

        this.xxPos += dt * xxVel;
        this.yyPos += dt * yyVel;
}

    public void draw() {
        StdDraw.picture(xxPos, yyPos, ".images/" + imgFileName);
    }
}
