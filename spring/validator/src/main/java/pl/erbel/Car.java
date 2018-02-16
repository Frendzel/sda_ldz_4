package pl.erbel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Car {

    @NotNull
    @Size(max = 25)
    private String owner;

    @NotNull
    @Min(2)
    @Max(7)
    private int doorNumber;

    @NotNull
    @Size(max = 11)
    private String color;

    @NotNull
    @Size(max = 10)
    private String type;

    @NotNull
    @Size(max = 10)
    private String brand;

    public Car() {
    }

    public Car(String owner, int doorNumber, String color, String type, String brand) {
        this.owner = owner;
        this.doorNumber = doorNumber;
        this.color = color;
        this.type = type;
        this.brand = brand;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(int doorNumber) {
        this.doorNumber = doorNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (doorNumber != car.doorNumber) return false;
        if (owner != null ? !owner.equals(car.owner) : car.owner != null) return false;
        if (color != null ? !color.equals(car.color) : car.color != null) return false;
        if (type != null ? !type.equals(car.type) : car.type != null) return false;
        return brand != null ? brand.equals(car.brand) : car.brand == null;
    }

    @Override
    public int hashCode() {
        int result = owner != null ? owner.hashCode() : 0;
        result = 31 * result + doorNumber;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "owner='" + owner + '\'' +
                ", doorNumber=" + doorNumber +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
