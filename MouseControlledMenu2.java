import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MouseControlledMenu2 extends JFrame {
    public String folder="/images/";
    private JButton startGameButton;
    private JButton chooseGameButton;
    private JButton settingsButton;
    private JButton exitButton;
    private JLabel titleLabel;
    public JLabel currentLevel;
    public int type = 0;
    public String[] lvlType = {"Оптика", "Электричество", "Механика"};
    public int level = 0;
    public String[] lvlComp = {"Лёгкий", "Средний", "Сложный"};
    private BufferedImage backgroundImage;

    public MouseControlledMenu2() {
        try {
            backgroundImage = ImageIO.read(new File(folder+"BG.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTitle("Game Menu");
        setSize(1900, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new BackgroundPanel()); 
        setLayout(new FlowLayout());
        
        startGameButton = new JButton(new ImageIcon(folder+"start.png"));
        chooseGameButton = new JButton( new ImageIcon(folder+"ChooseGame.png"));
        settingsButton = new JButton( new ImageIcon(folder+"Settings.png"));
        exitButton = new JButton( new ImageIcon(folder+"Exit.png"));
        startGameButton.setBorderPainted(false);
        startGameButton.setFocusPainted(false);
        startGameButton.setContentAreaFilled(false);
        chooseGameButton.setBorderPainted(false);
        chooseGameButton.setFocusPainted(false);
        chooseGameButton.setContentAreaFilled(false);
        settingsButton.setBorderPainted(false);
        settingsButton.setFocusPainted(false);
        settingsButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setContentAreaFilled(false);
        startGameButton.addActionListener(new MenuActionListener());
        chooseGameButton.addActionListener(new MenuActionListener());
        settingsButton.addActionListener(new MenuActionListener());
        exitButton.addActionListener(new MenuActionListener());

        add(chooseGameButton);
        add(settingsButton);
        add(startGameButton);
        add(exitButton);

        currentLevel = new JLabel("Тема: " + lvlType[type] + ", Сложность: " + lvlComp[level]);
        currentLevel.setFont(new Font("Arial", Font.BOLD, 40));
        currentLevel.setForeground(Color.WHITE);
        add(currentLevel);
    }

    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    private class MenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            if (source == startGameButton) {
                startGame();
            } else if (source == chooseGameButton) {
                chooseGame();
            } else if (source == settingsButton) {
                settings();
            } else if (source == exitButton) {
                exit();
            }
        }
    }

    private void startGame() {
        this.dispose();
        ImageMover1 imageMover = new ImageMover1(level, type);
        imageMover.setVisible(true);
    }
    private void chooseGame() {
        String selectedLevelType = (String) JOptionPane.showInputDialog(
                this,
                "Выберите уровень:",
                "Настройки",
                JOptionPane.QUESTION_MESSAGE,
                null,
                lvlType,
                lvlType[0]
        );

        if (selectedLevelType != null) {
            for (int i = 0; i < lvlType.length; i++) {
                if (lvlType[i].equals(selectedLevelType)) {
                    type = i;
                    break;
                }
            }
            currentLevel.setText("Тема: " + lvlType[type] + ", Сложность: " + lvlComp[level]);
        }
    }

    private void settings() {
        String selectedLevel = (String) JOptionPane.showInputDialog(
                this,
                "Выберите уровень сложности:",
                "Настройки",
                JOptionPane.QUESTION_MESSAGE,
                null,
                lvlComp,
                lvlComp[0]
        );

        if (selectedLevel != null) {
            for (int i = 0; i < lvlComp.length; i++) {
                if (lvlComp[i].equals(selectedLevel)) {
                    level = i;
                    break;
                }
            }
            currentLevel.setText("Тема: " + lvlType[type] + ", Сложность: " + lvlComp[level]);
        }
    }

    private void exit() {
        int confirm = JOptionPane.showConfirmDialog(this, "Вы уверены,что хотите выйти?", "Выход", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MouseControlledMenu2 menu = new MouseControlledMenu2();
            menu.setVisible(true);
        });
    }


    public int get_Type() {
        return type;
    }
    public int get_Level() {
        return level;
    }
}
