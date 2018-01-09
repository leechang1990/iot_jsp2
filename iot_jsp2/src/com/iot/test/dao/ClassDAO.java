package com.iot.test.dao;

import java.util.List;

import com.iot.test.vo.ClassInfo;
import com.iot.test.vo.UserClass;

public interface ClassDAO {
	List<ClassInfo> selectClassList();
	int deleteClass(ClassInfo ci);
	int updateClass(ClassInfo ci);
}
