package com.hyperapps.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.hyperapps.constants.HyperAppsConstants;
import com.hyperapps.fcm.PushNotificationService;
import com.hyperapps.logger.ConfigProperties;
import com.hyperapps.logger.HyperAppsLogger;
import com.hyperapps.model.APIResponse;
import com.hyperapps.model.DeliveryAreas;
import com.hyperapps.model.DeliverySettings;
import com.hyperapps.model.Designation;
import com.hyperapps.model.OfferHistoryData;
import com.hyperapps.model.Response;
import com.hyperapps.model.Store;
import com.hyperapps.model.WelcomeMessage;
import com.hyperapps.model.Profile.Business_operating_timings;
import com.hyperapps.service.StoreService;
import com.hyperapps.util.ResponseKeys;
import com.hyperapps.service.EmailService;
import com.hyperapps.service.RetailerService;
import com.hyperapps.validation.RetailerValidationService;

@Component
public class StoreBusiness {

	@Autowired
	HyperAppsLogger LOGGER;

	@Autowired
	ConfigProperties configProp;

	@Autowired
	StoreService storeService;

	@Autowired
	EmailService emailService;

	@Autowired
	PushNotificationService pushNotificationService;

	@Autowired
	RetailerValidationService retailerValidationService;

	@Autowired
	APIResponse apiResponse;

	@Autowired
	Response response;

	public Object getWelcomeMessages(String token) {
		ResponseEntity<Object> respEntity = null;
		if ((respEntity = retailerValidationService.validateToken(token, respEntity)) == null) {
			List<WelcomeMessage> welComeList = storeService.getWelcomeMessages(token);
			if (welComeList.size() != 0) {
				LOGGER.info(this.getClass(), "WELCOME MESSAGES LISTED SUCCESSFULLY");
				response.setStatus(HttpStatus.OK.toString());
				response.setMessage("Welcome Messages Listed Successfully");
				response.setData(welComeList);
				response.setError(HyperAppsConstants.RESPONSE_FALSE);
				apiResponse.setResponse(response);
				return new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
			} else {
				LOGGER.error(this.getClass(), "WELCOME MESSAGES NOT FOUND");
				response.setStatus(HttpStatus.NOT_FOUND.toString());
				response.setMessage("Welcome Messages Not found");
				response.setError(HyperAppsConstants.RESPONSE_TRUE);
				response.setData(null);
				apiResponse.setResponse(response);
				return new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
			}

		}
		return respEntity;
	}

	public Object updateWelcomeMessage(String token, int user_id, String message, int designation) {
		ResponseEntity<Object> respEntity = null;
		if ((respEntity = retailerValidationService.validateToken(token, respEntity)) == null) {
			if (storeService.updateWelcomeMessage(user_id, message, designation)) {
				LOGGER.info(this.getClass(), "WELCOME MESSAGES UPDATED SUCCESSFULLY");
				response.setStatus(HttpStatus.OK.toString());
				response.setMessage("Welcome Messages Updated Successfully");
				response.setData(null);
				response.setError(HyperAppsConstants.RESPONSE_FALSE);
				apiResponse.setResponse(response);
				return new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
			} else {
				LOGGER.error(this.getClass(), "WELCOME MESSAGES NOT FOUND");
				response.setStatus(HttpStatus.NOT_FOUND.toString());
				response.setMessage("Welcome Messages Not found");
				response.setError(HyperAppsConstants.RESPONSE_TRUE);
				response.setData(null);
				apiResponse.setResponse(response);
				return new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
			}

		}
		return respEntity;
	}

	public Object getDesignation(String token) {
		ResponseEntity<Object> respEntity = null;
		if ((respEntity = retailerValidationService.validateToken(token, respEntity)) == null) {
			List<Designation> desgList = storeService.getDesignation();
			if (desgList.size() != 0) {
				LOGGER.info(this.getClass(), "DESIGNATION LISTED SUCCESSFULLY");
				response.setStatus(HttpStatus.OK.toString());
				response.setMessage("Designation Listed Successfully");
				response.setData(desgList);
				response.setError(HyperAppsConstants.RESPONSE_FALSE);
				apiResponse.setResponse(response);
				return new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
			} else {
				LOGGER.error(this.getClass(), "DESIGNATION NOT FOUND");
				response.setStatus(HttpStatus.NOT_FOUND.toString());
				response.setMessage("Designations Not found");
				response.setError(HyperAppsConstants.RESPONSE_TRUE);
				response.setData(null);
				apiResponse.setResponse(response);
				return new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
			}

		}
		return respEntity;
	}

