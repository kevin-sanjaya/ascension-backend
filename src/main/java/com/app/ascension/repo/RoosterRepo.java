package com.app.ascension.repo;

import com.app.ascension.model.rooster.Rooster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoosterRepo extends JpaRepository<Rooster, String> {
    public List<Rooster> findAll();
    public Page<Rooster> findAll(Pageable p);
    Rooster save(Rooster e);
    void delete(Rooster e);
    void delete(String id);
    boolean exists(String id);
}
