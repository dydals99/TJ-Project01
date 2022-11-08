package shopping;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class IConnectlmpl implements IConnect{
	
	public Connection con;
	
	public Statement stmt;
	
	public PreparedStatement psmt;
	
	public CallableStatement csmt;
	
	public ResultSet rs;
	
	//기본 생성자
	public IConnectlmpl() {
		System.out.println("기본생성자 호출");
	}
	//인자 생성자1
	public IConnectlmpl(String user , String pass) {
		System.out.println("인자생성자 호출");
		
		try {
			Class.forName(ORCLE_DRIVER);
			
			connect(user, pass);
		} 
		catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
//			e.printStackTrace();
		}
	}
	public IConnectlmpl(String driver , String user , String pass) {
		System.out.println("인자생성자 호출 2");
		
		try {
			Class.forName(driver);
			
			connect(user, pass);
		} 
		catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
//			e.printStackTrace();
		}
	}
	@Override
	public void connect(String user, String pass) {
		
		try {
			con = DriverManager.getConnection(ORCLE_URL,user,pass);
			
		} 
		catch (SQLException e) {
			System.out.println("DB연결오류");
//			e.printStackTrace();
		}
	}

	@Override
	public void execute() {
		
	}

	@Override
	public void close() {
		
		try {
			if(con!=null) con.close();
			if(psmt!=null) psmt.close();
			if(rs!=null) rs.close();
			
			if(stmt!=null) stmt.close();
			if(csmt!=null) csmt.close();
			
		} catch (Exception e) {
			System.out.println("자원반납 오류");
//			e.printStackTrace();
		}
		
	}

	@Override
	public String scanValue(String title) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println(title + "을(를)입력(exit->종료) :");
		String inputStr = scan.nextLine();
		
		if("EXIT".equalsIgnoreCase(inputStr)) {
			
			System.out.println("==프로그램종료==");
			close();
			System.exit(0);
		}
		return inputStr;
	}
}
