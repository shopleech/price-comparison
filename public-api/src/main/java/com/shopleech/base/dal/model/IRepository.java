package com.shopleech.base.dal.model;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 25.01.2023
 */
public interface IRepository<T> {
    public T getById(int id);

    public T getByName(String name);

    public List<T> getAll();

    public void add(T repo);

    // add range
    public void update(T repo);

    public void delete(T repo);
    // remove range
}
