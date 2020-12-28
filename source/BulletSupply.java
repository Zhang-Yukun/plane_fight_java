package source;

import java.util.Random;

///创建BulletSupply弹药补给类，继承FlyingObject飞行物类，并且实现Award奖励接口
public class BulletSupply extends FlyingObject implements Award{
	//弹药补给下落时x方向与y方向速度
	private int xSpeed = 2;
	private int ySpeed = 5;
	//弹药补给对应奖励类型
	private int awardType;
	
	//无参构造函数
	public BulletSupply() {
		Random rand = new Random();
		this.image = MainGameScenery.bulletSupply;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		//从上方随机位置产生弹药补给
		this.x = rand.nextInt(MainGameScenery.WIDTH - this.width);
		this.y = -this.height;
		this.awardType = 1;
	}
	
	//访问器，获得弹药补给对应奖励类型
	public int getAwardType() {
		return this.awardType;
	}
	
	//重写超类抽象方法，判断补给是否越界
	@Override
	public boolean outOfBoundary() {
		if(this.y > MainGameScenery.HEIGHT) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//重写超类抽象方法，让补给不断向下移动
	@Override
	public void moveOneStep() {
		Random xRand = new Random();
		int xChoice = xRand.nextInt(2);
		if(xChoice < 1) {
			this.x += this.xSpeed;
		}
		else {
			this.x -= this.xSpeed;
		}
		this.y += this.ySpeed;
	}
	
	//重写超类抽象方法，当弹药补给被击中时，切换图片
	@Override
	public void hitObject(int index) {
		//弹药补给被击中后，无需图片切换，直接销毁该对象
	}
	
	//重写超类抽象方法，获得弹药补给对应图片数量
	@Override
	public int getImagesArrayLength() {
		return 1;
	}
	
}
