package rhythm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image noteRoutePressedImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image blueFlareImage;
	private Image judgeImage;
	
	//점수
	private int score = 0;
	private int sum = 0;
	
	
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);

		
		
	}
	
	public void screenDraw(Graphics2D g) {

		
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);

		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);
		
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);
		
		
		for(int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if(note.getY() > 620) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage();
			}
			if(!note.isProceeded()) {
				noteList.remove(i);
				i--;
			}
			else {
				note.screenDraw(g);
			}
		}

		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		// 글자에 안티엘리어싱 적용
		
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		//제목,가수
		
		g.setFont(new Font("Elephant", Font.BOLD, 30)); 
		g.drawString(difficulty , 1190, 702);
		
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 270, 650);
		g.drawString("D", 374, 650);
		g.drawString("F", 478, 650);
		g.drawString("Space Bar", 580, 650);
		g.drawString("J", 784, 650);
		g.drawString("K", 889, 650);
		g.drawString("L", 993, 650);
		//키 인터페이스
		
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		
		//점수를 문자열로 변환후 출력
		String s = String.valueOf(sum);
		g.drawString(s, 565, 702);
		
		//점수 인터페이스
		g.drawImage(blueFlareImage, 157, 250, null);
		g.drawImage(judgeImage, 460, 420, null);
		
	}
	
	
	
	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumBig.mp3", false).start();
		//반복하지말고 한번만 소리가 나와야하기때문에 false 줌 
	}
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumBig.mp3", false).start();
	}
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumBig.mp3", false).start();
	}
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall.mp3", false).start();
	}
	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumBig.mp3", false).start();
	}
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumBig.mp3", false).start();
	}
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumBig.mp3", false).start();
	}
	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	@Override
	public void run() {
		dropNotes();
	
	}
	
	
	
	public void close() {
		gameMusic.close();
		this.interrupt();
		//급하게 정지시킨 스레드의 불안정한 일시정지를 예외처리해 정상종료시킨다.
	}
	
	public void dropNotes() {
		Beat[] beats = null;
		if(titleName.equals("Downtown Baby - Bloo") && difficulty.equals("Easy")) {
			int startTime = 3200 - Main.REACH_TIME * 1000;
			int gap = 125;
			int sum = 0;
			int auto = 3;
			sum = sum + auto;
			int jump = 12;

			beats = new Beat[] {
					new Beat(startTime , "S"),
					new Beat(startTime , "L"),
					new Beat(startTime + gap * sum, "D"),
					new Beat(startTime + gap * sum, "K"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * sum, "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + jump), "S"),
					new Beat(startTime + gap * sum, "L"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * sum, "K"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * sum, "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + jump), "S"),
					new Beat(startTime + gap * sum, "L"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * sum, "K"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * sum, "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + jump), "S"),
					new Beat(startTime + gap * sum, "L"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * sum, "K"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * sum, "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"), 
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"), 
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"), 
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"), 
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"), 
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"), 
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"), 
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"), 
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"), 
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "S"),
					new Beat(startTime + gap * (sum= sum + auto), "D"),
					new Beat(startTime + gap * (sum= sum + auto), "F"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"), 
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"), 
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"), 
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"), 
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "L"),
					new Beat(startTime + gap * (sum= sum + auto), "K"),
					new Beat(startTime + gap * (sum= sum + auto), "J"),
					new Beat(startTime + gap * (sum= sum + auto), "Space"), 
					
			};
		}
		
		if(titleName.equals("Downtown Baby - Bloo") && difficulty.equals("Hard")) {
			int startTime = 6000 - Main.REACH_TIME * 1000;
			int gap = 125;

			beats = new Beat[] {
					new Beat(startTime, "Space"),
					new Beat(startTime + gap * 1, "S"),
					new Beat(startTime + gap * 1, "L"),
					new Beat(startTime + gap * 4, "D"),
					new Beat(startTime + gap * 4, "K"),
					new Beat(startTime + gap * 7, "F"),
					new Beat(startTime + gap * 7, "J"),
					new Beat(startTime + gap * 10, "Space"),
					new Beat(startTime + gap * 13, "S"),
					new Beat(startTime + gap * 13, "L"),
					new Beat(startTime + gap * 16, "D"),
					new Beat(startTime + gap * 16, "K"),
					new Beat(startTime + gap * 19, "F"),
					new Beat(startTime + gap * 19, "J"),
					new Beat(startTime + gap * 22, "Space"),
					new Beat(startTime + gap * 25, "S"),
					new Beat(startTime + gap * 25, "L"),
					new Beat(startTime + gap * 28, "D"),
					new Beat(startTime + gap * 28, "K"),
					new Beat(startTime + gap * 31, "F"),
					new Beat(startTime + gap * 31, "J"),
					new Beat(startTime + gap * 34, "Space"),
					new Beat(startTime + gap * 37, "D"),
					new Beat(startTime + gap * 40, "F"),
					new Beat(startTime + gap * 43, "D"),
					new Beat(startTime + gap * 46, "F"),
					new Beat(startTime + gap * 49, "S"),
					new Beat(startTime + gap * 52, "D"),
					new Beat(startTime + gap * 55, "F"),
					new Beat(startTime + gap * 58, "L"),
					new Beat(startTime + gap * 61, "K"),
					new Beat(startTime + gap * 64, "L"),
					new Beat(startTime + gap * 67, "K"),
					new Beat(startTime + gap * 70, "L"),
					new Beat(startTime + gap * 73, "K"),
					new Beat(startTime + gap * 76, "J"),
					new Beat(startTime + gap * 76, "Space"),
					new Beat(startTime + gap * 79, "D"),
					new Beat(startTime + gap * 82, "F"),
					new Beat(startTime + gap * 85, "D"),
					new Beat(startTime + gap * 88, "F"),
					new Beat(startTime + gap * 91, "S"),
					new Beat(startTime + gap * 94, "D"),
					new Beat(startTime + gap * 97, "F"),
					new Beat(startTime + gap * 100, "L"),
					new Beat(startTime + gap * 103, "K"),
					new Beat(startTime + gap * 106, "L"),
					new Beat(startTime + gap * 109, "K"),
					new Beat(startTime + gap * 112, "L"),
					new Beat(startTime + gap * 115, "K"),
					new Beat(startTime + gap * 118, "J"),
					new Beat(startTime + gap * 118, "Space"),
					new Beat(startTime + gap * 121, "D"),
					new Beat(startTime + gap * 124, "F"),
					new Beat(startTime + gap * 127, "D"),
					new Beat(startTime + gap * 130, "F"),
					new Beat(startTime + gap * 133, "S"),
					new Beat(startTime + gap * 136, "D"),
					new Beat(startTime + gap * 139, "F"),
					new Beat(startTime + gap * 142, "L"),
					new Beat(startTime + gap * 145, "K"),
					new Beat(startTime + gap * 148, "L"),
					new Beat(startTime + gap * 151, "K"),
					new Beat(startTime + gap * 154, "L"),
					new Beat(startTime + gap * 157, "K"),
					new Beat(startTime + gap * 160, "J"),
					new Beat(startTime + gap * 160, "Space"),
					new Beat(startTime + gap * 163, "D"),
					new Beat(startTime + gap * 166, "F"),
					new Beat(startTime + gap * 169, "D"),
					new Beat(startTime + gap * 172, "F"),
					new Beat(startTime + gap * 175, "S"),
					new Beat(startTime + gap * 178, "D"),
					new Beat(startTime + gap * 181, "F"),
					new Beat(startTime + gap * 184, "L"),
					new Beat(startTime + gap * 187, "K"),
					new Beat(startTime + gap * 190, "L"),
					new Beat(startTime + gap * 193, "K"),
					new Beat(startTime + gap * 196, "L"),
					new Beat(startTime + gap * 199, "K"),
					new Beat(startTime + gap * 202, "J"),
					new Beat(startTime + gap * 202, "Space"),

					
			};
		}
		else if(titleName.equals("Fadeaway - Jvcki Wai") && difficulty.equals("Easy")) {
			int startTime = 1000;
			int gap = 125;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
					new Beat(startTime + gap * 3, "S"),
					new Beat(startTime + gap * 6, "S"),
					new Beat(startTime + gap * 9, "S"),
					new Beat(startTime + gap * 12, "S"),
					new Beat(startTime + gap * 15, "S"),
					new Beat(startTime + gap * 18, "S"),
					new Beat(startTime + gap * 21, "S"),
					new Beat(startTime + gap * 24, "S"),
					new Beat(startTime + gap * 27, "S"),
					new Beat(startTime + gap * 30, "S"),
					new Beat(startTime + gap * 33, "S"),
					new Beat(startTime + gap * 36, "S"),
					new Beat(startTime + gap * 39, "S"),
					new Beat(startTime + gap * 42, "S"),
					new Beat(startTime + gap * 45, "S"),
					new Beat(startTime + gap * 48, "S"),
					new Beat(startTime + gap * 51, "S"),
					new Beat(startTime + gap * 54, "S"),
					new Beat(startTime + gap * 57, "S"),
					new Beat(startTime + gap * 60, "S"),
					new Beat(startTime + gap * 63, "S"),
					new Beat(startTime + gap * 66, "S"),
					
			};
		}
		
		else if(titleName.equals("Fadeaway - Jvcki Wai") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "J"),
			};
		}
		
		else if(titleName.equals("No you can't - Zico") && difficulty.equals("Easy")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "K"),
			};
		}
		
		else if(titleName.equals("No you can't - Zico") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "L"),
			};
		}
		
		
		int i = 0;
		gameMusic.start();
		while(i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
			}
			if(!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void judge(String input) {
		for(int i = 0 ; i < noteList.size(); i++ ) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}
	//점수구현  
	public int scoreSum() {
		sum = sum + score;
		score = 0;
		return sum;
	}
	// 점수구현 추가
	public void judgeEvent(String judge) {
		if(!judge.equals("None")) {
			blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
		}
		if(judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage();
		}
		if(judge.equals("Bad")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/bad.png")).getImage();
			score = score + 10;
			scoreSum();
		}
		if(judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/good.png")).getImage();
			score = score + 30;
			scoreSum();
		}
		if(judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/great.png")).getImage();
			score = score + 50;
			scoreSum();
		}
		if(judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/perfect.png")).getImage();
			score = score + 100;
			scoreSum();
		}
	}
}
