import hellojpa.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
      EntityManager em = emf.createEntityManager();

      EntityTransaction tx = em.getTransaction();
      tx.begin();

      try {

        List<Member> resultList = em.createQuery("select m from Member m where m.username like '%kim%'", Member.class)
            //페이징 0 ~ 10
            .setFirstResult(0)
            .setMaxResults(10)
            .getResultList();

        System.out.println(resultList);

        tx.commit();
      } catch (Exception e) {
        tx.rollback();
      } finally {
        em.close();
      }
      emf.close();
    }
}