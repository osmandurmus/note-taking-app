package com.osmandurmus.dao;

import java.util.ArrayList;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.osmandurmus.entity.Note;
import com.osmandurmus.entity.User;


@Repository
public class UserDAO {
	
	@Autowired ////servlet-context' den al�yoruz.
	private SessionFactory sessionFactory;
	
	//CRUD
	
	public Long insert(User user) {
		return (Long) sessionFactory.getCurrentSession().save(user);		
	}
	
	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	
	//READ
	
	public User getFindByUsernameAndPass(String username,String pass){
		Query query=sessionFactory.getCurrentSession().createQuery("FROM User WHERE username=:username AND password=:pass AND active=:active")
				.setString("username",username)
				.setString("pass", pass)
				.setBoolean("active", true);
		User user=null;	
		try {
			user=(User) query.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			user=null; 
		}
		return user;
	}
	
	public User getFindByUsername(String username){
		Query query=sessionFactory.getCurrentSession().createQuery("FROM User WHERE username=:username")
				.setString("username",username);
		return (User) query.getSingleResult();
	}
	
	public User getFindByKey(String key){
		Query query=sessionFactory.getCurrentSession().createQuery("FROM User WHERE keyreg=:key")
				.setString("key",key);
		
		User user=null;	
		try {
			user=(User) query.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			user=null; 
		}
		return user;
		
	}
	
	
	
	
	
	
	
	
}