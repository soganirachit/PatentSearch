package com.patent.userInteraction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patent.userInteraction.dto.InputData;
import com.patent.userInteraction.dto.PatentDetailsProjection;
import com.patent.userInteraction.repository.PatentRepository;

@Service
public class PatentService {

	@Autowired
	private PatentRepository patentRepo;

	public List<PatentDetailsProjection> getpatentDetails(InputData inputData) {

		List<PatentDetailsProjection> pDP= patentRepo.findUserByProjection(
				inputData.getFieldOfInvention());
		System.out.println("Patent details fetched form db is : "+pDP.size());
		return (pDP);
	}
}

//inputData.getKeyWords(), inputData.getApplicationFilingDate(),