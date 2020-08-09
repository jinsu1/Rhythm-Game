package rhythm;

public class Main {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	//창 크기는 절대 변하지않는 상수이므로 final을 사용 
	public static final int NOTE_SPEED = 3;
	public static final int SLEEP_TIME = 10;

	public static final int REACH_TIME = 2;
	
	public static void main(String[] args) {
			
		new DynamicBeat();
		
	}

}
 	