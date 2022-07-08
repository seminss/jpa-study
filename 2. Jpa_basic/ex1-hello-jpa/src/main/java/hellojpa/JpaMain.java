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
           Member member=new Member();
           member.setUsername("member1");
           member.setHomeAddress(new Address("homeCity","city","10000"));

           member.getFavoriteFoods().add("치킨");
           member.getFavoriteFoods().add("족발");
           member.getFavoriteFoods().add("피자");

           member.getAddressHistory().add(new AddressEntity("old1","street","10000"));
           member.getAddressHistory().add(new AddressEntity("old2","street","10000"));

           em.persist(member);

           em.flush();
           em.clear();

           System.out.println("========== START ==========");
           Member findMember = em.find(Member.class,member.getId());

           //homeCity -> newCity
           Address a = findMember.getHomeAddress();
           /*findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));*/

           //치킨 -> 한식 // delete 후 insert
           /*findMember.getFavoriteFoods().remove("치킨");
           findMember.getFavoriteFoods().add("한식");*/

            //old1 만 delete했지만 insert 할 때는 newCity와 old2 모두 새로 insert 해서 갈아끼움
           findMember.getAddressHistory().remove(new AddressEntity("old1","street","10000"));
           findMember.getAddressHistory().add(new AddressEntity("newCity","street","10000"));

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
