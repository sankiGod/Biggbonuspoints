package com.bonuspoint.rest.service;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bonuspoint.exception.CustomErrorException;
import com.bonuspoint.exception.ResourceNotFoundException;
import com.bonuspoint.model.PotentialCorporateProjects;
import com.bonuspoint.model.PotentialMerchants;
import com.bonuspoint.repository.MerchantRepository;
import com.bonuspoint.repository.PotentialCorporateProjectsRepository;
import com.bonuspoint.repository.PotentialMerchantsRespository;
import com.bonuspoint.repository.ProjectRepository;
import com.bonuspoint.util.ErrorCodes;

@Service
public class PotentialService {

	@Autowired
	PotentialCorporateProjectsRepository pcRepository;

	@Autowired
	PotentialMerchantsRespository pmRespository;

	@Autowired
	ProjectRepository pRepository;

	@Autowired
	MerchantRepository mRepository;

	public List<PotentialCorporateProjects> getAllPotentialProjects() {
		return pcRepository.findAll();
	}

	public List<PotentialMerchants> getAllPotentialMerchants() {
		return pmRespository.findAll();
	}

	public PotentialCorporateProjects createProject(@Valid PotentialCorporateProjects project) {
		if (pRepository.findByMobileNumber(project.getMobileNumber()) != null) {
			throw new CustomErrorException("Projects", ErrorCodes.PROJECT_MOBILE_EXISTS.getDescription(),
					ErrorCodes.PROJECT_MOBILE_EXISTS.getCode());
		}

		if (pRepository.findByEmailId(project.getEmailId()) != null) {
			throw new CustomErrorException("Projects", ErrorCodes.PROJECT_EMAIL_EXISTS.getDescription(),
					ErrorCodes.PROJECT_EMAIL_EXISTS.getCode());
		}
		try {
			return pcRepository.save(project);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("PotentialCorporateProjects", errMsg[0],
					ErrorCodes.DUPLICATE_ENTRY.getCode());
		}

	}

	public PotentialMerchants createMerchant(@Valid PotentialMerchants merchant) {
		if (mRepository.findByMobileNumber(merchant.getMobileNumber()) != null) {
			throw new CustomErrorException("Merchants", ErrorCodes.MERCHANT_MOBILE_EXISTS.getDescription(),
					ErrorCodes.MERCHANT_MOBILE_EXISTS.getCode());
		}

		if (mRepository.findByEmailId(merchant.getEmailId()) != null) {
			throw new CustomErrorException("Merchants", ErrorCodes.MERCHANT_EMAIL_EXISTS.getDescription(),
					ErrorCodes.MERCHANT_EMAIL_EXISTS.getCode());
		}

		if (mRepository.findByShopName(merchant.getShopName()) != null) {
			throw new CustomErrorException("Merchants", ErrorCodes.MERCHANT_SHOP_EXISTS.getDescription(),
					ErrorCodes.MERCHANT_SHOP_EXISTS.getCode());
		}
		try {
			return pmRespository.save(merchant);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("PotentialMerchant", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("PotentialMerchant", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}

	}

	public void approveProject(String mobileNumber) {
		try {
			if (pcRepository.findByMobileNumber(mobileNumber) != null) {
				PotentialCorporateProjects project = pcRepository.findByMobileNumber(mobileNumber);
				project.setIsApproved(true);
				pcRepository.save(project);
			} else
				throw new ResourceNotFoundException("PotentialCorporateProjects", "mobileNumber", mobileNumber);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("PotentialMerchant", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("PotentialMerchant", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	public void approveMerchant(String mobileNumber) {
		try {
			if (pmRespository.findByMobileNumber(mobileNumber) != null) {
				PotentialMerchants merchant = pmRespository.findByMobileNumber(mobileNumber);
				merchant.setIsApproved(true);
				pmRespository.save(merchant);
			} else
				throw new ResourceNotFoundException("PotentialMerchants", "mobileNumber", mobileNumber);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("PotentialMerchant", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("PotentialMerchant", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	public PotentialCorporateProjects getProject(String mobileNumber) {
		if (pcRepository.findByMobileNumber(mobileNumber) != null) {
			return pcRepository.findByMobileNumber(mobileNumber);
		} else
			throw new ResourceNotFoundException("PotentialCorporateProjects", "mobileNumber", mobileNumber);
	}

	public PotentialMerchants getMerchant(String mobileNumber) {
		if (pmRespository.findByMobileNumber(mobileNumber) != null) {
			return pmRespository.findByMobileNumber(mobileNumber);
		} else
			throw new ResourceNotFoundException("PotentialMerchants", "mobileNumber", mobileNumber);
	}

}
