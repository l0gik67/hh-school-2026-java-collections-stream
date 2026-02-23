package tasks;

import common.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Задача 2
На вход принимаются две коллекции объектов Person и величина limit
Необходимо объеденить обе коллекции
отсортировать персоны по дате создания и выдать первые limit штук.
 */
public class Task2 {

  public static List<Person> combineAndSortWithLimit(Collection<Person> persons1,
                                                     Collection<Person> persons2,
                                                     int limit) {
    // по месту создается поток и лист размеров n + m, где n и m - размеры коллекций
    // по времени сортировка пройдет за nlgn -> и соберется в список за n
    // по месту получаем O(n) по времени наибольшее O(nlgn)
    // выбрал stream, потому что создавать список через циклы for займет много места (два обхода итераторами)
    return Stream.of(persons1, persons2)
            .flatMap(Collection::stream)
            .sorted(Comparator.comparing(Person::createdAt))
            .limit(limit)
            .toList();
  }
}
