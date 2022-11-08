package banking;

import java.util.Scanner;

public class Game {
//	static Game[][] game = new Game[3][3]; 
//	static int X ;
//	static int Y;
//	//생성자 
//	Game(int X , int Y ) {
//		this.X = X;
//		this.Y = Y; 
//	}
//	static void showgame() {
//
//	}
	public static void main(String[] args) {
//		ArrayList<Integer> list2 = new ArrayList<Integer>();
		
//		showgame();
		
		
		String arr[][] = new String [3][3];
		
		for(int i = 0 ; i < arr.length ; i++) {
			for(int j = 1 ; j <= arr.length ; j++) {
				
				arr[i][j-1] = Integer.toString(j + (i*3));
			}
		}
		arr[2][2] = "X";
		for(int i = 0 ; i < arr.length ; i++) {
			for(int j = 0 ; j < arr.length ; j++) {
			
				System.out.printf("%-2s" , arr[i][j]);
			}
			System.out.println();
		}
		
		Scanner sc = new Scanner(System.in);
		
		String bt = sc.nextLine(); 
		//(2,2)"X"값으 저장할변수
		String temp ;
//		String arrX = arr[2][2];
		for(int i = 0 ; i < arr.length ; i++) {
			for(int j = 0 ; j < arr.length ; j++) {
				//좌표에 맞는 값이 들어갈 변수
				
				if("A".equalsIgnoreCase(bt)) {
					temp = arr[2][2];
					arr[2][2] = arr[2][1];
					arr[2][1] = temp;
//					System.out.println("1"+temp);
//					System.out.println("2"+arr[2][2]);
				}
				System.out.printf("%-2s" ,arr[i][j] );
				
//				else if("D".equalsIgnoreCase(bt)) {
////					temp = "X";
//				}
//				else if("W".equalsIgnoreCase(bt)) {
////					temp = "X";
//				}
//				else if("S".equalsIgnoreCase(bt)) {
////					temp = "X";
//				}
				System.out.println();
			}
		}
	}
}
