package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team") //Member_ex @ManyToOne 맞먹는 양방향 연관관계
    private List<Member_ex> memberEx = new ArrayList<>();

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

    public List<Member_ex> getMemberEx() {
        return memberEx;
    }

    public void setMemberEx(List<Member_ex> memberEx) {
        this.memberEx = memberEx;
    }
}
