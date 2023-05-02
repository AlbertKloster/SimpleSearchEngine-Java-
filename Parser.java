package search;

public class Parser {

    public static Person parseToPerson(String input) {
        String[] split = input.split(" +");
        if (split.length == 1) return new Person(split[0].trim());
        if (split.length == 2) return new Person(split[0].trim(), split[1].trim());
        if (split.length == 3) return new Person(split[0].trim(), split[1].trim(), split[2].trim());
        return null;
    }

}
