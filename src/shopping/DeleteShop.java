package shopping;

import java.sql.SQLException;
import java.sql.Types;

public class DeleteShop extends IConnectlmpl{

	public DeleteShop() {
		super("education" , "1234");
	}
	@Override
	public void execute() {
		
		try {
			csmt = con.prepareCall("{call ShopDeleteGoods(?,?)}");
			
			//인파라미터
			csmt.setString(1, scanValue("삭제할상품의 일련번호"));
			
			//아웃파라미터
			csmt.registerOutParameter(2, Types.NUMERIC);
			
			csmt.execute();
			//1이나오면 삭제 0이나오면 삭제할 레코드 X
			System.out.print("레코드삭제결과() : ");
			System.out.println(csmt.getString(2));
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		
	}
	public static void main(String[] args) {
		new DeleteShop().execute();
	}
	
}
