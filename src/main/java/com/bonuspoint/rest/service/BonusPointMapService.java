package com.bonuspoint.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonuspoint.exception.ResourceNotFoundException;
import com.bonuspoint.model.BonusPointMap;
import com.bonuspoint.model.BonusPointMapTransactions;
import com.bonuspoint.repository.BonusPointMapRepository;
import com.bonuspoint.repository.BonusPointMapTransactionRepository;
import com.bonuspoint.repository.MerchantRepository;
import com.bonuspoint.repository.ProjectRepository;

@Service
public class BonusPointMapService {

	@Autowired
	BonusPointMapRepository repository;
	@Autowired
	BonusPointMapTransactionRepository trepository;
	@Autowired
	ProjectRepository prepository;
	@Autowired
	MerchantRepository mrepository;

	public List<BonusPointMap> findAll() {

		return repository.findAll();
	}

	public BonusPointMap create(@Valid BonusPointMap map) {

		if (prepository.findByProjectID(map.getProjectID()) != null) {
			if (mrepository.findByMerchantID(map.getMerchantID()) != null) {
				return repository.save(map);
			} else
				throw new ResourceNotFoundException("Merchant", "merchantID", map.getMerchantID());
		} else
			throw new ResourceNotFoundException("Project", "projectID", map.getProjectID());
	}

	public BonusPointMap update(long bpmId, @Valid BonusPointMap map) {

		if (repository.existsById(bpmId)) {
			return repository.save(map);
		} else
			throw new ResourceNotFoundException("Mapping", "bmpId", bpmId);
	}

	public void delete(String merchantID) {
		if (repository.findByMerchantID(merchantID) != null) {

			BonusPointMap map = repository.findByMerchantID(merchantID);
			repository.delete(map);

		} else
			throw new ResourceNotFoundException("Merchant", "merchantID", merchantID);
	}

	public List<BonusPointMap> getByProjectId(String projectID) {

		if (!repository.findByProjectID(projectID).isEmpty()) {
			return repository.findByProjectID(projectID);
		} else
			throw new ResourceNotFoundException("Project", "projectID", projectID);
	}

	public BonusPointMap getByMerchantId(String merchantID) {

		if (repository.findByMerchantID(merchantID) != null) {
			return repository.findByMerchantID(merchantID);
		} else
			throw new ResourceNotFoundException("Mapping", "merchantID", merchantID);
	}

	public List<BonusPointMapTransactions> getTransByMerchantId(String merchantID) {
		if (!trepository.findFirst10ByMerchantIDOrderByCreatedAtDesc(merchantID).isEmpty()) {
			return trepository.findFirst10ByMerchantIDOrderByCreatedAtDesc(merchantID);
		} else
			return new ArrayList<BonusPointMapTransactions>();
	}

	public List<BonusPointMapTransactions> getTransByProjectId(String projectID) {
		if (!trepository.findFirst10ByProjectIDOrderByCreatedAtDesc(projectID).isEmpty()) {
			return trepository.findFirst10ByProjectIDOrderByCreatedAtDesc(projectID);
		} else
			return new ArrayList<BonusPointMapTransactions>();
	}

}
