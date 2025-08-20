package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

      EntityManager em = emf.createEntityManager();

      //모든 JPA 변경작업은 트랜젝션 설정 필수
      EntityTransaction tx = em.getTransaction();
      tx.begin();

      try {
        //비영속상태
        Member member = new Member();
        member.setId(1L);
        member.setName("HelloA");

        //영속상태
        em.persist(member);

        //1차캐시조회 후 없으면 DB조회로 성능개선
        Member findMember = em.find(Member.class, 1L);
        System.out.println("findMember = " + findMember.getId());
        System.out.println("findMember = " + findMember.getName());

        findMember.setName("HelloUpdateJPA"); //별도 저장로직없이 변경감지로 JPA에서 UPDATE 수행지원

        //JPQL : SQL 테이블아닌 엔티티명을 대상으로 SQL 문법 유사수행
        List<Member> result = em.createQuery("select m from Member m", Member.class)
            .getResultList();

        for(Member item : result) {
          System.out.println("member.name " + item.getName());
        }

        tx.commit(); //실제 영속상태들을 모두 DB반영
      } catch (Exception e) {
        tx.rollback();
      } finally {
        em.close();
      }

      emf.close();
    }
}