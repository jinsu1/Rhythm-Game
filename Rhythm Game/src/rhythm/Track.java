package rhythm;

public class Track {
	
	private String titleImage;//���� �κ� �̹���
	private String startImage;// ���� ���� â ǥ�� �̹���
	private String startMusic; // ���� ���� â ����
	private String gameMusic; // �ش� ���� �������� �� ����
	//private String gameImage �ش� ���� ������ �� ��� 
	private String titleName;//�� ����
	
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
