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
            /**생성*/
/*            Member member = new Member();
            member.setId(3L);
            member.setName("HelloB");*/

            /**조회*/
            Member findMember =em.find(Member.class, 1L);
/*            System.out.println("findMember.id="+findMember.getId());
            System.out.println("findMember.name="+findMember.getName());*/

            /**삭제*/
//            em.remove(findMember);

            /**수정*/
//            findMember.setName("HelloJPA");

            /**쿼리(jpgl)*/
            List<Member> result = em.createQuery("select m from Member as m",Member.class)
                    .setFirstResult(5) //5번째 페이지임을 명시한다
                    .setMaxResults(8) //5번째 페이지에서 8개의 데이터를 가져온다
                    .getResultList(); //여기는 해당되는게 없으니까 출력되는게 없겠지?!
            for(Member member : result){
                System.out.println("member.name = "+member.getName());
            }

//            em.persist(member);
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
