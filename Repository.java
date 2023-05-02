package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {

    private List<Person> people = new ArrayList<>();

    private Map<String, List<Integer>> invertedIndexMap = new HashMap<>();

    public Repository(String[] args) {
        init(args);
    }

    private void init(String[] args) {
        if (args.length < 2) return;
        if (!args[0].equals("--data")) return;
        String arg = args[0];
        String filename = args[1];
        FileHandler.read(filename).stream().map(Parser::parseToPerson).forEach(people::add);
        invertedIndexMap = IndexInverter.getInvertedIndexMap(people);
    }

    public List<Person> getPeople() {
        return people;
    }

    public Map<String, List<Integer>> getInvertedIndexMap() {
        return invertedIndexMap;
    }

}
