import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.net.URL;

public class CatProgram extends JFrame {
    private JButton yesButton, noButton;
    private JLabel catLabel;
    private boolean catConfirmed = false;

    public CatProgram() {
        setTitle("Are you a cat?");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel questionLabel = new JLabel("Are you a cat?");
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(questionLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        yesButton = new JButton("Yes");
        noButton = new JButton("No");
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        add(buttonPanel, BorderLayout.SOUTH);

        catLabel = new JLabel();
        catLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add ActionListeners to the buttons
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!catConfirmed) {
                    catConfirmed = true;
                    questionLabel.setVisible(false);
                    ImageIcon icon = new ImageIcon(getClass().getResource("cat.jpg"));
                    catLabel.setIcon(icon);
                    add(catLabel, BorderLayout.CENTER);
                    playSound("meow.wav");
                    JOptionPane.showMessageDialog(null, "No u're not lol!");
                }
            }
        });

        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void playSound(String soundFile) {
        try {
            URL url = getClass().getResource("/" + soundFile);
            System.out.println("URL: " + url); // Print the URL to verify

            Clip clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip.open(audioInputStream);
            clip.start();

            // Wait for the sound to finish playing
            clip.addLineListener(new LineListener() {
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CatProgram().setVisible(true);
            }
        });
    }
}