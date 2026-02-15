package tasks;

import common.Area;
import common.Person;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Task6Test {

  @Test
  void test() {
    List<Person> persons = List.of(
        new Person(1, "Oleg", null, null, Instant.now()),
        new Person(2, "Vasya", null, null, Instant.now())
    );
    Map<Integer, Set<Integer>> personAreaIds = Map.of(1, Set.of(1, 2), 2, Set.of(2, 3));
    List<Area> areas = List.of(new Area(1, "Moscow"), new Area(2, "Spb"), new Area(3, "Ivanovo"));
    assertEquals(
        Set.of("Oleg - Moscow", "Oleg - Spb", "Vasya - Spb", "Vasya - Ivanovo"),
        Task6.getPersonDescriptions(persons, personAreaIds, areas)
    );
  }

  @Test
  void test2() {
    List<Person> persons = List.of(
        new Person(1, "Oleg", null, null, Instant.now()),
        new Person(2, "Vasya", null, null, Instant.now())
    );
    Map<Integer, Set<Integer>> personAreaIds = Map.of(1, Set.of(1, 2), 2, Set.of(2, 3));
    List<Area> areas = List.of(new Area(2, "Spb"), new Area(3, "Ivanovo"), new Area(1, "Moscow"));
    assertEquals(
        Set.of("Oleg - Moscow", "Oleg - Spb", "Vasya - Spb", "Vasya - Ivanovo"),
        Task6.getPersonDescriptions(persons, personAreaIds, areas)
    );
  }
}
