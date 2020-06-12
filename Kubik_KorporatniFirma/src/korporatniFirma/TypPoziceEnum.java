package korporatniFirma;

public enum TypPoziceEnum {
    REDITEL_POBOCKY("Ředitel pobočky"),
    VEDOUCI_USEKU("Vedoucí úseku"),
    VEDOUCI_ODDELENI("Vedoucí oddělení"),
    PRACOVNIK_ODDELENI("Pracovník oddělení");
    private final String name;

    private TypPoziceEnum(String name) {
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
