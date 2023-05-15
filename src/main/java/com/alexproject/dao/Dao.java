package com.alexproject.dao;

import com.alexproject.model.Publication;

import java.util.List;

public interface Dao {
    void clear();

    void save(Publication p);

    void update(Publication p);

    Publication get(Long id);

    void delete(Long id);

    List<Publication> getAll();

    int size();
}
