package ddo;

import java.security.PublicKey;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dao.Customer;
import dao.Items;

public class Mydao {
	EntityManagerFactory e= Persistence.createEntityManagerFactory("dev");
	EntityManager m=e.createEntityManager();
	EntityTransaction t=m.getTransaction();
	
	public void save(Customer customer) {
		t.begin();
		m.persist(customer);
		t.commit();
		
		
	}
	public void item(Items items) {
		t.begin();
		m.persist(items);
		t.commit();
	}

	public Customer fetchByEmail(String email) {
		List<Customer> list=m.createQuery("select x from Customer x where email=?1").setParameter(1, email).getResultList();
		if(list.isEmpty()) {
			return null;
		}else {
			return list.get(0);
		}
		
	}

	public Customer fetchbyMobile(long phno) {
		List<Customer> list=m.createQuery("select x from Customer x where phonenumber=?1").setParameter(1, phno).getResultList();
		if(list.isEmpty()) {
			return null;
		}else {
			return list.get(0);
		}

		
		}
	public List<Items> fetchAllFoodItem(){
		return m.createQuery("select x from Items x").getResultList();
	}
	public Items find(int id) {
	
		return m.find(Items.class, id);
	}
	public void delete(Items item) {
		t.begin();
		m.remove(item);
		t.commit();
	}
	

}
