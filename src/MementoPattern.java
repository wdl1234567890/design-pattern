import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName MementoPattern
 * @Description 备忘录模式
 * @author fuling
 * @date 2020年11月4日 上午10:58:13
 */
public class MementoPattern {
	public static void main(String[] args) {
		//创建一个保存状态的容器
		Caretaker caretaker = new Caretaker();
		
		//创造一个游戏角色
		GameRole gameRole = new GameRole();
		gameRole.vit = 100;
		gameRole.def = 100;
		
		System.out.println("大战前游戏角色的状态" + gameRole);
		
		//保存游戏角色当前状态
		caretaker.addMemento(gameRole.id, gameRole.saveMemento());
		
		//模拟大战后
		gameRole.vit = 40;
		gameRole.def = 50;
		
		System.out.println("大战后游戏角色的状态" + gameRole);
		
		//恢复到上一次的状态
		gameRole.reCoverMemento(caretaker.getMemento(gameRole.id, 0));
		
		System.out.println("恢复后游戏角色的状态" + gameRole);
		
		
	}
}

class GameRole{
	String id;
	int vit;
	int def;
	
	GameRole(){
		this.id = "a" + (new Date()).getDate();
	}
	
	
	/**
	 * 
	 * @Title saveMemento
	 * @Description 保存当前状态
	 * @param @return 参数说明
	 * @return 返回说明
	 * @throws
	 */
	Memento saveMemento() {
		return new Memento(vit,def);
	}
	
	/**
	 * 
	 * @Title reCoverMemento
	 * @Description 恢复到之前的某一状态
	 * @param @param memento 参数说明
	 * @return 返回说明
	 * @throws
	 */
	void reCoverMemento(Memento memento){
		this.vit = memento.vit;
		this.def = memento.def;
	}
	
	@Override
	public String toString() {
		
		return "vit=" + vit + ",def=" + def;
	}
}

class Memento{
	int vit;
	int def;
	
	Memento(int vit, int def){
		this.vit = vit;
		this.def = def;
	}
}

class Caretaker{
	Map<String,List<Memento>> mementos;//{角色1的id=>角色1保存的所有状态,角色2的id=>...}
	
	void addMemento(String id, Memento memento) {
		if(memento != null) {
			if(mementos == null)mementos = new HashMap<>();
			if(mementos.get(id) == null)mementos.put(id, new ArrayList<>());
			mementos.get(id).add(memento);
		}
	}
	
	Memento getMemento(String id, int index){
		return mementos.get(id).get(index);
	}
}
