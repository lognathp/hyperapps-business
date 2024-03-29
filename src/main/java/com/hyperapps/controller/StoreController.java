package com.hyperapps.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyperapps.business.StoreBusiness;
import com.hyperapps.logger.HyperAppsLogger;

@RestController
public class StoreController {
	
private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	

	@Autowired
	HyperAppsLogger Logger;
	
	@Autowired
	StoreBusiness storeBusiness;

	@GetMapping("/api/retailer/welcomeMessages")
	public Object getWelcomeMessages(@RequestParam String token) throws Exception {
		Logger.info(this.getClass(),"GET WELCOME MESSAGE API CALL STARTED AT "+dateFormat.format(new Date()));
		return storeBusiness.getWelcomeMessages(token);
	}
	
	@PostMapping(value = "/api/retailer/welcome/update",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public Object updateWelcomeMessage(@RequestParam String token,@RequestParam int user_id,
			@RequestParam String message,@RequestParam int designation) {
		Logger.info(this.getClass(),"UPDATE WELCOME MESSAGE API CALL STARTED AT "+dateFormat.format(new Date()));
		return storeBusiness.updateWelcomeMessage(token,user_id,message,designation);
	}

	@GetMapping("/api/retailer/designation")
	public Object getDesignation(@RequestParam String token) throws Exception {
		Logger.info(this.getClass(),"GET DESIGNATIONS API CALL STARTED AT "+dateFormat.format(new Date()));
		return storeBusiness.getDesignation(token);
	}
	
	@GetMapping("/api/retailer/delivery/show/{storeId}")
	public Object getDeliverySettingsDetails(@PathVariable ("storeId") int storeId,@RequestParam String token) throws Exception {
		Logger.info(this.getClass(),"GET DELIVERY SETTTINGS API CALL STARTED AT "+dateFormat.format(new Date()));
		return storeBusiness.getDeliverySettingsDetails(storeId,token);
	}
	
	@PostMapping("/api/retailer/store/running_status")
	public Object updateRunningStatus(@RequestParam String token,@RequestParam int user_id,@RequestParam int store_id,@RequestParam int running_status) {
		Logger.info(this.getClass(),"STORE RUNNING STATUS UPDATE API CALL STARTED AT "+dateFormat.format(new Date()));
		return storeBusiness.updateRunningStatus(token,user_id,store_id,running_status);
		
	}
	
	@PutMapping("/api/retailer/profile/storeTaxStatus/{storeId}")
	public Object updateTaxInfo(@PathVariable("storeId") int store_id,@RequestParam int tax_status,@RequestParam int tax_percentage,@RequestParam String tax_gst) {
		Logger.info(this.getClass(),"STORE TAX INFO UPDATE API CALL STARTED AT "+dateFormat.format(new Date()));
		return storeBusiness.updateTaxInfo(store_id,tax_status,tax_percentage,tax_gst);
		
	}
	
	@PostMapping("/api/retailer/delivery/add")
	public Object addStandardDeliverSettingsDetails(@RequestParam String token,@RequestParam int store_id,@RequestParam int delivery_type,@RequestParam double min_order_amount,
			@RequestParam double delivery_charge,@RequestParam double free_delivery_above,@RequestParam String delivery_areas,
			@RequestParam int home_delivery) {
		Logger.info(this.getClass(),"ADD STANDARD DELIVERY SETTINGS API CALL STARTED AT "+dateFormat.format(new Date()));
		return storeBusiness.addStandardDeliverSettingsDetails(token,store_id,delivery_type,min_order_amount,delivery_charge,
				free_delivery_above,delivery_areas,home_delivery);
		
	}
	
	@PostMapping("/api/retailer/delivery/update")
	public Object updateStandardDeliverSettingsDetails(@RequestParam String token,@RequestParam int store_id,@RequestParam int delivery_type,@RequestParam double min_order_amount,
			@RequestParam double delivery_charge,@RequestParam double free_delivery_above,@RequestParam String delivery_areas,
			@RequestParam int home_delivery) {
		Logger.info(this.getClass(),"UPDATE STANDARD DELIVERY SETTINGS API CALL STARTED AT "+dateFormat.format(new Date()));
		return storeBusiness.updateStandardDeliverSettingsDetails(token,store_id,delivery_type,min_order_amount,delivery_charge,
				free_delivery_above,delivery_areas,home_delivery);
	}
	
	@GetMapping("/api/retailer/offer/ongoing/{storeId}/{userId}")
	public Object getOngoingOffer(@PathVariable ("storeId") int storeId,@PathVariable ("userId") int userId,@RequestParam String token) throws Exception {
		Logger.info(this.getClass(),"GET OFFER DETAILS API CALL STARTED AT "+dateFormat.format(new Date()));
		return storeBusiness.getOngoingOffer(storeId,userId,token);
	}
	
	@GetMapping("/api/retailer/offer/old/{storeId}")
	public Object getHistoryOffer(@PathVariable ("storeId") int storeId,@RequestParam String token) throws Exception {
		Logger.info(this.getClass(),"GET OLD OFFER DETAILS API CALL STARTED AT "+dateFormat.format(new Date()));
		return storeBusiness.getHistoryOffer(storeId,token);
	}
	
	@PostMapping("/api/retailer/offer/add")
	public Object addOffer(@RequestParam String token,@RequestParam int store_id,@RequestParam String offer_heading,@RequestParam String offer_description,
			@RequestParam String offer_valid,@RequestParam String active,@RequestParam String offer_percentage,@RequestParam int offer_type,
			@RequestParam String offer_start_date,@RequestParam String offer_flat_amount,@RequestParam String offer_percentage_max_amount,
			@RequestParam String offer_max_apply_count) throws Exception {
		Logger.info(this.getClass(),"ADD OFFER DETAILS API CALL STARTED AT "+dateFormat.format(new Date()));
		return storeBusiness.addOffer(token,store_id,offer_heading,offer_description,offer_valid,active,
				offer_percentage,offer_type,offer_start_date,offer_flat_amount,offer_percentage_max_amount,offer_max_apply_count);
	}
	
	@PostMapping("/api/retailer/offer/update")
	public Object updateOffer(@RequestParam String token,@RequestParam int store_id,@RequestParam String offer_heading,@RequestParam String offer_description,
			@RequestParam String offer_valid,@RequestParam String active,@RequestParam int id,@RequestParam String offer_percentage,@RequestParam int offer_type,
			@RequestParam String offer_start_date,@RequestParam String offer_flat_amount,@RequestParam String offer_percentage_max_amount,
			@RequestParam String offer_max_apply_count) throws Exception {
		Logger.info(this.getClass(),"UPDATED OFFER DETAILS API CALL STARTED AT "+dateFormat.format(new Date()));
		return storeBusiness.updateOffer(token,store_id,offer_heading,offer_description,offer_valid,active,id,
				offer_percentage,offer_type,offer_start_date,offer_flat_amount,offer_percentage_max_amount,offer_max_apply_count);
	}
	
	@PostMapping("/api/retailer/offer/resume")
	public Object resumeOffer(@RequestParam String token,@RequestParam int store_id,@RequestParam String offer_valid,
			@RequestParam String offer_start_date,@RequestParam int id) throws Exception {
		Logger.info(this.getClass(),"RESUME OFFER DETAILS API CALL STARTED AT "+dateFormat.format(new Date()));
		return storeBusiness.resumeOffer(token,store_id,offer_valid,offer_start_date,id);
	}
	
	@DeleteMapping("/api/retailer/offer/remove/{id}")
	public Object removeOffer(@PathVariable ("id") int id,@RequestParam String token) throws Exception {
		Logger.info(this.getClass(),"REMOVE OFFER DETAILS API CALL STARTED AT "+dateFormat.format(new Date()));
		return storeBusiness.removeOffer(id,token);
	}
	
	@GetMapping("/api/retailer/reward/show/{storeId}")
	public Object rewardShow(@PathVariable ("storeId") int storeId,@RequestParam String token) throws Exception {
		Logger.info(this.getClass(),"GET REWARD API CALL STARTED AT "+dateFormat.format(new Date()));
		return storeBusiness.rewardShow(storeId,token);
	}
	
	@PostMapping("/api/retailer/reward/update")
	public Object updateRewardPoints(@RequestParam String token,@RequestParam int store_id,@RequestParam int reward_point) throws Exception {
		Logger.info(this.getClass(),"UPDATED REWARD POINTS API CALL STARTED AT "+dateFormat.format(new Date()));
		return storeBusiness.updateRewardPoints(token,store_id,reward_point);
	}
	
	@PostMapping("/api/retailer/notify/sendNotfication")
	public Object sendNotification(@RequestParam String notification_type,@RequestParam int store_id,@RequestParam String customer_ids,
			@RequestParam String email_subject,@RequestParam String email_content,@RequestParam String fcm_title,
			@RequestParam String fcm_body,@RequestParam String fcm_data) throws Exception {
		Logger.info(this.getClass(),"SEND NOTIFICATIONS API CALL STARTED AT "+dateFormat.format(new Date()));
		return storeBusiness.sendNotification(notification_type,store_id,customer_ids,email_subject,
				email_content,fcm_title,fcm_body,fcm_data);
	}
	
	
	
}
