package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); //변경작업시 반드시 필요

        // 스프링은 try~catch~finally 처리지원
        try {
            //비영속
            Member_ex memberEx = new Member_ex();
            memberEx.setId(1L);
            memberEx.setName("HelloJPA");

            //영속
            Member_ex memberEx1 = new Member_ex(1L, "A");
            Member_ex memberEx2 = new Member_ex(2L, "B");
            em.persist(memberEx1);
            em.persist(memberEx2);
            System.out.println("-------------------------------------------");

            //1차캐시에서 먼저조회 후 없으면 그때 DB에서 조회
            Member_ex findMemberEx = em.find(Member_ex.class, 1L);
            Member_ex findMemberEx2 = em.find(Member_ex.class, 1L);

            //1차캐시로 조회 시 영속성 엔티티 동일성 보장
            System.out.println("result = " + (findMemberEx == findMemberEx2));

            //조회 내역 setter 시 변경감지 발생하여 별도 UPDATE 호출없이 JPA가 UPDATE 수행지원
            Member_ex memberEx3 = em.find(Member_ex.class, 1L);
            memberEx3.setName("zzzzzz");

            tx.commit(); // 실제 DB 반영처리
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}