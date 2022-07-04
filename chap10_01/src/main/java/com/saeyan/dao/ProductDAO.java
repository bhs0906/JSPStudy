package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.saeyan.dto.ProductVO;

import utill.DBManager;

public class ProductDAO {
//	싱글톤 객체 생성
	private ProductDAO() {
		
	}
	
	private static ProductDAO instance = new ProductDAO();
	
	public static ProductDAO getInstance() {
		return instance;
	}
	
//	c Read u d
	public List<ProductVO> selectAllProducts() {
//		최근 등록한 상품 먼저 출력하기
		String sql = "SELECT * FROM PRODUCT ORDER BY CODE DESC";
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {		// 이동은 행(로우) 단위로
				ProductVO pvo = new ProductVO();
				pvo.setCode(rs.getInt("code"));
				pvo.setName(rs.getString("name"));
				pvo.setPrice(rs.getInt("price"));
				pvo.setPictureUrl(rs.getString("pictureUrl"));
				pvo.setDescription(rs.getString("description"));
				list.add(pvo);
			} //while문 끝
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	} //selsectAllProducts(){
//ProductDAO{

//Create r u d
public int insertProduct(ProductVO pVo) {
	int result = 0;
	String sql = "insert into product values(product_seq.nextval, ?, ?, ?, ?)";
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	try {
		conn = DBManager.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, pVo.getName());
		pstmt.setInt(2, pVo.getPrice());
		pstmt.setString(3, pVo.getPictureUrl());
		pstmt.setString(4, pVo.getDescription());
		result = pstmt.executeUpdate();	//실행
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		DBManager.close(conn, pstmt);
	}
	return result;
	}

public ProductVO selectProductByCode(String code) {
	String sql = "SELECT * FROM PRODUCT WHERE CODE=?";
	ProductVO pvo = null;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		conn = DBManager.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, code);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			pvo = new ProductVO();
			pvo.setCode(rs.getInt("code"));
			pvo.setName(rs.getString("name"));
			pvo.setPrice(rs.getInt("price"));
			pvo.setPictureUrl(rs.getString("pictureUrl"));
			pvo.setDescription(rs.getString("description"));
		}
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		DBManager.close(conn, pstmt, rs);
	}
	return pvo;
	} 

//c r Update d
public int updateProduct(ProductVO pVo) {
	int result = 0;
	String sql = "UPDATE PRODUCT SET NAME=?, PRICE=?, PICTUREURL=?, DESCRIPTION=? WHERE CODE=?";
	Connection conn = null;
	PreparedStatement pstmt = null;
	try {
		conn = DBManager.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, pVo.getName());
		pstmt.setInt(2, pVo.getPrice());
		pstmt.setString(3, pVo.getPictureUrl());
		pstmt.setString(4, pVo.getDescription());
		pstmt.setInt(5, pVo.getCode());
		result = pstmt.executeUpdate();
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		DBManager.close(conn, pstmt);
	}
	return result;
	}

public void deleteProduct(String code) {
	String sql = "DELETE FROM PRODUCT WHERE CODE=?";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	try {
		conn = DBManager.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, code);
		pstmt.executeQuery();
		} catch(Exception e) {
		e.printStackTrace();
		} finally {
		DBManager.close(conn, pstmt);
		}
	} 
} 
