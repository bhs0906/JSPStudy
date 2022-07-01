package com.saeyan.dao;
import com.saeyan.dto.membervo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class MemberDAO {
	private MemberDAO() {
		
	}
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws Exception{
		
		Connection conn = null;
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/oracle");
		conn = ds.getConnection();
		return conn;
	}
	
	// 사용자 인증시 사용하는 메소드
	public int userCheck(String userid, String pwd) {
		int result = -1;
		String sql = "SELECT PWD FROM MEMBER WHERE USERID=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("pwd")!=null && rs.getString("pwd").equals(pwd)){
					result = 1; //기본 회원임과 동시에, 입력된 pwd가 USERID에 대응되는 PWD와 일치
				} else {
					result = 0; //기본 회원이지만, 입력된 pwd가 USERID에 대응되는 PWD와 불일치
				}
			} else {
				result = -1; //기본 회원이 아님
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	//아이디로 회원 정보 가져오는 메소드
	   public membervo getMember(String userid) {
		   membervo mVo=null;
		      String sql="select * from member where userid=?";
		      Connection conn=null;
		      PreparedStatement pstmt=null;
		      ResultSet rs=null;
		      
		      try {
		         conn=getConnection();
		         pstmt=conn.prepareStatement(sql);
		         pstmt.setString(1, userid);
		         rs=pstmt.executeQuery();
		         
		         if(rs.next()) {
		            mVo = new membervo(); 
		            mVo.setName(rs.getString("name"));
		            mVo.setUserid(rs.getString("userid"));
		            mVo.setPwd(rs.getString("pwd"));
		            mVo.setEmail(rs.getString("email"));
		            mVo.setPhone(rs.getString("phone"));
		            mVo.setAdmin(rs.getInt("admin"));
		         }

		      }catch(Exception e) {
		         e.printStackTrace();
		      }finally {
		         try {
		            if(rs!=null) rs.close();
		            if(pstmt!=null) pstmt.close();
		            if(conn!=null) conn.close();
		         }catch(Exception e) {
		            e.printStackTrace();
		         }
		      }
		      return mVo;
		   }
	   
	   
	  public int confirmID(String userid) {
			int result = -1;
			String sql = "SELECT USERID FROM MEMBER WHERE USERID=?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userid);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					result = 1; //동일한 ID가 이미 존재합니다.
				} else {
					result = -1; //사용 가능한 ID입니다.
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}
	  public int insertMember(membervo mVo) {
			int result = -1;
			String sql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, ?)";
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, mVo.getName());
				pstmt.setString(2, mVo.getUserid());
				pstmt.setString(3, mVo.getPwd());
				pstmt.setString(4, mVo.getEmail());
				pstmt.setString(5, mVo.getPhone());
				pstmt.setInt(6, mVo.getAdmin());
				
				result = pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		public void updateMember(membervo mVo) {
			String sql = "UPDATE MEMBER SET PWD=?, EMAIL=?,"
					+ "PHONE=?, ADMIN=? WHERE USERID=?";
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, mVo.getPwd());
				pstmt.setString(2, mVo.getEmail());
				pstmt.setString(3, mVo.getPhone());
				pstmt.setInt(4, mVo.getAdmin());
				pstmt.setString(5, mVo.getUserid());
				
				pstmt.executeUpdate();
				System.out.println("result......");
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(conn != null) conn.close();
					if(pstmt != null) pstmt.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}