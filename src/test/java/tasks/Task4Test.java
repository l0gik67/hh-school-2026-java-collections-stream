package tasks;

import common.Person;
import java.time.Instant;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Task4Test {

  private Task4 task;
  private PersonConverterImpl personConverter;

  @BeforeEach
  void before() {
    personConverter = new PersonConverterImpl();
    task = new Task4(personConverter);
  }

  @Test
  public void test() {
    Person person1 = new Person(1, "Name1", "SecondName1", "MiddleName1", Instant.now());
    Person person2 = new Person(2, "Name2", "SecondName2", "MiddleName2", Instant.now());
    assertEquals(List.of(personConverter.convert(person1), personConverter.convert(person2)), task.convert(List.of(person1, person2)));
  }
}
