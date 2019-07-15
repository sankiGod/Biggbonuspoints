package com.bonuspoint.rest.service;

/*import java.io.IOException;*/
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
import com.bonuspoint.repository.TerminalRepository;
import com.bonuspoint.util.ErrorCodes;
import com.bonuspoint.util.GenerateID;
import com.bonuspoint.util.GeneratePassword;
/*import com.bonuspoint.util.SendSMS;

import okhttp3.Response;*/

@Service
public class TerminalService {

	@Autowired
	TerminalRepository repository;

	@Autowired
	MerchantRepository mrepository;

	public List<Terminal> getAll() {
		return repository.findAll();
	}

	public Terminal createTerminal(String projectID, String merchantID) {

		List<Terminal> terminals = repository.findAll();
		ArrayList<String> tids = new ArrayList<String>();
		for (Terminal t : terminals) {
			tids.add(t.getTerminalID());
		}

		try {
			Terminal terminal = new Terminal(projectID, merchantID);
			terminal.setPassword(GeneratePassword.generatePassword(8));
			terminal.setPin(GenerateID.generatePin(4));
			terminal.setTerminalID(GenerateID.generateTerminalIDs(tids));
			return repository.save(terminal);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Terminal", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Terminal", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	public Terminal updateTerminal(long tid, @Valid Terminal terminal) {
		try {
			if (repository.existsById(tid)) {
				return repository.save(terminal);
			} else
				throw new ResourceNotFoundException("Terminal", "tId", tid);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Terminal", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Terminal", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	public List<Terminal> getByProjectId(String projectID) {
		if (!repository.findByProjectID(projectID).isEmpty()) {
			return repository.findByProjectID(projectID);
		} else
			throw new ResourceNotFoundException("Terminal", "projectID", projectID);
	}

	public List<Terminal> getByMerchantId(String merchantID) {
		if (!repository.findByMerchantID(merchantID).isEmpty()) {

			return repository.findByMerchantID(merchantID);
		} else
			throw new ResourceNotFoundException("Terminal", "merchantID", merchantID);
	}

	public Terminal getByTerminalId(String terminalID) {
		if (repository.findByTerminalID(terminalID) != null) {

			return repository.findByTerminalID(terminalID);
		} else
			throw new ResourceNotFoundException("Terminal", "terminalID", terminalID);
	}

	public Terminal create(@Valid Terminal terminal) {

		List<Terminal> terminals = repository.findAll();
		ArrayList<String> tids = new ArrayList<String>();
		for (Terminal t : terminals) {
			tids.add(t.getTerminalID());
		}

		if (mrepository.findByMerchantID(terminal.getMerchantID()) != null) {
			terminal.setPassword(GeneratePassword.generatePassword(8));
			terminal.setPin(GenerateID.generatePin(4));
			terminal.setTerminalID(GenerateID.generateTerminalIDs(tids));
			Terminal terminalDetails = repository.save(terminal);
			Merchant merchant = mrepository.findByMerchantID(terminal.getMerchantID());
			merchant.getTerminals().add(terminalDetails);

			/*
			 * String messageLine1 = "Biggbonuspoints : New Terminal Added! "; String
			 * messageLine2 = " MID: " + merchant.getMerchantID(); String messageLine3 =
			 * " TID: " + terminalDetails.getTerminalID(); String messageLine4 = " PWD: " +
			 * terminalDetails.getPassword(); String messageLine5 = " PIN: " +
			 * terminalDetails.getPin(); String fullMessage = messageLine1 + "\n" +
			 * messageLine2 + "\n" + messageLine3 + "\n" + messageLine4 + "\n" +
			 * messageLine5;
			 */

			/* Response response; */
			try {
				/*
				 * response = SendSMS.sendSMS(merchant.getMobileNumber(), fullMessage);
				 * response.close();
				 */
				mrepository.save(merchant);
				return repository.save(terminalDetails);
			} catch (DataIntegrityViolationException e) {
				String[] msg = e.getRootCause().toString().split(":");
				String[] errMsg = msg[1].split("for");
				throw new CustomErrorException("Terminal", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
			} catch (ConstraintViolationException e) {
				String[] msg = e.getMessage().split("=");
				String[] errMsg = msg[1].split(",");
				throw new CustomErrorException("Terminal", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
			} /*
				 * catch (IOException e) { throw new CustomErrorException("Customer",
				 * "Not Able To Send Terminal Details",
				 * ErrorCodes.INTERNAL_SERVER_ERROR.getCode()); }
				 */

		} else
			throw new ResourceNotFoundException("Merchant", "merchantID", terminal.getMerchantID());
	}
}
