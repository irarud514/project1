public class Prak {
    private objDevice[] allDevices = {
            new objDevice("object (1).png", 0, 1, 30, 300, false),
            new objDevice("object (2).png", 1, 0, 200, 500, true),
            new objDevice("object (3).png", 3, 0, 270, 500, true),
            new objDevice("object (4).png", 4, 0, 340, 500, true),
            new objDevice("object (5).png", 5, 0, 410, 500, true), 
            new objDevice("object (9).png", 9, 1, 480, 500, true)
    };

    private String text;
    private String devices;
    private String name;
    private String help;
    private int[] numDevices;
    private objDevice[] objDevices = {};

    public Prak(String name, String text, String devices, int[] numDevices, String help) {
        this.name = name;
        this.text = text;
        this.devices = devices;
        this.help = help;
        this.numDevices = numDevices;
        initializeObjDevices(); // Инициализация objDevices
    }

    // Метод для инициализации objDevices на основе numDevices
    private void initializeObjDevices() {
        objDevices = new objDevice[numDevices.length];
        for (int i = 0; i < numDevices.length; i++)
                objDevices[i] = allDevices[numDevices[i]];

    }

    public String getText() {
        return text;
    }

    public String getDevices() {
        return devices;
    }

    public String getName() {
        return name;
    }

    public objDevice[] getObjDevices() {
        return objDevices;
    }

    public String getHelp() {
        return help;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setObjDevices(objDevice[] objDevices) {
        this.objDevices = objDevices;
    }

    public void setHelp(String help) {
        this.help = help;
    }
}