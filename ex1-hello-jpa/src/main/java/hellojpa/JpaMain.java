package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("A");
            Member member2 = new Member();
            member.setUsername("B");
            Member member3 = new Member();
            member.setUsername("C");

            System.out.println("===================");
            // DB SEQ =1    | 1
            // DB SEQ =51   | 2
            // DB SEQ =51   | 3
            em.persist(member);
            em.persist(member2);
            em.persist(member3);
            System.out.println("member1.id = " + member.getId());  //1,51
            System.out.println("member2.id = " + member2.getId());  //MEM
            System.out.println("member3.id = " + member3.getId());  //MEM

            System.out.println("===================");

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
