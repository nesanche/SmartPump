package com.smartpump.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smartpump.transaction.TransactionBo;

@Component
@Path("/payment")
public class PaymentService {
 
	@Autowired
	TransactionBo transactionBo;
 
	@GET
	@Path("/smartpump")
	public Response savePayment() {
 
		String result = transactionBo.save();
 
		return Response.status(200).entity(result).build();
 
	}
 
}
