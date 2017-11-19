package com.dummies.dao;

import com.dummies.model.Dummy;

import java.util.List;

/**
 * @author taumal
 * @since 11/14/17 05:15 PM
 */
public interface DummyDAO {

    public void saveOrUpdate(Dummy dummy);

    public void delete(int id);

    public Dummy getDummyDataById(int id);

    public List<Dummy> getAllDummyData();

}
