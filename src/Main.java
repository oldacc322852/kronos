import javax.swing.*;
import java.awt.*;

public class Main {

    private static JFrame frame;
    private static JComboBox<String> comboBox;
    private static JButton startButton;
    private static JButton stopButton;
    private static JLabel timePassed;

    public static void main(String[] args) {

        // Creates the JFrame
        frame = new JFrame();

        // Creates the app icon
        ImageIcon clockIcon = new ImageIcon("clock2.png");

        // Creates the drop-down menu to select the programming language
        String[] options = {"Java", "Python", "HTML/CSS", "JavaScript"};
        comboBox = new JComboBox<>(options);
        comboBox.setSelectedIndex(0);
        comboBox.setFont(new Font("RuneScape UF", Font.PLAIN, 16));

        // Creates the time label 00:00:00
        timePassed = new JLabel();
        timePassed.setText("00:00:00");
        timePassed.setFont(new Font("RuneScape UF", Font.PLAIN, 32));
        timePassed.setVerticalAlignment(JLabel.BOTTOM);

        // Creates the start button
        startButton = new JButton();
        startButton.setText("START");
        startButton.setFont(new Font("RuneScape UF", Font.PLAIN, 16));
        startButton.setFocusable(false);

        // Creates the stop button
        stopButton = new JButton();
        stopButton.setText("STOP");
        stopButton.setFont(new Font("RuneScape UF", Font.PLAIN, 16));
        stopButton.setFocusable(false);

        // JFrame configuration
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(240, 120);
        frame.setResizable(false);
        frame.setTitle("Kronos");
        frame.setIconImage(clockIcon.getImage());
        frame.setLayout(new GridLayout(2, 2, 10, 10));
        frame.add(comboBox);
        frame.add(timePassed);
        frame.add(startButton);
        frame.add(stopButton);
        frame.setVisible(true); // Makes the frame visible



    }
}
