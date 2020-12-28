package source;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

//创建MainGameScenery游戏主窗口类
public class MainGameScenery extends JPanel{
	//定义常量，设置主窗口的宽和高
	public static final int WIDTH = 400;
	public static final int HEIGHT = 600;
	//定义常量，对应游戏四个状态：开始，运行，暂停，结束
	private static final int START = 0;
	private static final int RUNNING = 1;
	private static final int PAUSE = 2;
	private static final int GAME_OVER = 3;
	//游戏现在状态
	private int state;
	//游戏得分
	private static int score = 0;
	//计时器
	private Timer timer;
	//刷新界面间隔：25ms
	private int interval = 25;
	//子弹初始发射速度
	private int shootSpeed = 10;
	//飞行物初始产生速度
	private int enterSpeed = 40;
	//主机坠毁对应图片
	private int mainPlaneDestroyIndex = 2;
	//入场飞行物数
	public int flyingObjectEnteredIndex = 0;
	//发射子弹数
	public int bulletsIndex = 0;
	//游戏所用图片
	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage enemyPlane_1;
	public static BufferedImage enemyPlane_1_Destroy_1;
	public static BufferedImage enemyPlane_1_Destroy_2;
	public static BufferedImage enemyPlane_1_Destroy_3;
	public static BufferedImage enemyPlane_1_Destroy_4;
	public static BufferedImage enemyPlane_2;
	public static BufferedImage enemyPlane_2_Destroy_1;
	public static BufferedImage enemyPlane_2_Destroy_2;
	public static BufferedImage enemyPlane_2_Destroy_3;
	public static BufferedImage enemyPlane_2_Destroy_4;
	public static BufferedImage enemyPlane_3;
	public static BufferedImage enemyPlane_3_Destroy_1;
	public static BufferedImage enemyPlane_3_Destroy_2;
	public static BufferedImage enemyPlane_3_Destroy_3;
	public static BufferedImage enemyPlane_3_Destroy_4;
	public static BufferedImage enemyPlane_3_Destroy_5;
	public static BufferedImage enemyPlane_3_Destroy_6;
	public static BufferedImage bee;
	public static BufferedImage bullet_normal_level;
	public static BufferedImage bullet_high_level;
	public static BufferedImage bomb;
	public static BufferedImage bulletSupply;
	public static BufferedImage bombSupply;
	public static BufferedImage mainPlane_0;
	public static BufferedImage mainPlane_1;
	public static BufferedImage mainPlane_Destroy_1;
	public static BufferedImage mainPlane_Destroy_2;
	public static BufferedImage mainPlane_Destroy_3;
	public static BufferedImage mainPlane_Destroy_4;
	public static BufferedImage pause;
	public static BufferedImage gameOver;
	
	//创建敌机数组
	private FlyingObject[] flyingObjects = {};
	//创建子弹数组
	private Bullet[] bullets = {};
	//创建主机对象
	private MainPlane mainPlane = new MainPlane();
	
