package tasks;

import common.Person;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task2Test {

  private static Person person1, person2, person3, person4;

  @BeforeAll
  public static void before() {
    Instant time = Instant.now();
    person1 = new Person(1, "Person 1", null, null, time);
    person2 = new Person(2, "Person 2", null, null, time.plusSeconds(1));
    person3 = new Person(3, "Person 3", null, null, time.minusSeconds(1));
    person4 = new Person(4, "Person 4", null, null, time.plusSeconds(2));

  }

  private static Stream<Arguments> generateData() {
    return Stream.of(
        Arguments.of(Set.of(person1, person2), Set.of(person3, person4), 3, List.of(3, 1, 2)),
        Arguments.of(Set.of(person1, person2), Set.of(person3, person4), 5, List.of(3, 1, 2, 4)),
        Arguments.of(Set.of(person1, person2), Set.of(person3, person4), 0, List.of()),
        Arguments.of(Set.of(), Set.of(person1, person2), 2, List.of(1, 2)),
        Arguments.of(Set.of(person1, person2), Set.of(), 2, List.of(1, 2))
    );
  }


  @ParameterizedTest
  @MethodSource("generateData")
  public void test(Set<Person> persons1, Set<Person> persons2, int limit, List<Integer> expectedIds) {
    assertEquals(expectedIds,
        Task2.combineAndSortWithLimit(persons1, persons2, limit)
            .stream()
            .map(Person::id)
            .toList()
    );
  }
}
