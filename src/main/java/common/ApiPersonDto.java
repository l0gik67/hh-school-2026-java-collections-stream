package common;

import java.util.Objects;

public class ApiPersonDto {
  private String id;
  private Long created;
  private String name;
  private Integer areaId;

  public String getId() {
    return id;
  }

  public ApiPersonDto setId(String id) {
    this.id = id;
    return this;
  }

  public Long getCreated() {
    return created;
  }

  public ApiPersonDto setCreated(Long created) {
    this.created = created;
    return this;
  }

  public String getName() {
    return name;
  }

  public ApiPersonDto setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getAreaId() {
    return areaId;
  }

  public ApiPersonDto setAreaId(Integer areaId) {
    this.areaId = areaId;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ApiPersonDto dto = (ApiPersonDto) o;
    return Objects.equals(id, dto.id) &&
        Objects.equals(created, dto.created) &&
        Objects.equals(name, dto.name) &&
        Objects.equals(areaId, dto.areaId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, created, name, areaId);
  }

  @Override
  public String toString() {
    return "ApiPersonDto{" +
        "id='" + id + '\'' +
        ", created=" + created +
        ", name='" + name + '\'' +
        ", areaId=" + areaId +
        '}';
  }
}
