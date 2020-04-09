package AssociationAssets;

public class Field {

    String name;
    String city;
    int capacity;

    public Field(String name, String city, int capacity) {
        this.name = name;
        this.city = city;
        this.capacity = capacity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
