package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL auto increment 지원, 오라클은 SEQUENCE
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING) //권장방식, 문자열타입, ORDINAL쓰면 순번변경되어 금지!!
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) //현재 날짜시간자동설정
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 자바 8이후는 아래 타입으로 사용
    //private LocalDateTime testLocalDateTime;

    @Lob //대용량 데이터 (긴텍스트) 저장
    private String description;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
