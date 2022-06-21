package hellojpa;

import org.hibernate.Hibernate;
import org.hibernate.persister.walking.spi.EntityIdentifierDefinition;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);//db저장

            Team teamB=new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(teamB);
            em.persist(member2);

            em.flush();
            em.clear();

            List<Member> members = em.createQuery("select m from Member m", Member.class)
                    .getResultList();

/*            Member m = em.find(Member.class, member1.getId());
            System.out.println("m = "+m.getTeam().getClass());
            System.out.println("============");
            m.getTeam().getName();
            System.out.println("============");*/

            tx.commit();
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
