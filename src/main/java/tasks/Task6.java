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
    Map<Integer, Person> mapPersons  = persons.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toMap(Person::id, Function.identity()));
    // создаю за n - кол-во человек O(n) времени и места мапу для быстрого доступа к person

    // то же самое нужно сделать для регионов
    Map<Integer, Area> mapAreas = areas.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toMap(Area::getId, Function.identity()));
    // по месту пока получаем O(n+m) - количество персон + количество регионов
    // по времени O(max(n, m)) или O(n+m) в целом не так важно

      // заполняем set через две мапы готовы сочетаниями строк
    // по месту выйдет O(nm) если у каждого человека уникальное имя и присутсвуют все регионы из списка
    // по времени получим также в худшем случае O(nm) // если у каждого есть все m регионов

    return personAreaIds.entrySet().stream()
            .filter(entryPersonAreas -> entryPersonAreas.getKey() != null)
            .map(entryPersonAreas -> {
              Person person = mapPersons.get(entryPersonAreas.getKey());
              if (person == null || person.firstName() == null) return new HashSet<String>(); // не уверен что если у person нет имени нужно выводить с ним строки
              // сделал так, что если имени нет, то и комбинаций с этим (пустым) именем тоже не будет
              return entryPersonAreas.getValue().stream()
                      .map(mapAreas::get)
                      .filter(Objects::nonNull)
                      .map(area -> person.firstName() + " - " + area.getName())
                      .collect(Collectors.toSet());
            })
            .flatMap(Set::stream)
            .collect(Collectors.toSet());
  }
}
