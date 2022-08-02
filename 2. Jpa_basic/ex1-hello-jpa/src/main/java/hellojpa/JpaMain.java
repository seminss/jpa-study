package hellojpa;

import org.hibernate.Hibernate;
import org.hibernate.persister.walking.spi.EntityIdentifierDefinition;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            String jpql = "select m from Member m where m.username like'%hello%'";
            List<Member> result = em.createQuery(jpql, Member.class).getResultList();
            tx.commit();

            for (Member member : result) {
                System.out.println("member = "+member);
            }
        } catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        finally{
            em.close();
        }

        emf.close();
    }
}
