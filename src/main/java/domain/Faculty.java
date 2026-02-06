package domain;

public class Faculty {
    private final String id;
    private final String name;
    private final String shortName;
    private  String dean;
    private final String phoneNumber;

    public Faculty(String id, String name, String shortName, String dean, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.dean = dean;
        this.phoneNumber = phoneNumber;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getShortName() {
        return shortName;
    }
    public String getDean() {
        return dean;
    }

    public void setDean(String dean) {
        this.dean = dean;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    @Override
    public String toString() {
        return String.format("Faculty(id=%s, name=%s, shortName=%s, dean=%s, phoneNumber=%s)",
                id, name, shortName, dean, phoneNumber);
    }

}
