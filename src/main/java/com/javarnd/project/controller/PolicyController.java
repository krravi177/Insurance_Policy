package com.javarnd.project.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javarnd.project.dto.PolicyDTO;
import com.javarnd.project.entity.Policy;
import com.javarnd.project.policyservice.IPolicyServiceImpl;

@RestController
@RequestMapping("/api")
public class PolicyController {

	@Autowired
	private IPolicyServiceImpl service;

	@PostMapping("/policies")
	public ResponseEntity<?> create(@RequestParam("Policy") String policy, @RequestParam("docs") MultipartFile file) {
		try {
			service.create(policy, file);
			return ResponseEntity.status(HttpStatus.CREATED).body("Data Sved");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}

	@GetMapping("/policies")
	public ResponseEntity<?> getAll() {
		try {
			List<Policy> list = service.getAll();
			if (list.isEmpty() || list == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Mo Policy Available");
			}
			return ResponseEntity.status(HttpStatus.OK).body(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}

	@GetMapping("/policies/{Id}")
	public ResponseEntity<?> getById(@PathVariable int Id) {
		try {
			Policy byId = service.getById(Id);
			if (byId == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Mo Policy Available for this Id: " + Id);
			}
			return ResponseEntity.status(HttpStatus.OK).body(byId);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}

	@GetMapping("/customers/{Id}/policies")
	public ResponseEntity<?> getAllPolicyByCustomerId(@PathVariable Integer Id) {
		try {
			List<Policy> list = service.getAllPolicyByCustomerId(Id);
			if (list.isEmpty() || list == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Mo Policy Available");
			}
			return ResponseEntity.status(HttpStatus.OK).body(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}

	@GetMapping("/policies/policy-No/{PolicyNo}")
	public ResponseEntity<?> getByPolicyNo(@PathVariable String PolicyNo) {
		try {
			PolicyDTO policy = service.searchPolicyByPolicyNo(PolicyNo);
			if (policy == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Mo Policy Available for this PolicyNo: " + PolicyNo);
			}
			return ResponseEntity.status(HttpStatus.OK).body(policy);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}

	@GetMapping("/policies/expired/{date}")
	public ResponseEntity<?> getExpiredPolicy(@PathVariable Date date) {
		try {
			List<Policy> policy = service.expiredPolicy(date);
			if (policy == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Mo Policy is expiring till: " + date);
			}
			return ResponseEntity.status(HttpStatus.OK).body(policy);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}

	@GetMapping("/policies/active/{date}")
	public ResponseEntity<?> getActivePolicy(@PathVariable Date date) {
		try {
			List<Policy> policy = service.activePolicy(date);
			if (policy == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Mo Policy is expiring till: " + date);
			}
			return ResponseEntity.status(HttpStatus.OK).body(policy);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}

	@PutMapping("/policies")
	public ResponseEntity<?> update(@RequestParam("Policy") String policy, @RequestParam("docs") MultipartFile file) {
		try {
			service.update(policy, file);
			return ResponseEntity.status(HttpStatus.CREATED).body("Data updates");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}

}
