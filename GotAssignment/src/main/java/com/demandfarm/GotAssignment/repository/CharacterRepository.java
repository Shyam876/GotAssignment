package com.demandfarm.GotAssignment.repository;

import com.demandfarm.GotAssignment.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {

    List<Character> findByCharacterName(String characterName);


}
