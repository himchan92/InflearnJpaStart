package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Member_ex {

    @Id //PK 설정
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL 타입 auto increment 지원
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    private Integer age;

    //주의: 반드시 STRING 타입 해야지 값변경안되게 보장됨
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    //자바 8이후 되는 JPA 아래 날짜함수 지원하여 그대로 사용하면됨
    private LocalDate createdDate;
    private LocalDateTime lastModifiedDate;

    @Lob //대량사이즈
    private String description;

    @Transient
    private int temp;

    public Member_ex() {}

    public Member_ex(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
