package shopping;

import java.sql.SQLException;

public class SelectShop extends IConnectlmpl{

	public SelectShop() {
		
		super("education" , "1234");
	}
	@Override
	public void execute() {
		
		
		try {
			while(true) {
			
			String select = " SELECT g_idx, goods_name, to_char(goods_price ,'999,000'),p_code,to_char(sysdate,'yyyy-mm-dd hh:mi') FROM sh_goods "
					+ " WHERE goods_name LIKE '%'||?||'%' ";
			
			psmt = con.prepareStatement(select);
			psmt.setString(1, scanValue("찾는이름"));
			rs = psmt.executeQuery();
			
				while(rs.next()) {
				String idx = rs.getString(1);
				String iname = rs.getString(2);
				String iprice = rs.getString(3);
				String icode = rs.getString(4);
				
				String regidate = rs.getString(5);
				
				System.out.printf("%s %s %s %s %s\n",idx,iname,iprice,icode,regidate);
				}
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
		new SelectShop().execute();
	}
}
