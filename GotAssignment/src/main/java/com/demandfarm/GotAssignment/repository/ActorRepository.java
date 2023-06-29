package com.demandfarm.GotAssignment.repository;

import com.demandfarm.GotAssignment.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {



}
