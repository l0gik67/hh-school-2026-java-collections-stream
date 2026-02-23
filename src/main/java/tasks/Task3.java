package tasks;

import common.Person;

import java.util.*;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
public class Task3 {

  public static List<Person> sort(Collection<Person> persons) {
    // Создание нового списка - по времени и месту O(n), n - количество person в начальной коллекции
    // сортировка O(nlgn)
    // общая сложность по времени O(nlgn), по месту O(n)
    // Добавил дополнительное условие к каждому полю на null
    return persons.stream()
            .sorted(Comparator.comparing(Person::secondName, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER))
                    .thenComparing(Person::firstName, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER))
                    .thenComparing(Person::createdAt, Comparator.nullsLast(Comparator.naturalOrder())))
            .toList();
  }
}
