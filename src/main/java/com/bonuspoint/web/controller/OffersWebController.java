package com.bonuspoint.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bonuspoint.model.Offers;
import com.bonuspoint.web.service.OffersWebService;

@Controller
public class OffersWebController {

	@Autowired
	OffersWebService service;

	@PostMapping("goto_view_offer")
	public String goToViewOffer(@RequestParam("offer_id") long offerID, ModelMap model) {
		Offers offer = service.getOfferById(offerID);
		model.addAttribute("offer", offer);
		return "view_offer";
	}

	@PostMapping("goto_offer_action")
	public String goToOfferAction(@RequestParam("offer_id") long offerID, ModelMap model) {
		Offers offer = service.getOfferById(offerID);
		model.addAttribute("offer", offer);
		return "offer_action";
	}

	@PostMapping("approve_offer")
	public String approveOffer(@RequestParam("offer_id") long offerID, ModelMap model) {
		String message = service.approveOffer(offerID);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Offer Approved!");
			List<Offers> aoffers = service.getByStatus(true);
			List<Offers> poffers = service.getByStatus(false);
			List<Offers> roffers = service.getAllRejected(true);
			model.addAttribute("aoffers", aoffers);
			model.addAttribute("poffers", poffers);
			model.addAttribute("roffers", roffers);
			return "received_offers";
		} else {
			model.put("errorMessage", message);
			Offers offer = service.getOfferById(offerID);
			model.addAttribute("offer", offer);
			return "offer_action";
		}
	}

	@PostMapping("reject_offer")
	public String rejectOffer(@RequestParam("offer_id") long offerID, @RequestParam("reason") String reason,
			ModelMap model) {
		String message = service.rejectOffer(offerID, reason);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Offer Rejected!");
			List<Offers> aoffers = service.getByStatus(true);
			List<Offers> poffers = service.getByStatus(false);
			List<Offers> roffers = service.getAllRejected(true);
			model.addAttribute("aoffers", aoffers);
			model.addAttribute("poffers", poffers);
			model.addAttribute("roffers", roffers);
			return "received_offers";
		} else {
			model.put("errorMessage", message);
			Offers offer = service.getOfferById(offerID);
			model.addAttribute("offer", offer);
			return "offer_action";
		}
	}
}
