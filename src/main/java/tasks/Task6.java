package tasks;

import common.Area;
import common.Person;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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


    Map<Integer, Area> mapAreas = areas.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toMap(Area::getId, Function.identity()));
    // по месту пока получаем O(n+m) - количество персон + количество регионов
    // по времени O(max(n, m)) или O(n+m) в целом не так важно

      // заполняем set через две мапы готовы сочетаниями строк
    // по месту выйдет O(nm) если у каждого человека уникальное имя и присутсвуют все регионы из списка
    // по времени получим также в худшем случае O(nm) // если у каждого есть все m регионов

    return persons.stream()
            .filter(Objects::nonNull)
            .filter(person -> Objects.nonNull(person.firstName()))
            .flatMap(person -> {
                return personAreaIds.get(person.id()).stream()
                        .map(areaId -> person.firstName() + " - " + mapAreas.get(areaId).getName());
            }).collect(Collectors.toSet());
  }
}
