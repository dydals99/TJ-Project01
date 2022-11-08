package banking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

class AccountManager {
	//hastset컬렉션을 사용하여 Account타입의 acc객체를 선언하여 저장
	HashSet<Account> acc = new HashSet<Account>();
	//Account [] accArr = new Account[50];//50 칸짜리 Account 타입의 배열
	// 계좌개설을 위한 함수
	void makeAccount() {
		Scanner scan = new Scanner(System.in);
		int inter; //정수형 입력값을 받을 변수
		
		System.out.println("1.보통 계좌");
		System.out.println("2.신용 신뢰계좌");
		int choice = scan.nextInt();
		scan.nextLine(); // 엔터키 제거용
		
		System.out.print("계좌번호입력 : ");
		String accountNum = scan.nextLine();
		
		System.out.print("이름입력 : ");
		String name = scan.nextLine();
		
		System.out.print("잔고 : ");
		int balance = Integer.parseInt(scan.nextLine());
		
		System.out.print("기본이자(정수입력) : ");
		inter = scan.nextInt();
		scan.nextLine();
		Account acc = null;
		try {
			if (choice == 1) {
				
			acc = new NormalAccount(accountNum, name, balance, inter);
				// 기존에 컬렉션에 저장되어있던값을 조건으로 true라면 입력한값을 저장
			}
			// 신용계좌 정보 입력
			else if (choice == 2) {
				String credit;
				System.out.printf("등급선택 : ");
				credit = scan.nextLine();
					if (!("A".equalsIgnoreCase(credit) || "B".equalsIgnoreCase(credit) || "C".equalsIgnoreCase(credit))) {
						MenuSelectException ex = new MenuSelectException();
						throw ex;
					}
				acc = new HighCreditAccount(accountNum, name, balance, inter, credit);
			}
			// 기존에 컬렉션에 저장되어있던값을 조건으로 true라면 입력한값을 저장
			boolean isDup = this.acc.add(acc);
			if (isDup == false) {
				System.out.println("==계좌 중북이 발생되었습니다==");
				System.out.println("[Y]덮어쓰기,[N]취소하기");
				String overlap = scan.nextLine();
				// equalsIgnoreCase : 사용자가 입력할값이 소문자일지 대문자일지 모르기에 사용
				// 덮어쓰기를 원한다면 Y
				if ("Y".equalsIgnoreCase(overlap)) {
					// 기존에 입력된(normal)을 삭제하고
					this.acc.remove(acc);
					// 다시 입력된 정보를 저장(add)
					this.acc.add(acc);
					System.out.println("===완료되었습니다===");
					return;
				}
				// N을 누를시 초기화면으로 return
				else if ("N".equalsIgnoreCase(overlap)) {
					System.out.println("===메뉴로 돌아갑니다===");
					return;
				}
			}
		System.out.println("==계좌계설이 완료되었습니다==");
		} 
		catch (MenuSelectException e) {
			System.out.println(e.getMessage());
		}
	}
	// 입 금
	void depositMoney() {
		Scanner scan = new Scanner(System.in);
		//boolean타입에 변수를 false로 선언
		boolean accFind = false;
		
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호 : ");
		String search = scan.nextLine();
		try {
//			컬렉션에 크기만큼 Account 타입의 객체 am에 대입
			for(Account am : acc )	{
				if (search.equals(am.getAccount())) {
					System.out.print("입금액 : ");
					int depo = Integer.parseInt(scan.nextLine());
					//0보다 크거나 (입력값)%500에 나머지가 0인 입력값을 true로반환하는 조건
					if (!(depo < 0 || (depo % 500) != 0)) { 
						
						am.setBalance(depo, 1);
						accFind = true;
						System.out.println("==입금이 완료되었습니다==");
					}
					//입금시 아래조건을통해 위에조건에 틀린값을 입력할시 문장이 실행
					if(accFind == false) {
						System.out.println("정확값을 입력해주세요");
						//for문탈출
						return;
					}
				}
			}
			//계좌 입력시에 아래와같은 조건을 통해 없는계좌를 입력할시 문장실행
			if (accFind == false) {
				System.out.println("==없는 계좌입니다==");
			}
		} 
		//위 조건에 포함되지 않는 숫자를 입력하거나 할시 catch문으로 던져
		catch (NumberFormatException e) {
			System.out.println("==잘못입력하셨습니다 메뉴로 돌아갑니다==");
			//e.printStackTrace();
		}
	}
	// 출 금
	void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		boolean accFind = false;
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호 : ");
		String search = scan.nextLine();
		int total = 0;
		try {
//			for (int i = 0; i < index; i++) {
			for(Account ac : acc) {	
				if (search.equals(ac.getAccount())) {
					
					System.out.print("출금액 : ");
					int with = Integer.parseInt(scan.nextLine());
					total = ac.getBalance();
						if(!(with > 0 && (with % 1000)==0)) {
							
							System.out.println("정확값을 입력해주세요");
							return;
						}
						if(total<with) {
							System.out.println("==출금하려는 금액이 잔금보다큽니다==");
							System.out.println("===전액 출금하시겠습니다까 Y,N===");
							String YN = scan.nextLine();
							if("Y".equalsIgnoreCase(YN)) {
								System.out.println("전액출금하겠습니다.");
							}
							else {
								System.out.println("메뉴로 돌아갑니다.");
								return;
							}
						}
						ac.setBalance(with,2);
					accFind = true;				
					System.out.println("출금이 완료되었습니다.");
						
					if(accFind == false) {
						System.out.println("잘못된입력입니다.");
						return;
					}
				}
			}
			if (accFind == false) {
				System.out.println("==없는 계좌입니다==");
			}
		} 
		catch (NumberFormatException e) {
			System.out.println("==잘못입력하셨습니다 초기화면으로 돌아갑니다==");
			//e.printStackTrace();
		}
	}
	// 전체계좌정보출력
	void showAccInfo() {
		for(Account ac : acc) {
			ac.showAccInfo();
		}
	}
	public void saveAccount() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("src/banking/AccountInfo.obj"));	
			for(Account ac : acc) {
				
				out.writeObject(ac);
			}
			System.out.println("===계좌정보가 저장되었습니다===");
		}
		catch (Exception e) {
			System.out.println("정보저장중 예외발생");
		}
	}
	public void readAccount() {
		try {
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream("src/banking/AccountInfo.obj"));
			while(true) {
				
				Account ac = (Account) in.readObject();
				acc.add(ac);
			}
		} 
		catch (Exception e) {
		}
		System.out.println("복원완료");
	}
	//메인에 호출한 메서드와 마찬가지로 오토세이버 타입의 매개변수를 받는다
	//오토세이버 클래스에 어카운트매니져 객체와 연결
	public void deamonThread(AutoSaver auto) {
		Scanner sc = new Scanner(System.in);
		System.out.println("==1.자동저장On 2.자동저장Off==");
		int sel = sc.nextInt();
		try {
			if(sel == 1) {
				if(auto.isAlive()) {
					System.out.println("=====실행중입니다=====");
				}
				else {
					auto.start();
				}
			}
			else if(sel == 2) {
				auto.interrupt();
			}
		} 
		catch (Exception e) {
			//e.printStackTrace();
			System.out.println("자동저장시 오류발생1");
		}
	}
	public void saveThread() {
		try {
			PrintWriter out = new PrintWriter(
					new FileWriter("src/banking/AutoSaveAccount.txt"));
			for(Account ac1 :acc) {
				out.println(ac1.toString());
			}
			out.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}