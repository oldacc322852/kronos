import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Main implements ActionListener{

    private static JFrame frame;
    private static JComboBox<String> comboBox;
    private static JButton startButton;
    private static JButton stopButton;
    private static JLabel timePassed;
    private static int selected = 0;
    private static boolean isRunning = false;
    private static long minutesDisplay = 0;
    private static long hoursDisplay = 0;
    private static int stupidFix = 0;
    private static long startTime;

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
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == comboBox) {
                    selected = comboBox.getSelectedIndex() + 1;
                }
            }
        });

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
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == startButton) {
                    isRunning = true;
                    Thread kronosThread = new Thread(){

                        public void run(){
                            startTime = System.currentTimeMillis();
                            while (isRunning) {
                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                } catch (InterruptedException interruptedException) {
                                    interruptedException.printStackTrace();
                                }

                                long elapsedTime = System.currentTimeMillis() - startTime;
                                long elapsedSeconds = elapsedTime / 1000;
                                if (elapsedSeconds == 60) {
                                    elapsedSeconds = 0;
                                    startTime = System.currentTimeMillis();
                                }
                                if ((elapsedSeconds % 60) == 0) {
                                    minutesDisplay++;
                                }
                                if ((minutesDisplay % 60) == 0 && stupidFix > 0) {
                                    stupidFix++;
                                    hoursDisplay++;
                                }
                                String h = String.format("%02d", hoursDisplay);
                                String m = String.format("%02d", minutesDisplay);
                                String s = String.format("%02d", elapsedSeconds);

                                timePassed.setText(h + ":" + m + ":" + s);
                            }
                        }
                    };
                    kronosThread.start();
                }
            }
        });

        // Creates the stop button
        stopButton = new JButton();
        stopButton.setText("STOP");
        stopButton.setFont(new Font("RuneScape UF", Font.PLAIN, 16));
        stopButton.setFocusable(false);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == stopButton) {
                    isRunning = false;
                    try {
                        writeTimelog();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
            }
        });

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

    public static void writeTimelog() throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        String language = comboBox.getSelectedItem().toString();
        String timeStudied = timePassed.getText();
        String line = date + ", " + language + ", " + timeStudied;

        FileWriter writer = new FileWriter("timelog.csv", true);
        writer.append(line).append("\n");
        writer.close();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