	public Object getDeliverySettingsDetails(int storeId, String token) {
		ResponseEntity<Object> respEntity = null;
		if ((respEntity = retailerValidationService.validateToken(token, respEntity)) == null) {
			List<DeliverySettings> destList = storeService.getDeliverySettingsDetails(storeId);
			if (destList.size() != 0) {
				LOGGER.info(this.getClass(), "DESIGNATION LISTED SUCCESSFULLY");
				response.setStatus(HttpStatus.OK.toString());
				response.setMessage("Designation Listed Successfully");
				response.setData(destList);
				response.setError(HyperAppsConstants.RESPONSE_FALSE);
				apiResponse.setResponse(response);
				return new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
			} else {
				LOGGER.error(this.getClass(), "DESIGNATION NOT FOUND");
				response.setStatus(HttpStatus.NOT_FOUND.toString());
				response.setMessage("Designations Not found");
				response.setError(HyperAppsConstants.RESPONSE_TRUE);
				response.setData(null);
				apiResponse.setResponse(response);
				return new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
			}

		}
		return respEntity;
	}

	public Object updateRunningStatus(String token, int user_id, int store_id, int running_status) {
		ResponseEntity<Object> respEntity = null;
		if ((respEntity = retailerValidationService.validateToken(token, respEntity)) == null) {
			if ((respEntity = retailerValidationService.validateStoreId(store_id, respEntity)) == null) {
				if (storeService.updateStoreRunningStatus(store_id, running_status)) {
					LOGGER.info(this.getClass(), "STORE RUNNING STATUS UPDATED SUCCESSFULLY");
					response.setStatus(HttpStatus.OK.toString());
					response.setMessage("Store Running status Updated Successfully");
					response.setError(HyperAppsConstants.RESPONSE_FALSE);
					response.setData(null);
					apiResponse.setResponse(response);
					respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
				} else {
					LOGGER.error(this.getClass(), "UNABLE TO UPDATE CUSTOMER DETAILS");
					response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
					response.setMessage("Unable to update Store Running status");
					response.setError(HyperAppsConstants.RESPONSE_TRUE);
					response.setData(null);
					apiResponse.setResponse(response);
					respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
				}
			}
		}
		return respEntity;
	}

	public Object updateTaxInfo(int store_id, int tax_status, int tax_percentage, String tax_gst) {
		ResponseEntity<Object> respEntity = null;
		if ((respEntity = retailerValidationService.validateStoreId(store_id, respEntity)) == null) {
			if (storeService.updateTaxInfo(store_id, tax_status, tax_percentage, tax_gst)) {
				LOGGER.info(this.getClass(), "STORE TAX INFO UPDATED SUCCESSFULLY");
				response.setStatus(HttpStatus.OK.toString());
				response.setMessage("Store Tax info Updated Successfully");
				response.setError(HyperAppsConstants.RESPONSE_FALSE);
				response.setData(null);
				apiResponse.setResponse(response);
				respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
			} else {
				LOGGER.error(this.getClass(), "UNABLE TO UPDATE TAX INFO");
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
				response.setMessage("Unable to update Tax Info");
				response.setError(HyperAppsConstants.RESPONSE_TRUE);
				response.setData(null);
				apiResponse.setResponse(response);
				respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
			}
		}
		return respEntity;
	}

