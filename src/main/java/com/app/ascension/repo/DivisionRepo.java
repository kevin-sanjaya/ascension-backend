package com.app.ascension.repo;

import com.app.ascension.model.division.Division;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DivisionRepo extends JpaRepository<Division, Integer> {
    public List<Division> findAll();
    public Page<Division> findAll(Pageable p);
    Division save(Division e);
    void delete(Division e);
    void delete(Integer id);
    boolean exists(Integer id);
}
