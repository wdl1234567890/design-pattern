import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @ClassName IteratorPattern
 * @Description 迭代器模式
 * @author fuling
 * @date 2020年11月5日 下午9:08:00
 */
public class IteratorPattern {
	public static void main(String[] args) {
		
		//创建一个存储学院的集合
		List<College<Department>> colleges = new ArrayList<>();
		
		//创建一个计算机学院，并添加两个系
		ComputerCollege computerCollege = new ComputerCollege();
		computerCollege.addDepartment(new Department("网络工程"));
		computerCollege.addDepartment(new Department("软件工程"));
		
		//创建一个信息学院，并添加两个系
		InfoCollege infoCollege = new InfoCollege();
		infoCollege.addDepartment(new Department("信息系1"));
		infoCollege.addDepartment(new Department("信息系2"));
		
		//将以上两个学院添加到学院集合中
		colleges.add(computerCollege);
		colleges.add(infoCollege);
		
		//开始展示学院和系的信息
		OutputImpl outputImpl = new OutputImpl(colleges);
		outputImpl.show();
	}
}

/**
 * 
 * @ClassName OutputImpl
 * @Description 工具类
 * @author fuling
 * @date 2020年11月5日 下午9:39:37
 */
class OutputImpl{
	
	//维护所有的学院
	List<College<Department>> colleges;
	
	OutputImpl(List<College<Department>> colleges){
		this.colleges = colleges;
	}
	
	void show() {
		java.util.Iterator<College<Department>> iterator = colleges.iterator();
		while(iterator.hasNext()) {
			College<Department> college = iterator.next();
			System.out.println(college);
			System.out.println("=====================");
			showDepartment(college.createIterato());
			System.out.println("=====================");
		}
	}
	
	/**
	 * 
	 * @Title showDepartment
	 * @Description 展示系信息
	 * @param @param iterator 参数说明
	 * @return 返回说明
	 * @throws
	 */
	void showDepartment(Iterator<Department> iterator) {
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
}

/**
 * 
 * @ClassName College
 * @Description 聚合接口
 * @author fuling
 * @date 2020年11月5日 下午9:18:26
 */
interface College<E>{
	//获取迭代器
	Iterator<E> createIterato();
	
}

/**
 * 
 * @ClassName Iterator
 * @Description 迭代器接口
 * @author fuling
 * @date 2020年11月5日 下午9:19:39
 */
interface Iterator<E>{
	
	//判断是否有下一个元素
	boolean hasNext();
	
	//获取下一个元素
	E next();
	
	//删除下一个元素
	void remove();
}

/**
 * 
 * @ClassName ComputerCollege
 * @Description 计算机学院
 * @author fuling
 * @date 2020年11月5日 下午9:20:45
 */
class ComputerCollege implements College<Department>{
	
	String name;
	
	//计算机学院以集合的方式维护系
	List<Department> departments = new ArrayList<>();
	
	ComputerCollege(){
		name = "计算机学院";
	}
	
	void addDepartment(Department department){
		departments.add(department);
	}

	/**
	 * 
	 * @Title createIterato
	 * @Description ComputerCollegeIterator迭代器了解ComputerCollege对于系的存储结构，只需要将departments传给它，由它实现遍历的逻辑就行了
	 * @param @return
	 * @see College#createIterato()
	 */
	@Override
	public Iterator<Department> createIterato() {
		
		return new ComputerCollegeIterator(departments);
	}
	
	@Override
	public String toString() {
		
		return name;
	}
	
}

/**
 * 
 * @ClassName InfoCollege
 * @Description 信息学院
 * @author fuling
 * @date 2020年11月5日 下午9:21:04
 */
class InfoCollege implements College<Department>{
	
	String name;
	
	//信息学院以数组的方式维护系
	Department[] departments = new Department[10];
	
	//记录当前数组最后一个元素的下标
	int index = -1;
	
	InfoCollege() {
		name = "信息学院";
	}
	
	void addDepartment(Department department){
		departments[++index] = department;
	}

	
	/**
	 * 
	 * @Title createIterato
	 * @Description InfoCollegeIterator迭代器了解InfoCollege对于系的存储结构，只需要将departments传给它，由它实现遍历的逻辑就行了
	 * @param @return
	 * @see College#createIterato()
	 */
	@Override
	public Iterator<Department> createIterato() {
		
		return new InfoCollegeIterator(departments);
	}
	
	@Override
	public String toString() {
		
		return name;
	}
	
}


/**
 * 
 * @ClassName ComputerCollegeIterator
 * @Description 计算机学院迭代器
 * @author fuling
 * @date 2020年11月5日 下午9:21:15
 */
class ComputerCollegeIterator implements Iterator<Department>{
	
	//计算机学院以集合的方式维护系
	List<Department> departments;
	
	//记录下一个待迭代的元素下标
	int index;
	
	ComputerCollegeIterator(List<Department> departments){
		this.departments = departments;
	}

	@Override
	public boolean hasNext() {
		if(departments != null && departments.size() != 0 && departments.size() > index)return true;
		return false;
	}

	@Override
	public Department next() {
		if(!hasNext())return null;
		return departments.get(index++);
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
}

/**
 * 
 * @ClassName InfoCollegeIterator
 * @Description 信息学院迭代器
 * @author fuling
 * @date 2020年11月5日 下午9:22:14
 */
class InfoCollegeIterator implements Iterator<Department>{
	
	//信息学院以数组的方式维护系
	Department[] departments;
	
	//记录下一个待迭代的元素下标
	int index;
	
	InfoCollegeIterator(Department[] departments){
		this.departments = departments;
	}

	@Override
	public boolean hasNext() {
		if(departments != null && departments.length > index && departments[index] != null)return true;
		return false;
	}

	@Override
	public Department next() {
		if(!hasNext())return null;
		return departments[index++];
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
}

/**
 * 
 * @ClassName Department
 * @Description 系
 * @author fuling
 * @date 2020年11月5日 下午9:16:33
 */
class Department{
	String name;
	Department(String name){
		this.name = name;
	}
	@Override
	public String toString() {
		
		return name;
	}
}
