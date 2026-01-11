import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Display extends JPanel implements ActionListener{
    int boardWidth = 720;
    int boardHeight = boardWidth;

    //double dt = 0.05;
    ArrayList<Pendulum> pendulums;
    ArrayList<Color> pendulum_colors;
    ArrayList<Double> angles1;
    ArrayList<Double> angles2;
    double energy = 0;
    double dt = 1;
    int gridScale = 720;
    int count = 0;
    Timer gameloop;

    int first = 150;
    int second = 20;
    int third = 20;

    Display(int bWidth, int bHeight)
    {
        this.boardWidth = bWidth;
        this.boardHeight = bHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        setFocusable(true);

        pendulums = new ArrayList<>();
        pendulum_colors = new ArrayList<>();

        angles1 = new ArrayList<>();
        angles2 = new ArrayList<>();
        

        for (int i = 0; i < gridScale; i++)
        {
            for (int j = 0; j < gridScale; j++)
            {
                Pendulum pend = new Pendulum(-Math.PI + i * Math.PI/360, -Math.PI + j*Math.PI/360);
                pendulums.add(pend);
                pendulum_colors.add(Color.white);
            }
        }

        gameloop = new Timer(0, this);
        gameloop.start();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        draw(g, g2d);
    }

    public void draw(Graphics g, Graphics2D g2d)
    {
        int index = 0;
        for (int i = 0; i < gridScale; i++)
        {
            for (int j = 0; j < gridScale; j++)
            {
                g2d.setColor(pendulum_colors.get(index));
                g2d.fillRect(i, gridScale - j - 1, 1, 1);

                index++;
            }
        }
        //display energy
        /*g.setFont(new Font("Verdana", Font.BOLD, 14));
        g.setColor(Color.white);
        g.drawString("Total Energy: " + energy, 100, 100);
        //grid
        int scale = 50;
        g.setColor(Color.gray);*/
        /*for (int i = 0; i < boardWidth/scale; i++)
        {
            g.drawLine(i * scale, 0, i * scale, boardHeight);
            g.drawLine(0, i * scale, boardWidth, i * scale);
        }*/
        //GRAPH!!!
        /*g.setColor(Color.white);
        for (int i = 0; i < angles1.size(); i++)
        {
            int point_x = (int)(900 + angles1.get(i) * 100);
            int point_y = (int)(400 + angles2.get(i) * 100);
            g.drawLine(point_x, point_y, point_x, point_y);
        }
        g.drawLine(600, 400, 1200, 400);
        g.drawLine(900, 100, 900, 700);

        //Draw Pendulums
        g2d.translate(300, 400);
        g2d.setColor(Color.white);
        for (Pendulum p : pendulums)
        {
            g2d.fillOval(-12, -12, 25, 25);
            g2d.drawLine(0, 0, (int)p.x1, (int)p.y1);
            g2d.fillOval((int)p.x1 - 12, (int)p.y1 - 12, 25, 25);
            g2d.drawLine((int)p.x1, (int)p.y1, (int)p.x2, (int)p.y2);
            g2d.fillOval((int)p.x2 - 12, (int)p.y2 - 12 , 25, 25);
        }
        g2d.translate(300, 100);*/
        //g2d.fillOval(-50, -50, 100, 100);
    }

    

    @Override
    public void actionPerformed(ActionEvent e)
    {
        int first_flip = 38;
        int second_flip = 85;
        int third_flip = 160;
        if (count < 10000)
        {
            for (int i = 0; i < gridScale * gridScale; i++)
            {
                if (i == 0)
                {
                    if (first < 208)
                        first += 2;
                    else if (second < 208)
                        second += 1;
                    else if (third < 208)
                        third += 1;
                    else {}
                    /*if (count < first_flip)
                        first += 3;
                    else if (count < second_flip)
                        second += 2;
                    else if (count < third_flip)
                        third += 2;
                    else {}*/
                }

                Pendulum p = pendulums.get(i);
                if (!p.hasFlipped)
                {
                    Physics.updatePhysicsRK4(p, dt);
                    if (p.hasFlipped)
                    {
                        Color newColor = new Color(first, second, third);
                        /*if (count < first_flip)
                        {
                            newColor = new Color(5, 115 + first, 38 + first);
                        }
                        else if (count < second_flip)
                            newColor = new Color(255, 0 + second, 75 + second);
                        else if (count < third_flip)
                            newColor = new Color(213, 92 + third, 255);
                        else
                            newColor = new Color(217, 174, 255);**/
                        pendulum_colors.set(i, newColor);
                    }
                }
                else { }
            }
            count++;
            System.out.println("Update Number " + count);
            //angles1.add(pendulums.get(0).a1);
            //angles2.add(pendulums.get(0).a2);
            repaint();
        }
        else
        {
            gameloop.stop();
        }
    }
}
