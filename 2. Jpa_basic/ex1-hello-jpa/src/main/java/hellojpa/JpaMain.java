package hellojpa;

import org.hibernate.persister.walking.spi.EntityIdentifierDefinition;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.net.SocketOption;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("user");
            member.setCreateBy("kim");
            member.setCreateDate(LocalDateTime.now());

            em.persist(member); // db에 저장

            em.flush();
            em.clear();

            tx.commit();
        } catch(Exception e){
            tx.rollback();
        }
        finally{
            em.close();
        }

        emf.close();
    }
}
