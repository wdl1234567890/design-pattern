import java.util.Random;

/**
 * 
 * @ClassName ResponseChainPattern
 * @Description 职责链模式演示
 * @author fuling
 * @date 2020年11月2日 上午11:43:41
 */
public class ResponseChainPattern {
	public static void main(String[] args) {
	
		
		//创建一个请求
		Request request = new Request(2000);
		
		//创建四个处理者
		Handler handler1 = new DepartmentHandler();
		Handler handler2 = new CollegeHandler();
		Handler handler3 = new ViceSchoolMasterHandler();
		Handler handler4 = new SchoolMasterHandler();
		
		//将四个处理者链成一个环
		handler1.setHandler(handler2);
		handler2.setHandler(handler3);
		handler3.setHandler(handler4);
		handler4.setHandler(handler1);
		
		//将请求交给第一个处理者，开始“走链”
		handler1.process(request);
	}
}

/**
 * 
 * @ClassName Request
 * @Description 请求者
 * @author fuling
 * @date 2020年11月2日 上午11:50:38
 */
class Request{
	int id;
	float money;
	Request(float money){
		this.id = (new Random()).nextInt();
		this.money = money;
	}
}

/**
 * 
 * @ClassName Handler
 * @Description 处理者的抽象类定义
 * @author fuling
 * @date 2020年11月2日 上午11:49:22
 */
abstract class Handler{
	//关键点1！
	Handler handler;
	
	Handler(){}
	
	Handler(Handler handler){
		this.handler = handler;
	}
	
	void setHandler(Handler handler) {
		this.handler = handler;
	}
	
	//关键点2！
	public abstract void process(Request request);
}

/**
 * 
 * @ClassName DepartmentHandler
 * @Description 教学主任
 * @author fuling
 * @date 2020年11月2日 下午1:59:34
 */
class DepartmentHandler extends Handler{
	
	DepartmentHandler(){}

	DepartmentHandler(Handler handler){
		super(handler);
	}
	
	@Override
	public void process(Request request) {
		if(request.money > 0 && request.money <= 500) {
			System.out.println("第一个处理器处理了编号为" + request.id + "的请求");
		}else {
			this.handler.process(request);
		}
		
	}
	
}


/**
 * 
 * @ClassName CollegeHandler
 * @Description 院长
 * @author fuling
 * @date 2020年11月2日 下午1:59:34
 */
class CollegeHandler extends Handler{

	CollegeHandler(){}
	
	CollegeHandler(Handler handler){
		super(handler);
	}
	
	@Override
	public void process(Request request) {
		if(request.money > 500 && request.money <= 2000) {
			System.out.println("第二个处理器处理了编号为" + request.id + "的请求");
		}else {
			this.handler.process(request);
		}
		
	}
	
}


/**
 * 
 * @ClassName ViceSchoolMasterHandler
 * @Description 副校长
 * @author fuling
 * @date 2020年11月2日 下午1:59:34
 */
class ViceSchoolMasterHandler extends Handler{

	ViceSchoolMasterHandler(){}
	
	ViceSchoolMasterHandler(Handler handler){
		super(handler);
	}
	
	@Override
	public void process(Request request) {
		if(request.money > 2000 && request.money <= 5000) {
			System.out.println("第三个处理器处理了编号为" + request.id + "的请求");
		}else {
			this.handler.process(request);
		}
		
	}
	
}

/**
 * 
 * @ClassName SchoolMasterHandler
 * @Description 校长
 * @author fuling
 * @date 2020年11月2日 下午1:59:34
 */
class SchoolMasterHandler extends Handler{

	SchoolMasterHandler(){}
	
	SchoolMasterHandler(Handler handler){
		super(handler);
	}
	
	@Override
	public void process(Request request) {
		if(request.money > 5000) {
			System.out.println("第四个处理器处理了编号为" + request.id + "的请求");
		}else {
			this.handler.process(request);
		}
		
	}
	
}
