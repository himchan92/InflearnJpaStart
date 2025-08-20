package hellojpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity //JPA 인식
public class Member {

  @Id //PK 지정
  private Long id;
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
