/**
 * 
 * @ClassName TemplateMethodPattern
 * @Description 模板模式
 * @author fuling
 * @date 2020年11月8日 下午4:10:24
 */
public class TemplateMethodPattern {
	public static void main(String[] args) {
		
		
		
		SoyaMilk soyaMilk = new PureSoyaMilk();
		soyaMilk.make();
	}
}

/**
 * 
 * @ClassName SoyaMilk
 * @Description 豆浆抽象类，实现了模板方法make，定义了整个做豆浆的流程框架
 * @author fuling
 * @date 2020年11月8日 下午4:13:13
 */
abstract class SoyaMilk{
	
	//模板方法，定义了做豆浆的流程
	final void make() {
		select();
		if(isAdd())add();
		soak();
		beat();
	}
	
	//挑选材料
	protected void select() {
		System.out.println("挑选上好的黄豆");
	}
	
	//添加配料（每种豆浆添加不同的配料，该方法交给子类实现）
	protected abstract void add();
	
	
	//浸泡
	protected void soak() {
		System.out.println("浸泡黄豆");
	}
	
	//放到豆浆机打碎
	protected void beat() {
		if(isAdd())System.out.println("将黄豆和其他配料一起放进豆浆机打碎");
		else System.out.println("将黄豆放进豆浆机打碎");
	}
	
	//钩子方法，默认需要添加配料，子类可以覆写
	boolean isAdd() {
		return true;
	}
	
}

class BlackBeanSoyaMilk extends SoyaMilk{

	@Override
	protected void add() {
		System.out.println("加入黑豆");
		
	}
	
}

class PeanutSoyaMilk extends SoyaMilk{

	@Override
	protected void add() {
		System.out.println("加入坚果");
		
	}
	
}
/**
 * 
 * @ClassName PureSoyaMilk
 * @Description 纯豆浆不加配料
 * @author fuling
 * @date 2020年11月8日 下午4:57:56
 */
class PureSoyaMilk extends SoyaMilk{

	@Override
	protected void add() {
		
		
	}
	
	@Override
	boolean isAdd() {
		
		return false;
	}
	
}
