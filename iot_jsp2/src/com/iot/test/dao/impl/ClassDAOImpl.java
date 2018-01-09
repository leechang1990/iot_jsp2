package com.iot.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iot.test.common.DBCon;
import com.iot.test.dao.ClassDAO;
import com.iot.test.utils.DBUtil;
import com.iot.test.vo.ClassInfo;

public class ClassDAOImpl implements ClassDAO {

	@Override
	public List<ClassInfo> selectClassList() {
		List<ClassInfo> classList = new ArrayList<ClassInfo>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			con = DBCon.getCon();
			String sql = "select * from class_info";
			ps = con.prepareStatement(sql);
			rs= ps.executeQuery();
			while(rs.next()) {
				ClassInfo ci = new ClassInfo();
				ci.setCiDesc(rs.getString("cidesc"));
				ci.setCiName(rs.getString("ciname"));
				ci.setCiNo(rs.getInt("cino"));
				classList.add(ci);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return classList;
	}

	@Override
	public int deleteClass(ClassInfo ci) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			con = DBCon.getCon();
			String sql ="delete from class_info where cino=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, ci.getCiNo());
			return ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//DBUtil.closeAll(null, con, ps);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return 0;	
	}

	@Override
	public int updateClass(ClassInfo ci) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			con = DBCon.getCon();
			String sql ="update class_info " + 
					"set ciName=?, ciDesc=? where ciNo=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, ci.getCiName());
			ps.setString(2, ci.getCiDesc());
			ps.setInt(3, ci.getCiNo());
			return ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {

			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return 0;	
	}

}	
