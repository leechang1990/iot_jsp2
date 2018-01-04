package com.iot.test.service.impl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.iot.test.dao.UserDAO;
import com.iot.test.dao.impl.UserDAOImpl;
import com.iot.test.service.UserService;
import com.iot.test.vo.UserClass;

public class UserServiceImpl implements UserService{
	private Gson gs = new Gson();
	private UserDAO ud = new UserDAOImpl();

	@Override
	public HashMap<String,Object> login(HttpServletRequest req) {
		
		UserClass uc = gs.fromJson(req.getParameter("param"), UserClass.class);
		HashMap<String,Object> hm = new HashMap<String,Object>();
		hm.put("msg", "�� �α��� �����ϼ̳׿� ~");
		hm.put("login", "ok");
		
		UserClass checkUc=ud.selectUser(uc.getUiId());
		if(checkUc!=null) {
			if(!checkUc.getUiPwd().equals(uc.getUiPwd())) {
				hm.put("msg", "��й�ȣ Ȯ���ϼ���");
				hm.put("login", "no");
			}else {
				HttpSession hs=req.getSession();
				hs.setAttribute("user", uc);
				
			}
		}else {
			 hm.put("msg", "���̵� Ȯ���ϼ���");
			 hm.put("login", "no");
		}
		return hm;
	}

	@Override
	public void logout(HttpServletRequest req) {
		HttpSession hs = req.getSession();
		hs.invalidate();//���ǰ��� ������ �Ҹ��Ŵ 
	}
	
}
