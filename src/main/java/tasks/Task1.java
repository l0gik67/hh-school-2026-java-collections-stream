package tasks;

import common.Person;
import common.PersonService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимптотику работы
 */
public class Task1 {

  private final PersonService personService;

  public Task1(PersonService personService) {
    this.personService = personService;
  }

  public List<Person> findOrderedPersons(List<Integer> personIds) {
    Map<Integer, Person> persons = personService.findPersons(personIds).stream()
            .collect(Collectors.toMap(Person::id, Function.identity()));
    // n - количество person в множестве за O(n) по времени и месту создаю map
    // можно было бы выделить это в отдельный метод, чтобы не тратить место на поток
    // но насколько я понял, потоки работают быстрее чем for loop, поэтому оставил через него
    // m - кол-во элементов в списке personIds, тогда создание новое списка O(m) по времени и месту
    // так как операция получения в мапе работает обычно за O(1)
    // хотелось бы возвращать список optional, в том случае когда нам передали в списке id, которого нет в множестве
    // но непонятно насколько правильно это (просто мое предположение)
    return personIds.stream().map(persons::get)
            .toList();
  } // Обе операции занимают по времени и месту по O(n) -> общее время выполнения O(n) и общее место O(n)

}
