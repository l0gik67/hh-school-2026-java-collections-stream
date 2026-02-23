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
    // n - кол-во элементов в persons -> время и место работы O(n)
    // изначально сделал просто через for loop, так как реализуется просто операция добавления
    // Но также как и с первой таской переключился на стримы, после подсказки из backend канала и погуглив
    return persons.stream()
            .map(personConverter::convert)
            .toList();
  }
}
