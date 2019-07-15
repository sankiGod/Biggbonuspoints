package com.bonuspoint.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.BonusPointMap;
import com.bonuspoint.model.BonusPointMapTransactions;
import com.bonuspoint.rest.service.BonusPointMapService;

@RestController
@RequestMapping("/admin/map")
public class BonusPointMapController {

	@Autowired
	BonusPointMapService service;

	@GetMapping("/getAll")
	public List<BonusPointMap> getAll() {
		return service.findAll();
	}

	@PostMapping("/create")
	public ResponseEntity<BonusPointMap> create(@Valid @RequestBody BonusPointMap map) {
		BonusPointMap maps = service.create(map);
		return new ResponseEntity<BonusPointMap>(maps, HttpStatus.CREATED);
	}

	@PutMapping("/update/{bpmId}")
	public ResponseEntity<BonusPointMap> update(@PathVariable long bpmId, @Valid @RequestBody BonusPointMap map) {
		BonusPointMap maps = service.update(bpmId, map);
		return new ResponseEntity<BonusPointMap>(maps, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{merchantID}")
	public ResponseEntity<BonusPointMap> delete(@PathVariable String merchantID) {
		service.delete(merchantID);
		return new ResponseEntity<BonusPointMap>(HttpStatus.OK);
	}

	@GetMapping("/getByProjectId/{projectID}")
	public ResponseEntity<List<BonusPointMap>> getByProjectId(@PathVariable String projectID) {
		List<BonusPointMap> maps = service.getByProjectId(projectID);
		return new ResponseEntity<List<BonusPointMap>>(maps, HttpStatus.OK);
	}

	@GetMapping("/getByMerchantId/{merchantID}")
	public ResponseEntity<BonusPointMap> getByMerchantId(@PathVariable String merchantID) {
		BonusPointMap maps = service.getByMerchantId(merchantID);
		return new ResponseEntity<BonusPointMap>(maps, HttpStatus.OK);
	}

	@GetMapping("/getTransByMerchantId/{merchantID}")
	public ResponseEntity<List<BonusPointMapTransactions>> getTransByMerchantId(@PathVariable String merchantID) {
		List<BonusPointMapTransactions> trans = service.getTransByMerchantId(merchantID);
		return new ResponseEntity<List<BonusPointMapTransactions>>(trans, HttpStatus.OK);
	}

	@GetMapping("/getTransByProjectId/{projectID}")
	public ResponseEntity<List<BonusPointMapTransactions>> getTransByProjectId(@PathVariable String projectID) {
		List<BonusPointMapTransactions> trans = service.getTransByProjectId(projectID);
		return new ResponseEntity<List<BonusPointMapTransactions>>(trans, HttpStatus.OK);
	}
}
