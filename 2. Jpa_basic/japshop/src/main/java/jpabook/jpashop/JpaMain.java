package jpabook.jpashop;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Order order=new Order();
            order.addOrderItem(new OrderItem());
/*            orderItem.setOrder(order)로 위에서 계산하게 해도 된다.
            즉, 양방향이 아니여도 어플리케이션 개발하는데 아무 문제가 없다.
            편의를 위해 양방향으로 만들었다고 생각하면 된다. (단방향도 ok) */

        } catch(Exception e){
            tx.rollback();
        }
        finally{
            em.close();
        }

        emf.close();
    }
}
