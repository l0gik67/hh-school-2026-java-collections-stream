package tasks;

import common.ApiPersonDto;
import common.Person;
import common.PersonConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
Задача 5
Расширим предыдущую задачу.
Есть список персон, и словарь сопоставляющий id каждой персоны и id региона
Необходимо выдать список персон ApiPersonDto, с правильно проставленными areaId
Конвертер одной персоны дополнен!
 */
public class Task5 {

  private final PersonConverter personConverter;

  public Task5(PersonConverter personConverter) {
    this.personConverter = personConverter;
  }

  public List<ApiPersonDto> convert(List<Person> persons, Map<Integer, Integer> personAreaIds) {
    List<ApiPersonDto> result = persons.stream()
            .map(personConverter::convert)
            .peek(person -> person.setAreaId(personAreaIds.get(getIntApiPersonDto(person))))
            .toList();

    // решил использовать стрим для удобства уже (не уверен что правильно здесь выделять под него доп память)
    // по памяти O(n) на список и O(n) на stream -> в общем O(n)
    // по времени O(n) на создание списка и O(n) на изменение каждого элемента (получение areaId из мапы O(1)) ->
    // общее время O(n)
    return result;
  }

  public int getIntApiPersonDto(ApiPersonDto apiPersonDto) {
    return Integer.parseInt(apiPersonDto.getId());
  }
}
