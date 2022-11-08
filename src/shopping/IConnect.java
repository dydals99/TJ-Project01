package shopping;

public interface IConnect {
	//멤버상수 : 오라클 드라이버명과 커넥션URL을 선언
	String ORCLE_DRIVER = "oracle.jdbc.OracleDriver";
	String ORCLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	//DB연결
	void connect(String user , String pass); 
	
	//쿼리문실행
	void execute();
	
	//자원반납
	void close(); 
	
	//사용자로부터 입력을 받기위해 정의
	String scanValue(String title);
	
	
}
