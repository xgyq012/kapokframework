package co.zwmcn;

import co.zwmcn.bean.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by zhangweiming on 2016年06月25日 11:56:41。
 */
public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("kapok-data");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Address address = new Address();

        em.merge(address);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

}
