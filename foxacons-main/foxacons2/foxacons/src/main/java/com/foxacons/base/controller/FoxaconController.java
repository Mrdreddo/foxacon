package com.foxacons.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.foxacons.base.entity.ContactDetails;
import com.foxacons.base.entity.ProjectDetails;
import com.foxacons.base.jwt.JwtUtil;
import com.foxacons.base.model.Request;
import com.foxacons.base.model.Response;
import com.foxacons.base.service.AdminImpl;
import com.foxacons.base.service.ContactDetailsService;
import com.foxacons.base.service.ProjectDetailsService;

@RestController
//@CrossOrigin(allowedHeaders = "*", origins = "*")   
@CrossOrigin("*")
public class FoxaconController {

//	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AdminImpl adminImpl;

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private ProjectDetailsService detailsService;

	@Autowired
	private ContactDetailsService contactDetailsService;

	// Admin .......................

	@PostMapping("/authentication")
	public Response authentication(@RequestBody Request request) throws Exception {
		Response response = new Response();

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getAdminEmail(), request.getAdminPassword()));
		} catch (AuthenticationException e) {
			throw new Exception("invaild id and password", e);
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getAdminEmail());
		String jwt = jwtUtil.generateToken(userDetails);
		response.setStatuscode(200);
		response.setMsg("admin logged");
		response.setToken(jwt);

		return response;
	}

	// content Controller...........
	@PostMapping("/addProject")
	public Response addProject(@RequestBody ProjectDetails details) {
		Response response = new Response();

		boolean addedProjectResponse = detailsService.addProject(details);
		if (addedProjectResponse == true) {
			response.setStatuscode(200);
			response.setMsg("Project added succesfully!!");
			return response;
		} else {
			response.setStatuscode(400);
			response.setMsg("Something went wrong");
			return response;
		}
	}

	@GetMapping("/getAllProjects")
	public Response getAllProjects() {

		Response response = new Response();
		List<ProjectDetails> details = detailsService.getAllProjects();

		if (details != null) {
			response.setStatuscode(200);
			response.setMsg("All projects");
			response.setDetails(details);

		} else {
			response.setStatuscode(400);
			response.setMsg("Something went wrong");
		}
		return response;
	}

	@DeleteMapping("/deleteProject/{projectId}")
	public Response deleteProject(@PathVariable int projectId) {
		Response response = new Response();

		if (detailsService.deleteProject(projectId)) {
			response.setStatuscode(200);
			response.setMsg("Project deleted succesfully!!");
		} else {
			response.setStatuscode(400);
			response.setMsg("Somethin went wrong!!");
		}
		return response;
	}

	@PostMapping("/addContact")
	public Response addContactDetails(@RequestBody ContactDetails contactDetails) {
		Response response = new Response();

		if (contactDetailsService.addContactDetails(contactDetails)) {
			response.setStatuscode(200);
			response.setMsg("Contact Added succefully!!!!");
		} else {
			response.setStatuscode(400);
			response.setMsg("Somethin went wrong");
		}
		return response;
	}

	@GetMapping("/getAllContactDetails")
	public Response getAllContactDetails() {
		Response response = new Response();
		List<ContactDetails> contactDetails = contactDetailsService.getAllContactDetails();
		if (contactDetails.size() >= 0) {
			response.setStatuscode(200);
			response.setMsg("All contact details");
			response.setContactDetails(contactDetails);
		} else {
			response.setStatuscode(400);
			response.setMsg("No records found");
		}
		return response;
	}

	@DeleteMapping("/deleteContactDetails/{id}")
	public Response deleteContactDetails(@PathVariable int id) {
		Response response = new Response();

		if (contactDetailsService.deleteContactDetail(id)) {
			response.setStatuscode(200);
			response.setMsg("Project deleted succesfully!!");
		} else {
			response.setStatuscode(400);
			response.setMsg("Somethin went wrong");
		}
		return response;
	}

}
