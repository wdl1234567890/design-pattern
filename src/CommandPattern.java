import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName CommandPattern
 * @Description 命令模式
 * @author fuling
 * @date 2020年11月7日 下午1:11:19
 */
public class CommandPattern {
	public static void main(String[] args) {
		
		//创建一个app调用者
		RemoteController remoteController = new RemoteController();
		//为app设置一组电灯的命令（开和关）
		remoteController.setCommand(0, new LightOnCommand(), new LightOffCommand());
		//发出开电灯的命令
		remoteController.pushOn(0);
	}
}

/**
 * 
 * @ClassName Command
 * @Description 命令接口，定义了两个统一的对外接口方法
 * @author fuling
 * @date 2020年11月7日 下午1:14:16
 */
interface Command{
	
	//执行命令
	void execute();
	
	//撤销命令
	void undo();
}

/**
 * 
 * @ClassName LightReceiver
 * @Description 电灯类
 * @author fuling
 * @date 2020年11月7日 下午1:13:35
 */
class LightReceiver{
	
	//开灯操作
	void on() {
		System.out.println("开灯");
	}
	
	//关灯操作
	void off() {
		System.out.println("关灯");
	}
}


/**
 * 
 * @ClassName LightOnCommand
 * @Description 开灯命令，将开灯命令和电灯绑定
 * @author fuling
 * @date 2020年11月7日 下午1:22:44
 */
class LightOnCommand implements Command{
	
	LightReceiver lightReceiver = new LightReceiver();

	@Override
	public void execute() {
		lightReceiver.on();
		
	}

	@Override
	public void undo() {
		lightReceiver.off();
		
	}
}

/**
 * 
 * @ClassName LightOffCommand
 * @Description 关灯命令，将关灯命令和电灯绑定
 * @author fuling
 * @date 2020年11月7日 下午1:24:45
 */
class LightOffCommand implements Command{
	
	LightReceiver lightReceiver = new LightReceiver();

	@Override
	public void execute() {
		lightReceiver.off();
		
	}

	@Override
	public void undo() {
		lightReceiver.on();
		
	}

}
/**
 * 
 * @ClassName NoCommand
 * @Description 空命令，作用是在调用者调用时可以省去空判断
 * @author fuling
 * @date 2020年11月7日 下午1:26:27
 */
class NoCommand implements Command{

	@Override
	public void execute() {
		System.out.println("这是一条空命令");
		
	}

	@Override
	public void undo() {
		System.out.println("这是一条空命令");
		
	}
	
}

/**
 * 
 * @ClassName RemoteController
 * @Description app调用者
 * @author fuling
 * @date 2020年11月7日 下午2:19:23
 */
class RemoteController{
	//开启命令的集合
	Command[] onCommands = new Command[5];
	//关闭命令的集合
	Command[] offCommands = new Command[5];
	
	//最近一次操作的命令，方便撤回
	Command currentCommand;
	
	RemoteController() {
		//初始化两个命令集合为空命令,这样就不需要在每次pushOn和pushOff时判断命令是否为空
		for(int i = 0; i < onCommands.length; i++) {
			onCommands[i] = new NoCommand();
		}
		for(int i = 0; i < offCommands.length; i++) {
			offCommands[i] = new NoCommand();
		}
	}
	
	//设置一组开关命令，index表示执行者的下标，onCommand是对应的开命令，offCommand是对应的关命令
	void setCommand(int index, Command onCommand, Command offCommand){
		onCommands[index] = onCommand;
		offCommands[index] = offCommand;
		
	}
	
	//按下某个执行者的开按钮（比如按下开灯按钮）
	void pushOn(int index) {
		//执行开操作
		onCommands[index].execute();
		//记录这次执行的命令
		currentCommand = onCommands[index];
	}
	
	void pushOff(int index) {
		offCommands[index].execute();
		currentCommand = offCommands[index];
	}
	
	//撤回操作
	void undo() {
		//调用最近一次执行的命令的撤回操作
		currentCommand.undo();
	}
}
