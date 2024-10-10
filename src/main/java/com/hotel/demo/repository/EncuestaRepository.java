package com.hotel.demo.repository;

import com.hotel.demo.entity.Encuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncuestaRepository extends JpaRepository<Encuesta, Long> {
    List<Encuesta> findByNsgeneral(String nsgeneral);
}
