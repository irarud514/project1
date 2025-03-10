import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageMover1 extends JFrame {
    public String folder = "C:/Users/Irina/ujqlf/images/";
    private BackgroundPanel backgroundPanel;
    private int level;
    private int type;

    private Prak[] praki = {
            new Prak(
                    "1) Лампочка над полом.\n",
                    "Оборудование: Линейка, лампочка, лист бумаги.\n",
                    "Найдите расстояние от пола до потолка.",
                    new int[]{0, 1}, // Индексы для инициализации objDevice
                    "-"
            ),
            new Prak(
                    "2) Определение показателя преломления стекла\n",
                    "Оборудование: стеклянная призма, лазерная указка, линейка\n",
                    "Найдите показатель преломления стекла, из которого изготовлена призма.",
                    new int[]{2}, // Индекс для инициализации objDevice
                    "-"
            ),
            new Prak(
                    "3) Хроматические аберрации\n",
                    "Оборудование: Собирающая линза с известными радиусами кривизны, красный и синий светодиоды, линейка.\n" ,
                            "Показатель преломления линзы зависит от длины волны света, проходящего через линзу (то есть от цвета света). Измерьте разность показателей преломления для света красного и синего цветов.",
                    new int[]{3, 4, 2}, // Индексы для инициализации objDevice
                    "-"
            ),
            new Prak(
                    "1) Удельное сопротивление графита:\n",
                    "Оборудование: графитовый стержень, линейка, мультиметр\n",
                    "Измерьте удельное сопротивление материала выданного вам графитового стержня, используя линейку и мультиметр в режиме омметра.",
                    new int[]{0, 1}, // Индексы для инициализации objDevice
                    "-"
            ),
            new Prak(
                    "2) \"Звезда\" в сером ящике.\n",
                    "Оборудование: серый ящик с 6 выводами, мультиметр.\n",
                    "Внутри выданного серого ящика находятся 6 резисторов, соединенных \"звездой\". Проводя измерения между выводами, используя мультиметр в режиме омметра, найдите сопротивления резисторов.",
                    new int[]{2, 3}, // Индексы для инициализации objDevice
                    "-"
            ),
            new Prak(
                    "3) Неидеальный вольтметр.\n",
                    "Оборудование: Черный ящик с 3 выводами, мультиметр, резистор известного сопротивления\n",
                    "Определите сопротивление резисторов внутри черного ящика, проводя измерение с помощью мультиметра в режиме вольтметра.",
                    new int[]{0, 1, 5}, // Индексы для инициализации objDevice
                    "В условиях данной задачи считать вольтметр идеальным некорректно!"
            ),
            new Prak(
                    "1) Масса груза\n",
                    "Оборудование: линейка, штатив, груз известной массы, груз неизвестной массы\n",
                    "Найдите массу груза неизвестной массы.",
                    new int[]{1, 2, 3, 4}, // Индексы для инициализации objDevice
                    "гойда"
            ),
            new Prak(
                    "2) Усилитель\n",
                    "Оборудование: весы, линейка, штатив, груз малой массы, предмет цилиндрической формы\n",
                    "Найдите массу груза (Примечание: масса груза слишком мала, чтобы измерить ее напрямую с помощью весов.)",
                    new int[]{1, 3}, // Индексы для инициализации objDevice
                    "Соберите рычажную конструкцию, позволяющую измерить массу."
            ),
            new Prak(
                    "3) Тепловое расширение\n",
                    "Оборудование: штатив, две линейки, мензурка с горячей водой, термометр, трубка\n",
                    "Определите коэффициент линейного расширения материала трубки. (Примечание: коэффициент линейного расширения равен относительному изменению линейных размеров тела (∆l/l) при изменении температуры на 1 °С (∆Т). α=∆l/(l∆T)).",
                    new int[]{3, 4}, // Индексы для инициализации objDevice
                    "-"
            )
    };

    public ImageMover1(int level, int type) {
        this.level = level;
        this.type = type;

        setTitle("Game");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null);
        add(backgroundPanel, BorderLayout.CENTER);

        loadDevicesForLevelAndType();
        int num = type * 3 + level;

        JButton abobaButton = new JButton("Помощь");
        abobaButton.setBounds(50, 250, 100, 30);
        backgroundPanel.add(abobaButton);
        abobaButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, praki[num].getHelp(), "Информация", JOptionPane.INFORMATION_MESSAGE);
        });

        JLabel nameLabel = new JLabel(praki[num].getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 25));
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setBounds(10, 10, 1900, 30);
        backgroundPanel.add(nameLabel);

        JLabel textLabel = new JLabel(praki[num].getText());
        textLabel.setFont(new Font("Arial", Font.BOLD, 24));
        textLabel.setForeground(Color.BLACK);
        textLabel.setBounds(10, 100, 1900, 50);
        backgroundPanel.add(textLabel);

        JLabel deviceLabel = new JLabel(praki[num].getDevices());
        deviceLabel.setFont(new Font("Arial", Font.BOLD, 24));
        deviceLabel.setForeground(Color.BLACK);
        deviceLabel.setBounds(10, 200, 1900, 50);
        backgroundPanel.add(deviceLabel);
    }

    private void loadDevicesForLevelAndType() {
        int num = type * 3 + level;
        objDevice[] deviceObjs = praki[num].getObjDevices(); // Получаем индексы устройств
        for (objDevice device : deviceObjs) {
            try {
                BufferedImage image = ImageIO.read(new File(folder + device.getImagePath()));
                DevicePanel devicePanel = new DevicePanel(image, device);
                devicePanel.setBounds(device.getXStart(), device.getYStart(), image.getWidth(), image.getHeight());
                backgroundPanel.add(devicePanel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class BackgroundPanel extends JPanel {
        private BufferedImage backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = ImageIO.read(new File(folder + "background.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    private class DevicePanel extends JPanel {
        private BufferedImage image;
        private objDevice device;
        private boolean dragging = false;
        private Point dragStartPoint;

        public DevicePanel(BufferedImage image, objDevice device) {
            this.image = image;
            this.device = device;
            setBounds(device.getXStart(), device.getYStart(), image.getWidth(), image.getHeight());
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setOpaque(false);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    dragging = true;
                    dragStartPoint = e.getPoint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    dragging = false;
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (dragging && device.isMovable()) {
                        int newX = e.getXOnScreen() - dragStartPoint.x;
                        int newY = e.getYOnScreen() - dragStartPoint.y;

                        int frameWidth = getParent().getWidth();
                        int frameHeight = getParent().getHeight();

                        if (newX < 0) {
                            newX = 0;
                        } else if (newX + image.getWidth() > frameWidth) {
                            newX = frameWidth - image.getWidth();
                        }

                        if (newY < 0) {
                            newY = 0;
                        } else if (newY + image.getHeight() > frameHeight) {
                            newY = frameHeight - image.getHeight();
                        }

                        boolean canMove = true;
                        for (Component comp : backgroundPanel.getComponents()) {
                            if (comp instanceof DevicePanel) {
                                DevicePanel otherDevicePanel = (DevicePanel) comp;
                                if (device.getConnectType() == otherDevicePanel.device.getType()) {
                                    Rectangle otherBounds = otherDevicePanel.getBounds();
                                    if (otherBounds.contains(newX, newY)) {
                                        newX = otherDevicePanel.device.getXConnect();
                                        newY = otherDevicePanel.device.getYConnect();
                                        canMove = false;
                                        break;
                                    }
                                }
                            }
                        }

                        if (canMove) {
                            setBounds(newX, newY, image.getWidth(), image.getHeight());
                            device.setX(newX);
                            device.setY(newY);
                            backgroundPanel.revalidate();
                            backgroundPanel.repaint();
                        } else {
                            setBounds(newX, newY, image.getWidth(), image.getHeight());
                        }
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageMover1 imageMover = new ImageMover1(0, 1);
            imageMover.setVisible(true);
        });
    }
}
