import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JWindow implements ActionListener {

    JButton button;
    JWindow window = new JWindow();

    MyWindow (boolean thereIsCalamari) {

        String message;
        ImageIcon icon;

        int targetPositionY = 570;
        int offScreenY = 800;

        if (thereIsCalamari) {
            message = "Calamari found!";
            icon = new ImageIcon("src/calamari.jpg");
        }
        else {
            message = "No Calamari!";
            icon = new ImageIcon("src/CryingEmojy.jpg");
        }

        //the window
        window.setLayout(null);
        window.setVisible(true);
        window.setSize(220,260);
        window.setLocation(1300,offScreenY);

        //the image
        Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        JLabel image = new JLabel(resizedIcon);
        image.setBounds(35, 40, 150, 150);

        //the text
        JLabel text = new JLabel(message);
        text.setForeground(Color.GREEN);
        text.setBounds(65, 10, 100, 35);

        //the border
        JLabel border = new JLabel();
        border.setBounds(0,0,220,260);
        border.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));

        //the button
        button = new JButton();
        button.setBounds(70, 200, 80, 35);
        button.setText("OK");
        button.addActionListener(this);

        window.getContentPane().setBackground(Color.BLACK);
        window.add(image);
        window.add(text);
        window.add(button);
        window.add(border);

        int speed = 5; // Pixels moved per tick

        Timer slideInTimer = new Timer(15, null);
        slideInTimer.addActionListener(e -> {
            Point loc = window.getLocation();
            if (loc.y > targetPositionY) {
                window.setLocation(loc.x, Math.max(targetPositionY, loc.y - speed));
            } else {
                slideInTimer.stop();

                Timer pauseTimer = new Timer(3000, event -> {
                    startSlideOut(window, targetPositionY, offScreenY, speed);
                });
                pauseTimer.setRepeats(false); // Run only once
                pauseTimer.start();
            }
        });
        slideInTimer.start();

    }

    private static void startSlideOut(Window window, int targetY, int offScreenY, int speed) {
        Timer slideOutTimer = new Timer(15, null);
        slideOutTimer.addActionListener(e -> {
            Point loc = window.getLocation();
            if (loc.y < offScreenY) {
                window.setLocation(loc.x, Math.min(offScreenY, loc.y + speed));
            } else {
                slideOutTimer.stop();
                window.dispose(); // Cleans up and closes the window completely
            }
        });
        slideOutTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            window.dispose();
        }
    }
}
