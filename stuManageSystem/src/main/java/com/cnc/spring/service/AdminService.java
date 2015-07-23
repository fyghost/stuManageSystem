package com.cnc.spring.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cnc.spring.DAO.AdminDAO;
import com.cnc.spring.model.Admin;

/*
 * 每种用户里面单独定义自己的增删改查（CRUD）
 */
@Component
public class AdminService {
	@Resource
	private AdminDAO adminDAO;
	
	@Transactional
	public Admin getAdmin(String admin_id) {
		return adminDAO.getAdmin(admin_id);
	}
	
	@Transactional
	public void updateAdmin(String username, String password) {
		adminDAO.updateAdmin(username, password);
	}
	
	@Transactional
	public void deleteAdmin(String username) {
		adminDAO.deleteAdmin(username);
	}
	
	@Transactional
	public void addAdmin(Admin admin) {
		adminDAO.addAdmin(admin);
	}
}
