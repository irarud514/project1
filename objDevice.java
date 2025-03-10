public class objDevice {
    private String imagePath; // Путь к изображению
    private int type;         // Тип устройства
    private int connectType;  // Тип подключения
    private int xStart;       // Начальная позиция по X
    private int yStart;       // Начальная позиция по Y
    private boolean movable;   // Возможность перемещения
    private int x;
    private int y;
    private int xConnect=0;
    private int yConnect=0;

    public objDevice(String imagePath, int type, int connectType, int xStart, int yStart, boolean movable) {
        this.imagePath = imagePath;
        this.type = type;
        this.connectType = connectType;
        this.xStart = xStart;
        this.yStart = yStart;
        this.x=xStart;
        this.y=yStart;
        this.movable = movable;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getType() {
        return type;
    }

    public int getConnectType() {
        return connectType;
    }

    public int getXConnect() {
        return xConnect+x;
    }

    public int getYConnect() {
        return yConnect+y;
    }
    public int getXStart(){
        return xStart;
    }
    public int getYStart(){
        return yStart;
    }
    public boolean isMovable() {
        return movable;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y){
        this.y=y;
    }
}