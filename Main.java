package search;

import java.util.*;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static Repository repository;

    public static void main(String[] args) {

        repository = new Repository(args);

        boolean exit = false;
        while (!exit) {
            printMenu();
            switch (Options.getOption(SCANNER.nextLine())) {
                case FIND -> find();
                case PRINT -> printAll();
                case EXIT -> exit = true;
                case INCORRECT -> System.out.println("Incorrect option! Try again.");
            }
        }
        System.out.println("Bye!");
    }

    private static void find() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        findWithStrategy(Strategies.getStrategy(SCANNER.nextLine()));
    }

    private static void findWithStrategy(Strategies strategy) {
        System.out.println("Enter a name or email to search all suitable people.");
        List<String> searchWords = Arrays.stream(SCANNER.nextLine().toLowerCase().split(" +")).toList();
        switch (strategy) {
            case ALL -> printAllList(searchWords);
            case ANY -> printAnyList(searchWords);
            case NONE -> printNoneList(searchWords);
            case INCORRECT -> System.out.println("Incorrect option! Try again.");
        }

    }

    private static void printAllList(List<String> searchWords) {
        List<List<Integer>> indexesList = getIndexesList(searchWords);

        if (indexesList.isEmpty()) {
            System.out.println("No matching people found.");
            return;
        }

        List<Integer> allList = new ArrayList<>(indexesList.get(0));
        for (List<Integer> currentList : indexesList) {
            List<Integer> tmp = new ArrayList<>();
            for (Integer index : allList) {
                if (currentList.contains(index))
                    tmp.add(index);
            }
            allList = tmp;
        }
        printList(allList);
    }

    private static void printAnyList(List<String> searchWords) {
        List<List<Integer>> indexesList = getIndexesList(searchWords);

        if (indexesList.isEmpty()) {
            System.out.println("No matching people found.");
            return;
        }
        printList(indexesList.stream().flatMap(List::stream).distinct().sorted().toList());
    }

    private static void printNoneList(List<String> searchWords) {
        List<List<Integer>> indexesList = getIndexesList(searchWords);

        List<Integer> anyList = indexesList.stream().flatMap(List::stream).distinct().sorted().toList();

        List<Person> nonePersonList = new ArrayList<>();
        for (int i = 0; i < repository.getPeople().size(); i++) {
            if (anyList.contains(i)) continue;
            nonePersonList.add(repository.getPeople().get(i));
        }

        if (nonePersonList.isEmpty()) {
            System.out.println("No matching people found.");
            return;
        }
        nonePersonList.forEach(System.out::println);
    }

    private static List<List<Integer>> getIndexesList(List<String> searchWords) {
        List<List<Integer>> indexesList = new ArrayList<>();
        repository.getInvertedIndexMap().forEach((s, indexList) -> {
            if (searchWords.contains(s.toLowerCase()))
                indexesList.add(indexList);
        });
        return indexesList;
    }

    private static void printList(List<Integer> indexList) {

        indexList.stream().map(repository.getPeople()::get).forEach(System.out::println);
    }

    private static void printAll() {
        System.out.println("=== List of people ===");
        repository.getPeople().forEach(System.out::println);
    }

    private static void printMenu() {
        System.out.println("""
                
                === Menu ===
                1. Find a person
                2. Print all people
                0. Exit
                """);
    }

}
