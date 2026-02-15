package common;

import java.util.Collection;
import java.util.Set;

public interface PersonService {
  Set<Person> findPersons(Collection<Integer> personIds);
  Set<Resume> findResumes(Collection<Integer> personIds);
}