	public Object getStoreBusinesstime(int store_id) {
		ResponseEntity<Object> respEntity = null;
		Store store = new Store();
		if ((respEntity = retailerValidationService.validateStoreId(store_id, respEntity)) == null) {
			List<Business_operating_timings> botList = storeService.getStoreBusinessTime(store_id, store)
					.getBusiness_operating_timings();
			if (!botList.isEmpty()) {
				LOGGER.info(this.getClass(), "STORE BUSINESS TIME RETRIVED SUCCESSFULLY");
				response.setStatus(HttpStatus.OK.toString());
				response.setMessage("Store Business Time listed Successfully");
				response.setError(HyperAppsConstants.RESPONSE_FALSE);
				response.setData(botList);
				apiResponse.setResponse(response);
				respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
			} else {
				LOGGER.error(this.getClass(), "STORE BUSINESS TIME RETRIEVAL FAILED");
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
				response.setMessage("Unable to List Store Business Time");
				response.setError(HyperAppsConstants.RESPONSE_TRUE);
				response.setData(null);
				apiResponse.setResponse(response);
				respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
			}

		}
		return respEntity;
	}

	public Object addStandardDeliverSettingsDetails(String token, int store_id, int delivery_type,
			double min_order_amount, double delivery_charge, double free_delivery_above, String delivery_areas,
			int home_delivery) {
		ResponseEntity<Object> respEntity = null;
		
		
		if ((respEntity = retailerValidationService.validateToken(token, respEntity)) == null) {
			if ((respEntity = retailerValidationService.validateStoreId(store_id, respEntity)) == null) {
				if (storeService.addUpdatedStandardDeliverSettingsDetails(store_id, delivery_type, min_order_amount,
						delivery_charge, free_delivery_above, delivery_areas, home_delivery, 0)) {
					LOGGER.info(this.getClass(), "DELIVERY TIME DETAILS ADDED SUCCESSFULLY");
					response.setStatus(HttpStatus.OK.toString());
					response.setMessage("Deliery Time Details Added Successfully");
					response.setError(HyperAppsConstants.RESPONSE_FALSE);
					response.setData(null);
					apiResponse.setResponse(response);
					respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
				} else {
					LOGGER.error(this.getClass(), "UNABLE TO ADD DELIVERY TIME DETAILS DETAILS");
					response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
					response.setMessage("Unable to Add Deliery Time Details");
					response.setError(HyperAppsConstants.RESPONSE_TRUE);
					response.setData(null);
					apiResponse.setResponse(response);
					respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
				}
			}
		}
		return respEntity;
	}

	public Object updateStandardDeliverSettingsDetails(String token, int store_id, int delivery_type,
			double min_order_amount, double delivery_charge, double free_delivery_above, String delivery_areas,
			int home_delivery) {
		ResponseEntity<Object> respEntity = null;
		if ((respEntity = retailerValidationService.validateToken(token, respEntity)) == null) {
			if ((respEntity = retailerValidationService.validateStoreId(store_id, respEntity)) == null) {
				if (storeService.addUpdatedStandardDeliverSettingsDetails(store_id, delivery_type, min_order_amount,
						delivery_charge, free_delivery_above, delivery_areas, home_delivery, 1)) {
					LOGGER.info(this.getClass(), "DELIVERY TIME DETAILS UPDATED SUCCESSFULLY");
					response.setStatus(HttpStatus.OK.toString());
					response.setMessage("Deliery Time Details Updated Successfully");
					response.setError(HyperAppsConstants.RESPONSE_FALSE);
					response.setData(null);
					apiResponse.setResponse(response);
					respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
				} else {
					LOGGER.error(this.getClass(), "UNABLE TO UPDATE DELIVERY TIME DETAILS DETAILS");
					response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
					response.setMessage("Unable to Update Deliery Time Details");
					response.setError(HyperAppsConstants.RESPONSE_TRUE);
					response.setData(null);
					apiResponse.setResponse(response);
					respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
				}
			}
		}
		return respEntity;
	}

