package com.patent.userInteraction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patent.userInteraction.dto.InputData;
import com.patent.userInteraction.dto.PatentDetailsProjection;
import com.patent.userInteraction.service.PatentService;

@RestController
@RequestMapping("/home")
public class PatentWebController {
	
	@Autowired
	PatentService patentService;
	
	@GetMapping("/fetchPatent")
	public ResponseEntity<List<PatentDetailsProjection>> getPatnets(InputData inputData) {
		System.out.println("got the data as : " + inputData.toString());
		return ResponseEntity.ok(patentService.getpatentDetails(inputData));

	}
}
