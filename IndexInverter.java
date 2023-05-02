package search;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexInverter {

    public static Map<String, List<Integer>> getInvertedIndexMap(List<Person> people) {
        Map<String, List<Integer>> invertedIndexMap = new HashMap<>();
        for (int i = 0; i < people.size(); i++) {
            for (Field field : people.get(i).getClass().getDeclaredFields()) {
                field.setAccessible(true);
                String value;
                try {
                    value = (String)field.get(people.get(i));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                if (value != null) {
                    if (invertedIndexMap.containsKey(value)) {
                        List<Integer> indexes = invertedIndexMap.get(value);
                        List<Integer> updatedIndexes = new ArrayList<>(indexes);
                        updatedIndexes.add(i);
                        invertedIndexMap.put(value, updatedIndexes);
                    } else {
                        invertedIndexMap.put(value, List.of(i));
                    }
                }
            }
        }
        return invertedIndexMap;
    }

}
