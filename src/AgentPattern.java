import java.lang.reflect.Method;
import java.lang.invoke.MethodHandleInfo;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/**
 * 
 * @ClassName AgentPattern
 * @Description 代理模式--cglib代理
 * @author fuling
 * @date 2020年11月9日 下午9:15:35
 */
public class AgentPattern {
	public static void main(String[] args) {
		//创建目标对象
		Dao dao = new Dao();
		
		//创建代理对象工厂
		ProxyFactory proxyFactory = new ProxyFactory(dao);
		//获取代理对象
		Dao myDao = (Dao) proxyFactory.getProxyInstance();
		
		//执行代理对象的方法
		myDao.Operation();
	}
}



/**
 * 
 * @ClassName Dao
 * @Description 被代理类
 * @author fuling
 * @date 2020年11月9日 下午9:17:56
 */
class Dao{

	public void Operation() {
		System.out.println("目标对象执行操作");
	
	}
	
}

/**
 * 
 * @ClassName ProxyFactory
 * @Description 代理类，聚合了目标对象，实现了MethodInterceptor接口，具备拦截方法的能力
 * @author fuling
 * @date 2020年11月9日 下午9:20:11
 */
class ProxyFactory implements MethodInterceptor{
	//聚合目标对象
	Object target;
	
	ProxyFactory(Object target){
		this.target = target;
	}
	
	
	//获取代理对象
	Object getProxyInstance(){
		Enhancer enhancer = new Enhancer();
		//设置子类的父类型
		enhancer.setSuperclass(target.getClass());
		//设置实现拦截方法接口（MethodInterceptor）的对象
		enhancer.setCallback(this);
		//生成代理对象
		return enhancer.create();
	}

	
	//拦截目标对象的方法调用
	@Override
	public Object intercept(Object arg0, Method method, Object[] args, MethodProxy arg3) throws Throwable {
		System.out.println("cglib开始代理");
		Object result = method.invoke(target, args);
		System.out.println("cglib代理结束");
		return result;
	}

	
	
}


