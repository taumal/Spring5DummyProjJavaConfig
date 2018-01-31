package com.dummies.dao;

import com.dummies.model.DummyUser;
import com.dummies.model.Persistent;
import com.dummies.util.EncryptionPair;
import com.dummies.util.PasswordUtils;
import com.dummies.util.Utils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author taumal
 * @since 12/10/17 6:41 PM
 */
@SuppressWarnings({"deprecation", "unchecked"})
@Repository
@Transactional
public class CommonDao {
    private static final Logger log = Logger.getLogger(CommonDao.class);

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Persistent load(Class clazz, Serializable id) {
        return (Persistent) hibernateTemplate.get(clazz, id);
    }

    public void save(Object object) {
        hibernateTemplate.saveOrUpdate(object);
    }

    public Persistent get(Class c, long id) {
        return (Persistent) hibernateTemplate.get(c, id);
    }

    public void delete(long id, Class clazz) {
        Persistent p = get(clazz, id);
        getCurrentSession().delete(p);
    }

    public List<DummyUser> getRecentlyAccessedUsers() {
        String sqlQuery = "FROM DummyUser r ";

        Query query = sessionFactory.getCurrentSession().createQuery(sqlQuery)
                .setFirstResult(0).setMaxResults(10);

        return query.list();
    }

    public void saveUser(DummyUser user) {
        if (Utils.isNotEmpty(user.getPassword())){
            EncryptionPair ep = PasswordUtils.getEncryptionPair(user.getPassword());
            user.setPasswordHash(ep.getHashValue());
            user.setPasswordSalt(ep.getSalt());
        }
        hibernateTemplate.saveOrUpdate(user);
    }

    public DummyUser getUser(String targetUsername) {
        List list = hibernateTemplate.find("FROM DummyUser r WHERE r.userName = ?", targetUsername);
        if (list.size() > 0) {
            return (DummyUser) list.get(0);
        }
        return null;
    }
}
