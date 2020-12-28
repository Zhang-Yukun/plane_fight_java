package source;

//创建Enemy敌人接口
public interface Enemy {
	//定义常量，分别对应三种类型敌机：简单，正常，困难
	public static final int EASYENEMY = 0;
	public static final int NORMALENEMY = 1;
	public static final int HARDENEMY = 2;
	
	//获得敌机类型
	public abstract int getEnemyType();
	//获得击落敌机后的得分
	public abstract int getScore();
}
