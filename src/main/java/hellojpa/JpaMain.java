package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); //변경작업시 반드시 필요

        // 스프링은 try~catch~finally 처리지원
        try {
            //비영속
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloJPA");

            //영속
            Member member1 = new Member(1L, "A");
            Member member2 = new Member(2L, "B");
            em.persist(member1);
            em.persist(member2);
            System.out.println("-------------------------------------------");

            //1차캐시에서 먼저조회 후 없으면 그때 DB에서 조회
            Member findMember = em.find(Member.class, 1L);
            Member findMember2 = em.find(Member.class, 1L);

            //1차캐시로 조회 시 영속성 엔티티 동일성 보장
            System.out.println("result = " + (findMember == findMember2));

            //조회 내역 setter 시 변경감지 발생하여 별도 UPDATE 호출없이 JPA가 UPDATE 수행지원
            Member member3 = em.find(Member.class, 1L);
            member3.setName("zzzzzz");

            tx.commit(); // 실제 DB 반영처리
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}