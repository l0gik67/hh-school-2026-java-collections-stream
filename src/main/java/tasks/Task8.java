package tasks;

import common.Person;
import common.PersonService;
import common.PersonWithResumes;
import common.Resume;

import java.util.*;
import java.util.stream.Collectors;

/*
  Еще один вариант задачи обогащения
  На вход имеем коллекцию персон
  Сервис умеет по personId искать их резюме (у каждой персоны может быть несколько резюме)
  На выходе хотим получить объекты с персоной и ее списком резюме
 */
public class Task8 {
  private final PersonService personService;

  public Task8(PersonService personService) {
    this.personService = personService;
  }

  public Set<PersonWithResumes> enrichPersonsWithResumes(Collection<Person> persons) {
    Set<Integer> personIds = new HashSet<>(); // создаю set id для того что получить по нему резюме пользователей
    // не использую stream так как просто создаю set место O(n) n - количество пользователей, время также
    for (Person person : persons) {
      personIds.add(person.id());
    }
    Set<Resume> resumes = personService.findResumes(personIds);

    // создаю через стрим группировкой коллекцию id пользовтелей и их резюме основываясь на поле personId в Resume
    // группировка должна пройти за O(m) по времени и месту, где m - количество резюме
    Map<Integer, Set<Resume>> mapIdPersonAndResume = resumes.stream()
            .collect(Collectors.groupingBy(Resume::personId, Collectors.toSet()));


    Set<PersonWithResumes> result = new HashSet<>();
    // теперь по каждому пользователю из переданной коллекции находим его множество резюме и создаем элемент
    // PersonWithResumes и месту O(n+m)
    // по времени O(n)

    for (Person person : persons) {
      result.add(new PersonWithResumes(person, mapIdPersonAndResume.getOrDefault(person.id(),  Collections.emptySet())));
    }

    // Общее время алгоритма O(max(n, m)) по месту O(n+m)
  return result;
  }
}
