package tasks;

import common.Area;
import common.Person;

import java.util.*;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 {

  public Task6() {
  }

  public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {
    Map<Integer, Person> mapPersons  = new HashMap<>();
    for (Person person : persons) {
      mapPersons.put(person.id(), person); // добавляю в мапу всех person по id, чтобы потом можно было получить их
      // за О(1), не использую поток, чтобы не тратить лишнюю память
    }

    // то же самое нужно сделать для регионов
    Map<Integer, Area> mapAreas = new HashMap<>();
    for (Area area : areas) {
      mapAreas.put(area.getId(), area);
    }
    // по месту пока получаем O(n+m) - количество персон + количество регионов

    Set<String> result = new HashSet<>();
    for (Map.Entry<Integer, Set<Integer>> entry : personAreaIds.entrySet()) {
      String personName = mapPersons.get(entry.getKey()).firstName();
      for (Integer areaId : entry.getValue()) {
        String areaName = mapAreas.get(areaId).getName();
        result.add(personName + " - " + areaName);
      }
    } // заполняем set через две мапы готовы сочетаниями строк
    // по месту выйдет O(nm) если у каждого человека уникальное имя и присутсвуют все вакансии из списка
    // по времени получим также в худшем случае O(nm) // если у каждого есть все m вакансий

    return result;
  }
}
