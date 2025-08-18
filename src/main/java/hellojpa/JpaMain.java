package hellojpa;

import jakarta.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        //트랜젝션 시작
      EntityTransaction tx = em.getTransaction();
      tx.begin();

      //code
        Member member = new Member();
        member.setId(1L);
        member.setName("HelloA");
        em.persist(member);

        Member findMember = em.find(Member.class, 1L); //조회 = 1차캐시우선조회하고 없으면 DB조회로 성능개선
        findMember.setName("HelloJPA"); //setter 작업 시 변경감지로 JPA UPDATE 수행지원
        System.out.println("findMember.id = " + findMember.getId());
        System.out.println("findMember.name = " + findMember.getName());

        //엔티티 테이블 대상 조히 JPQL
        List<Member> result = em.createQuery("select m from Member m", Member.class)
          .getResultList();

      for(Member member1 : result) {
        System.out.println("member.name = " + member.getName());
      }

      tx.commit(); //실제 DB 반영

      em.close();
        emf.close();
    }
}