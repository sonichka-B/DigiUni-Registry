package domain;

public class Person {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String middleName;
    private final String dateOfBirth;
    private final String email;
    private final String phoneNumber;

    public Person(String id, String firstName, String middleName, String lastName,
                  String dateOfBirth, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    @Override
    public String toString() {
        return String.format("Person(id=%s, firstName=%s, middleName=%s, lastName=%s, dateOfBirth=%s, email=%s, phoneNumber=%s)",
                id, firstName, middleName, lastName, dateOfBirth, email, phoneNumber);
    }
}
