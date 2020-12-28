package source;

import java.awt.image.BufferedImage;

//创建MainPlane主机类，继承FLyingObject飞行物类
public class MainPlane extends FlyingObject{
	//主机对应图片数组
	private BufferedImage[] mainPlaneImages = {};
	//用于主机移动时图片切换的索引
	private int movingIndex = 0;
	//主机的生命
	private int life;
	//主机的增益：子弹加倍，威力加倍
	private boolean doubleBullet;
	private boolean changeBomb;
	
	//无参构造函数
	public MainPlane() {
		//主机对应的图片数组
		this.mainPlaneImages = new BufferedImage[] {MainGameScenery.mainPlane_0,MainGameScenery.mainPlane_1,MainGameScenery.mainPlane_Destroy_1,MainGameScenery.mainPlane_Destroy_2,MainGameScenery.mainPlane_Destroy_3,MainGameScenery.mainPlane_Destroy_4};
		//主机初始状态
		this.life = 5;
		this.doubleBullet = false;
		this.changeBomb = false;
		this.image = this.mainPlaneImages[0];
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		//主机初始位置
		this.x = 150;
		this.y = 250;
	}
	
	//访问器，获得主机目前的子弹加倍效果
	public boolean getDoubleBullet() {
		return this.doubleBullet;
	}
	
	//修改器，修改主机子弹加倍效果
	public void setDoubleBullet(boolean state) {
		this.doubleBullet = state;
	}
	
	//访问器，获得主机目前威力加倍效果
	public boolean getChangeBomb() {
		return this.changeBomb;
	}
	
	//修改器，修改主机威力加倍效果
	public void setChangeBomb(boolean state) {
		this.changeBomb = state;
	}
	
	//主机生命增加
	public void addLife() {
		this.life++;
	}
	
	//主机生命减少
	public void subLife() {
		this.life--;
	}
	
	//访问器，获得主机目前生命值
	public int getLife() {
		return this.life;
	}
	
	//主机移动至鼠标所在位置
	public void moveToMouse(int x,int y) {
		this.x = x - (this.width / 2);
		this.y = y - (this.height / 2);
	}
	
	//主机发射子弹
	public Bullet[] shoot() {
		//子弹相对于主机的发射位置
		int xOffset = this.width / 16;
		int yOffset = 20;
		//根据主机目前增益状态，发射相对应的子弹
		if(this.doubleBullet) {
			Bullet[] bullets = new Bullet[2];
			bullets[0] = new Bullet(this.x + 7 * xOffset,this.y - yOffset,1);
			bullets[1] = new Bullet(this.x + 9 * xOffset,this.y - yOffset,1);
			return bullets;
		}
		else if(this.changeBomb) {
			Bullet[] bullets = new Bullet[1];
			bullets[0] = new Bullet(this.x + 3 * xOffset,this.y - yOffset,2);
			return bullets;
		}
		else {
			Bullet[] bullets = new Bullet[1];
			bullets[0] = new Bullet(this.x + 8 * xOffset,this.y - yOffset,0);
			return bullets;
		}
	}
	
	//判断主机是否被其余飞行物击中
	public boolean crash(FlyingObject other) {
		int x_min = other.x - (this.width / 2);
		int x_max = other.x + (this.width / 2) + other.width;
		int y_min = other.y - (this.height / 2);
		int y_max = other.y + (this.height / 2) + other.height;
		
		int mainPlane_x = this.x + (this.width / 2);
		int mainPlane_y = this.y + (this.height / 2);
		
		if((mainPlane_x > x_min) && (mainPlane_x < x_max) && (mainPlane_y > y_min) && (mainPlane_y < y_max)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//重写超类抽象方法，判断主机是否越界
	@Override
	public boolean outOfBoundary() {
		//主机无越界，当鼠标移出界面，判定游戏暂停，不会销毁主机
		return false;
	}
	
	//重写超类抽象方法，主机移动时不断切换图片，实现移动动画效果
	@Override
	public void moveOneStep() {
		this.image = this.mainPlaneImages[movingIndex % 2];
		movingIndex++;
	}
	
	//重写超类抽象方法，主机坠毁时切换图片，实现坠毁动画效果
	@Override
	public void hitObject(int index) {
		//显示对应的主机图片
		this.image = this.mainPlaneImages[index];
	}
	
	//重写超类抽象方法，获得主机对应照片数组长度，即主机对应照片数量
	@Override
	public int getImagesArrayLength() {
		return this.mainPlaneImages.length;
	}
}
