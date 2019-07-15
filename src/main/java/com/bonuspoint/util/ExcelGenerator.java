package com.bonuspoint.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bonuspoint.model.AwardTransactions;
import com.bonuspoint.model.CorporateMerchantsBalanceTables;
import com.bonuspoint.model.CustomersBalanceTables;
import com.bonuspoint.model.Merchant;
import com.bonuspoint.model.MerchantsBalanceTables;
import com.bonuspoint.model.PaymentDueReport;
import com.bonuspoint.model.Project;
import com.bonuspoint.model.ProjectsBalanceTable;
import com.bonuspoint.model.RedeemTransactions;
import com.bonuspoint.model.Terminal;

public class ExcelGenerator {

	public static ByteArrayInputStream noDataFound() throws IOException {
		String[] COLUMNs = { "NO", "DATA", "AVAILABLE", "TO", "DOWNLOAD" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			Sheet sheet = workbook.createSheet("Projects");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.RED.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}

	}

	public static ByteArrayInputStream projectsToExcel(List<Project> projects) throws IOException {
		String[] COLUMNs = { "PId", "Project Name", "Project ID", "Contact Person", "Mobile Number", "Email Id",
				"Abbrv", "Legal Name", "City", "State", "Pincode", "Country", "Is Transfer Allowed Within Project",
				"Is Corporate", "Project Type", "Created At", "Updated At" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			Sheet sheet = workbook.createSheet("Projects");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (Project project : projects) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(project.getpId());
				row.createCell(1).setCellValue(project.getProjectName());
				row.createCell(2).setCellValue(project.getProjectID());
				row.createCell(3).setCellValue(project.getContactPerson());
				row.createCell(4).setCellValue(project.getMobileNumber());
				row.createCell(5).setCellValue(project.getEmailId());
				row.createCell(6).setCellValue(project.getAbbrv());
				row.createCell(7).setCellValue(project.getLegalName());
				row.createCell(8).setCellValue(project.getAddress().getCity());
				row.createCell(9).setCellValue(project.getAddress().getState());
				row.createCell(10).setCellValue(project.getAddress().getPin());
				row.createCell(11).setCellValue(project.getAddress().getCountry());
				row.createCell(12).setCellValue(project.getIsTransferAllowedWithinProject());
				row.createCell(13).setCellValue(project.getIsCorporate());
				row.createCell(14).setCellValue(project.getProjectType());
				row.createCell(15).setCellValue(project.getCreatedAt().toString());
				row.createCell(16).setCellValue(project.getUpdatedAt().toString());
			}

			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
			sheet.autoSizeColumn(9);
			sheet.autoSizeColumn(10);
			sheet.autoSizeColumn(11);
			sheet.autoSizeColumn(12);
			sheet.autoSizeColumn(13);
			sheet.autoSizeColumn(14);
			sheet.autoSizeColumn(15);
			sheet.autoSizeColumn(16);
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream merchantsToExcel(List<Merchant> merchants) throws IOException {
		String[] COLUMNs = { "MId", "Project ID", "Merchant ID", "Contact Person", "Mobile Number", "Email Id",
				"Gst Number", "Aadhaar Number", "Pan Number", "Shop Name", "Legal Name", "Shop No", "Lane No",
				"Lane Name", "Area Name", "Landmark", "City", "State", "Pincode", "Country", "Status", "Created At",
				"Updated At" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			Sheet sheet = workbook.createSheet("Merchants");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (Merchant merchant : merchants) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(merchant.getmId());
				row.createCell(1).setCellValue(merchant.getProjectID());
				row.createCell(2).setCellValue(merchant.getMerchantID());
				row.createCell(3).setCellValue(merchant.getContactPerson());
				row.createCell(4).setCellValue(merchant.getMobileNumber());
				row.createCell(5).setCellValue(merchant.getEmailId());
				row.createCell(6).setCellValue(merchant.getGstNumber());
				row.createCell(7).setCellValue(merchant.getAadharNumber());
				row.createCell(8).setCellValue(merchant.getPanNumber());
				row.createCell(9).setCellValue(merchant.getShopName());
				row.createCell(10).setCellValue(merchant.getLegalName());
				row.createCell(11).setCellValue(merchant.getAddress().getShopNo());
				row.createCell(12).setCellValue(merchant.getAddress().getLaneNo());
				row.createCell(13).setCellValue(merchant.getAddress().getLaneName());
				row.createCell(14).setCellValue(merchant.getAddress().getAreaName());
				row.createCell(15).setCellValue(merchant.getAddress().getLandmark());
				row.createCell(16).setCellValue(merchant.getAddress().getCity());
				row.createCell(17).setCellValue(merchant.getAddress().getState());
				row.createCell(18).setCellValue(merchant.getAddress().getPin());
				row.createCell(19).setCellValue(merchant.getAddress().getCountry());
				row.createCell(20).setCellValue(merchant.getStatus());
				row.createCell(21).setCellValue(merchant.getCreatedAt().toString());
				row.createCell(22).setCellValue(merchant.getUpdatedAt().toString());
			}

			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
			sheet.autoSizeColumn(9);
			sheet.autoSizeColumn(10);
			sheet.autoSizeColumn(11);
			sheet.autoSizeColumn(12);
			sheet.autoSizeColumn(13);
			sheet.autoSizeColumn(14);
			sheet.autoSizeColumn(15);
			sheet.autoSizeColumn(16);
			sheet.autoSizeColumn(17);
			sheet.autoSizeColumn(18);
			sheet.autoSizeColumn(19);
			sheet.autoSizeColumn(20);
			sheet.autoSizeColumn(21);
			sheet.autoSizeColumn(22);

			String[] COLUMNs2 = { "tId", "ProjectID", "MerchantID", "TerminalID", "Password", "Pin", "Created At",
					"Updated At" };
			Sheet sheet1 = workbook.createSheet("Terminals");

			// Row for Header
			Row headerRow1 = sheet1.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs2.length; col++) {
				Cell cell = headerRow1.createCell(col);
				cell.setCellValue(COLUMNs2[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int row2Idx = 1;
			for (Merchant merchant : merchants) {
				for (Terminal terminal : merchant.getTerminals()) {
					Row row = sheet1.createRow(row2Idx++);
					row.createCell(0).setCellValue(terminal.gettId());
					row.createCell(1).setCellValue(terminal.getProjectID());
					row.createCell(2).setCellValue(terminal.getMerchantID());
					row.createCell(3).setCellValue(terminal.getTerminalID());
					row.createCell(4).setCellValue(terminal.getPassword());
					row.createCell(5).setCellValue(terminal.getPin());
					row.createCell(6).setCellValue(terminal.getCreatedAt().toString());
					row.createCell(7).setCellValue(terminal.getUpdatedAt().toString());
				}
			}
			sheet1.autoSizeColumn(0);
			sheet1.autoSizeColumn(1);
			sheet1.autoSizeColumn(2);
			sheet1.autoSizeColumn(3);
			sheet1.autoSizeColumn(4);
			sheet1.autoSizeColumn(5);
			sheet1.autoSizeColumn(6);
			sheet1.autoSizeColumn(7);

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream merchantWiseBalanceToExcel(List<MerchantsBalanceTables> merchants)
			throws IOException {
		String[] COLUMNs = { "MbId", "Project ID", "Merchant ID", "Total Points Awarded", "Total Amount Awarded",
				"Total Points Redeemed", "Total Amount Redeemed", "Final Points", "Final Amount", "Created At",
				"Updated At" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			Sheet sheet = workbook.createSheet("Merchants");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (MerchantsBalanceTables mtable : merchants) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(mtable.getMbId());
				row.createCell(1).setCellValue(mtable.getProjectID());
				row.createCell(2).setCellValue(mtable.getMerchantID());
				row.createCell(3).setCellValue(mtable.getTotalPointsAwarded());
				row.createCell(4).setCellValue(mtable.getTotalAmountAwarded().doubleValue());
				row.createCell(5).setCellValue(mtable.getTotalPointsRedeemed());
				row.createCell(6).setCellValue(mtable.getTotalAmountRedeemed().doubleValue());
				row.createCell(7).setCellValue(mtable.getFinalPoints());
				row.createCell(8).setCellValue(mtable.getFinalAmount().doubleValue());
				row.createCell(9).setCellValue(mtable.getCreatedAt().toString());
				row.createCell(10).setCellValue(mtable.getUpdatedAt().toString());

			}
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
			sheet.autoSizeColumn(9);
			sheet.autoSizeColumn(10);

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream projectWiseBalanceToExcel(List<ProjectsBalanceTable> projects)
			throws IOException {
		String[] COLUMNs = { "PbId", "Project ID", "Total Points Awarded", "Total Amount Awarded",
				"Total Points Redeemed", "Total Amount Redeemed", "Final Points", "Final Amount", "Created At",
				"Updated At" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			Sheet sheet = workbook.createSheet("Projects");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (ProjectsBalanceTable ptable : projects) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(ptable.getPbId());
				row.createCell(1).setCellValue(ptable.getProjectID());
				row.createCell(2).setCellValue(ptable.getTotalPointsAwarded());
				row.createCell(3).setCellValue(ptable.getTotalAmountAwarded().doubleValue());
				row.createCell(4).setCellValue(ptable.getTotalPointsRedeemed());
				row.createCell(5).setCellValue(ptable.getTotalAmountRedeemed().doubleValue());
				row.createCell(6).setCellValue(ptable.getFinalPoints());
				row.createCell(7).setCellValue(ptable.getFinalAmount().doubleValue());
				row.createCell(8).setCellValue(ptable.getCreatedAt().toString());
				row.createCell(9).setCellValue(ptable.getUpdatedAt().toString());

			}
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
			sheet.autoSizeColumn(9);

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream customerWiseBalanceToExcel(List<CustomersBalanceTables> customers)
			throws IOException {
		String[] COLUMNs = { "CbId", "Project ID", "Customer ID", "Total Points Awarded", "Total Amount Awarded",
				"Total Points Redeemed", "Total Amount Redeemed", "Final Points", "Final Amount", "Created At",
				"Updated At" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			Sheet sheet = workbook.createSheet("Customers");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (CustomersBalanceTables ctable : customers) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(ctable.getCbId());
				row.createCell(1).setCellValue(ctable.getProjectID());
				row.createCell(2).setCellValue(ctable.getCustomerID());
				row.createCell(3).setCellValue(ctable.getTotalPointsAwarded());
				row.createCell(4).setCellValue(ctable.getTotalAmountAwarded().doubleValue());
				row.createCell(5).setCellValue(ctable.getTotalPointsRedeemed());
				row.createCell(6).setCellValue(ctable.getTotalAmountRedeemed().doubleValue());
				row.createCell(7).setCellValue(ctable.getFinalPoints());
				row.createCell(8).setCellValue(ctable.getFinalAmount().doubleValue());
				row.createCell(9).setCellValue(ctable.getCreatedAt().toString());
				row.createCell(10).setCellValue(ctable.getUpdatedAt().toString());

			}
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
			sheet.autoSizeColumn(9);
			sheet.autoSizeColumn(10);

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream awardTransactionsToExcel(List<AwardTransactions> awards) throws IOException {
		String[] COLUMNs = { "AtId", "Project ID", "MerchantID", "TerminalID", "CustomerID", "Mobile No", "Award Code",
				"Purchase Amount", "Award Points Accrued", "Award Amount", "RRN", "Created At", "Updated At" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			Sheet sheet = workbook.createSheet("Awards");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (AwardTransactions award : awards) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(award.getAtId());
				row.createCell(1).setCellValue(award.getProjectID());
				row.createCell(2).setCellValue(award.getMerchantID());
				row.createCell(3).setCellValue(award.getTerminalID());
				row.createCell(4).setCellValue(award.getCustomerID());
				row.createCell(5).setCellValue(award.getMobileNumber());
				row.createCell(6).setCellValue(award.getAwardCode());
				row.createCell(7).setCellValue(award.getPurchaseAmount().doubleValue());
				row.createCell(8).setCellValue(award.getAwardPointsAccrued());
				row.createCell(9).setCellValue(award.getAwardAmount().doubleValue());
				row.createCell(10).setCellValue(award.getRrn());
				row.createCell(11).setCellValue(award.getCreatedAt().toString());
				row.createCell(12).setCellValue(award.getUpdatedAt().toString());
			}
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
			sheet.autoSizeColumn(9);
			sheet.autoSizeColumn(10);
			sheet.autoSizeColumn(11);
			sheet.autoSizeColumn(12);

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream redeemTransactionsToExcel(List<RedeemTransactions> redeems) throws IOException {
		String[] COLUMNs = { "RtId", "Project ID", "MerchantID", "TerminalID", "CustomerID", "Mobile No", "Redeem Code",
				"Purchase Amount", "Redeem Points Accrued", "Redeem Amount", "RRN", "Created At", "Updated At" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			Sheet sheet = workbook.createSheet("Redeems");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (RedeemTransactions redeem : redeems) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(redeem.getRtId());
				row.createCell(1).setCellValue(redeem.getProjectID());
				row.createCell(2).setCellValue(redeem.getMerchantID());
				row.createCell(3).setCellValue(redeem.getTerminalID());
				row.createCell(4).setCellValue(redeem.getCustomerID());
				row.createCell(5).setCellValue(redeem.getMobileNumber());
				row.createCell(6).setCellValue(redeem.getRedeemCode());
				row.createCell(7).setCellValue(redeem.getPurchaseAmount().doubleValue());
				row.createCell(8).setCellValue(redeem.getRedeemPointsAccrued());
				row.createCell(9).setCellValue(redeem.getRedeemAmount().doubleValue());
				row.createCell(10).setCellValue(redeem.getRrn());
				row.createCell(11).setCellValue(redeem.getCreatedAt().toString());
				row.createCell(12).setCellValue(redeem.getUpdatedAt().toString());
			}
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
			sheet.autoSizeColumn(9);
			sheet.autoSizeColumn(10);
			sheet.autoSizeColumn(11);
			sheet.autoSizeColumn(12);

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream uploadLimitFormat() throws IOException {
		String[] COLUMNs = { "Project ID", "Project Name", "MerchantID", "Agreed Top-Up Amount", "Actual Top-Up Paid",
				"Bank Name", "Cheque Number", "Cheque Date(dd-mm-yyyy)", "Cheque Clearance Date(dd-mm-yyyy)",
				"Transation ID", "City" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			Sheet sheet = workbook.createSheet("Redeems");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
			sheet.autoSizeColumn(9);
			sheet.autoSizeColumn(10);
			sheet.autoSizeColumn(11);

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream paymentDueReport(List<PaymentDueReport> reports) throws IOException {
		String[] COLUMNs = { "Sr No.", "MerchantID", "Project ID", "Contact Person", "Mobile Number", "Email ID",
				"Due Date", "Merchant Limit" };

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			Sheet sheet = workbook.createSheet("Payment Due");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			int rowCount = 1;
			for (PaymentDueReport report : reports) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(rowCount);
				row.createCell(1).setCellValue(report.getMerchantID());
				row.createCell(2).setCellValue(report.getProjectID());
				row.createCell(3).setCellValue(report.getContactPerson());
				row.createCell(4).setCellValue(report.getMobileNumber());
				row.createCell(5).setCellValue(report.getEmailId());
				row.createCell(6).setCellValue(report.getDueDate().toString());
				row.createCell(7).setCellValue(report.getMerchantLimit().doubleValue());

				rowCount++;
			}
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}

	}

	public static ByteArrayInputStream corporateMerchantWiseBalanceToExcel(
			List<CorporateMerchantsBalanceTables> cmbtables) throws IOException {
		String[] COLUMNs = { "MbId", "Project ID", "Project Name", "Merchant ID", "Contact Person", "Mobile Number",
				"Previous Day Balance", "Total Points Awarded", "Total Amount Awarded", "Total Points Redeemed",
				"Total Amount Redeemed", "Final Points", "Final Amount", "Current Balance", "Shop No", "Lane No",
				"Lane Name", "Area Name", "Landmark", "City", "State", "Pincode", "Country", "Created At",
				"Updated At" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			Sheet sheet = workbook.createSheet("Corporate Merchants");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (CorporateMerchantsBalanceTables cmbtable : cmbtables) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(cmbtable.getCmbId());
				row.createCell(1).setCellValue(cmbtable.getProjectID());
				row.createCell(2).setCellValue(cmbtable.getProjectName());
				row.createCell(3).setCellValue(cmbtable.getMerchantID());
				row.createCell(4).setCellValue(cmbtable.getContactPerson());
				row.createCell(5).setCellValue(cmbtable.getMobileNumber());
				row.createCell(6).setCellValue(cmbtable.getPreviousDayBalance().doubleValue());
				row.createCell(7).setCellValue(cmbtable.getTotalPointsAwarded());
				row.createCell(8).setCellValue(cmbtable.getTotalAmountAwarded().doubleValue());
				row.createCell(9).setCellValue(cmbtable.getTotalPointsRedeemed());
				row.createCell(10).setCellValue(cmbtable.getTotalAmountRedeemed().doubleValue());
				row.createCell(11).setCellValue(cmbtable.getFinalPoints());
				row.createCell(12).setCellValue(cmbtable.getFinalAmount().doubleValue());
				row.createCell(13).setCellValue(cmbtable.getCurrentBalance().doubleValue());
				row.createCell(14).setCellValue(cmbtable.getAddress().getShopNo());
				row.createCell(15).setCellValue(cmbtable.getAddress().getLaneNo());
				row.createCell(16).setCellValue(cmbtable.getAddress().getLaneName());
				row.createCell(17).setCellValue(cmbtable.getAddress().getAreaName());
				row.createCell(18).setCellValue(cmbtable.getAddress().getLandmark());
				row.createCell(19).setCellValue(cmbtable.getAddress().getCity());
				row.createCell(20).setCellValue(cmbtable.getAddress().getState());
				row.createCell(21).setCellValue(cmbtable.getAddress().getPin());
				row.createCell(22).setCellValue(cmbtable.getAddress().getCountry());
				row.createCell(23).setCellValue(cmbtable.getCreatedAt().toString());
				row.createCell(24).setCellValue(cmbtable.getUpdatedAt().toString());
			}
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
			sheet.autoSizeColumn(9);
			sheet.autoSizeColumn(10);
			sheet.autoSizeColumn(11);
			sheet.autoSizeColumn(12);
			sheet.autoSizeColumn(13);
			sheet.autoSizeColumn(14);
			sheet.autoSizeColumn(15);
			sheet.autoSizeColumn(16);
			sheet.autoSizeColumn(17);
			sheet.autoSizeColumn(18);
			sheet.autoSizeColumn(19);
			sheet.autoSizeColumn(20);
			sheet.autoSizeColumn(21);
			sheet.autoSizeColumn(22);
			sheet.autoSizeColumn(23);
			sheet.autoSizeColumn(24);
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
}
