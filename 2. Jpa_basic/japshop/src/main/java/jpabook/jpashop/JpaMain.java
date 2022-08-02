package jpabook.jpashop;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            String jpql = "select m from Member m where m.name like'%hello%'";
            List<Member> result = em.createQuery(jpql, Member.class).getResultList();
            tx.commit();

            for (Member member : result) {
                System.out.println("member = "+member);
            }
        } catch(Exception e){
            tx.rollback();
        }
        finally{
            em.close();
        }

        emf.close();
    }
}
