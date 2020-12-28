package source;

import java.util.Random;

//创建BombSupply炸弹补给类，继承FlyingObject飞行物类，并且实现Award奖励接口
public class BombSupply extends FlyingObject implements Award{
	//炸弹补给下落时x方向与y方向速度
		private int xSpeed = 4;
		private int ySpeed = 5;
		//炸弹补给对应奖励类型
		private int awardType;
		
		//无参构造函数
		public BombSupply() {
			Random rand = new Random();
			this.image = MainGameScenery.bombSupply;
			this.width = this.image.getWidth();
			this.height = this.image.getHeight();
			//从上方随机位置产生炸弹补给
			this.x = rand.nextInt(MainGameScenery.WIDTH - this.width);
			this.y = -this.height;
			this.awardType = 2;
		}
		
		//访问器，获得炸弹补给对应奖励类型
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
		
		//重写超类抽象方法，当炸弹补给被击中时，切换图片
		@Override
		public void hitObject(int index) {
			//炸弹补给被击中后，无需图片切换，直接销毁该对象
		}
		
		//重写超类抽象方法，获得炸弹补给对应图片数量
		@Override
		public int getImagesArrayLength() {
			return 1;
		}
		
}
