package com.iot.test.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.iot.test.dao.ClassDAO;
import com.iot.test.dao.impl.ClassDAOImpl;
import com.iot.test.service.ClassService;
import com.iot.test.vo.ClassInfo;
import com.iot.test.vo.UserClass;

public class ClassServiceImpl implements ClassService {
	private ClassDAO cdao = new ClassDAOImpl();
	private Gson gs = new Gson();

	@Override
	public List<ClassInfo> getClassList() {
		return cdao.selectClassList();
	}

	@Override
	public String deleteClass(HttpServletRequest req) {
		int ciNo=Integer.parseInt(req.getParameter("ciNo"));
		ClassInfo ci=new ClassInfo();
		ci.setCiNo(ciNo);
		int result = cdao.deleteClass(ci);
		Map<String,String> rm = new HashMap<String,String>();
		rm.put("result", "no");
		rm.put("msg", "삭제가 실패했어 ");
		if(result==1) {
			rm.put("result", "ok");
			rm.put("msg", "삭제가 성공했어");
		}
		return gs.toJson(rm);
	}

	@Override
	public String updateClass(HttpServletRequest req) {
		String param =req.getParameter("param");
		ClassInfo ci=gs.fromJson(param, ClassInfo.class);
		int result =cdao.updateClass(ci);
		Map<String,String> rm = new HashMap<String,String>();
		rm.put("result", "no");
		rm.put("msg", "수정이 실패했어 ");
		if(result==1) {
			rm.put("result", "ok");
			rm.put("msg", "수정이 성공했어");
		}
		return gs.toJson(rm);
	}
}
