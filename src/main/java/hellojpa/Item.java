package hellojpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Item {

  @Id @GeneratedValue
  private Long id;

  private String name;
  private int price;
}
