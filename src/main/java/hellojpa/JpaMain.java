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
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");
            em.persist(member); //영속성컨택스트 등록

//            Member findMember = em.find(Member.class, 1L); //JPA PK기준 조회
//            findMember.setName("HelloJPA"); //변경감지 JPA UPDATE 수행지원

            //JPQL : Entity 대상 수행
            List<Member> result = em.createQuery("select m from Member")
                    .setFirstResult(5) //페이징 첫
                    .setMaxResults(8) //페이징 마지막
                    .getResultList();

            for(Member m : result) {
                System.out.println("member.name = " + m.getName());
            }

            tx.commit(); // DB 반영
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}