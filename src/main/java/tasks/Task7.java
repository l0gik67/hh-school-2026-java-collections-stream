package tasks;

import common.Company;
import common.Vacancy;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
Из коллекции компаний необходимо получить всевозможные различные названия вакансий
 */
public class Task7 {

  public static Set<String> vacancyNames(Collection<Company> companies) {
    return companies.stream()
            .map(Company::getVacancies)
            .flatMap(Set::stream)
            .map(Vacancy::getTitle)
            .collect(Collectors.toSet());
    // если рассматривать n - кол-во компаний m - общее число всех вакансий
    // по памяти получим O(nm) - (это будет до того как
    // преобразовали stream в set), после преобразования в сет, будет O(m)
    // логичнее взять максимальную границу O(nm) (по месту тот случай когда у каждой компании есть все вакансии)
    // по времени O(nm) - каждую вакансию просмотрим один раз
  }

}