	public Object getOngoingOffer(int store_id, int userId, String token) {
		ResponseEntity<Object> respEntity = null;
		if ((respEntity = retailerValidationService.validateToken(token, respEntity)) == null) {
			if ((respEntity = retailerValidationService.validateStoreId(store_id, respEntity)) == null) {
				List<OfferHistoryData> offerList = new ArrayList<OfferHistoryData>();
				offerList = storeService.getOnGoingOfferDetails(store_id);
				if (offerList.size() > 0) {
					LOGGER.info(this.getClass(), "ON GOING OFFERS LISTED SUCCESSFULLY");
					response.setStatus(HttpStatus.OK.toString());
					response.setMessage("On Going Offers Listed Successfully");
					HashMap<String, Object> data = new HashMap<String, Object>();
					data.put(ResponseKeys.data, offerList);
					response.setData(new JSONObject(data).toMap());
					response.setError(HyperAppsConstants.RESPONSE_FALSE);
					apiResponse.setResponse(response);
					respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
				} else {
					LOGGER.error(this.getClass(), "ON GOING OFFERS LIST NOT FOUND");
					response.setStatus(HttpStatus.NOT_FOUND.toString());
					response.setMessage("On Going Offers not found");
					response.setData(null);
					response.setError(HyperAppsConstants.RESPONSE_TRUE);
					apiResponse.setResponse(response);
					respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
				}
			}
		}
		return respEntity;
	}

	public Object getHistoryOffer(int store_id, String token) {
		ResponseEntity<Object> respEntity = null;
		if ((respEntity = retailerValidationService.validateToken(token, respEntity)) == null) {
			if ((respEntity = retailerValidationService.validateStoreId(store_id, respEntity)) == null) {
				List<OfferHistoryData> offerList = new ArrayList<OfferHistoryData>();
				offerList = storeService.getHistoryOffer(store_id);
				if (offerList.size() > 0) {
					LOGGER.info(this.getClass(), "OLD OFFERS LISTED SUCCESSFULLY");
					response.setStatus(HttpStatus.OK.toString());
					response.setMessage("Old Offers Listed Successfully");
					HashMap<String, Object> data = new HashMap<String, Object>();
					data.put(ResponseKeys.data, offerList);
					response.setData(new JSONObject(data).toMap());
					response.setError(HyperAppsConstants.RESPONSE_FALSE);
					apiResponse.setResponse(response);
					respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
				} else {
					LOGGER.error(this.getClass(), "OLD OFFERS LIST NOT FOUND");
					response.setStatus(HttpStatus.NOT_FOUND.toString());
					response.setMessage("Old Offers not found");
					response.setData(null);
					response.setError(HyperAppsConstants.RESPONSE_TRUE);
					apiResponse.setResponse(response);
					respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
				}
			}
		}
		return respEntity;
	}

	public Object addOffer(String token, int store_id, String offer_heading, String offer_description,
			String offer_valid, String active, String offer_percentage, int offer_type, String offer_start_date,
			String offer_flat_amount, String offer_percentage_max_amount, String offer_max_apply_count) {
		ResponseEntity<Object> respEntity = null;
		OfferHistoryData offer = new OfferHistoryData();
		offer.setStore_id(String.valueOf(store_id));
		offer.setOffer_heading(offer_heading);
		offer.setOffer_description(offer_description);
		offer.setOffer_valid(offer_valid);
		offer.setActive(active);
		offer.setOffer_percentage(offer_percentage);
		offer.setOffer_type(String.valueOf(offer_type));
		offer.setOffer_start_date(offer_start_date);
		offer.setOffer_flat_amount(offer_flat_amount);
		offer.setOffer_percentage_max_amount(offer_percentage_max_amount);
		offer.setOffer_max_apply_count(Integer.valueOf(offer_max_apply_count));
		if ((respEntity = retailerValidationService.validateStoreId(store_id, respEntity)) == null) {
			if ((respEntity = retailerValidationService.validateToken(token, respEntity)) == null) {
				if (storeService.addOffer(offer)) {
					LOGGER.info(this.getClass(), "OFFER ADDED SUCCESSFULLY");
					response.setStatus(HttpStatus.OK.toString());
					response.setMessage("Offer Added Successfully");
					response.setError(HyperAppsConstants.RESPONSE_FALSE);
					response.setData(null);
					apiResponse.setResponse(response);
					respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
				} else {
					LOGGER.error(this.getClass(), "UNABLE TO ADD OFFER DETAILS");
					response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
					response.setMessage("Unable to Add Offer Details");
					response.setError(HyperAppsConstants.RESPONSE_TRUE);
					response.setData(null);
					apiResponse.setResponse(response);
					respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
				}
			}
		}
		return respEntity;
	}

