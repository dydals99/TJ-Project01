package banking;
//쓰레드를 상속받은 autosaver 클래스
public class AutoSaver extends Thread {
	//기능을 가진 AccMgr타입의 멤버변수 선언
	AccountManager acm;
	//생성자를 통해 어카운트매니저 타입에 변수를 초기화
	public AutoSaver(AccountManager ac) {
		acm  = ac ;
	}
	//쓰레드에 실행부 start->run
	@Override
	public void run() {
		
		while(true) {
			try {
				System.out.println("=====5초마다 자동저장합니다=====");
				acm.saveThread();
				sleep(5000);
				System.out.println("=====Txt파일 생성됨=====");
			} 
			catch (Exception e) {
				//e.printStackTrace();
				System.out.println("=====자동저장 종료=====");
				break;
			}
		}
	}
}
