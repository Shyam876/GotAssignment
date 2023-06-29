package com.demandfarm.GotAssignment.repository;

import com.demandfarm.GotAssignment.model.CharacterHouseMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CharacterHouseMappingRepository extends JpaRepository<CharacterHouseMapping, Long> {

    public List<CharacterHouseMapping> findByHouseIdAndCharacterId(int houseId, int characterId);

    public List<CharacterHouseMapping> findByHouseId(int houseId);

}