	//绑定游戏图片
	static {
		try {
			background = ImageIO.read(MainGameScenery.class.getResource("background.png"));
			start = ImageIO.read(MainGameScenery.class.getResource("start.png"));
			enemyPlane_1 = ImageIO.read(MainGameScenery.class.getResource("enemy_1.png"));
			enemyPlane_1_Destroy_1 = ImageIO.read(MainGameScenery.class.getResource("enemy_1_destroy_1.png"));
			enemyPlane_1_Destroy_2 = ImageIO.read(MainGameScenery.class.getResource("enemy_1_destroy_2.png"));
			enemyPlane_1_Destroy_3 = ImageIO.read(MainGameScenery.class.getResource("enemy_1_destroy_3.png"));
			enemyPlane_1_Destroy_4 = ImageIO.read(MainGameScenery.class.getResource("enemy_1_destroy_4.png"));
			enemyPlane_2 = ImageIO.read(MainGameScenery.class.getResource("enemy_2.png"));
			enemyPlane_2_Destroy_1 = ImageIO.read(MainGameScenery.class.getResource("enemy_2_destroy_1.png"));
			enemyPlane_2_Destroy_2 = ImageIO.read(MainGameScenery.class.getResource("enemy_2_destroy_2.png"));
			enemyPlane_2_Destroy_3 = ImageIO.read(MainGameScenery.class.getResource("enemy_2_destroy_3.png"));
			enemyPlane_2_Destroy_4 = ImageIO.read(MainGameScenery.class.getResource("enemy_2_destroy_4.png"));
			enemyPlane_3 = ImageIO.read(MainGameScenery.class.getResource("enemy_3.png"));
			enemyPlane_3_Destroy_1 = ImageIO.read(MainGameScenery.class.getResource("enemy_3_destroy_1.png"));
			enemyPlane_3_Destroy_2 = ImageIO.read(MainGameScenery.class.getResource("enemy_3_destroy_2.png"));
			enemyPlane_3_Destroy_3 = ImageIO.read(MainGameScenery.class.getResource("enemy_3_destroy_3.png"));
			enemyPlane_3_Destroy_4 = ImageIO.read(MainGameScenery.class.getResource("enemy_3_destroy_4.png"));
			enemyPlane_3_Destroy_5 = ImageIO.read(MainGameScenery.class.getResource("enemy_3_destroy_5.png"));
			enemyPlane_3_Destroy_6 = ImageIO.read(MainGameScenery.class.getResource("enemy_3_destroy_6.png"));
			bee = ImageIO.read(MainGameScenery.class.getResource("bee.png"));
			bullet_normal_level = ImageIO.read(MainGameScenery.class.getResource("bullet_normal_level.png"));
			bullet_high_level = ImageIO.read(MainGameScenery.class.getResource("bullet_high_level.png"));
			bomb = ImageIO.read(MainGameScenery.class.getResource("bomb.png"));
			bulletSupply = ImageIO.read(MainGameScenery.class.getResource("bullet_supply.png"));
			bombSupply = ImageIO.read(MainGameScenery.class.getResource("bomb_supply.png"));
			mainPlane_0 = ImageIO.read(MainGameScenery.class.getResource("mainplane_0.png"));
			mainPlane_1 = ImageIO.read(MainGameScenery.class.getResource("mainplane_1.png"));
			mainPlane_Destroy_1 = ImageIO.read(MainGameScenery.class.getResource("mainplane_destroy_1.png"));
			mainPlane_Destroy_2 = ImageIO.read(MainGameScenery.class.getResource("mainplane_destroy_2.png"));
			mainPlane_Destroy_3 = ImageIO.read(MainGameScenery.class.getResource("mainplane_destroy_3.png"));
			mainPlane_Destroy_4 = ImageIO.read(MainGameScenery.class.getResource("mainplane_destroy_4.png"));
			pause = ImageIO.read(MainGameScenery.class.getResource("pause.png"));
			gameOver = ImageIO.read(MainGameScenery.class.getResource("gameover.png"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//主程序入口
	public static void main(String[] args) {
		//标题栏显示"Welcome to Plane Fight"
		JFrame frame = new JFrame("Welcome to Plane Fight");
		MainGameScenery scenery = new MainGameScenery();
		frame.add(scenery);
		//设置主窗口宽和高
		frame.setSize(WIDTH,HEIGHT);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(new ImageIcon("images/icon.jpg").getImage());
		frame.setLocationRelativeTo(null);
		//窗口可见化
		frame.setVisible(true);
		//调用action方法
		scenery.action();
	}
	
	//绘制主机
	public void paintMainPlane(Graphics g) {
		g.drawImage(mainPlane.getImage(),mainPlane.getX(),mainPlane.getY(),null);
	}
	
	//绘制子弹
	public void paintBullets(Graphics g) {
		for(int i = 0;i < bullets.length;i++) {
			g.drawImage(bullets[i].getImage(),bullets[i].getX() - (bullets[i].getWidth() / 2),bullets[i].getY(),null);
		}
	}
	
	//绘制飞行物，包括敌机和补给
	public void paintFlyingObjects(Graphics g) {
		for(int i = 0;i < flyingObjects.length;i++) {
			g.drawImage(flyingObjects[i].getImage(), flyingObjects[i].getX(), flyingObjects[i].getY(), null);
		}
	}
	
	//绘制分数
	public void paintScore(Graphics g) {
		//得分框的x，y坐标
		int xScore = 10;
		int yScore = 25;
		//设置字体
		Font font = new Font(Font.SERIF,Font.BOLD,22);
		g.setFont(font);
		//设置颜色
		Color color = new Color(0xF00000);
		g.setColor(color);
		//绘制得分框
		g.drawString("Score: " + score, xScore, yScore);
		g.drawString("Life: " + mainPlane.getLife(), xScore, yScore + 25);
	}
	
	//根据游戏不同状态，绘制游戏界面
	public void paintGameState(Graphics g) {
		switch(state) {
			case START:{
				g.drawImage(start, 0, 0, null);
				break;
			}
			case PAUSE:{
				g.drawImage(pause, 0, 0, null);
				break;
			}
			case GAME_OVER:{
				g.drawImage(gameOver, 0, 0, null);
				break;
			}
			default:{
				break;
			}
		}
	}
	
	//重写paint方法，绘制整个游戏界面
	@Override
	public void paint(Graphics g) {
		//
		g.drawImage(background, 0, 0, null);
		paintMainPlane(g);
		paintBullets(g);
		paintFlyingObjects(g);
		paintScore(g);
		paintGameState(g);
	}
	
	//执行飞行物进入操作，随机生成飞行物（包括敌机和补给），并且增加入flyingObjects数组
	public void enterAction() {
		flyingObjectEnteredIndex++;
		//飞行物进入速度
		if(flyingObjectEnteredIndex % enterSpeed == 0) {
			FlyingObject object = nextOne();
			flyingObjects = Arrays.copyOf(flyingObjects, flyingObjects.length + 1);
			flyingObjects[flyingObjects.length - 1] = object;
		}

	}
	
	//执行移动操作，每个飞行物和子弹进行移动
	public void stepAction() {
		//
		for(int i = 0;i < flyingObjects.length;i++) {
			flyingObjects[i].moveOneStep();
		}
		//
		for(int i = 0;i < bullets.length;i++) {
			bullets[i].moveOneStep();
		}
	}
	
	//执行射击操作，根据主机当前状态创建响应的子弹并且添加进bullets数组
	public void shootAction() {
		bulletsIndex++;
		//子弹发射速度
		if(bulletsIndex % shootSpeed == 0) {
			Bullet[] bulletsShoot = mainPlane.shoot();
			bullets = Arrays.copyOf(bullets, bullets.length + bulletsShoot.length);
			System.arraycopy(bulletsShoot, 0, bullets, bullets.length - bulletsShoot.length,bulletsShoot.length);
		}

	}
	
	//检测子弹与飞行物是否相撞，相撞后根据敌机和补给更新主机状态，并且销毁子弹，改变飞行物状态
	public void bitObject(Bullet bullet,int bulletsIndex) {
		int index = -1;
		//判断子弹是否击中某一个飞行物，击中则返回该飞行物对应索引
		for(int i = 0;i < flyingObjects.length;i++) {
			if(flyingObjects[i].shootByBullet(bullet)) {
				index = i;
				break;
			}
		}
		//如果子弹击中了某个飞行物
		if(index != -1) {
			//销毁该子弹
			bullets[bulletsIndex] = bullets[bullets.length - 1];
			bullets[bullets.length - 1] = bullet;
			bullets = Arrays.copyOf(bullets, bullets.length - 1);
			
			//将该飞行物移到flyingObjects数组末尾，方便销毁
			FlyingObject oneHitedByBullet = flyingObjects[index];
			flyingObjects[index] = flyingObjects[flyingObjects.length - 1];
			flyingObjects[flyingObjects.length - 1] = oneHitedByBullet;
			
			//该飞行物是敌机
			if(oneHitedByBullet instanceof Enemy) {
				flyingObjects[flyingObjects.length - 1].hitObject(flyingObjects[flyingObjects.length - 1].objectHitIndex);
				//根据子弹的伤害更新飞行物状态
				flyingObjects[flyingObjects.length - 1].objectHitIndex += bullet.getAttack();
				//如果飞行物受到总伤害超过其耐久度，则销毁该飞行物，并且获得对应分值
				if(flyingObjects[flyingObjects.length - 1].objectHitIndex >= flyingObjects[flyingObjects.length - 1].getImagesArrayLength()) {
					flyingObjects = Arrays.copyOf(flyingObjects, flyingObjects.length - 1);
					Enemy enemy = (Enemy) oneHitedByBullet;
					score += enemy.getScore();
				}
			}
			//该飞行物是奖励
			else {
				Award award = (Award) oneHitedByBullet;
				int awardType = award.getAwardType();
				//根据对应奖励更新主机状态
				switch(awardType) {
					case Award.LIFE:{
						//生命增加
						mainPlane.addLife();
						break;
					}
					case Award.DOUBLE_BULLET:{
						//弹药加倍，威力略微增加
						mainPlane.setDoubleBullet(true);
						mainPlane.setChangeBomb(false);
						//子弹发射速度增加
						setShootSpeed(5);
						//飞行物产生速度加快
						increaseEnterSpeed();
						break;
					}
					case Award.CHANGE_BOMB:{
						//威力大幅增加，弹药数不变
						mainPlane.setChangeBomb(true);
						mainPlane.setDoubleBullet(false);
						//子弹发射速度减慢
						setShootSpeed(10);
						//飞行物产生速度加快
						increaseEnterSpeed();
						break;
					}
					default:{
						break;
					}
				}
				//销毁该补给
				flyingObjects = Arrays.copyOf(flyingObjects, flyingObjects.length - 1);
			}
		}
	}
	
	//执行击中操作，判断每一颗子弹是否击中飞行物，并且执行相关操作
	public void bitAction() {
		for(int i = 0;i < bullets.length;i++) {
			bitObject(bullets[i],i);
		}
	}
	
	//检测子弹与飞行物是否越界，如果越界则销毁，仅保留主窗口内部的飞行物与子弹
	public void outOfBoundaryAction() {
		int index = 0;
		//检测飞行物是否越界，仅保留未越界的飞行物
		FlyingObject[] flyingLives = new FlyingObject[flyingObjects.length];
		for(int i = 0;i < flyingObjects.length;i++) {
			FlyingObject object = flyingObjects[i];
			if(!object.outOfBoundary()) {
				flyingLives[index] = object;
				index++;
			}
		}
		flyingObjects = Arrays.copyOf(flyingLives, index);
		//重置index索引
		index = 0;
		//检测子弹是否越界，仅保留未越界的子弹
		Bullet[] bulletLives = new Bullet[bullets.length];
		for(int i = 0;i < bullets.length;i++) {
			Bullet bullet = bullets[i];
			if(!bullet.outOfBoundary()) {
				bulletLives[index] = bullet;
				index++;
			}
		}
		bullets = Arrays.copyOf(bulletLives,index);
	}
	
	//判断主机是否与飞行物发生碰撞，如果有，则生命减一，销毁与之碰撞的飞行物，同时主机增益清除，飞行物产生速度恢复初始
	public boolean isHitedByFlyingObject() {
		//检测每一个飞行物是否与主机相撞
		for(int i = 0;i < flyingObjects.length;i++) {
			int index = -1;
			//飞行物与主机相撞后，主机生命减一，增益效果清除，飞行物产生速度恢复
			if(mainPlane.crash(flyingObjects[i])) {
				mainPlane.subLife();
				mainPlane.setDoubleBullet(false);
				mainPlane.setChangeBomb(false);
				setShootSpeed(20);
				setEnterSpeed(40);
				index = i;
			}
			//清除与主机相撞的飞行物
			if(index != -1) {
				FlyingObject oneHitedByMainPlane = flyingObjects[index];
				flyingObjects[index] = flyingObjects[flyingObjects.length - 1];
				flyingObjects[flyingObjects.length - 1] = oneHitedByMainPlane;
				flyingObjects = Arrays.copyOf(flyingObjects, flyingObjects.length - 1);
			}
		}
		//主机生命为0时游戏结束
		if(mainPlane.getLife() <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//判断游戏是否结束，结束则将界面转换至结束界面
	public void checkGameOverAction() {
		if(isHitedByFlyingObject()) {
			//显示主机坠毁动画
			mainPlane.hitObject(mainPlaneDestroyIndex);
			mainPlaneDestroyIndex++;
			//显示坠毁动画后游戏结束
			if(mainPlaneDestroyIndex == mainPlane.getImagesArrayLength()) {
				state = GAME_OVER;
			}
		}
	}
	//监听鼠标操作，并根据鼠标操作更新游戏界面
	public void action() {
		MouseAdapter mouse = new MouseAdapter() {
			//重写mouseMoved方法，根据鼠标位置，将主机移动至该位置
			@Override
			public void mouseMoved(MouseEvent e) {
				if(state == RUNNING) {
					int x = e.getX();
					int y = e.getY();
					mainPlane.moveToMouse(x, y);
				}
			}
			
			//重写mouseExited方法，游戏进行时，鼠标移出主窗口后，游戏暂停
			@Override
			public void mouseExited(MouseEvent e) {
				if(state == RUNNING) {
					state = PAUSE;
				}
			}
			
			//重写mouseEntered方法，当暂停时，鼠标移入后，游戏继续
			@Override
			public void mouseEntered(MouseEvent e) {
				if(state == PAUSE) {
					state = RUNNING;
				}
			}
			
			//重写mouseClicked方法，点击时，游戏从开始状态变为运行状态，从结束状态变为开始状态，并且重置游戏内容
			@Override
			public void mouseClicked(MouseEvent e) {
				switch(state) {
					case START:{
						state = RUNNING;
						break;
					}
					case GAME_OVER:{
						//重置游戏内容以及得分
						flyingObjects = new FlyingObject[0];
						bullets = new Bullet[0];
						mainPlane = new MainPlane();
						score = 0;
						mainPlaneDestroyIndex = 2;
						state = START;
						break;
					}
					default:{
						break;
					}
				}
			}
		};
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		
		//计时器
		timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			//建立定时任务，并且重写run方法，用于定时更新游戏界面
			@Override
			public void run() {
				if(state == RUNNING) {
					enterAction();
					stepAction();
					shootAction();
					bitAction();
					outOfBoundaryAction();
					checkGameOverAction();
				}
				repaint();
			}
		};
		//定时执行更新游戏界面任务
		timer.schedule(timerTask, interval, interval);
	}
	
	//随机生成飞行物
	public static FlyingObject nextOne() {
		Random random = new Random();
		int type = random.nextInt(100);
		//根据得分不同，以不同的概率生成飞行物
		if(score < 500) {
			if(type < 4) {
				return new BombSupply();
			}
			else if(type < 40) {
				return new BulletSupply();
			}
			else if(type < 50) {
				return new LifeSupply();
			}
			else if(type < 85) {
				return new EasyEnemyPlane();
			}
			else if(type < 95) {
				return new NormalEnemyPlane();
			}
			else {
				return new HardEnemyPlane();
			}
		}
		else if(score < 1000) {
			if(type < 4) {
				return new BombSupply();
			}
			else if(type < 30) {
				return new BulletSupply();
			}
			else if(type < 40) {
				return new LifeSupply();
			}
			else if(type < 75) {
				return new EasyEnemyPlane();
			}
			else if(type < 90) {
				return new NormalEnemyPlane();
			}
			else {
				return new HardEnemyPlane();
			}
		}
		else if(score < 2000){
			if(type < 4) {
				return new BombSupply();
			}
			else if(type < 25) {
				return new BulletSupply();
			}
			else if(type < 30) {
				return new LifeSupply();
			}
			else if(type < 70) {
				return new EasyEnemyPlane();
			}
			else if(type < 80) {
				return new NormalEnemyPlane();
			}
			else {
				return new HardEnemyPlane();
			}
		}
		else {
			if(type < 2) {
				return new BombSupply();
			}
			else if(type < 5) {
				return new BulletSupply();
			}
			else if(type < 10) {
				return new LifeSupply();
			}
			else if(type < 50) {
				return new EasyEnemyPlane();
			}
			else if(type < 75) {
				return new NormalEnemyPlane();
			}
			else {
				return new HardEnemyPlane();
			}
		}
	}
	//设置子弹发射速度
	public void setShootSpeed(int speed) {
		shootSpeed = speed;
	}
	
	//设置飞行物产生速度
	public void setEnterSpeed(int speed) {
		enterSpeed = speed;
	}
	
	//主机获得增益时，飞行物产生速度提升
	public void increaseEnterSpeed() {
		enterSpeed -= 5;
		if(enterSpeed <= 0) {
			enterSpeed = 5;
		}
	}
}
