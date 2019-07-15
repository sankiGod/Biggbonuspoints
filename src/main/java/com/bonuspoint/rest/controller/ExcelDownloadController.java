package com.bonuspoint.rest.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.AwardTransactions;
import com.bonuspoint.model.CorporateMerchantsBalanceTables;
import com.bonuspoint.model.CustomersBalanceTables;
import com.bonuspoint.model.Merchant;
import com.bonuspoint.model.MerchantLimit;
import com.bonuspoint.model.MerchantsBalanceTables;
import com.bonuspoint.model.PaymentDueReport;
import com.bonuspoint.model.Project;
import com.bonuspoint.model.ProjectsBalanceTable;
import com.bonuspoint.model.RedeemTransactions;
import com.bonuspoint.repository.AwardTransationsRepository;
import com.bonuspoint.repository.CorporateMerchantsBalanceTablesRepository;
import com.bonuspoint.repository.CustomerBalanceTableRepository;
import com.bonuspoint.repository.MerchantBalanceTableRepository;
import com.bonuspoint.repository.MerchantLimitRepository;
import com.bonuspoint.repository.MerchantRepository;
import com.bonuspoint.repository.ProjectBalanceTableRepository;
import com.bonuspoint.repository.ProjectRepository;
import com.bonuspoint.repository.RedeemTransactionsRepository;
import com.bonuspoint.util.ExcelGenerator;
import com.bonuspoint.web.service.MerchantWebService;

@RestController
@RequestMapping("/downloads")
public class ExcelDownloadController {

	@Autowired
	ProjectRepository prepository;

	@Autowired
	MerchantRepository mrepository;

	@Autowired
	MerchantBalanceTableRepository mbrepository;

	@Autowired
	ProjectBalanceTableRepository pbrepository;

	@Autowired
	CustomerBalanceTableRepository cbrepository;

	@Autowired
	AwardTransationsRepository atrepository;

	@Autowired
	RedeemTransactionsRepository rdrepository;

	@Autowired
	MerchantLimitRepository mlrepository;

	@Autowired
	MerchantWebService mwservice;

	@Autowired
	CorporateMerchantsBalanceTablesRepository cmbrepository;

