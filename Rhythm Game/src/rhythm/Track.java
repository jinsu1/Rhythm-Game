package rhythm;

public class Track {
	
	private String titleImage;//제목 부분 이미지
	private String startImage;// 게임 선택 창 표지 이미지
	private String startMusic; // 게임 선택 창 음악
	private String gameMusic; // 해당 곡을 실행했을 때 음악
	//private String gameImage 해당 곡을 실했을 때 배경 
	private String titleName;//곡 제목
	
	public String getTitleImage() {
		return titleImage;
	}
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	public String getStartImage() {
		return startImage;
	}
	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}
	public String getStartMusic() {
		return startMusic;
	}
	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}
	public String getGameMusic() {
		return gameMusic;
	}
	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String gameMusic) {
		this.titleName = titleName;
	}
	
	
	public Track(String titleImage, String startImage, String startMusic, String gameMusic, String titleName) {
		super();
		this.titleImage = titleImage;
		this.startImage = startImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
		this.titleName = titleName;
	}
	
	
	
}
