package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); //변경작업시 반드시 필요

        try {

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member_ex memberEx = new Member_ex();
            memberEx.setUsername("member1");
            //memberEx.setTeamId(team.getId());
            memberEx.setTeam(team); //왜래키 TEAM 참조
            em.persist(memberEx);

            //영속성 초기화클리어
            em.flush();
            em.clear();

            //조회
            Member_ex findMember = em.find(Member_ex.class, memberEx.getId());
            List<Member_ex> memberExs = findMember.getTeam().getMemberEx();

            for(Member_ex m : memberExs) {
                System.out.println("m = " + m.getUsername());
            }

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam);

            Team newTeam = em.find(Team.class, 1L);
            findMember.setTeam(newTeam);

            tx.commit(); // 실제 DB 반영처리
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}