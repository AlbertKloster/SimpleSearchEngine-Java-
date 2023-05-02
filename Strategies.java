package search;

public enum Strategies {
    ALL("ALL"),
    ANY("ANY"),
    NONE("NONE"),
    INCORRECT("");

    public  final String name;

    Strategies(String name) {
        this.name = name;
    }

    public static Strategies getStrategy(String input) {
        for (Strategies strategy : Strategies.values()) {
            if (strategy.name.equals(input.trim().toUpperCase())) return strategy;
        }
        return INCORRECT;
    }

}
