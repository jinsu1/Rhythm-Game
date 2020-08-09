package rhythm;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	
	private Image noteImage = new ImageIcon(Main.class.getResource("../images/note.png")).getImage();
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME ;
	private String noteType;
	private boolean proceeded = true;
	
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	
	public void close() {
		proceeded = false;
	}
	
	public Note(String noteType) {
		if(noteType.equals("S")) {
			x = 228;
		}
		else if(noteType.equals("D")) {
			x = 332;
		}
		else if(noteType.equals("F")) {
			x = 436;
		}
		else if(noteType.equals("Space")) {
			x = 540;
		}
		else if(noteType.equals("J")) {
			x = 744;
		}
		else if(noteType.equals("K")) {
			x = 848;
		}
		else if(noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType;
		
	}
	public void screenDraw(Graphics2D g) {
		if(noteType.equals("Space")) {
			g.drawImage(noteImage, x, y, null);
			g.drawImage(noteImage, x + 100, y, null);

		}
		else 
			g.drawImage(noteImage,  x, y, null);
		}


	public void drop() {
		y += Main.NOTE_SPEED; 
		if(y > 620) {
			System.out.println("Miss");
			close();
		}
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				}
				else {
					interrupt();
					break;
				}
			}
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public String judge() {
		if(y >=639) {
			System.out.println("Bad");
			close();
			return "Bad";
		}
		else if(y >= 617 ) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if(y >= 595 ) {
			System.out.println("Great");
			close();
			return "Great";
		}
		else if(y >= 573) {
			System.out.println("Perfect");
			close();
			return "Perfect";
		}
		else if(y >= 551 ) {
			System.out.println("Great");
			close();
			return "Great";
		}
		else if(y >= 529) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if(y >=508) {
			System.out.println("Bad");
			close();
			return "Bad";
		}
		return "None";
	}
	
	public int getY() {
		return y;
	}
}
