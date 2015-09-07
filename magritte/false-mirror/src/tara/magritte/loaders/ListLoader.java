package tara.magritte.loaders;

import java.util.ArrayList;
import java.util.List;

public class ListLoader {

    public static List<Double> toList(double... doubles) {
        List<Double> result = new ArrayList<>();
        for (Double aDouble : doubles) result.add(aDouble);
        return result;
    }

    public List<Integer> toList(int... integers) {
        List<Integer> result = new ArrayList<>();
        for (Integer integer : integers) result.add(integer);
        return result;
    }
}
