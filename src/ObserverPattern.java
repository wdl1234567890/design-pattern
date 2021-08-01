import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName ObserverPattern
 * @Description 观察者模式
 * @author fuling
 * @date 2020年11月5日 下午12:03:23
 */
public class ObserverPattern {
	public static void main(String[] args) {
		
		Subject subject = new WeatherData();
		Observer observer1 = new Baidu();
		Observer observer2 = new Sina();
		subject.registerObserver(observer1);
		subject.registerObserver(observer2);
		System.out.println("第一次百度获取到的天气消息");
		System.out.println(observer1);
		System.out.println("第一次新浪获取到的天气消息");
		System.out.println(observer2);
		
		System.out.println("=========================");
		
		System.out.println("气象台更新天气消息");
		subject.setData(30, 110, 32);
		System.out.println("百度获取到的天气消息");
		System.out.println(observer1);
		System.out.println("新浪获取到的天气消息");
		System.out.println(observer2);
	}
}

/**
 * 
 * @ClassName Subject
 * @Description 描述这个类的作用
 * @author fuling
 * @date 2020年11月5日 下午12:05:19
 */
interface Subject{
	void registerObserver(Observer observer);
	void removeObserver(Observer observer);
	void notifyObservers();
	void setData(float temperature, float pressure, float humidty);
}

/**
 * 
 * @ClassName Observer
 * @Description 观察者
 * @author fuling
 * @date 2020年11月5日 下午12:05:28
 */
interface Observer{
	void update(float temperature, float pressure, float humidty);
}


/**
 * 
 * @ClassName WeatherData
 * @Description 气象台（管理已经注册的观察者，存储天气数据）
 * @author fuling
 * @date 2020年11月5日 下午1:39:22
 */
class WeatherData implements Subject{
	
	float temperature;//温度
	float pressure;//气压
	float humidty;//湿度
	
	//管理注册到气象台的观察者
	List<Observer> observers;
	
	WeatherData(){
		this.temperature = 28;
		this.pressure = 100;
		this.humidty = 30;
	}
	
	/**
	 * 
	 * @Title setData
	 * @Description 天气数据改变，及时推送通知给观察者们
	 * @param @param temperature
	 * @param @param pressure
	 * @param @param humidty 参数说明
	 * @return 返回说明
	 * @throws
	 */
	public void setData(float temperature, float pressure, float humidty){
		this.temperature = temperature;
		this.pressure = pressure;
		this.humidty = humidty;
		notifyObservers();
	}

	@Override
	public void registerObserver(Observer observer) {
		if(observers == null)observers = new ArrayList<>();
		if(observer != null) {
			observers.add(observer);
			observer.update(temperature, pressure, humidty);
		}
	}

	@Override
	public void removeObserver(Observer observer) {
		if(observers == null)return;
		if(observers != null)observers.remove(observers);
		
	}

	
	/**
	 * 
	 * @Title notifyObservers
	 * @Description 向所有的已注册观察者推送通知
	 * @param 
	 * @see Subject#notifyObservers()
	 */
	@Override
	public void notifyObservers() {
		for(Observer observer: observers) {
			observer.update(temperature, pressure, humidty);
		}
		
	}
	
}


/**
 * 
 * @ClassName Baidu
 * @Description 第三方观察者“百度”
 * @author fuling
 * @date 2020年11月5日 下午1:42:49
 */
class Baidu implements Observer{
	
	float temperature;//温度
	float pressure;//气压
	float humidty;//湿度

	
	/**
	 * 
	 * @Title update
	 * @Description 气象台推送信息给观察者的入口
	 * @param @param temperature
	 * @param @param pressure
	 * @param @param humidty
	 * @see Observer#update(float, float, float)
	 */
	@Override
	public void update(float temperature, float pressure, float humidty) {
		this.temperature = temperature;
		this.pressure = pressure;
		this.humidty = humidty;		
	}
	
	@Override
	public String toString() {
		
		return "温度：" + temperature + ",气压：" + pressure + ",湿度：" + humidty;
	}
	
}


/**
 * 
 * @ClassName Sina
 * @Description 第三方观察者“新浪”
 * @author fuling
 * @date 2020年11月5日 下午1:43:14
 */
class Sina implements Observer{
	
	float temperature;//温度
	float pressure;//气压
	float humidty;//湿度

	@Override
	public void update(float temperature, float pressure, float humidty) {
		this.temperature = temperature;
		this.pressure = pressure;
		this.humidty = humidty;
		
	}
	
	@Override
	public String toString() {
		
		return "温度：" + temperature + ",气压：" + pressure + ",湿度：" + humidty;
	}
	
}
