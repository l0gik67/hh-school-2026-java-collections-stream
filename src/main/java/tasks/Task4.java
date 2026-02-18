package tasks;

import common.ApiPersonDto;
import common.Person;
import common.PersonConverter;
import java.util.ArrayList;
import java.util.List;

/*
Задача 4
Список персон класса Person необходимо сконвертировать в список ApiPersonDto
(предположим, что это некоторый внешний формат)
Конвертер для одной персоны - personConverter.convert()
FYI - DTO = Data Transfer Object - распространенный паттерн, можно погуглить
 */
public class Task4 {

  private final PersonConverter personConverter;

  public Task4(PersonConverter personConverter) {
    this.personConverter = personConverter;
  }

  public List<ApiPersonDto> convert(List<Person> persons) {
    List<ApiPersonDto> result = new ArrayList<>(persons.size());
    for (Person person : persons) {
      result.add(personConverter.convert(person));
    }
    // не стал использовать stream, так как одна операция добавления, нет смысла использовать лишнее место
    // время выполнения O(n) -> добавляем каждый элемент за O(1), без амортизации так как изначально задали размер списка
    // по месту O(n) - список ApiPersonDto
    return result;
  }
}
