import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName VisitorPattern
 * @Description 访问者模式
 * @author fuling
 * @date 2020年11月6日 下午9:30:14
 */
public class VisitorPattern {
	public static void main(String[] args) {
		ObjectStructure objectStructure = new ObjectStructure();
		objectStructure.accept(new Success());
		objectStructure.addPerson(new Man());
		objectStructure.addPerson(new Woman());
		
		objectStructure.Operation1();
		
	}
}

/**
 * 
 * @ClassName Action
 * @Description 定义操作接口，封装了所有的操作方法
 * @author fuling
 * @date 2020年11月6日 下午10:03:52
 */
interface Action{
	//操作1，访问传进来的person
	void Operation1(Person person);
	//操作2，访问传进来的person
	void Operation2(Person person);
}

/**
 * 
 * @ClassName Success
 * @Description 成功的操作实现
 * @author fuling
 * @date 2020年11月6日 下午10:04:26
 */
class Success implements Action{

	@Override
	public void Operation1(Person person) {
		
		System.out.println(person.getName() + "执行了成功的操作1");
	}

	@Override
	public void Operation2(Person person) {
		System.out.println(person.getName() + "执行了成功的操作2");
		
	}
	
}


/**
 * 
 * @ClassName Fail
 * @Description 失败的操作实现
 * @author fuling
 * @date 2020年11月6日 下午10:04:39
 */
class Fail implements Action{

	@Override
	public void Operation1(Person person) {
		System.out.println(person.getName() + "执行了失败的操作1");
		
	}

	@Override
	public void Operation2(Person person) {
		System.out.println(person.getName() + "执行了失败的操作2");
		
	}
	
}


/**
 * 
 * @ClassName Person
 * @Description 被访问者的接口定义，定义了可以被访问者访问的入口accept
 * @author fuling
 * @date 2020年11月6日 下午10:04:54
 */
interface Person{
	//可以被访问者访问的入口
	void accept(Action action);
	
	//封装的操作1
	void Operation1();
	
	//封装的操作2
	void Operation2();
	
	String getName();
}

/**
 * 
 * @ClassName Man
 * @Description 男
 * @author fuling
 * @date 2020年11月6日 下午10:11:28
 */
class Man implements Person{
	
	Action action;
	String name = "man";

	@Override
	public void accept(Action action) {
		this.action = action;
		
	}

	@Override
	public void Operation1() {
		this.action.Operation1(this);
		
	}

	@Override
	public void Operation2() {
		this.action.Operation2(this);
		
	}

	@Override
	public String getName() {
		
		return name;
	}
	
}


/**
 * 
 * @ClassName Woman
 * @Description 女
 * @author fuling
 * @date 2020年11月6日 下午10:12:24
 */
class Woman implements Person{
	Action action;
	String name = "woman";

	@Override
	public void accept(Action action) {
		this.action = action;
		
	}

	@Override
	public void Operation1() {
		this.action.Operation1(this);
		
	}

	@Override
	public void Operation2() {
		this.action.Operation2(this);
		
	}

	@Override
	public String getName() {
		
		return name;
	}
}


/**
 * 
 * @ClassName ObjectStructure
 * @Description 持有数据结构的类，维护所有的Person，聚合某个Action实例对数据结构进行操作
 * @author fuling
 * @date 2020年11月6日 下午10:12:33
 */
class ObjectStructure{
	
	//维护所有的Person
	List<Person> persons = new ArrayList<>();
	
	//聚合了操作接口
	Action action;
	
	//接收访问者，允许访问者（操作接口实例）访问
	void accept(Action action){
		this.action = action;
	}
	
	void Operation1(){
		for(Person person: persons) {
			person.Operation1();
		}
	}
	
	void Operation2(){
		for(Person person: persons) {
			person.Operation2();
		}
	}
	
	void addPerson(Person person){
		person.accept(action);
		persons.add(person);
	}
	
	void removePerson(Person person) {
		persons.remove(person);
	}
}
