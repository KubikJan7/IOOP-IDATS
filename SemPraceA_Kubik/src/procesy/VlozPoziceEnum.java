package procesy;

public enum VlozPoziceEnum {
    PRVNI("První"), POSLEDNI("Posledni"), PREDCHUDCE("Předchůdce"), NASLEDNIK("Následník");

    private final String name;

    private VlozPoziceEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
