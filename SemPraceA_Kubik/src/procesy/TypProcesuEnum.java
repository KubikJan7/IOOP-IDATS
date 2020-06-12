
package procesy;

public enum TypProcesuEnum {
    ROBOT("Robotický"), MANUAL("Manualní");

    private final String name;

    private TypProcesuEnum(String name) {
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
