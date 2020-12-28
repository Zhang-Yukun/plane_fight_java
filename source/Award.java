package source;

//创建Award奖励接口
public interface Award {
	
	//定义常量，分别对应三种奖励：生命增加，子弹翻倍，威力增加
	public static final int LIFE = 0;
	public static final int DOUBLE_BULLET = 1;
	public static final int CHANGE_BOMB = 2;
	
	//获取奖励类型
	public abstract int getAwardType();
}
