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

            Member member = new Member();
            member.setId(4L);
            member.setUsername("D");
            member.setRoleType(RoleType.GUEST);
            em.persist(member);

            tx.commit();
            /**생성*/
//          Member member = new Member();
/*            member.setId(3L);
            member.setName("HelloB");*/

            /**조회*/
/*            Member member =em.find(Member.class, 1L);
            System.out.println("findMember.id="+member.getId());
            System.out.println("findMember.name="+member.getName());*/

            /**삭제*/
//            em.remove(findMember);

            /**수정*/
//            findMember.setName("HelloJPA");

            /**쿼리(jpgl)*/
/*            List<Member> result = em.createQuery("select m from Member as m",Member.class)
                    .setFirstResult(5) //5번째 페이지임을 명시한다
                    .setMaxResults(8) //5번째 페이지에서 8개의 데이터를 가져온다
                    .getResultList(); //여기는 해당되는게 없으니까 출력되는게 없겠지?!
            for(Member member : result){
                System.out.println("member.name = "+member.getName());
            }*/

            /**영속*/
/*            Member member = new Member(200L,"member200");
            em.persist(member);

            em.flush(); //영속

            System.out.println("====================");
            tx.commit();*/

            /**준영속*/
/*            Member member = em.find(Member.class, 1L);
            member.setName("AAAAA");

            em.clear(); // clear 후엔 조회하면 다시 처음부터 영속성 context로 올라가야

            Member member2 = em.find(Member.class, 1L);
            System.out.println("====================");
            tx.commit();*/
        } catch(Exception e){
            tx.rollback();
        }
        finally{
            em.close();
        }

        emf.close();
    }
}
