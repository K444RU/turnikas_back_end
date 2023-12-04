package com.example.turnikas_back_end.business.common.repository;

import java.util.List;

public interface TurnikasRepository {

    /**
     * Adds a new object.
     * @param object    The information of the added object.
     * @return  The added object.
     */
    public Integer add(Object object);

    /**
     * Deletes a object.
     * @param id    The id of the deleted object.
     * @return  The deleted object.
     */
    public Object delete(Integer id);

    /**
     * Finds all objects.
     * @return  Found objects.
     */
    public List<?> findAll();

    /**
     * Finds a object.
     * @param id    The id of the requested object.
     * @return  The found object.
     */
    public Object findById(Integer id);

    /**
     * Updates the information of an object.
     * @param object The new information of an object.
     * @return  The updated object.
     */
    public Object update(Object object);
}
