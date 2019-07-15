package com.bonuspoint.rest.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonuspoint.exception.CustomErrorException;
import com.bonuspoint.model.BonusPointMap;
import com.bonuspoint.model.Map;
import com.bonuspoint.repository.BonusPointMapRepository;
import com.bonuspoint.util.ErrorCodes;

@Service
public class MappingService {

	@Autowired
	BonusPointMapRepository repository;

	public Map getBp(String merchantID, int bonusPoint) {
		if (merchantID.equals(null)) {
			throw new CustomErrorException("Map", "MerchantID cannot be empty", ErrorCodes.BAD_REQUEST.getCode());
		} else if (bonusPoint == 0) {
			throw new CustomErrorException("Map", "bonusPoint cannot be 0)", ErrorCodes.BAD_REQUEST.getCode());
		}
		if (repository.findByMerchantID(merchantID) != null) {
			BonusPointMap bpmap = repository.findByMerchantID(merchantID);
			Map map = new Map();
			map.setPoints(bonusPoint);
			map.setAmount(bpmap.getAmountPerBP().multiply(new BigDecimal(bonusPoint)));
			return map;
		} else
			throw new CustomErrorException("BonusPointMap", ErrorCodes.MAPPING_NOT_FOUND.getDescription(),
					ErrorCodes.MAPPING_NOT_FOUND.getCode());

	}

	public Map getAccrued(String merchantID, BigDecimal purchaseAmount) {
		if (merchantID.equals(null)) {
			throw new CustomErrorException("Map", "MerchantID cannot be empty", ErrorCodes.BAD_REQUEST.getCode());
		} else if (purchaseAmount==null) {
			throw new CustomErrorException("Map", "purchaseAmount cannot be empty", ErrorCodes.BAD_REQUEST.getCode());
		}
		if (repository.findByMerchantID(merchantID) != null) {
			BonusPointMap bpmap = repository.findByMerchantID(merchantID);
			Map map = new Map();

			double tempPoints = (purchaseAmount.divide(new BigDecimal(100)))
					.multiply(new BigDecimal(bpmap.getBpPer100())).doubleValue();
			int points = (int) Math.round(tempPoints);
			BigDecimal amount = bpmap.getAmountPerBP().multiply(new BigDecimal(points));

			map.setPoints(points);
			map.setAmount(amount);
			return map;
		} else
			throw new CustomErrorException("BonusPointMap", ErrorCodes.MAPPING_NOT_FOUND.getDescription(),
					ErrorCodes.MAPPING_NOT_FOUND.getCode());
	}
}
