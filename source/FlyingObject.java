package source;

import java.awt.image.BufferedImage;

//定义FlyingObject飞行物抽象类，作为敌机类、主机类、子弹类、奖励物类的超类
public abstract class FlyingObject {
	//飞行物的x，y坐标
	protected int x;
	protected int y;
	//飞行物的宽和高
	protected int width;
	protected int height;
	//飞行物的图片
	protected BufferedImage image;
	//飞行物被击中的次数
	public int objectHitIndex = 1;
	
	//访问器，访问飞行物的x坐标
	public int getX() {
		return this.x;
	}
	
	//修改器，修改飞行物的x坐标
	public void setX(int x) {
		this.x = x;
	}
	
	//访问器，访问飞行物的y坐标
	public int getY() {
		return this.y;
	}
	
	//修改器，修改飞行物的y坐标
	public void setY(int y) {
		this.y = y;
	}
	
	//访问器，访问飞行物的宽
	public int getWidth() {
		return this.width;
	}
	
	//修改器，修改飞行物的宽
	public void setWidth(int width) {
		this.width = width;
	}
	
	//访问器，访问飞行物的高
	public int getHeight() {
		return this.height;
	}
	
	//修改器，修改飞行物高
	public void setHeight(int height) {
		this.height = height;
	}

	//访问器，访问飞行物对应的图片
	public BufferedImage getImage() {
		return this.image;
	}
	
	//修改器，修改飞行物对应的图片
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	//判断飞行物是否被子弹击中，是则返回true，否则返回false
	public boolean shootByBullet(Bullet bullet) {
		//子弹的x，y坐标
		int x = bullet.x;
		int y = bullet.y;
		
		//判断飞行物是否被子弹击中
		if((this.x < x) && (x < (this.x + this.width)) && (this.y < y) && (y < (this.y + this.height))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//定义抽象方法
	
	//抽象方法，判断飞行物是否越界
	public abstract boolean outOfBoundary();
	//抽象方法，飞行物进行移动
	public abstract void moveOneStep();
	//获得飞行物的图像数组的长度，即飞行物对应总图像数量
	public abstract int getImagesArrayLength();
	//击中飞行物，切换飞行物图片
	public abstract void hitObject(int index);
}
