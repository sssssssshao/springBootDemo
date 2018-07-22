package com.sfy.myproj.respository;

import com.sfy.myproj.entity.Coords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoordsDao extends JpaRepository<Coords, String> {

    List<Coords> findBySsxqLike(String pcs);
}
