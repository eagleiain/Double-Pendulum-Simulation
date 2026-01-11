

public class Pendulum
{
    public double g = 1;
    public int scale = 75;
    public double r1 = scale * 2;
    public double r2 = scale * 2;
    public double m1 = 1;
    public double m2 = 1;
    public double a1;
    public double a2;
    public double a1_v = 0;
    public double a2_v = 0;
    public double x1;
    public double y1; //change
    public double init_y1 = scale * 2;
    public double x2;
    public double y2; //change
    public double init_y2 = scale * 4;
    public double[] state = new double[4];
    public boolean hasFlipped = false;

    Pendulum(double a1, double a2)
    {
        this.a1 = a1;
        this.a2 = a2;
        state[0] = a1;
        state[1] = a2;
        state[2] = a1_v;
        state[3] = a2_v;
    }

    public void setState(double[] state)
    {
        this.state = state;
        this.a1 = state[0];
        this.a2 = state[1];
        this.a1_v = state[2];
        this.a2_v = state[3];
    }
}