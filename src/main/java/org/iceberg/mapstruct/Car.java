package org.iceberg.mapstruct;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
 
    private String make;
    private int numberOfSeats;
    private String type;


    public static void main(String[] args) {
        Car car = new Car();
        car.setMake("123");
        System.out.println(car);
    }
}