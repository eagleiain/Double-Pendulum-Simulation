public class Physics {
    public static double[] derivative(double[] state, Pendulum p)
    {
        double g = p.g;
        double m1 = p.m1;
        double m2 = p.m2;
        double r1 = p.r1;
        double r2 = p.r2;

        double a1 = state[0];
        double a2 = state[1];
        double a1_v = state[2];
        double a2_v = state[3];

        double num1 = -g * (2 * m1 + m2) * Math.sin(a1);
        double num2 = -m2 * g * Math.sin(a1 - 2 * a2);
        double num3 = -2 * Math.sin(a1 - a2) * m2;
        double num4 = a2_v * a2_v * r2 + a1_v * a1_v * r1 * Math.cos(a1 - a2);
        double den = r1 * (2 * m1 + m2 - m2 * Math.cos(2 * a1 - 2 * a2));
        double a1_a = (num1 + num2 + num3 * num4) / den;

        num1 = 2 * Math.sin(a1 - a2);
        num2 = (a1_v * a1_v * r1 * (m1 + m2));
        num3 = g * (m1 + m2) * Math.cos(a1);
        num4 = a2_v * a2_v * r2 * m2 * Math.cos(a1 - a2);
        den = r2 * (2 * m1 + m2 - m2 * Math.cos(2 * a1 - 2 * a2));
        double a2_a = (num1 * (num2 + num3 + num4)) / den;

        return new double[]{a1_v, a2_v, a1_a, a2_a};
    }

    public static void updatePhysicsRK4(Pendulum p, double dt)
    {
        double[] state = p.state;

        double[] k1 = derivative(state, p);

        double[] state2 = new double[4];
        for (int i = 0; i < 4; i++)
        {
            state2[i] = state[i] + dt * k1[i] / 2.0;
        }

        double[] k2 = derivative(state2, p);

        double[] state3 = new double[4];
        for (int i = 0; i < 4; i++)
        {
            state3[i] = state[i] + dt * k2[i] / 2.0;
        }
        double[] k3 = derivative(state3, p);

        double[] state4 = new double[4];
        for (int i = 0; i < 4; i++)
        {
            state4[i] = state[i] + dt * k3[i];
        }
        double[] k4 = derivative(state4, p);

        double[] newState = new double[4];
        for (int i = 0; i < 4; i++)
        {
            newState[i] = state[i] + dt * (k1[i] + 2*k2[i] + 2*k3[i] + k4[i]) / 6.0;
        }
        p.setState(newState);
        //update positions
        p.x1 = p.r1 * Math.sin(p.a1);
        p.y1 = p.r1 * Math.cos(p.a1);

        p.x2 = p.x1 + p.r2 * Math.sin(p.a2);
        p.y2 = p.y1 + p.r2 * Math.cos(p.a2);

        if (p.a1 >= Math.PI || p.a1 <= -Math.PI || p.a2 >= Math.PI || p.a2 <= -Math.PI)
        {
            p.hasFlipped = true;
        }
    }
}
