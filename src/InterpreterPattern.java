import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 
 * @ClassName InterpreterPattern
 * @Description 解释器模式
 * @author fuling
 * @date 2020年11月3日 下午9:11:20
 */
public class InterpreterPattern {
	public static void main(String[] args) {
		
		String exp = "a+b-c";
		Map<String, Integer> var = new HashMap<>();
		var.put("a", 1);
		var.put("b", 4);
		var.put("c", 2);
		Calculator calculator = new Calculator(exp);
		System.out.println(calculator.run(var));
	}
	
}

class Calculator{
	Expression expression;
	
	Calculator(String exp){
		
		Stack<Expression> stack = new Stack();
		char[] charArray = exp.toCharArray();
		Expression left;
		Expression right;
		for(int i = 0; i < charArray.length; i++) {
			switch(charArray[i]) {
			case '+':
				left = stack.pop();
				right = new VarExpression(String.valueOf(charArray[++i]));
				stack.push(new AddExpression(left,right));
				break;
			case '-':
				left = stack.pop();
				right = new VarExpression(String.valueOf(charArray[++i]));
				stack.push(new SubExpression(left,right));
				break;
			default:
				stack.push(new VarExpression(String.valueOf(charArray[i])));
				break;
					
			}
		}
		this.expression = stack.pop();
	}
	
	int run(Map<String, Integer> var) {
		return this.expression.interpreter(var);
	}
}

/**
 * 
 * @ClassName Expression
 * @Description 抽象表达式类
 * @author fuling
 * @date 2020年11月3日 下午9:17:56
 */
abstract class Expression{
	/**
	 * 
	 * @Title interpreter
	 * @Description 描述这个方法的作用
	 * @param @param var 存放终结符和数值的映射，比如{a=>1,b=>3}
	 * @param @return 参数说明
	 * @return 返回解释器解析后的结果
	 * @throws
	 */
	abstract int interpreter(Map<String, Integer> var);
}

/**
 * 
 * @ClassName VarExpression
 * @Description 终结符表达式
 * @author fuling
 * @date 2020年11月3日 下午9:27:15
 */
class VarExpression extends Expression{
	String key;

	VarExpression(String key){
		this.key = key;
	}
	
	/**
	 * 
	 * @Title interpreter
	 * @Description 终结符的解释方法：从键值对映射中取出值
	 * @param @param var
	 * @param @return
	 * @see Expression#interpreter(java.util.Map)
	 */
	@Override
	int interpreter(Map<String, Integer> var) {
		return var.get(key);
	}
	
}

/**
 * 
 * @ClassName SymbolExpression
 * @Description 非终结符表达式
 * @author fuling
 * @date 2020年11月3日 下午9:30:18
 */
abstract class SymbolExpression extends Expression{
	
	Expression left;//左操作表达式
	Expression right;//右操作表达式
	
	SymbolExpression(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}

	@Override
	abstract int interpreter(Map<String, Integer> var);
	
}

/**
 * 
 * @ClassName SubExpression
 * @Description “减”表达式
 * @author fuling
 * @date 2020年11月3日 下午9:30:33
 */
class SubExpression extends SymbolExpression{

	SubExpression(Expression left, Expression right) {
		super(left, right);
	}

	
	/**
	 * 
	 * @Title interpreter
	 * @Description 非终结符的解释方法：对两边的操作表达式执行减的操作
	 * @param @param var
	 * @param @return
	 * @see SymbolExpression#interpreter(java.util.Map)
	 */
	@Override
	int interpreter(Map<String, Integer> var) {
		return this.left.interpreter(var) - this.right.interpreter(var);
	}
	
}


/**
 * 
 * @ClassName AddExpression
 * @Description “加”表达式
 * @author fuling
 * @date 2020年11月3日 下午9:30:58
 */
class AddExpression extends SymbolExpression{

	AddExpression(Expression left, Expression right) {
		super(left, right);
	}

	/**
	 * 
	 * @Title interpreter
	 * @Description 非终结符的解释方法：对两边的操作表达式执行加的操作
	 * @param @param var
	 * @param @return
	 * @see SymbolExpression#interpreter(java.util.Map)
	 */
	@Override
	int interpreter(Map<String, Integer> var) {
		return this.left.interpreter(var) + this.right.interpreter(var);
	}
	
}
