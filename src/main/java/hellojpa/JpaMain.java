package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        // TODO: JPA 모든 변경은 트랜젝션안에서 이뤄져야해서 필수 셋팅
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");
            em.persist(member);

            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA"); //별도 저장로직없이 Transaction 안에서는 변경있을시 자동 UPDATE 수행지원
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}