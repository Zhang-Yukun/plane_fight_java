package source;

import java.util.Random;
import java.awt.image.BufferedImage;

//创建HardEnemyPlane中级敌机类，继承FlyingObject飞行物类，并且实现Enemy敌机接口
public class HardEnemyPlane extends FlyingObject implements Enemy{
	//高级飞机移动速度
	private int flyingSpeed = 1;
	//高级敌机对应图片数组
	private BufferedImage[] enemyObject = {};
	//敌机类型
	private int enemyType;
	
	//无参构造函数
	public HardEnemyPlane() {
		this.enemyObject = new BufferedImage[] {MainGameScenery.enemyPlane_3,MainGameScenery.enemyPlane_3_Destroy_1,MainGameScenery.enemyPlane_3_Destroy_2,MainGameScenery.enemyPlane_3_Destroy_3,MainGameScenery.enemyPlane_3_Destroy_3,MainGameScenery.enemyPlane_3_Destroy_4,MainGameScenery.enemyPlane_3_Destroy_4,MainGameScenery.enemyPlane_3_Destroy_5,MainGameScenery.enemyPlane_3_Destroy_5,MainGameScenery.enemyPlane_3_Destroy_6};
		Random rand = new Random();
		this.image = this.enemyObject[0];
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.x = rand.nextInt(MainGameScenery.WIDTH - this.width);
		this.y = -this.height;
		this.enemyType = HARDENEMY;
	}
	
	//实现接口方法，获得敌机类型
	@Override
	public int getEnemyType() {
		return this.enemyType;
	}
		
	//实现接口方法，击落敌机后获得20分
	@Override
	public int getScore() {
		return 20;
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
		Random random = new Random();
		int randomDirection = random.nextInt(2);
		int randomX = random.nextInt(10);
		if(randomDirection < 1) {
			this.x += randomX;
		}
		else {
			this.x -= randomX;
		}
	}
	
	//重写超类抽象方法，当敌机被击中后，切换图片
	@Override
	public void hitObject(int index) {
		//击中次数达到高级敌机所对应坠毁图片数量后，高级敌机被击落
		this.image = this.enemyObject[index];
	}
		
	//重写超类抽象方法，获得初级敌机对应图片数组的长度，即高级敌机对应图片数量
	@Override
	public int getImagesArrayLength() {
		return this.enemyObject.length;
	}
	
}
