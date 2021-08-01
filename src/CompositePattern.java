import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName CompositePattern
 * @Description 组合模式
 * @author fuling
 * @date 2020年11月15日 下午5:02:29
 */
public class CompositePattern {
	public static void main(String[] args) {
		University university = new University("xxx大学");
		College1 college1 = new College1("计算机学院");
		College1 college2 = new College1("医学院");
		
		college1.add(new Department1("软件工程"));
		college1.add(new Department1("网络工程"));
		college2.add(new Department1("动物医学"));
		college2.add(new Department1("临床医学"));
		
		university.add(college1);
		university.add(college2);
		
		university.print();
	}
}

/**
 * 
 * @ClassName OrganizationComponent
 * @Description 组织的统一接口
 * @author fuling
 * @date 2020年11月15日 下午5:45:45
 */
interface OrganizationComponent{
	
	//定义非叶子添加组织的接口
	default void add(OrganizationComponent organizationComponent) {
		throw new UnsupportedOperationException();
	}
	
	//定义非叶子删除组织的接口
	default void remove(OrganizationComponent organizationComponent) {
		throw new UnsupportedOperationException();
	}
	
	//展示当前组织节点和其下的组织子节点
	void print();
}

/**
 * 
 * @ClassName Department1
 * @Description 系（叶子结点）
 * @author fuling
 * @date 2020年11月15日 下午5:48:36
 */
class Department1 implements OrganizationComponent{
	
	//系的名称
	String name;
	
	Department1(String name){
		this.name = name;
	}

	//实现打印方法，打印当前组织节点信息
	@Override
	public void print() {
		System.out.println(name);	
	}
	
	
}


/**
 * 
 * @ClassName College1
 * @Description 院（非叶子结点），聚合了许多的系
 * @author fuling
 * @date 2020年11月15日 下午5:50:50
 */
class College1 implements OrganizationComponent{

	//院的名称
	String name;
	
	//聚合了系
	List<OrganizationComponent> childs = new ArrayList<>();
	
	College1(String name){
		this.name = name;
	}
	
	//重写添加子节点（系）的方法
	public void add(OrganizationComponent organizationComponent) {
		childs.add(organizationComponent);
	}
	
	//重写删除子节点（系）的方法
	public void remove(OrganizationComponent organizationComponent) {
		childs.remove(organizationComponent);
	}
	
	
	//实现打印方法，打印当前组织节点信息和其下组织子节点的信息
	@Override
	public void print() {
		
		System.out.println("------" + name + "-------");
		for(OrganizationComponent child: childs) {
			child.print();
		}
	}
	
}

/**
 * 
 * @ClassName University
 * @Description 学校（非叶子结点），聚合了院
 * @author fuling
 * @date 2020年11月15日 下午5:51:56
 */
class University implements OrganizationComponent{
	
	//学校的名称
	String name;
	
	//聚合了院
	List<OrganizationComponent> childs = new ArrayList<>();
	
	University(String name){
		this.name = name;
	}
	
	//重写添加子节点（院）的方法
	public void add(OrganizationComponent organizationComponent) {
		childs.add(organizationComponent);
	}
	
	//重写删除子节点（院）的方法
	public void remove(OrganizationComponent organizationComponent) {
		childs.remove(organizationComponent);
	}
	
	
	//实现打印方法，打印当前组织节点信息和其下组织子节点的信息
	@Override
	public void print() {
		
		System.out.println("=======" + name + "=======");
		for(OrganizationComponent child: childs) {
			child.print();
		}
	}
}
