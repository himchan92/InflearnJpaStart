package jpabook.jpashop.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    @ManyToOne(fetch = FetchType.LAZY) //기본 즉시로딩으로 참조시 호출하는 지연로딩으로 설정해서 성능개선
    @JoinColumn(name = "team_id") //Team team_id(fk) 매핑
    private Team team;
}
