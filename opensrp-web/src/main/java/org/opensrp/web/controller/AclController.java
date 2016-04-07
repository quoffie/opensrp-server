package org.opensrp.web.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.awt.peer.LightweightPeer;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONException;
import org.opensrp.connector.openmrs.service.OpenmrsUserService;
import org.opensrp.dto.AclDTO;
import org.opensrp.dto.PrivilegeDTO;
import org.opensrp.dto.RoleDTO;
import org.opensrp.dto.UserDTO;
import org.opensrp.register.mcare.domain.Privilege;
import org.opensrp.register.mcare.service.AclService;
import org.opensrp.register.mcare.service.PrivilegeService;
import org.opensrp.register.mcare.service.RoleService;
import org.opensrp.register.mcare.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class AclController {

	private RoleService roleService;
	private AclService aclService;	
	private OpenmrsUserService openmrsUserService;
	private PrivilegeService privilegeService;
	private UsersService userService;
	private static Logger logger = LoggerFactory.getLogger(AclController.class);

	@Autowired
	public AclController(RoleService roleService, AclService aclService,
			OpenmrsUserService openmrsUserService, PrivilegeService privilegeService,
			UsersService userService) {
		this.roleService = roleService;
		this.aclService = aclService;
		this.openmrsUserService = openmrsUserService;		
		this.privilegeService = privilegeService;
		this.userService = userService;
	}

	/*@RequestMapping(headers = { "Accept=application/json" }, method = POST, value = "/add-user")
	public ResponseEntity<String> addRole(@RequestBody RoleDTO roleDTO) {
		String message = roleService.addRole(roleDTO);
		return new ResponseEntity<>(message,OK);
	}*/
	
	/*@RequestMapping(headers = { "Accept=application/json" }, method = POST, value = "/edit-user")
	public ResponseEntity<String> editRole(@RequestBody RoleDTO roleDTO) {
		String message = roleService.editRole(roleDTO);
		return new ResponseEntity<>(message,OK);
	}*/

	@RequestMapping(headers = { "Accept=application/json" }, method = POST, value = "/add-acl")
	public ResponseEntity<String> addAcl(@RequestBody AclDTO aclDTO) {
		String message = aclService.addAcl(aclDTO);		
		return new ResponseEntity<>(message,OK);
	}
	@RequestMapping(headers = { "Accept=application/json" }, method = POST, value = "/edit-acl")
	public ResponseEntity<String> editAcl(@RequestBody AclDTO aclDTO) {
		String message = aclService.editAcl(aclDTO);		
		return new ResponseEntity<>(message,OK);
	}

	@RequestMapping(method = GET, value = "/all-user-name")
	@ResponseBody
	public ResponseEntity<String> getAllUserName() throws JSONException {
		return new ResponseEntity<>(new Gson().toJson(openmrsUserService
				.getAllUsers()), OK);
	}

	@RequestMapping(method = GET, value = "/role-access-tokens")
	@ResponseBody
	public AclDTO getRoleAndAccessTokens(@RequestParam String userName) {
		return aclService.getRoleAndAccessTokens(userName);
	}
	
	@RequestMapping(method = GET, value = "/role-access-tokens-by-name")
	@ResponseBody
	public AclDTO getRoleAndAccessTokensByRoleName(@RequestParam String roleName) {
		return aclService.getRoleAndAccessTokensByName(roleName);
	}
	
	@RequestMapping(method = GET, value = "/all-roles-access-tokens")
	@ResponseBody
	public ArrayList<AclDTO> getRolesAndAccessTokens() {
		return (ArrayList<AclDTO>) aclService.getRolesAndAccessTokens();
	}
	
	@RequestMapping(method = GET, value = "/all-active-roles-access-tokens")
	@ResponseBody
	public ArrayList<AclDTO> getActiveRolesAndAccessTokens() {
		return (ArrayList<AclDTO>) aclService.getActiveRolesAndAccessTokens();
	}
	@RequestMapping(method = GET, value = "/all-roles-with-user")
	@ResponseBody
	public ArrayList<RoleDTO> getRolesAndUser() {
		return (ArrayList<RoleDTO>) roleService.getRolesAndUser();
	}
	
	@RequestMapping(headers = { "Accept=application/json" }, method = POST, value = "/add-privilege")
	public ResponseEntity<String> addPrivilege(@RequestBody PrivilegeDTO privilegeDTO) {
		logger.info("request reached inside /add-privilege");
		logger.info(privilegeDTO.getName());
		String message = privilegeService.addPrivilege(privilegeDTO);		
		//return new ResponseEntity<>(message,OK);
		return new ResponseEntity<>("1",OK);
		//return "1";
	}
	
	@RequestMapping(method = GET, value = "/privilege-by-name")
	@ResponseBody
	public PrivilegeDTO getPrivilegeByName(@RequestParam String privilegeName) {
		logger.info("requeset reached with - " + privilegeName );
		return privilegeService.getPrivilegeByName(privilegeName);
	}
	
	@RequestMapping(method = GET, value = "/all-privileges")
	@ResponseBody
	public ArrayList<PrivilegeDTO> getPrivileges() {
		logger.info("getting all privileges " );
		return privilegeService.getAllPrivileges();
	}
	
	@RequestMapping(headers = { "Accept=application/json" }, method = POST, value = "/edit-privilege")
	public ResponseEntity<String> editPrivilege(@RequestBody PrivilegeDTO privilegeDTO) {
		logger.info("received status - " + privilegeDTO.getStatus());
		String message = privilegeService.editPrivilege(privilegeDTO);		
		return new ResponseEntity<>(message,OK);
	}
	
	@RequestMapping(headers = { "Accept=application/json" }, method = POST, value = "/add-role")
	public ResponseEntity<String> addRole(@RequestBody RoleDTO roleDTO) {
		logger.info("create request received for role - " + roleDTO.getName());
		/*List<PrivilegeDTO> privileges = roleDTO.getPrivileges();
		for(int i = 0 ; i < privileges.size(); i++){
			logger.info("Privilege name - " + privileges.get(i).getName() + " - id - " + privileges.get(i).getId());
		}*/
		String message = roleService.addRole(roleDTO);//privilegeService.addPrivilege(privilegeDTO);		
		return new ResponseEntity<>(message,OK);
		//return new ResponseEntity<>("1",OK);
		//return "1";
	}
	
	@RequestMapping(headers = { "Accept=application/json" }, method = POST, value = "/edit-role")
	public ResponseEntity<String> editRole(@RequestBody RoleDTO roleDTO) {
		logger.info("create request received for role - " + roleDTO.getName());
		String message = roleService.editRole(roleDTO);//privilegeService.addPrivilege(privilegeDTO);		
		return new ResponseEntity<>(message,OK);
		//return new ResponseEntity<>("1",OK);
		//return "1";
	}
	
	@RequestMapping(method = GET, value = "role-by-name")
	@ResponseBody
	public RoleDTO getRoleByName(@RequestParam String roleName) {
		logger.info("requeset reached with - " + roleName );
		return roleService.getRoleByName(roleName);
	}
	
	@RequestMapping(headers = { "Accept=application/json" }, method = POST, value = "/add-user")
	public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
		logger.info("create request received for role - " + userDTO.getName());
		
		String message = userService.addUser(userDTO);		
		return new ResponseEntity<>(message,OK);
	}
}
