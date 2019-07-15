package com.bonuspoint.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bonuspoint.exception.CustomErrorException;
import com.bonuspoint.exception.ResourceNotFoundException;
import com.bonuspoint.model.Merchant;
import com.bonuspoint.model.Terminal;
import com.bonuspoint.repository.MerchantRepository;
import com.bonuspoint.util.ErrorCodes;
import com.bonuspoint.util.GenerateID;

@Service
public class MerchantService {

	@Autowired
	MerchantRepository repository;
	@Autowired
	TerminalService service;

	public List<Merchant> getAllMerchants() {
		return repository.findAll();
	}

	public Merchant createMerchant(@Valid Merchant merchant) {

		try {

			List<Merchant> merchants = repository.findAll();
			ArrayList<String> mids = new ArrayList<String>();
			for (Merchant m : merchants) {
				mids.add(m.getMerchantID());
			}
			merchant.setMerchantID(GenerateID.generateMerchantID(mids, merchant.getProjectID().substring(0, 4)));
			Merchant merchantDetails = repository.save(merchant);

			List<Terminal> terminals = new ArrayList<Terminal>();

			Terminal terminal = service.createTerminal(merchantDetails.getProjectID(), merchantDetails.getMerchantID());
			terminals.add(terminal);

			merchantDetails.setTerminals(terminals);
			return repository.save(merchantDetails);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Merchant", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Merchant", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	public Merchant findByMerchantID(String merchantID) {

		if (repository.findByMerchantID(merchantID) != null) {
			return repository.findByMerchantID(merchantID);
		} else
			throw new ResourceNotFoundException("Merchant", "merchantID", merchantID);
	}

	public List<Merchant> findByProjectID(String projectID) {

		if (!repository.findByProjectID(projectID).isEmpty()) {
			return repository.findByProjectID(projectID);
		} else
			throw new ResourceNotFoundException("Merchant", "projectID", projectID);
	}

	public Merchant findByMobileNumber(String mobileNumber) {

		if (repository.findByMobileNumber(mobileNumber) != null) {
			return repository.findByMobileNumber(mobileNumber);
		} else
			throw new ResourceNotFoundException("Merchant", "mobileNumber", mobileNumber);
	}

	public Merchant updateMerchant(long mid, @Valid Merchant m) {
		try {
			if (repository.existsById(mid)) {
				return repository.save(m);
			} else
				throw new ResourceNotFoundException("Merchant", "mid", mid);

		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Merchant", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Merchant", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	public Merchant disableMerchant(String merchantID) {
		try {
			if (repository.findByMerchantID(merchantID) != null) {
				Merchant mer = repository.findByMerchantID(merchantID);
				mer.setStatus(false);
				return repository.save(mer);
			} else
				throw new ResourceNotFoundException("Merchant", "merchantID", merchantID);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Merchant", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Merchant", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	public List<Merchant> getAllActiveMerchants() {
		if (!repository.findByStatus(true).isEmpty()) {
			return repository.findByStatus(true);
		} else
			throw new ResourceNotFoundException("Merchant", "Status", true);
	}

	public Merchant changeMobileNo(String merchantID, String mobileNumber) {
		try {
			if (repository.findByMerchantID(merchantID) != null) {
				Merchant mer = repository.findByMerchantID(merchantID);
				mer.setMobileNumber(mobileNumber);
				return repository.save(mer);
			} else
				throw new ResourceNotFoundException("Merchant", "merchantID", merchantID);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Merchant", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Merchant", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	public Merchant changePassword(String merchantID, String terminalID, String oldPassword, String newPassword) {
		try {
			if (repository.findByMerchantID(merchantID) != null) {
				Merchant mer = repository.findByMerchantID(merchantID);
				List<Terminal> terminals = mer.getTerminals();
				for (Terminal terminal : terminals) {
					if (terminal.getTerminalID().equals(terminalID)) {
						if (terminal.getPassword().equals(oldPassword)) {
							terminal.setPassword(newPassword);
						} else
							throw new CustomErrorException("Terminal", ErrorCodes.INCORRECT_PASSWORD.getDescription(),
									ErrorCodes.INCORRECT_PASSWORD.getCode());
					}
				}
				mer.setTerminals(terminals);
				return repository.save(mer);

			} else
				throw new ResourceNotFoundException("Merchant", "merchantID", merchantID);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Merchant", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Merchant", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	public Merchant changePin(long mid, String terminalID, int oldPin, int newPin) {
		try {
			Merchant mer = repository.findById(mid)
					.orElseThrow(() -> new ResourceNotFoundException("Merchant", "mid", mid));
			List<Terminal> terminals = mer.getTerminals();
			for (Terminal terminal : terminals) {
				if (terminal.getTerminalID().equals(terminalID)) {
					if (terminal.getPin() == oldPin) {
						terminal.setPin(newPin);
					} else
						throw new CustomErrorException("Terminal", "Incorrect Pin",
								ErrorCodes.INCORRECT_PASSWORD.getCode());
				}
			}
			mer.setTerminals(terminals);
			return repository.save(mer);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Merchant", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Merchant", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	public Merchant enableMerchant(String merchantID) {
		try {
			if (repository.findByMerchantID(merchantID) != null) {
				Merchant mer = repository.findByMerchantID(merchantID);
				mer.setStatus(true);
				return repository.save(mer);
			} else
				throw new ResourceNotFoundException("Merchant", "merchantID", merchantID);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Merchant", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Merchant", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	public boolean getStatus(String merchantID) {
		return repository.findByMerchantID(merchantID).getStatus();
	}
}
