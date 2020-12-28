package source;

import java.util.Random;
import java.awt.image.BufferedImage;

//创建NormalEnemyPlane中级敌机类，继承FlyingObject飞行物类，并且实现Enemy敌机接口
public class NormalEnemyPlane extends FlyingObject implements Enemy{
	//中级飞机移动速度
	private int flyingSpeed = 3;
	//初级敌机对应图片数组
	private BufferedImage[] enemyObject = {};
	//敌机类型
	private int enemyType;
	
	//无参构造函数
	public NormalEnemyPlane() {
		//图片数组长度为7，对应初级敌机耐久度为6，被击中六次后会被销毁
		this.enemyObject = new BufferedImage[] {MainGameScenery.enemyPlane_2,MainGameScenery.enemyPlane_2_Destroy_1,MainGameScenery.enemyPlane_2_Destroy_1,MainGameScenery.enemyPlane_2_Destroy_2,MainGameScenery.enemyPlane_2_Destroy_3,MainGameScenery.enemyPlane_2_Destroy_3,MainGameScenery.enemyPlane_2_Destroy_4};
		Random rand = new Random();
		this.image = this.enemyObject[0];
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		//从上方随机位置产生中级敌机
		this.x = rand.nextInt(MainGameScenery.WIDTH - this.width);
		this.y = -this.height;
		//敌机类型：中级
		this.enemyType = NORMALENEMY;
	}
	
	//实现接口方法，获得敌机类型
	@Override
	public int getEnemyType() {
		return this.enemyType;
	}
		
	//实现接口方法，击落敌机后获得10分
	@Override
	public int getScore() {
		return 10;
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
		//击中次数达到中级敌机所对应坠毁图片数量后，中级敌机被击落
		this.image = this.enemyObject[index];
	}
	
	//重写超类抽象方法，获得中级敌机对应图片数组的长度，即中级敌机对应图片数量
	@Override
	public int getImagesArrayLength() {
		return this.enemyObject.length;
	}
	
}
