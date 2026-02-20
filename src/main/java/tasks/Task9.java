package tasks;

import common.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
Далее вы увидите код, который специально написан максимально плохо.
Постарайтесь без ругани привести его в надлежащий вид
P.S. Код в целом рабочий (не везде), комментарии оставлены чтобы вам проще понять чего же хотел автор
P.P.S Здесь ваши правки необходимо прокомментировать (можно в коде, можно в PR на Github)
 */
public class Task9 {

  private static final int COUNT_OF_FAKE_PERSONS = 1; // сделал это в виде переменной
  // чтобы в коде было понятно что мы скипаем, а не просто 1

  private long count;
  public long getCount() {
    return count; // Добавил getter Для поля count
    // в моем понимании это поле создано для того чтобы получать это значение
    // не вызывая каждый раз метод подсчета
    // поэтому решил его добавить
    // если это не так, то нужно удалить вообще эту переменную
  }
  // Костыль, эластик всегда выдает в топе "фальшивую персону".
  // Конвертируем начиная со второй
  public List<String> getNames(List<Person> persons) {
    // мне кажется не стоит удалять элемент из самого листа
    // я бы просто пропустил его
    // поэтому добавил команду skip
    // плюс когда используем skip можно не проверять коллекцию, он просто вернет пустой stream
    // по хорошему здесь добавить Log что пропущен один элемент, на случай если в будущем пофиксят
    // фальшивую персону
    return persons.stream().skip(COUNT_OF_FAKE_PERSONS).map(Person::firstName).collect(Collectors.toList());
  }

  // Зачем-то нужны различные имена этих же персон (без учета фальшивой разумеется)
  public Set<String> getDifferentNames(List<Person> persons) {
    // не понятно зачем создавать здесь stream и тратить место под него, создал сразу множество
    return new HashSet<>(getNames(persons));
  }

  // Тут фронтовая логика, делаем за них работу - склеиваем ФИО
  public String convertPersonToString(Person person) {
    // соединил через stream, единственное не уверен на счет правильности порядка соединений полей
    return Stream.of(person.firstName(), person.middleName(), person.secondName())
            .filter(String::isBlank) // проверка на null и строку состоящую из пробелов
            .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    // проходить по коллекции через stream быстрее + нас просили имена, а не строковое представление
    // плюс в мапу теперь не будет добавляться null значение, так как я его фильтрую
    return persons.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toMap(Person::id, Person::firstName));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    Set<Person> personsSet1 = new HashSet<>(persons1); // складываем элементы в Set, чтобы уменьшить время выполнения до O(n+m)
    // n и m - количество элементов в каждой коллекции, вместо O(n*m)
    // но увеличиваем затрачиваемое место получая O(n) n - количество элементов в первой коллекции
    // не стал проверять какая из коллекций больше по размеру, так как в одной коллекции может быть 7 элементов
    // и все они одинаковые, а в другой 5 персон и все разные -> сет будет для второй коллекции больше
    for (Person person : persons2) {
      if (personsSet1.contains(person)) {
        return true;
      }
    }
    return false;
  }

  // Посчитать число четных чисел
  public long countEven(Stream<Integer> numbers) {
    // Не совсем понятно можно ли мне закрывать поток, который мне передают в метод
    // насколько это правильное решение закрывать его
    // Заменил метод forEach на count()
    // не понятен смысл переменной count, видимо для какого то быстрого получения значения
    // Но тогда хотелось бы создать под нее Getter
    count = numbers.filter(num -> num % 2 == 0).count();
    return count;
  }


  // Загадка - объясните почему assert тут всегда верен
  // Пояснение в чем соль - мы перетасовали числа, обернули в HashSet, а toString() у него вернул их в сортированном порядке
  void listVsSet() {
    List<Integer> integers = IntStream.rangeClosed(1, 10000).boxed().collect(Collectors.toList());
    List<Integer> snapshot = new ArrayList<>(integers);
    Collections.shuffle(integers);
    // первое в листе snapshot все элементы находятся в отсортированном порядке
    // второе я погуглил почему так и происходит и посмотрел конструктор hashset, а в последствии и hashMap
    // когда мы создаем hashSet От коллекции, мы создаем hashMap - размер которой равен (int) размераКоллекции / loadFactor
    // то есть размер hashSet по дефолту в 1,25 вроде раза больше чем исходная коллекция
    // и далее все числа от 1 до 10000 просто помещаются каждый в отдельный бакет по возрастанию, потому что для них hash
    // будет считаться как остаток от деления на размер HashSet
    // а когда будет запускаться метод toString мы будем проходиться по всем бакетам начиная с 0 заканчивая последним
    // также в порядке возрастания -> поэтому assert всегда верен
    Set<Integer> set = new HashSet<>(integers);
    assert snapshot.toString().equals(set.toString());
  }
}
