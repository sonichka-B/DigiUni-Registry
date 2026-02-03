package domain;

public class University {
    private final String fullName;
    private final String shortName;
    private final String city;
    private final String address;

    public University(String fullName, String shortName, String city, String address) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.city = city;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }
    public String getShortName() {
        return shortName;
    }
    public String getCity() {
        return city;
    }
    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return String.format("University(fullName=%s, shortName=%s, city=%s, address=%s)",
                fullName, shortName, city, address);
    }

}
