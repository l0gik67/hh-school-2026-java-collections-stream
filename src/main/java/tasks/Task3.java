package tasks;

import common.Person;

import java.util.*;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
public class Task3 {

  public static List<Person> sort(Collection<Person> persons) {
    List<Person> result = new ArrayList<>(persons);
    result.sort(new SecondNameFirstNameCreatedAtComparator()); // создал компаратор класс по фамилии, имени, дате
    // по времени сортировка займет O(nlgn) и создание списка O(n) -> общее время O(nlgn)
    // по месту выделяем список O(n), (возможно еще сортировка занимает место не уверен, но оно не превышает O(n)
    // в среднем O(lgn) -> общее место O(n)
    return result;
  }

  private static class SecondNameFirstNameCreatedAtComparator implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
      int compareSecondName = person1.secondName().compareTo(person2.secondName());
      if (compareSecondName != 0) {
        return compareSecondName;
      }
      int compareName = person1.firstName().compareTo(person2.firstName());
      if (compareName != 0) {
        return compareName;
      }
      return person1.createdAt().compareTo(person2.createdAt());
    }
  }
}
