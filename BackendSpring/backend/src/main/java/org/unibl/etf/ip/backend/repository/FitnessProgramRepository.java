package org.unibl.etf.ip.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.ip.backend.model.ProgramEntity;

import java.util.List;

@Repository
public interface FitnessProgramRepository extends JpaRepository<ProgramEntity, Integer> {

    List<ProgramEntity> findByKreator_Id(Integer kreatorId);

}
