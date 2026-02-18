package tasks;

import common.Person;
import common.PersonService;

import java.util.*;
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
    Set<Person> persons = personService.findPersons(personIds);
    Map<Integer, List<Integer>> idsOrderMap = getIdsOrderMap(personIds);
      return getPersonsInOrder(persons, idsOrderMap, personIds.size());
  } // Обе операции занимают по времени и месту по O(n) -> общее время выполнения O(n) и общее место O(n)

  private Map<Integer, List<Integer>> getIdsOrderMap(List<Integer> personIds) {
    // не создаю stream, так как просто создаю коллекцию, не хочу выделять под stream место
    Map<Integer, List<Integer>> idsAndIndexes = new HashMap<>(personIds.size());
    // использую итератор вместо currentIndex c forEach вместо цикла for(int i = 0; i < personIds.size(); i++)
    // так как может быть передан LinkedList и в таком случае операция get будет O(n)
    // через итератор операция get O(1)
    int currentIndex = 0;
    for (Integer personId : personIds) {
      if (!idsAndIndexes.containsKey(personId)) {
        idsAndIndexes.put(personId, new ArrayList<>());
      }
      idsAndIndexes.get(personId).add(currentIndex);
      currentIndex++;
    }
    return idsAndIndexes;
  } // операция по времени и месту O(n)

  private List<Person> getPersonsInOrder(Set<Person> persons, Map<Integer, List<Integer>> idsOrderMap, int listSize) {
    // Создаю сразу список длины persons.size(), чтобы он не переполнялся в процессе заполнения
    // И заполняю его null значениями
    List<Person> listOfPersons = new ArrayList<>(Collections.nCopies(listSize, null));
    for (Person person : persons) {
      List<Integer> indexesForCurrentPerson = idsOrderMap.get(person.id());
      addAllOccurrencesPerson(listOfPersons, indexesForCurrentPerson, person);
    }

    // вся операция занимает по времени и месту O(n) (добавление n элементов за O(1))
    return listOfPersons;
  }

  private void addAllOccurrencesPerson(List<Person> persons, List<Integer> indexes, Person person) {
    for (Integer index : indexes) {
      persons.set(index, person);
    }
  }
}
