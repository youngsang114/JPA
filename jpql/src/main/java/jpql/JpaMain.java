package jpql;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀A");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

//            em.flush();
//            em.clear();

            int resultCount = em.createQuery("update Member m set m.age =20")
                    .executeUpdate();
            System.out.println("resultCount = " + resultCount);

            em.clear(); // bulk 연산을 위해서 필수


            Member findMember = em.find(Member.class, member1.getId());
            System.out.println(findMember.getAge());


//            List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
//                    .setParameter("username", member1.getUsername())
//                    .getResultList();
//            for (Member member : resultList) {
//                System.out.println("member = " + member);
//            }

//            String query = "select m from Member m where m= :member";
//
//            Member findMember = em.createQuery(query, Member.class)
//                    .setParameter("member", member1).getSingleResult();
//            System.out.println("findMember = " + findMember);


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