	public Object updateOffer(String token, int store_id, String offer_heading, String offer_description,
			String offer_valid, String active, int id, String offer_percentage, int offer_type, String offer_start_date,
			String offer_flat_amount, String offer_percentage_max_amount, String offer_max_apply_count) {
		ResponseEntity<Object> respEntity = null;
		OfferHistoryData offer = new OfferHistoryData();
		offer.setStore_id(String.valueOf(store_id));
		offer.setOffer_heading(offer_heading);
		offer.setOffer_description(offer_description);
		offer.setOffer_valid(offer_valid);
		offer.setActive(active);
		offer.setOffer_percentage(offer_percentage);
		offer.setOffer_type(String.valueOf(offer_type));
		offer.setOffer_start_date(offer_start_date);
		offer.setOffer_flat_amount(offer_flat_amount);
		offer.setOffer_percentage_max_amount(offer_percentage_max_amount);
		offer.setOffer_max_apply_count(Integer.valueOf(offer_max_apply_count));
		offer.setId(id);
		if ((respEntity = retailerValidationService.validateToken(token, respEntity)) == null) {
			if ((respEntity = retailerValidationService.validateStoreId(store_id, respEntity)) == null) {
				if (storeService.updateOffer(offer)) {
					LOGGER.info(this.getClass(), "OFFER UPDATED SUCCESSFULLY");
					response.setStatus(HttpStatus.OK.toString());
					response.setMessage("Offer Updated Successfully");
					response.setError(HyperAppsConstants.RESPONSE_FALSE);
					response.setData(null);
					apiResponse.setResponse(response);
					respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
				} else {
					LOGGER.error(this.getClass(), "UNABLE TO UPDATE OFFER DETAILS");
					response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
					response.setMessage("Unable to Update Offer Details");
					response.setError(HyperAppsConstants.RESPONSE_TRUE);
					response.setData(null);
					apiResponse.setResponse(response);
					respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
				}
			}
		}
		return respEntity;
	}

	public Object resumeOffer(String token, int store_id, String offer_valid, String offer_start_date, int id) {
		ResponseEntity<Object> respEntity = null;
		if ((respEntity = retailerValidationService.validateToken(token, respEntity)) == null) {
			if ((respEntity = retailerValidationService.validateStoreId(store_id, respEntity)) == null) {
				if (storeService.resumeOffer(offer_valid, offer_start_date, id)) {
					LOGGER.info(this.getClass(), "OFFER RESUMED SUCCESSFULLY");
					response.setStatus(HttpStatus.OK.toString());
					response.setMessage("Offer Resumed Successfully");
					response.setError(HyperAppsConstants.RESPONSE_FALSE);
					response.setData(null);
					apiResponse.setResponse(response);
					respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
				} else {
					LOGGER.error(this.getClass(), "UNABLE TO RESUME OFFER DETAILS");
					response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
					response.setMessage("Unable to Resume Offer Details");
					response.setError(HyperAppsConstants.RESPONSE_TRUE);
					response.setData(null);
					apiResponse.setResponse(response);
					respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
				}
			}
		}
		return respEntity;
	}

	public Object removeOffer(int id, String token) {
		ResponseEntity<Object> respEntity = null;
		if ((respEntity = retailerValidationService.validateToken(token, respEntity)) == null) {
			if (storeService.removeOffer(id)) {
				LOGGER.info(this.getClass(), "OFFER REMOVED SUCCESSFULLY");
				response.setStatus(HttpStatus.OK.toString());
				response.setMessage("Offer Removed Successfully");
				response.setError(HyperAppsConstants.RESPONSE_FALSE);
				response.setData(null);
				apiResponse.setResponse(response);
				respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
			} else {
				LOGGER.error(this.getClass(), "UNABLE TO REMOVE OFFER DETAILS");
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
				response.setMessage("Unable to Remove Offer Details");
				response.setError(HyperAppsConstants.RESPONSE_TRUE);
				response.setData(null);
				apiResponse.setResponse(response);
				respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
			}
		}
		return respEntity;
	}

