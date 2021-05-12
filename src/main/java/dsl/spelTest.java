package dsl;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class spelTest {

	public static void main(String[] args) {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("New dsl.spelTest().m1()");
		Object message =   exp.getValue();
		System.out.println(message);

	}
	
	public void m1() {
		System.out.println("m1out");
	}

}
