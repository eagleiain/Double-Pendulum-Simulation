import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 720;
        int boardHeight = boardWidth;
        JFrame frame = new JFrame("doublePendulumSim");
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Display display = new Display(boardWidth, boardHeight);
        frame.add(display);
        frame.pack();
        display.requestFocus();
    }
}