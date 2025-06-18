package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 스프링은 try~catch~finally 처리지원
        try {
            //비영속
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloJPA");

            //영속
            System.out.println("BEFORE");
            em.persist(member); // INSERT 비슷 영속성 등록
            System.out.println("AFTER");

            tx.commit(); // 실제 DB 반영처리
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}