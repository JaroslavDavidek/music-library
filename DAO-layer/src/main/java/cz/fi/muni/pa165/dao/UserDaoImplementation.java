/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.exception.InvalidParamDataAccessExpection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JaroslavDavidek
 */
@Repository
@Transactional
public class UserDaoImplementation implements UserDao {
    
    @PersistenceContext
    private EntityManager em;  
    
    @Override
    public boolean create(User user) {
        if(user == null){
            throw new InvalidParamDataAccessExpection("UserDao - create - user must not be null");
        }
        if(em.contains(user)) {
            return false;
        }
        em.persist(user);
        return true;
    }

    @Override
    public User findById(Long id) {
        if(id < 0){
            throw new InvalidParamDataAccessExpection("UserDao - findById - invalid index param passed") {};
        }
        return em.find(User.class, id);
    }

    @Override
    public User findUserByEmail(String email) {
        if(email == null || email.isEmpty()){
            throw new InvalidParamDataAccessExpection("UserDao - findAllByEmail - email must not be null or empty") {};
        }
        return em.createQuery(
            "SELECT u FROM User u WHERE u.email= :email", User.class)
            .setParameter("email", email)
            .getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery(
            "SELECT u FROM User u", User.class)
            .getResultList();
    }
    
}
