package procesy;

public enum PoziceEnum {
    PRVNI("První"), POSLEDNI("Posledni"), PREDCHUDCE("Předchůdce"), NASLEDNIK("Následník"), AKTUALNI ("Aktuální");

    private final String name;

    private PoziceEnum(String name) {
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
