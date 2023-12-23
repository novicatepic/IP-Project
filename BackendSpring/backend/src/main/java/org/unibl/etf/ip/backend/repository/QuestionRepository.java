package org.unibl.etf.ip.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.ip.backend.model.PitanjeEntity;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<PitanjeEntity, Integer> {

    List<PitanjeEntity> findByProgram_Id(Integer programId);

}
