package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜젝션 시작

        try {
            //Member findMember = em.find(Member.class, 1L);
            //findMember.setName("HelloJPA"); //JPA SETTER 시 변경감지로 UPDATE 수행
            List<Member> result = em.createQuery("select m from Member as m", Member.class) //엔티티명 대상 수행
                    .setFirstResult(1) //페이징 1 시작
                    .setMaxResults(8) //페이징 8 종료
                    .getResultList(); //다건조회

            for(Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            tx.commit(); //DB 실제 반영
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}