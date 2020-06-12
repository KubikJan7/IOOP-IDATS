package kolekce;

public interface IAbstrLifo <E>{
    void zrus();
    boolean jePrazdny();
    void vloz (E data);
    E odeber();
}
