
package procesy;

public enum ReorgEnum {
    DEKOMPOZICE("Dekompozice"), AGREGACE("Agreagace");

    private final String name;

    private ReorgEnum(String name) {
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
