/**
 * 
 */
package com.starcast.account.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.starcast.account.model.User;
import com.starcast.account.repository.UserRepositoryCustom;

/**
 * @author WT033
 *
 */
public class UserRepositoryImpl implements UserRepositoryCustom {
	
	@PersistenceContext private EntityManager em;

	/**
	 * Configure the entity manager to be used.
	 *
	 * @param em the {@link EntityManager} to set.
	 */
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}


	@Override
	public User findByMobileNumber(Long mobilenumber) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
		Root<User> user = criteriaQuery.from(User.class);
		criteriaQuery.select(criteriaQuery.from(User.class));
		Predicate cond = builder.equal(user.get("mobilenumber"), mobilenumber);
		criteriaQuery.where(cond);
		TypedQuery<User> q = em.createQuery(criteriaQuery);
	    List<User> resultList = q.getResultList();
	    System.out.println(resultList);
		return (null == resultList || resultList.isEmpty()) ? null : resultList.get(0);
	}

	@Override
	public User findByEmail(String email) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
		Root<User> user = criteriaQuery.from(User.class);
		criteriaQuery.select(criteriaQuery.from(User.class));
		Predicate cond = builder.equal(user.get("email"), email);
		criteriaQuery.where(cond);
		TypedQuery<User> q = em.createQuery(criteriaQuery);
	    List<User> resultList = q.getResultList();
	    System.out.println(resultList);
		return (null == resultList || resultList.isEmpty()) ? null : resultList.get(0);
	}

}
