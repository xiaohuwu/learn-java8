package io.github.biezhi.java8.stream.lesson1;

import io.github.biezhi.java8.stream.Project;
import io.github.biezhi.java8.stream.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author biezhi
 * @date 2018/2/11
 */
public class Java8 {

    public static void main(String[] args) {
        List<Project> projects = Project.buildData();
        List<String> names = projects.stream().filter(p -> {
            System.out.println(p.getName());
            return p.getStars() > 1000;
        }).map(p -> {
            System.out.println(p.getName());
            return p.getName();
        }).limit(3).collect(Collectors.toList());
        System.out.println(names);

        names.stream().forEach(name -> System.out.println(name));


        System.out.println("---Min and Max for Integer---");
        List<Integer> numList = Arrays.asList(42, 44, 43, 41);
        OptionalInt min = numList.stream().mapToInt(Integer::intValue).min();
        int i = min.getAsInt();
        System.out.println("i = " + i);


        List<User> users = Arrays.asList(
                new User("Mahesh", 30),
                new User("Krishna", 29),
                new User("Virat", 28)
        );

        Optional<User> min1 = users.stream().min(Comparator.comparing(User::getAge));
        min1.ifPresent((item) -> {
            System.out.println("item = " + item);
        });


        List<String> list = Arrays.asList("xiao hu ", "good man");
        Stream<String> stringStream = list.stream().flatMap((item) -> {
            return Arrays.stream(item.split(" "));
        });
        List<String> collect = stringStream.collect(Collectors.toList());
        System.out.println("collect = " + collect);

    }

}
