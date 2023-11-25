package com.patent.userInteraction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.patent.userInteraction.dto.PatentDetails;
import com.patent.userInteraction.dto.PatentDetailsProjection;

@Repository
public interface PatentRepository extends JpaRepository<PatentDetails, String> {

	// Query need to be written here
	@Query(value = "SELECT inventionTitle, applicationNumber, applicationFilingDate, fieldOfInvention, abstractOfInvention, completeSpecification FROM PatentDetails WHERE fieldOfInvention = :fieldOfInvention", nativeQuery = true)
	List<PatentDetailsProjection> findUserByProjection(@Param("fieldOfInvention") String fieldOfInvention);
}

//(@Param("keywords") String keywords,
//@Param("applicationFilingDate") String applicationFilingDate,