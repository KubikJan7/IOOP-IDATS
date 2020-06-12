package procesy;

import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;
import kolekce.IAbstrDoubleList;
import kolekce.IAbstrLifo;

public interface IVyrobniProces {
    int importDat(String soubor) throws IOException;
    void vlozProces(Proces proces, VlozPoziceEnum pozice);
    Proces zpristupniProces(PoziceEnum pozice)throws IAbstrDoubleList.ListException;
    Proces odeberProces(PoziceEnum pozice)throws IAbstrDoubleList.ListException;
    Iterator iterator();
    IAbstrLifo vytipujKandidatiReorg(int Kriterium, ReorgEnum reorganizace);
    void reorganizace(ReorgEnum reorganizace, IAbstrLifo zasobnik);
    void zrus();
    Stream<Proces> stream();
    boolean jePrazdny();
}
