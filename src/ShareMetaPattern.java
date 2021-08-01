import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName ShareMetaPattern
 * @Description 享元模式
 * @author fuling
 * @date 2020年11月10日 下午2:19:49
 */
public class ShareMetaPattern {
	public static void main(String[] args) {
		
		
		
		//创建一个棋手
		User user = new User("棋手1");
		
		//获取一颗白色棋子
		Chess chess = ChessFactory.getChess(ChessFactory.BLACK);
		
		//棋手使用白色棋子
		chess.use(user);
	}
}

/**
 * 
 * @ClassName User
 * @Description 用户类
 * @author fuling
 * @date 2020年11月10日 下午2:28:59
 */
class User{
	//用户的名字
	String name;
	
	User(String name){
		this.name = name;
	}
	
	//用户摆放棋子
	void placeChess(){
		System.out.println(this.name + "把棋子放到任一位置");
	}
}

/**
 * 
 * @ClassName Chess
 * @Description 棋子类
 * @author fuling
 * @date 2020年11月10日 下午2:29:36
 */
interface Chess{
	//用户选择棋子
	void use(User user);
}


/**
 * 
 * @ClassName WhiteChess
 * @Description 白棋子类
 * @author fuling
 * @date 2020年11月10日 下午2:31:28
 */
class WhiteChess implements Chess{

	@Override
	public void use(User user) {
		System.out.println(user.name + "拿起白棋子");
		user.placeChess();
	}
	
}

/**
 * 
 * @ClassName BlackChess
 * @Description 黑棋子类
 * @author fuling
 * @date 2020年11月10日 下午2:32:28
 */
class BlackChess implements Chess{

	@Override
	public void use(User user) {
		System.out.println(user.name + "拿起黑棋子");
		user.placeChess();	
	}
	
}


/**
 * 
 * @ClassName ChessFactory
 * @Description 缓存棋子的工厂，有一个获取棋子的方法
 * @author fuling
 * @date 2020年11月10日 下午2:45:45
 */
class ChessFactory{
	
	//定义黑白棋子常量标识
	static final int WHITE = 0;
	
	static final int BLACK = 1;
	
	//缓存棋子的map
	static Map<Integer, Chess> chesses;
	
	//根据标识从map缓存中获取对应的棋子对象，如果获取不到，则创建一个新的棋子，将棋子放入map缓存并返回该棋子
	static Chess getChess(int type){
		if(chesses == null)chesses = new HashMap<>();
		switch (type) {
		case WHITE:
			if(chesses.get(WHITE) == null) {
				chesses.put(WHITE, new WhiteChess());
			}
			return chesses.get(WHITE);
		case BLACK:
			if(chesses.get(BLACK) == null) {
				chesses.put(BLACK, new BlackChess());
			}
			return chesses.get(BLACK);
		default:
			System.out.println("错误参数！");
			return null;
		}
	}
	
	
	
}
