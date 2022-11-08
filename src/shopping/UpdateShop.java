package shopping;

import java.sql.SQLException;
import java.sql.Types;

public class UpdateShop extends IConnectlmpl{

	public UpdateShop() {
		super("education" , "1234");
	}
	@Override
	public void execute() {
		
//		String update = "UPDATE sh_goods "
//				+ " SET goods_name = ? , goods_price = ?  , p_code = ? "
//					+ " WHERE g_idx = ? ";
		try {
			
			csmt = con.prepareCall("{call ShopUpdateGoods(?,?,?,?,?)}");
			
			while(true) {
			
			csmt.setString(4, scanValue("수정할상품의 일련번호"));
			
			csmt.setString(1, scanValue("상품명"));
			csmt.setString(2, scanValue("가격"));
			csmt.setString(3, scanValue("코드"));
			
			csmt.registerOutParameter(5, Types.NUMERIC);
			
			csmt.execute();
			System.out.println("레코드수정결과() : ");
			System.out.println(csmt.getString(5));
			}
		}	
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		
	}	
	public static void main(String[] args) {
		new UpdateShop().execute();
	}
}