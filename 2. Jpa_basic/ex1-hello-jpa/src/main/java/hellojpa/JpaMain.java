package hellojpa;

import org.hibernate.persister.walking.spi.EntityIdentifierDefinition;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.net.SocketOption;
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

            //역방향(주인이 아닌 방향)만 연관관계 설정
            team.getMembers().add(member);
            //연관관계의 주인에 값 설정
            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members=findTeam.getMembers();
            for(Member m: members){
                System.out.println("m= "+m.getUserName());
            }

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
