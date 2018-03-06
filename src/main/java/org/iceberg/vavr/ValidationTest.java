package org.iceberg.vavr;

import io.vavr.collection.CharSeq;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

/**
 * Created by xiaoyuzhzh on 1/18/2018.
 */
public class ValidationTest {

    public static void main(String[] args) {
        PersonValidator personValidator = new PersonValidator();

        // Valid(Person(John Doe, 30))
        Validation<Seq<String>, Person> valid = personValidator.validatePerson("John Doe", 30);
        if(valid.isValid()){
            Person person = valid.get();
            System.out.println(person);
        }

        // Invalid(List(Name contains invalid characters: '!4?', Age must be greater than 0))
        Validation<Seq<String>, Person> invalid = personValidator.validatePerson("John? Doe!4", -1);
        if(invalid.isValid()){
            Person person1 = invalid.get();

        }else {
            Seq<String> error = invalid.getError();
            System.out.println(error.toString());
            System.out.println(error.intersperse(",").foldLeft(new StringBuilder(),StringBuilder::append).toString());
            System.out.println(List.ofAll(error).mkString(","));
        }

    }
}

class Person {

    public final String name;
    public final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person(" + name + ", " + age + ")";
    }

}

class PersonValidator {

    private static final String VALID_NAME_CHARS = "[a-zA-Z ]";
    private static final int MIN_AGE = 0;

    public Validation<Seq<String>, Person> validatePerson(String name, int age) {
        return Validation.combine(validateName(name), validateAge(age)).ap((a,b)-> new Person(a,b));
    }

    private Validation<String, String> validateName(String name) {
        return CharSeq.of(name).replaceAll(VALID_NAME_CHARS, "").transform(seq -> seq.isEmpty()
                ? Validation.valid(name)
                : Validation.invalid("Name contains invalid characters: '"
                + seq.distinct().sorted() + "'"));
    }

    private Validation<String, Integer> validateAge(int age) {
        return age < MIN_AGE
                ? Validation.invalid("Age must be at least " + MIN_AGE)
                : Validation.valid(age);
    }

}
