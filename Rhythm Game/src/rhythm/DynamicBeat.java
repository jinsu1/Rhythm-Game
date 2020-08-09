package rhythm;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;
	// 게임같은경우 이미지를 그냥 불러올경우 버퍼링이 심해질수 있어 버퍼 매순간마다 이미지를 새로고치는 방식

	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonImage = new ImageIcon(Main.class.getResource("../images/exitButton.png"));
	private ImageIcon startButtonImage = new ImageIcon(Main.class.getResource("../images/startButton.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon quitButtonImage = new ImageIcon(Main.class.getResource("../images/quitButton.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon leftButtonImage = new ImageIcon(Main.class.getResource("../images/leftButton.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon rightButtonImage = new ImageIcon(Main.class.getResource("../images/rightButton.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon easyButtonImage = new ImageIcon(Main.class.getResource("../images/easyButton.png"));
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon hardButtonImage = new ImageIcon(Main.class.getResource("../images/hardButton.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));
	private ImageIcon backButtonImage = new ImageIcon(Main.class.getResource("../images/backButton.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));

	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();

	private JButton exitButton = new JButton(exitButtonImage);
	private JButton startButton = new JButton(startButtonImage);
	private JButton quitButton = new JButton(quitButtonImage);
	private JButton leftButton = new JButton(leftButtonImage);
	private JButton rightButton = new JButton(rightButtonImage);
	private JButton easyButton = new JButton(easyButtonImage);
	private JButton hardButton = new JButton(hardButtonImage);
	private JButton backButton = new JButton(backButtonImage);

	private int mouseX, mouseY;
	// 메뉴바를 잡고 드래그해서 창을 이동시키기 위한 변수

	private boolean isMainScreen = false;
	private boolean isGameScreen = false;

	ArrayList<Track> trackList = new ArrayList<Track>();

	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	private Music introMusic = new Music("introMusic.mp3", true);
	private int nowSelected = 0;
	
	public static Game game;
	//게임은 중복실행하면안되기떄문에 static을 사용해서 프로젝트 전체에서 사용하는 하나의 변수로 만듬

	// 창설정 및 메뉴바
	public DynamicBeat() {
		
		trackList.add(new Track("downtown baby Image.png", "downtown baby Title Image.png",
				"downtown baby Selected.mp3", "downtown baby.mp3", "Downtown Baby - Bloo"));
		trackList.add(new Track("fadeaway Image.png", "fadeaway Title Image.png", "fadeaway Selected.mp3",
				"fadeaway.mp3", "Fadeaway - Jvcki Wai"));
		trackList.add(new Track("no you can't Image.png", "no you can't Title Image.png", "no you can't Selected.mp3",
				"no you can't.mp3", "No you can't - Zico"));

		
		setUndecorated(true);
		// 실행했을 때 기본적으로 제공하는 초기메뉴바를 보이지 않게함
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		// 한번 설정된 창크기는 사용자가 임의로 크기를 조정할 수 없음

		setLocationRelativeTo(null);
		// 창이 정중앙에 뜨도록 함

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 창을 종료했을떄 JFrame도 종료하게함 안쓰면 창을 종료해도 프로그램이 내부에서 계속돌아감
		setVisible(true);
		// 창이 눈에 보이도록함 기본값 false임
		setBackground(new Color(0, 0, 0, 0));
		// paintComponents한 이미지의 배경을 흰색으로 바꿈
		setLayout(null);
		// 버튼이나 JLabel을 넣었을 떄 그 위치 그대로 들어가게함
		
		addKeyListener(new KeyListener());

		introMusic.start();
		// 게임실행시 배경 바꿀거면 Track가서 선언하고 여기서 사진 파일 넣어야함

		// 종료버튼
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);

		// 게임시작버튼
		startButton.setBounds(425, 300, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				enterMain();
			}
		});
		add(startButton);

		// 종료하기 버튼
		quitButton.setBounds(425, 400, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);

		// left버튼
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60); // 자동 들여쓰기 단축키 컨트롤쉬프트f
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				selectLeft();
			}
		});
		add(leftButton);

		// right 버튼
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				selectRight();
			}
		});
		add(rightButton);

		// easy 버튼
		easyButton.setVisible(false);
		easyButton.setBounds(375, 620, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);

		// hard 버튼
		hardButton.setVisible(false);
		hardButton.setBounds(655, 620, 250, 67);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);

		// back 버튼
		backButton.setVisible(false);
		backButton.setBounds(1, 40, 230, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				backMain();
			}
		});
		add(backButton);

		// 상단바 잡고 드래그
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});

		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

	}

	// 버퍼없는 이미지 설정
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		// (Graphics2D)글자 안깨짐 and isGameScreen 밑에 안티엘리어싱 적용까지 screenGraphic 그림 안꺠짐
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		// 단순한이미지를 넣는 방법
		if (isMainScreen) {
			g.drawImage(titleImage, 430, 110, null);
			g.drawImage(selectedImage, 360, 480, null);

		}
		if (isGameScreen) {
			game.screenDraw(g);
			
		}
		paintComponents(g);
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// JLabel의 이미지를 넣는 법. 메뉴바 처럼 고정되는 이미지에 사용
		this.repaint();

	}

	public void selectTrack(int nowSelected) {

		if (selectedMusic != null)
			selectedMusic.close();
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}

	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1; // .size()하면 배열 0,1,2면 3개 니까 3으로 나와서 -1해야 트랙의 맨 마지막 곡이 선택
		else
			nowSelected--;
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);
	}

	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null)
			selectedMusic.close();
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		// background = new ImageIcon(Main.class.getResource("../images/ +
		// trackList.get(nowSelected).getGameImage())).getImage();
		// 게임배경 바꾸기
		backButton.setVisible(true);
		game = new Game(trackList.get(nowSelected).getTitleName(),difficulty,trackList.get(nowSelected).getGameMusic());
		game.start();
		isGameScreen = true;
		setFocusable(true);
		gameEnd1();
		//화면(노래) 전환시 키보드 포커스가 풀려 키작동이 안될 수있는데 이걸 설정하니까 되넹
		requestFocus();
		//setFocusable 하면 될 줄 알았는데 안됨 이거까지 넣으니까 정말 잘댐 JComponent의 Key Binding 을 쓰는게 베스트라함
	}
	
	//게임끝
	public void gameEnd1 () {
		
		Timer m_timer = new Timer();
		TimerTask m_task = new TimerTask() {
		
		@Override
		public void run() {
			
			backMain();
			}
		};
		m_timer.schedule(m_task,213000);
	}

	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		// background = new ImageIcon(Main.class.getResource("../images/ +
		// trackList.get(nowSelected).getGameImage())).getImage();
		// 게임배경 바꾸기
		backButton.setVisible(false);
		selectTrack(nowSelected);
		isGameScreen = false;
		game.close();
	}

	public void enterMain() {
		startButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectTrack(0);
	}

}
