package banking;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;


public class BankingSystemMain extends Thread{
	// 메뉴출력
	static void showMenu() {
		System.out.println("=====================Menu=====================");
		System.out.print("[1.계좌계설] ");
		System.out.print("[2.입 금] ");
		System.out.print("[3.출 금] ");
		System.out.println("[4.계좌정보출력] ");
		System.out.print("[5.저장옵션] ");
		System.out.println("[6.프로그램종료] ");
//		System.out.print("6.퍼즐게임 ");
		System.out.println("==============================================");
	}
	public static void main(String[] args) {
		
		System.out.println("1차프로젝트(학원)");
		System.out.println("클론 후 작업01");
		
		AccountManager ac = new AccountManager();
		Scanner scan = new Scanner(System.in);
		//오토세이버 클래스의 객체 어카운트매니져 객체를 매개변수로 받는다.
		AutoSaver auto = new AutoSaver(ac);
		auto.setDaemon(true);
		ac.readAccount();
		while (true) {
			showMenu();
				
			int select = 0;
			try {
				select = scan.nextInt();
				if(select <1 || select > ICustomDefine.EXIT) {
				MenuSelectException ce = new MenuSelectException();
				throw ce;
				
				}
				if (select > 0 && select <= 7) {
				
				switch (select) {
				case ICustomDefine.MAKE:
					ac.makeAccount();
					break;
				case ICustomDefine.DEPOSIT:
					ac.depositMoney();
					break;
				case ICustomDefine.WITHDRAW:
					ac.withdrawMoney();
					break;
				case ICustomDefine.INQUIRE:
					ac.showAccInfo();
					break;
				case ICustomDefine.SAVE:
					//데몬쓰레드 메서드 매개변수를 오토세이버 객체로 받는다
					ac.deamonThread(auto);
					ac.saveThread();
					break;
				case ICustomDefine.EXIT:
					ac.saveAccount();
					System.out.println("===프로그램이 종료되었습니다===");
					return;
//				case ICustomDefine.GAME:
//					ac.puzzleGame();
//					break;
				}
			}
		} 
			catch(MenuSelectException e) {
				System.out.println(e.getMessage());
			}
			catch (Exception e) {
				scan.nextLine();
				System.out.println("숫자를입력하세요");
//				e.printStackTrace();
			}
		}
	}
}
