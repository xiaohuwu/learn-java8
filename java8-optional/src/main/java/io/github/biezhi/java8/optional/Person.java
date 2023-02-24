package io.github.biezhi.java8.optional;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * https://blog.csdn.net/aaaPostcard/article/details/123596787
 */
public class Person {
    private Car car;
    private Optional<Car> optionalCar;

    public Optional<Car> getOptionalCar() {
        return optionalCar;
    }

    public Car getCar() {
        return car;
    }

    public static String getCarInsuranceName(Person person){
        if (person!=null){
            Car car = person.getCar();
            if (car!=null){
                Insurance insurance = car.getInsurance();
                if (insurance!=null){
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }



    public static String getCarInsurance_1(Person person){
        String aNull = Optional.ofNullable(person)
                .map(Person::getCar)
                .map(Car::getInsurance)
                .map(Insurance::getName).orElse("null");
        return aNull;
    }


    public static String getCarInsurance_2(Person person){
        Optional<Person> optionalPerson = Optional.of(person);
        return optionalPerson.flatMap(Person::getOptionalCar)
                .flatMap(Car::getOptionalInsurance)
                .flatMap(Insurance::getOptionalString)
                .orElse("");
    }

    public static void main(String[] args) {
        Person person = new Person();
        String insurance = getCarInsurance_1(person);
        System.out.println("insurance = " + insurance);
    }

}

class Car {

    private Insurance insurance;
    private Optional<Insurance> optionalInsurance;

    public Optional<Insurance> getOptionalInsurance() {
        return optionalInsurance;
    }

    public Insurance getInsurance() {
        return insurance;
    }
}

class Insurance {

    private String name;
    private Optional<String> optionalString;


    public Optional<String> getOptionalString() {
        return optionalString;
    }

    public String getName() {
        return name;
    }
}

