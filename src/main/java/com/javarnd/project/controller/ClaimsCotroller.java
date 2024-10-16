package com.javarnd.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javarnd.project.claimservice.IClaimService;
import com.javarnd.project.dto.ClaimsDTO;
import com.javarnd.project.entity.Claims;
import com.javarnd.project.entity.Policy;

@RestController
@RequestMapping("/api")
public class ClaimsCotroller {
	
	@Autowired
	private IClaimService service;
	
	@PostMapping("/claims")
	public ResponseEntity<?> create(@RequestParam("Claims") String claim ,
			@RequestParam("docs") MultipartFile file ) {
		try {
			service.create(claim, file);                                          
			return ResponseEntity.status(HttpStatus.CREATED).body("Data Sved");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}
	
	@GetMapping("/claims")
	public ResponseEntity<?> getAll() {
		try {
			List<Claims> list = service.getAll();
			if (list.isEmpty() || list == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Mo Policy Available");
			}
			return ResponseEntity.status(HttpStatus.OK).body(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}
	
	@GetMapping("/claims/{Id}")
	public ResponseEntity<?> getById(@PathVariable int Id) {
		try {
			Claims byId = service.getById(Id);
			if (byId == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Mo Policy Available for this Id: "+Id);
			}
			return ResponseEntity.status(HttpStatus.OK).body(byId);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}
	
	@GetMapping("/customers/{Id}/claims")
	public ResponseEntity<?> getAllClaimsByCustomerId(@PathVariable int Id) {
		try {
			List<Claims> list = service.getAllClaimsByCustomerId(Id);
			
			if (list.isEmpty() || list == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Mo Claims Available");
			}
			return ResponseEntity.status(HttpStatus.OK).body(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}
	
	@GetMapping("/claims/claims-No/{claimNo}")
	public ResponseEntity<?> getByClaimsNo(@PathVariable String claimNo) {
		try {
			ClaimsDTO byId = service.searchClaimsByClaimNo(claimNo);
			if (byId == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Mo Claims are Available for this Number: "+claimNo);
			}
			return ResponseEntity.status(HttpStatus.OK).body(byId);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}
	
	@GetMapping("/claims/claim-status/{Status}")
	public ResponseEntity<?> getByClaimsStatus(@PathVariable String Status) {
		try {
			List<Claims> claims = service.findClaimsByStatus(Status);
			if (claims == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Mo Claims are: "+Status);
			}
			return ResponseEntity.status(HttpStatus.OK).body(claims);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}
	
	@PutMapping("/claims")
	public ResponseEntity<?> update(@RequestParam("Claims") String claim ,
			@RequestParam("docs") MultipartFile file ) {
		try {
			service.upadateClaims(claim, file);
			return ResponseEntity.status(HttpStatus.CREATED).body("Data Updated");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}

}
