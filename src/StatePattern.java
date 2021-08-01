import java.util.Random;

/**
 * 
 * @ClassName StatePattern
 * @Description 状态模式
 * @author fuling
 * @date 2020年11月3日 下午3:09:44
 */
public class StatePattern {
	public static void main(String[] args) {
		Context context = new Context(1);
		for(int i = 0; i < 20; i++) {
			System.out.println("=========================");
			context.deductPoints();
			context.luckyDraw();
		}
	}
}

class Context implements State{
	
	//组合所有的状态类
	static State CANNOT_LUCKY_DRAW_STATE;//不能抽奖状态
	static State CAN_LUCKY_DRAW_STATE;//可以抽奖状态
	static State GIVE_OUT_PRIZE_STATE;//发放奖品状态
	static State PRIZE_EMPTY_STATE;//奖品领完状态
	
	//当前拥有的奖品数量
	int count;
	//当前状态
	State currentState;
	Context(int count){
		this.count = count;
		CANNOT_LUCKY_DRAW_STATE = new CannotLuckyDrawState(this);
		CAN_LUCKY_DRAW_STATE = new CanLuckyDrawState(this);
		GIVE_OUT_PRIZE_STATE = new GiveOutPrizeState(this);
		PRIZE_EMPTY_STATE = new PrizeEmptyState(this);
		
		//如果当前奖品数量大于0，则初始化当前状态为不能抽奖状态,否则为奖品领完状态
		if(count > 0)currentState = CANNOT_LUCKY_DRAW_STATE;
		else currentState = PRIZE_EMPTY_STATE;
	}
	@Override
	public void deductPoints() {
		this.currentState.deductPoints();
		
	}
	@Override
	public boolean luckyDraw() {
		if(this.currentState.luckyDraw()) {
			giveOutPrize();
			return true;
		}
		return false;
	}
	@Override
	public void giveOutPrize() {
		this.currentState.giveOutPrize();
		
	}
}

interface State{
	
	RuntimeException EXCEPTION = new UnsupportedOperationException();
	
	//定义三个活动
	void deductPoints();//扣除积分
	boolean luckyDraw();//抽奖
	void giveOutPrize();//领取奖品
}

/**
 * 
 * @ClassName CannotLuckyDrawState
 * @Description 不能抽奖状态
 * @author fuling
 * @date 2020年11月3日 下午3:57:58
 */
class CannotLuckyDrawState implements State{
	
	Context context;
	
	CannotLuckyDrawState(Context context){
		this.context = context;
	}

	@Override
	public void deductPoints() {
		System.out.println("成功扣除50积分!");
		//当前状态变为可以抽奖状态
		this.context.currentState = Context.CAN_LUCKY_DRAW_STATE;
	}

	@Override
	public boolean luckyDraw() {
		throw EXCEPTION;
	}

	@Override
	public void giveOutPrize() {
		throw EXCEPTION;
		
	}


	
	
}

/**
 * 
 * @ClassName CanLuckyDrawState
 * @Description 可以抽奖状态
 * @author fuling
 * @date 2020年11月3日 下午3:58:12
 */
class CanLuckyDrawState implements State{
	
	Context context;
	
	CanLuckyDrawState(Context context){
		this.context = context;
	}

	@Override
	public void deductPoints() {
		throw EXCEPTION;
		
	}

	@Override
	public boolean luckyDraw() {
		int random = new Random().nextInt(10);
		if(random != 9) {
			System.out.println("很遗憾，没有中奖！");
			this.context.currentState = Context.CANNOT_LUCKY_DRAW_STATE;
			return false;
		}else {
			System.out.println("中奖了！");
			this.context.currentState = Context.GIVE_OUT_PRIZE_STATE;
			return true;
		}
		
	}

	@Override
	public void giveOutPrize() {
		throw EXCEPTION;
		
	}

	
	
}

/**
 * 
 * @ClassName GiveOutPrizeState
 * @Description 领取奖品状态
 * @author fuling
 * @date 2020年11月3日 下午3:58:24
 */
class GiveOutPrizeState implements State{
	
	Context context;
	
	GiveOutPrizeState(Context context){
		this.context = context;
	}

	@Override
	public void deductPoints() {
		throw EXCEPTION;
		
	}

	@Override
	public boolean luckyDraw() {
		throw EXCEPTION;
	}

	@Override
	public void giveOutPrize() {
		//礼物数量减一
		this.context.count--;
		System.out.println("成功领取到奖品！");
		
		//如果领取完奖品后还有奖品，则将当前状态设置为不可抽奖状态，否则设置为奖品领完状态
		if(this.context.count > 0) {
			this.context.currentState = Context.CANNOT_LUCKY_DRAW_STATE;
		}else {
			this.context.currentState = Context.PRIZE_EMPTY_STATE;
		}
		
	}

	

}


/**
 * 
 * @ClassName PrizeEmptyState
 * @Description 奖品领完状态
 * @author fuling
 * @date 2020年11月3日 下午3:58:40
 */
class PrizeEmptyState implements State{
	
	Context context;
	
	PrizeEmptyState(Context context){
		this.context = context;;
	}

	@Override
	public void deductPoints() {
		System.out.println("奖品已经领完了");
		
	}

	@Override
	public boolean luckyDraw() {
		System.out.println("奖品已经领完了");
		return false;
	}

	@Override
	public void giveOutPrize() {
		
		System.out.println("奖品已经领完了");
	}

	
	
	
}
