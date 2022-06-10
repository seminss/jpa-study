package hellojpa;

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
            //팀 저장
            Team team =new Team();
            team.setName("TeamA");
            em.persist(team);

            //회원 저장
            Member member = new Member();
            member.setUserName("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());

            Team findTeam= findMember.getTeam();
            System.out.println("findTeam =" +findTeam.getName());
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
