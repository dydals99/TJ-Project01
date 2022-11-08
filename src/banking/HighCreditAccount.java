package banking;
//어카운트 부모클래스를 상속받은 하이크레딧어카운트
public class HighCreditAccount extends Account {
	//멤버변수 이자,등급을 나타냄
	int inter;
	String credit;
	//생성자 상속받은 변수와 확장한 변수 초기화
	public HighCreditAccount(String account, String name,
			
		int balance, int inter, String credit) {
		super(account, name, balance);
		
		this.inter = inter;
		this.credit = credit;
	}
	//어카운트 클래스에서 오버라이딩한 메서드
	@Override
	public void showAccInfo() {
		
		System.out.println("====계좌정보출력====");
	
		super.showAccInfo();
		System.out.println("기본이자 : " + inter + "%");
		System.out.println("신용등급 A,B,C :" + credit);
	}
	//부모클래스에 setBalance를 오버라이딩을 통해 재정의한 기능을 가진다.
	@Override
	public void setBalance(int money ,int temp) {
		
		if(temp == 1) {
			//인터페이스 선언한 ICustomDefine.A,B,C를 이용해서 등급별 이자계산을 한다.
			if("A".equalsIgnoreCase(credit)) {
				int interNum = (super.getBalance() * this.inter/100) + (super.getBalance() * ICustomDefine.A/100) + money ;
				super.setBalance(interNum , 1);
			}
			else if("B".equalsIgnoreCase(credit)) {
				int interNum = (super.getBalance() * this.inter/100) + (super.getBalance() * ICustomDefine.B/100) + money ;
				super.setBalance(interNum , 1);
			}
			else if("C".equalsIgnoreCase(credit)) {
				int interNum = (super.getBalance() * this.inter/100) + (super.getBalance() * ICustomDefine.C/100) + money ;
				super.setBalance(interNum, 1);
			}
		}
		else if(temp == 2) {
			System.out.println("출금실행");
			super.setBalance(money, 2);
		}
		else {
			super.setBalance(money, temp);
		}
	}
	//txt파일 출력시 주소값을 참조하여 toString메소드를 값을 넣어준다.
	@Override
	public String toString() {
		
		return " [계좌번호 : " + super.getAccount() + " 고객이름 : " + super.getName() + 
			   " 잔고 : " + super.getBalance() + "기본이자 : " + inter + " 등급 : " + credit + " ]";
	}
}