	public Object rewardShow(int storeId, String token) {
		ResponseEntity<Object> respEntity = null;
		if ((respEntity = retailerValidationService.validateStoreId(storeId, respEntity)) == null) {
			if ((respEntity = retailerValidationService.validateToken(token, respEntity)) == null) {
					HashMap<String, Integer> reward = new HashMap<>();
					reward = storeService.rewardShow(storeId);
					if(reward.size()>0)
					{
						LOGGER.info(this.getClass(), "REWARD POINTS LISTED SUCCESSFULLY");
						response.setStatus(HttpStatus.OK.toString());
						response.setMessage("Reward Points Listed Successfully");
						response.setError(HyperAppsConstants.RESPONSE_FALSE);
						JSONObject jb = new JSONObject(reward);
						response.setData(new JSONArray().put(jb.toMap()).toList());
						apiResponse.setResponse(response);
						respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
					}
					else
					{
						LOGGER.info(this.getClass(), "NO REWARD POINTS FOUND");
						response.setStatus(HttpStatus.NOT_FOUND.toString());
						response.setMessage("No Reward Points Fount");
						response.setError(HyperAppsConstants.RESPONSE_TRUE);
						response.setData(null);
						apiResponse.setResponse(response);
						respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
					}
					
				}
		}
		return respEntity;
	}

	public Object updateRewardPoints(String token, int store_id, int reward_point) {
		ResponseEntity<Object> respEntity = null;
		if ((respEntity = retailerValidationService.validateStoreId(store_id, respEntity)) == null) {
		if ((respEntity = retailerValidationService.validateToken(token, respEntity)) == null) {
			if (storeService.updateRewardPoints(store_id,reward_point)) {
				LOGGER.info(this.getClass(), "REWARD POINTS UPDATED SUCCESSFULLY");
				response.setStatus(HttpStatus.OK.toString());
				response.setMessage("Reward Point Updated Successfully");
				response.setError(HyperAppsConstants.RESPONSE_FALSE);
				response.setData(null);
				apiResponse.setResponse(response);
				respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
			} else {
				LOGGER.error(this.getClass(), "UNABLE TO UPDATE REWARD POINTS");
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
				response.setMessage("Unable to Update Reward Points");
				response.setError(HyperAppsConstants.RESPONSE_TRUE);
				response.setData(null);
				apiResponse.setResponse(response);
				respEntity = new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
			}
		}
	}
		return respEntity;
	}

	public Object sendNotification(String notification_type, int store_id, String customer_ids, String email_subject,
			String email_content, String fcm_title, String fcm_body, String fcm_data) {
			String [] custIdArr = customer_ids.split(",");
			if(notification_type.equalsIgnoreCase(HyperAppsConstants.MAIL_NOTIFICATION))
			{
				for (String id : custIdArr) {
					String mailId = storeService.getMailId(id);
					if(mailId !=null)
					{
						emailService.sendEmail(mailId, email_subject, email_content);
					}
					
				}
				
				
			}
			else if (notification_type.equalsIgnoreCase(HyperAppsConstants.PUSH_NOTIFICATION))
			{
				
				ArrayList<String> tokenArray = new ArrayList<String>();
				for (String id : custIdArr) {
					tokenArray.add(storeService.getDeviceToken(id));
				}	
				LOGGER.info(getClass(), "SEND PUSH NOTIFICATION TOKEN ARRAY SIZE "+tokenArray.size());
				LOGGER.info(getClass(), "SEND PUSH NOTIFICATION TOKEN ARRAY "+tokenArray.toString());
				
				pushNotificationService.sendPushNotificationWithData(tokenArray, fcm_body, fcm_title);
				
				
			}
			response.setStatus(HttpStatus.OK.toString());
			response.setMessage("Notification Sent Successfully");
			response.setError(HyperAppsConstants.RESPONSE_FALSE);
			response.setData(null);
			apiResponse.setResponse(response);
			return new ResponseEntity<Object>(apiResponse, HttpStatus.OK);
	}

	

}
