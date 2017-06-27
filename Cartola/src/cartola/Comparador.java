package cartola;

import java.util.*;

public class Comparador implements Comparator<Participante> {

    @Override
    public int compare(Participante o1, Participante o2) {
        if (o1.getPontuacao() > o2.getPontuacao()) {
            return -1;
        } else if (o1.getPontuacao() < o2.getPontuacao()) {
            return 1;
        } else {
            return 0;
        }
    }

}
