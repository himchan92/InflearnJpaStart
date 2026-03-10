package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;

    /*
        mappedBy
        - 반대편에 team 매핑 걸려있는걸 명시
        - 연관관계 주인 아닌쪽에 명시
        - 주인 아닌쪽은 읽기만 가능
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}
