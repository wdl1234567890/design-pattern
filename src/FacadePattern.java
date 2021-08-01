/**
 * 
 * @ClassName FacadePattern
 * @Description 外观模式
 * @author fuling
 * @date 2020年11月15日 上午10:57:09
 */
public class FacadePattern {
	public static void main(String[] args) {
		//创建一个家庭影院遥控器外观类
		HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();
		System.out.println("观影前准备");
		homeTheaterFacade.ready();
		System.out.println("=============================");
		System.out.println("开始观影");
		homeTheaterFacade.play();
		System.out.println("=============================");
		System.out.println("暂停观影");
		homeTheaterFacade.pause();
		System.out.println("=============================");
		System.out.println("结束观影");
		homeTheaterFacade.end();
	}
}

/**
 * 
 * @ClassName HomeTheaterFacade
 * @Description 外观类（家庭影院遥控器）
 * @author fuling
 * @date 2020年11月15日 上午10:57:57
 */
class HomeTheaterFacade{
	
	//组合了家庭影院的各种设备
	DVDPlayer dvdPlayer = new DVDPlayer();
	Projector projector = new Projector();
	Stereo stereo = new Stereo();
	Popcorn popcorn = new Popcorn();
	Screen screen = new Screen();
	Light light = new Light();
	
	
	//观影前的准备工作
    void ready() {
    	//开爆米花机
    	popcorn.on();
    	//放下屏幕
    	screen.down();
    	//开投影仪
    	projector.on();
    	//开音响
    	stereo.on();
    	//开DVD，选dvd
    	dvdPlayer.on();
    	dvdPlayer.setDVD();
    	//去拿爆米花
    	popcorn.pop();
    	//调暗灯光
    	light.dim();
    }
    
    //观影结束的后的处理
    void end() {
    	//调亮灯光
    	light.bright();
    	//关掉DVD
    	dvdPlayer.off();
    	//关掉音响
    	stereo.off();
    	//关闭投影仪
    	projector.off();
    	//上升屏幕
    	screen.up();
    	//关闭爆米花机
    	popcorn.off();
    }
    
    //开始观影
    void play() {
    	dvdPlayer.play();
    }
    
    //暂停观影
    void pause() {
    	dvdPlayer.pause();
    }
}

/**
 * 
 * @ClassName DVDPlayer
 * @Description DVD播放器
 * @author fuling
 * @date 2020年11月15日 上午11:03:18
 */
class DVDPlayer{
	//打开DVD播放器
	void on() {
		System.out.println("打开DVD播放器");
	}
	
	//关闭DVD播放器
	void off() {
		System.out.println("关闭DVD播放器");
	}
	
	//开始播放
	void play() {
		System.out.println("开始播放");
	}
	
	
	//暂停播放
	void pause() {
		System.out.println("暂停播放");
	}
	
	
	//选择dvd
	void setDVD() {
		System.out.println("选择dvd");
	}
}

/**
 * 
 * @ClassName Projector
 * @Description 投影仪
 * @author fuling
 * @date 2020年11月15日 上午11:07:02
 */
class Projector{
	
	//打开投影仪
	void on() {
		System.out.println("打开投影仪");
	}
	
	//关闭投影仪
	void off() {
		System.out.println("关闭投影仪");
	}
	
	//聚焦投影仪
	void focus() {
		System.out.println("聚焦投影仪");
	}
	
	//移动投影仪位置
	void zoom() {
		System.out.println("移动投影仪位置");
	}
}


/**
 * 
 * @ClassName Stereo
 * @Description 环绕立体声系统
 * @author fuling
 * @date 2020年11月15日 上午11:09:02
 */
class Stereo{
	
	//打开环绕立体声
	void on() {
		System.out.println("打开环绕立体声");
	}
	
	//关闭环绕立体声
	void off() {
		System.out.println("关闭环绕立体声");
	}
	
	//调节声音
	void setVolume(int volume) {
		System.out.println("调节声音到" + volume);
	}
}

/**
 * 
 * @ClassName Popcorn
 * @Description 爆米花机
 * @author fuling
 * @date 2020年11月15日 上午11:11:15
 */
class Popcorn{
	
	//打开爆米花机
	void on() {
		System.out.println("打开爆米花机");
	}
	
	//关闭爆米花机
	void off() {
		System.out.println("关闭爆米花机");
	}
	
	//做爆米花
	void pop(){
		System.out.println("拿爆米花");
	}
}


/**
 * 
 * @ClassName Screen
 * @Description 自动屏幕
 * @author fuling
 * @date 2020年11月15日 上午11:18:12
 */
class Screen{
	//上升屏幕
	void up() {
		System.out.println("上升屏幕");
	}
	
	//放下屏幕
	void down() {
		System.out.println("放下屏幕");
	}
}


/**
 * 
 * @ClassName Light
 * @Description 影院灯光
 * @author fuling
 * @date 2020年11月15日 上午11:20:19
 */
class Light{
	
	//开灯
	void on() {
		System.out.println("开灯");
	}
	
	//关灯
	void off() {
		System.out.println("关灯");
	}
	
	
	//调暗灯光
	void dim() {
		System.out.println("调暗灯光");
	}
	
	
	//调亮灯光
	void bright() {
		System.out.println("调亮灯光");
	}
}