	@GetMapping("/xlsx/project.xlsx")
	public ResponseEntity<InputStreamResource> excelProjects() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=projects.xlsx");
		List<Project> projects = prepository.findAll();
		if (projects.isEmpty()) {
			ByteArrayInputStream in = ExcelGenerator.noDataFound();
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		}
		ByteArrayInputStream in = ExcelGenerator.projectsToExcel(projects);

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	@GetMapping("/xlsx/merchant.xlsx")
	public ResponseEntity<InputStreamResource> excelMerchants() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=merchants.xlsx");
		List<Merchant> merchants = mrepository.findAll();
		if (merchants.isEmpty()) {
			ByteArrayInputStream in = ExcelGenerator.noDataFound();
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		}
		ByteArrayInputStream in = ExcelGenerator.merchantsToExcel(merchants);

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	@GetMapping("/xlsx/disabledmerchants.xlsx")
	public ResponseEntity<InputStreamResource> excelDisabledMerchants(@RequestParam("startDate") Date startDate,
			@RequestParam("endDate") Date endDate) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=disabled_merchants.xlsx");
		List<Merchant> merchants = mrepository.findByStatusAndUpdatedAtBetween(false, startDate, endDate);
		if (merchants.isEmpty()) {
			ByteArrayInputStream in = ExcelGenerator.noDataFound();
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		}
		ByteArrayInputStream in = ExcelGenerator.merchantsToExcel(merchants);

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

	}

	@GetMapping("/xlsx/merchantwisebalance.xlsx")
	public ResponseEntity<InputStreamResource> excelMerchantWiseBalance(@RequestParam("startDate") Date startDate,
			@RequestParam("endDate") Date endDate) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=merchant_wise.xlsx");

		List<MerchantsBalanceTables> mtables = mbrepository.findByUpdatedAtBetween(startDate, endDate);
		if (mtables.isEmpty()) {
			ByteArrayInputStream in = ExcelGenerator.noDataFound();
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		}
		ByteArrayInputStream in = ExcelGenerator.merchantWiseBalanceToExcel(mtables);

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

	}

	@GetMapping("/xlsx/projectwisebalance.xlsx")
	public ResponseEntity<InputStreamResource> excelProjectWiseBalance(@RequestParam("startDate") Date startDate,
			@RequestParam("endDate") Date endDate) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=project_wise.xlsx");

		List<ProjectsBalanceTable> ptables = pbrepository.findByUpdatedAtBetween(startDate, endDate);
		if (ptables.isEmpty()) {
			ByteArrayInputStream in = ExcelGenerator.noDataFound();
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		}
		ByteArrayInputStream in = ExcelGenerator.projectWiseBalanceToExcel(ptables);

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

	}

	@GetMapping("/xlsx/customerwisebalance.xlsx")
	public ResponseEntity<InputStreamResource> excelCusotmerWiseBalance(@RequestParam("startDate") Date startDate,
			@RequestParam("endDate") Date endDate) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=cusotmer_wise.xlsx");

		List<CustomersBalanceTables> ctables = cbrepository.findByUpdatedAtBetween(startDate, endDate);
		if (ctables.isEmpty()) {
			ByteArrayInputStream in = ExcelGenerator.noDataFound();
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		}
		ByteArrayInputStream in = ExcelGenerator.customerWiseBalanceToExcel(ctables);

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

	}

	@GetMapping("/xlsx/awardtransactions.xlsx")
	public ResponseEntity<InputStreamResource> excelAwardTransactions(@RequestParam("project_id") String projectID,
			@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=award_transactions.xlsx");

		List<AwardTransactions> awards = atrepository.findByProjectIDAndCreatedAtBetween(projectID, startDate, endDate);
		if (awards.isEmpty()) {
			ByteArrayInputStream in = ExcelGenerator.noDataFound();
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		}
		ByteArrayInputStream in = ExcelGenerator.awardTransactionsToExcel(awards);

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

	}

	@GetMapping("/xlsx/redeemtransactions.xlsx")
	public ResponseEntity<InputStreamResource> excelRedeemTransactions(@RequestParam("project_id") String projectID,
			@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=redeem_transactions.xlsx");

		List<RedeemTransactions> redeems = rdrepository.findByProjectIDAndCreatedAtBetween(projectID, startDate,
				endDate);
		if (redeems.isEmpty()) {
			ByteArrayInputStream in = ExcelGenerator.noDataFound();
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		}
		ByteArrayInputStream in = ExcelGenerator.redeemTransactionsToExcel(redeems);

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

	}

	@GetMapping("/xlsx/uploadlimitformat.xlsx")
	public ResponseEntity<InputStreamResource> excelLimitFormat() throws IOException {
		ByteArrayInputStream in = ExcelGenerator.uploadLimitFormat();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=limit_upload_format.xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	@GetMapping("/xlsx/paymentdue.xlsx")
	public ResponseEntity<InputStreamResource> getPaymentDue() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=payment_dues.xlsx");
		Date endDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(Calendar.DATE, -7);
		Date startDate = cal.getTime();

		List<MerchantLimit> limits = mlrepository.findByDueDateBetween(startDate, endDate);
		if (limits.isEmpty()) {
			ByteArrayInputStream in = ExcelGenerator.noDataFound();
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		}

		List<PaymentDueReport> reports = mwservice.getReports(limits);
		if (reports.isEmpty()) {
			ByteArrayInputStream in = ExcelGenerator.noDataFound();
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		}
		ByteArrayInputStream in = ExcelGenerator.paymentDueReport(reports);

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

	}

	@GetMapping("/xlsx/corporaterecon.xlsx")
	public ResponseEntity<InputStreamResource> getCorporateRecon(@RequestParam("project_id") String projectID)
			throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + projectID + "-recon.xlsx");

		List<CorporateMerchantsBalanceTables> cmbtables = cmbrepository.findByProjectID(projectID);
		if (cmbtables.isEmpty()) {
			ByteArrayInputStream in = ExcelGenerator.noDataFound();
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		}
		ByteArrayInputStream in = ExcelGenerator.corporateMerchantWiseBalanceToExcel(cmbtables);

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
}
