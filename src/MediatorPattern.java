import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName MediatorPattern
 * @Description 中介者模式
 * @author fuling
 * @date 2020年11月4日 下午2:17:48
 */
public class MediatorPattern {
	public static void main(String[] args) {
		//创建一个中介者
		Mediator mediator = new ConcreteMediator();
		
		//创建四个家具
		Alarm alarm = new Alarm(mediator);
		CoffeeMachine coffeeMachine = new CoffeeMachine(mediator);
		Curtain curtain = new Curtain(mediator);
		TV TV = new TV(mediator);
		
		//闹钟开启
		alarm.openAlarm();
	}
}

/**
 * 
 * @ClassName Mediator
 * @Description 中介者接口
 * @author fuling
 * @date 2020年11月4日 下午2:45:05
 */
interface Mediator{
	//接收消息并处理的中心方法
	void getMessage(String colleagueName, int sign);
	
	void addColleague(Colleague colleague);
}

interface Colleague{
	
	//发送消息到中介者
	void sendMessage(int sign);
	
	String getName();
}

class ConcreteMediator implements Mediator{
	
	//中介者维护所有的Colleague
	Map<String, Colleague> colleagueMap = new HashMap<>();

	public void addColleague(Colleague colleague){
		colleagueMap.put(colleague.getName(), colleague);
	}
	
	/**
	 * 
	 * @Title getMessage
	 * @Description 核心处理方法，该方法接收来自各个colleague实例发送的消息，并处理之后的业务逻辑
	 * @param @param colleagueName
	 * @param @param sign
	 * @see Mediator#getMessage(java.lang.String, int)
	 */
	@Override
	public void getMessage(String colleagueName, int sign) {
		if(colleagueName == "闹钟") {
			if(sign == 1) {
				((CoffeeMachine)colleagueMap.get("咖啡机")).startDoCoffee();
			}
		}else if(colleagueName == "咖啡机") {
			if(sign == 1) {
				((Curtain)colleagueMap.get("窗帘")).downCurtain();
			}
			
		}else if(colleagueName == "窗帘") {
			if(sign == 1) {
				((TV)colleagueMap.get("电视机")).openTV();
			}
			
		}else if(colleagueName == "电视机") {
			
		}else {
			System.out.println("消息有误！");
		}
		
	}
	
}

/**
 * 
 * @ClassName Alarm
 * @Description 闹钟
 * @author fuling
 * @date 2020年11月4日 下午3:00:20
 */
class Alarm implements Colleague{
	
	String name;
	
	//聚合中介者，方便发送消息给中介者
	Mediator mediator;
	
	Alarm(Mediator mediator){
		this.mediator = mediator;
		this.name = "闹钟";
		
		//将当前对象加入到中介者的维护map中
		mediator.addColleague(this);
	}
	
	public String getName() {return name;}

	/**
	 * 
	 * @Title openAlarm
	 * @Description 打开闹钟，消息为1
	 * @param  参数说明
	 * @return 返回说明
	 * @throws
	 */
	void openAlarm(){
		System.out.println("闹铃响起");
		sendMessage(1);
	}
	
	/**
	 * 
	 * @Title closeAlarm
	 * @Description 关闭闹钟，消息为0
	 * @param  参数说明
	 * @return 返回说明
	 * @throws
	 */
	void closeAlarm(){
		System.out.println("闹铃关闭");
		sendMessage(0);
	}
	
	/**
	 * 
	 * @Title sendMessage
	 * @Description 将消息发送给中介者，剩下的逻辑业务交由中介者处理
	 * @param @param sign
	 * @see Colleague#sendMessage(int)
	 */
	@Override
	public void sendMessage(int sign) {
		this.mediator.getMessage(name, sign);
		
	}
	
}

/**
 * 
 * @ClassName CoffeeMachine
 * @Description 咖啡机
 * @author fuling
 * @date 2020年11月4日 下午3:00:09
 */
class CoffeeMachine implements Colleague{
	
	
	String name;
	
	//聚合中介者，方便发送消息给中介者
	Mediator mediator;
	
	CoffeeMachine(Mediator mediator){
		this.mediator = mediator;
		this.name = "咖啡机";
		
		//将当前对象加入到中介者的维护map中
		mediator.addColleague(this);
	}
	
	void startDoCoffee(){
		System.out.println("开始做咖啡");
		sendMessage(1);
	}
	
	void stopDoCoffee() {
		System.out.println("停止做咖啡");
		sendMessage(0);
	}

	@Override
	public void sendMessage(int sign) {
		this.mediator.getMessage(name, sign);
		
	}

	@Override
	public String getName() {
		
		return name;
	}
	
}

/**
 * 
 * @ClassName Curtain
 * @Description 窗帘
 * @author fuling
 * @date 2020年11月4日 下午3:00:44
 */
class Curtain implements Colleague{
	
	
	String name;
	
	//聚合中介者，方便发送消息给中介者
	Mediator mediator;
	
	Curtain(Mediator mediator){
		this.mediator = mediator;
		this.name = "窗帘";
		
		//将当前对象加入到中介者的维护map中
		mediator.addColleague(this);
	}
	
	void downCurtain() {
		System.out.println("窗帘自动落下");
		sendMessage(1);
	}
	
	
	void upCurtain() {
		System.out.println("窗帘自动升起");
		sendMessage(0);
	}

	@Override
	public void sendMessage(int sign) {
		this.mediator.getMessage(name, sign);
		
	}

	@Override
	public String getName() {
		
		return name;
	}
	
}

/**
 * 
 * @ClassName TV
 * @Description 电视机
 * @author fuling
 * @date 2020年11月4日 下午3:02:35
 */
class TV implements Colleague{
	
	
	String name;
	
	//聚合中介者，方便发送消息给中介者
	Mediator mediator;
	
	TV(Mediator mediator){
		this.mediator = mediator;
		this.name = "电视机";
		
		//将当前对象加入到中介者的维护map中
		mediator.addColleague(this);
	}
	
	void openTV() {
		System.out.println("打开电视");
		sendMessage(1);
	}
	
	void closeTV() {
		System.out.println("关闭电视");
		sendMessage(0);
	}

	@Override
	public void sendMessage(int sign) {
		this.mediator.getMessage(name, sign);
		
	}

	@Override
	public String getName() {
		
		return name;
	}
	
}

