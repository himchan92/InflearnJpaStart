package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); //JPA 모든변경은 트랜젝션내에서 이뤄지니 설정필수
        tx.begin();

        try {
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");
//            em.persist(member);

//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJpa");
//            System.out.println(findMember.getId());
//            System.out.println(findMember.getName());

            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for(Member member : result){
                System.out.println("member.name = " + member.getUsername());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        finally {
            em.close();
        }

        emf.close();
    }
}
