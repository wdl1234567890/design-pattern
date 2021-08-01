import java.util.Arrays;

/**
 * 
 * @ClassName StrategyPattern
 * @Description 策略模式
 * @author fuling
 * @date 2020年11月2日 下午9:58:05
 */
public class StrategyPattern {
	public static void main(String[] args) {
		
		Duck waterDuck = new WaterDuck();
		Duck beijingDuck = new BeijingDuck();
		Duck toyDuck = new ToyDuck();
		
		waterDuck.makeSound();
		waterDuck.fly();
		waterDuck.swim();
		
		beijingDuck.makeSound();
		beijingDuck.fly();
		beijingDuck.swim();
		
		toyDuck.makeSound();
		toyDuck.fly();
		toyDuck.swim();
	}
}

interface MakeSound{
	void makeSound(String name);
}

interface Fly{
	void fly(String name);
}

interface Swim{
	void swim(String name);
}

class NoMakeSound implements MakeSound {

	@Override
	public void makeSound(String name) {
		System.out.println(name + "不会叫");
		
	}	
}
class GaGaSound implements MakeSound{

	@Override
	public void makeSound(String name) {
		System.out.println(name + "嘎嘎叫");
	}
}

class KaKaSound implements MakeSound{

	@Override
	public void makeSound(String name) {
		System.out.println(name + "咔咔叫");
	}
}


class NoFly implements Fly{

	@Override
	public void fly(String name) {
		System.out.println(name + "不会飞");
		
	}
}

class CanFly implements Fly{

	@Override
	public void fly(String name) {
		System.out.println(name + "正在飞");
		
	}
}

class NoSwim implements Swim{

	@Override
	public void swim(String name) {
		System.out.println(name + "不会游泳");
		
	}
}

class CanSwim implements Swim{

	@Override
	public void swim(String name) {
		System.out.println(name + "正在游泳");
		
	}
}

class Duck{
	String name;
	MakeSound makeSound;
	Fly fly;
	Swim swim;
	
	Duck(String name){
		this.name = name;
	}
	
	void makeSound() {
		makeSound.makeSound(name);
	}
	
	void fly() {
		fly.fly(name);
	}
	
	void swim() {
		swim.swim(name);
	}
}

class WaterDuck extends Duck{

	WaterDuck() {
		super("水鸭");
		this.makeSound = new GaGaSound();
		this.fly = new NoFly();
		this.swim = new CanSwim();
	}
	
}

class BeijingDuck extends Duck{

	BeijingDuck() {
		super("北京烤鸭");
		this.makeSound = new NoMakeSound();
		this.fly = new NoFly();
		this.swim = new NoSwim();
	}
}

class ToyDuck extends Duck{

	ToyDuck() {
		super("玩具鸭");
		this.makeSound = new KaKaSound();
		this.fly = new CanFly();
		this.swim = new CanSwim();
	}
}
