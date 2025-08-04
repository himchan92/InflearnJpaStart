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
            //비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            //영속
            em.persist(member);

            //1차 캐시조회 : DB 조회전 조회하여 있으면 대신 반환하고 없으면 DB쪽 조회하여 성능개선
            //1차캐시 있으면 동일성 보장
            Member findMember1 = em.find(Member.class, "member1");
            Member findMember2 = em.find(Member.class, "member1");

            //엔티티 수정 : setter처리시 변경감지 일어나 UPDATE 수행
            member.setName("HelloMyBatis");

            //영속성 클리어 : 캐시 제거되어 이후 find 시 캐시없으니 쿼리 호출됨
            em.clear();

            //영속 분리 하여 준영속
            //em.detach(member);

            tx.commit(); //DB 실제 반영
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}