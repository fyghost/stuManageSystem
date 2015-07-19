package com.cnc.spring.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cnc.spring.DAO.AdminDAO;
import com.cnc.spring.model.Admin;

@Component
public class AdminService {
	@Resource
	private AdminDAO adminDAO;
	
	public Admin getAdmin(String admin_id) {
		return adminDAO.getAdmin(admin_id);
	}
	
	public void updateAdmin(String username, String password) {
		adminDAO.updateAdmin(username, password);
	}
	
	public void deleteAdmin(String username) {
		adminDAO.deleteAdmin(username);
	}
	
}
