package source;

import java.util.Random;
import java.awt.image.BufferedImage;

//创建EasyEnemyPlane初级敌机类，继承FlyingObject飞行物类，并且实现Enemy敌机接口
public class EasyEnemyPlane extends FlyingObject implements Enemy{
	//初级飞机移动速度
	private int flyingSpeed = 5;
	//初级敌机对应图片数组
	private BufferedImage[] enemyObject = {};
	//敌机类型
	private int enemyType;
	
	//无参构造函数
	public EasyEnemyPlane() {
		//图片数组长度为5，对应初级敌机耐久度为4，被击中四次后会被销毁
		this.enemyObject = new BufferedImage[] {MainGameScenery.enemyPlane_1,MainGameScenery.enemyPlane_1_Destroy_1,MainGameScenery.enemyPlane_1_Destroy_2,MainGameScenery.enemyPlane_1_Destroy_3,MainGameScenery.enemyPlane_1_Destroy_4};
		Random rand = new Random();
		this.image = this.enemyObject[0];
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		//从上方随机位置产生初级敌机
		this.x = rand.nextInt(MainGameScenery.WIDTH - this.width);
		this.y = -this.height;
		//敌机类型：初级
		this.enemyType = EASYENEMY;
	}
	
	//实现接口方法，获得敌机类型
	@Override
	public int getEnemyType() {
		return this.enemyType;
	}
	
	//实现接口方法，击落敌机后获得5分
	@Override
	public int getScore() {
		return 5;
	}
	
	//重写超类抽象方法，判断敌机是否越界
	@Override
	public boolean outOfBoundary() {
		if(this.y > MainGameScenery.HEIGHT) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//重写超类抽象方法，敌机向下移动
	@Override
	public void moveOneStep() {
		this.y += this.flyingSpeed;
	}
	
	//重写超类抽象方法，当敌机被击中后，切换图片
	@Override
	public void hitObject(int index) {
		//击中次数达到初级敌机所对应坠毁图片数量后，初级敌机被击落
		this.image = this.enemyObject[index];
	}
	
	//重写超类抽象方法，获得初级敌机对应图片数组的长度，即初级敌机对应图片数量
	@Override
	public int getImagesArrayLength() {
		return this.enemyObject.length;
	}
}
