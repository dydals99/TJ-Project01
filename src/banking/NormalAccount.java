package banking;
//어카운트 부모클래스를 상속받은 노멀어카운트
public class NormalAccount extends Account {
	//멤버변수 선언 
	int inter ; //이자를 나타냄
	//생성자 상속받은 변수와 확장한 변수 초기화
	public NormalAccount(String account, String name, int balance, int inter) {
		
		super(account, name, balance);
		
		this.inter = inter ;
	}
	//어카운트 클래스에서 오버라이딩한 메서드
	@Override
	public void showAccInfo() {
		
		System.out.println("====계좌정보출력====");
		//부모쪽에 메서드를 호출할때는 super.를 사용해서 호출해준다.
		super.showAccInfo();
		System.out.println("기본이자>" + inter + "%");
	}
	//부모클래스에 setBalance를 오버라이딩을 통해 재정의한 기능을 가진다.
	@Override
	public void setBalance(int money , int temp) { 
		
		if(temp == 1) {
		int interNum = (super.getBalance() * this.inter/100) + money ;
			super.setBalance(interNum , 1);
		}
		else if(temp == 2) {
			
			super.setBalance(money , 2);
		}
	}
	//txt파일 출력시 주소값을 참조하여 toString메소드를 값을 넣어준다.
	@Override
	public String toString() {
		
		return " [계좌번호 : " + super.getAccount() + " 고객이름 : " + super.getName() +
			   " 잔고 : " + super.getBalance() + " 기본이자 : " + inter+ " ]" ;
	}
}
