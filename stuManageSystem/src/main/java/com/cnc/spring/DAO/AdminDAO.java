package com.cnc.spring.DAO;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.cnc.spring.model.Admin;
//pull test
@Component
public class AdminDAO {
	@Resource
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void addAdmin(Admin admin) {
		getSession().save(admin);
	}
	
	public void deleteAdmin(String username) {
		Admin admin = (Admin)getSession().get(Admin.class, username);
		if(admin == null) {
			return;
		}
		String hql = "delete Admin a where a.username=" + username;
		getSession().createQuery(hql).executeUpdate();
	}
	
	public void updateAdmin(String username, String password) {
		Admin a = (Admin)getSession().get(Admin.class, username);
		if(a == null)
			return;
		String hql = "update Admin a set a.password=" + password + 
				" where a.username=" + username;
		getSession().createQuery(hql).executeUpdate();
	}
	
	public Admin getAdmin(String username) {
		return (Admin)getSession().get(Admin.class, username);
	}
}
