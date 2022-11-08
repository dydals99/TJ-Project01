package shopping;

import java.sql.SQLException;

/*
reparedStatement객체를 사용하여 작성한다.
클래스명 : InsertShop
상품명, 상품가격, 상품코드를 scanValue() 메소드로 입력받아 사용한다. 
입력이 완료되면 입력된 행의 갯수를 반환하여 출력한다. 
 */
public class InsertShop extends IConnectlmpl{
	public InsertShop() {
		
		super(ORCLE_DRIVER , "education" ,"1234");
	}
	@Override
	public void execute() {
//		int cnt = 0 ;
		String query = "insert into sh_goods "
				+  "( g_idx ,goods_name ,goods_price ,p_code)values(seq_total_idx.nextVal,?,?,? ) " ;
		
		try {
			psmt = con.prepareStatement(query);
			
			while(true) {
				
				psmt.setString(1, scanValue("상품명"));
				psmt.setString(2, scanValue("상품가격"));
				psmt.setString(3, scanValue("상품코드"));
				int affected = psmt.executeUpdate();
				
//				cnt++;
				System.out.println(affected + "행이 입력되었습니다");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	public static void main(String[] args) {
		
		new InsertShop().execute();
	}
}