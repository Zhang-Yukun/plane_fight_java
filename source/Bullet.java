package source;

import java.awt.image.BufferedImage;

//创建Bullet子弹类，继承FlyingObject飞行物类
public class Bullet extends FlyingObject{
	//子弹飞行速度
	private int flyingSpeed;
	//子弹伤害
	private int attack;
	//子弹数组
	private BufferedImage bullets[] = {};
	
	//无参构造函数
	public Bullet() {
		this(0,0,0);
	}
	//带参数构造函数，参数分别为子弹发射的x坐标，y坐标以及子弹类型：单发、双发、炮弹
	public Bullet(int x,int y,int type) {
		this.x = x;
		this.y = y;
		bullets = new BufferedImage[] {MainGameScenery.bullet_normal_level,MainGameScenery.bullet_high_level,MainGameScenery.bomb};
		this.image = bullets[type];
		//根据子弹类行设置对应飞行速度与伤害
		switch(type){
			case 0:{
				this.attack = 1;
				this.flyingSpeed = 2;
				break;
			}
			case 1:{
				this.attack = 2;
				this.flyingSpeed = 8;
				break;
			}
			case 2:{
				this.attack = 5;
				this.flyingSpeed = 1;
				break;
			}
			default:{
				break;
			}
		}
	}
	
	//访问器，访问子弹伤害
	public int getAttack() {
		return this.attack;
	}
	
	//重写超类抽象方法，判断子弹是否越界，是则返回true，否则返回false
	@Override
	public boolean outOfBoundary() {
		if(this.y < (-this.height)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//重写超类抽象方法，子弹向上进行移动
	@Override
	public void moveOneStep() {
		this.y -= this.flyingSpeed;
	}
	
	//重写超类抽象方法，当子弹击中目标后，切换图片
	@Override
	public void hitObject(int index) {
		//子弹击中目标后，无需图片切换，直接销毁该对象
	}
	
	//重写超类抽象方法，访问子弹对应图像数组的长度，即子弹类型的数量
	@Override
	public int getImagesArrayLength() {
		return bullets.length;
	}
}
