package search;

public class Person {
    private String firstName;
    private String lastName;
    private String email;

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = null;
    }

    public Person(String firstName) {
        this.firstName = firstName;
        this.lastName = null;
        this.email = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return (firstName + " " +
                (lastName != null ? lastName + " " : "") +
                (email != null ? email : "")).trim();
    }
}
