package tasks;

import common.ApiPersonDto;
import common.Person;
import common.PersonConverter;

class PersonConverterImpl implements PersonConverter {
  @Override
  public ApiPersonDto convert(Person person) {
    return new ApiPersonDto()
        .setCreated(person.createdAt().toEpochMilli())
        .setId(person.id().toString())
        .setName(person.firstName());
  }

  @Override
  public ApiPersonDto convert(Person person, Integer areaId) {
    return new ApiPersonDto()
        .setCreated(person.createdAt().toEpochMilli())
        .setId(person.id().toString())
        .setName(person.firstName())
        .setAreaId(areaId);
  }
}
