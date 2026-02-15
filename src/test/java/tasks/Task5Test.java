package tasks;

import common.ApiPersonDto;
import common.Person;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Task5Test {
  private Task5 task;
  private PersonConverterImpl personConverter;

  @BeforeEach
  void before() {
    personConverter = new PersonConverterImpl();
    task = new Task5(personConverter);
  }

  @Test
  public void test() {
    Person person1 = new Person(1, "Name", null, null, Instant.now());
    Person person2 = new Person(2, "Name", null, null, Instant.now());
    Person person3 = new Person(3, "Name", null, null, Instant.now());
    Map<Integer, Integer> personAreaIds = Map.of(1, 1, 2, 2);
    List<ApiPersonDto> expectedPersons = List.of(
        personConverter.convert(person1, 1),
        personConverter.convert(person2, 2),
        personConverter.convert(person3, null)
    );
    assertEquals(expectedPersons, task.convert(List.of(person1, person2, person3), personAreaIds));
  }
}
