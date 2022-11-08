package banking;

import java.io.Serializable;
import java.util.Objects;

public abstract class Account  implements Serializable{
	//멤버변수
	private String account;
	private String name;
	private int balance;
	//생성자
	public Account(String account,String name,int balance) {
		this.account = account;
		this.name = name;
		this.balance = balance;
	}
	//getter생성자
	public int getBalance() {
		return balance;
	}
	public String getAccount() {
		return account;
	}
	public String getName() {
		return name;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBalance(int money ,int temp) {
		
		if(temp == 1) {
			this.balance += money;
		}
		else if(temp == 2){
			
			if(this.balance-money<0) {
				this.balance =0 ;
			}
			else {
				this.balance -= money;
			}
		}
	}
	@Override
	public int hashCode() {
		
		int reAccount = Objects.hash(this.account);
		
		return reAccount;
	}
	@Override
	public boolean equals(Object obj) {
		Account acc = (Account)obj;
		
		if(acc.account.equals(account)) {
			
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public String toString() {
		
		return "계좌번호 : " + account + " 고객이름 : " + name + " 잔고 : " + balance  ;
	}
	//멤버메서드
	public void showAccInfo() {
	System.out.println("계좌번호 : " + account);
	System.out.println("고객이름 : " + name);
	System.out.println("잔고 : " + balance);
	//
	}
}
