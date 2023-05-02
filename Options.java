package search;

public enum Options {

    FIND("1"),
    PRINT("2"),
    EXIT("0"),
    INCORRECT("");

    public final String name;

    Options(String name) {
        this.name = name;
    }

    public static Options getOption(String input) {
        for (Options option : Options.values()) {
            if (option.name.equals(input.trim().toLowerCase())) return option;
        }
        return INCORRECT;
    }
}
