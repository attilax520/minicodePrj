package sprbtPKg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * import org.springframework.boot.test.context.SpringBootTest; from sprigoot  tests
import org.springframework.test.context.junit4.SpringRunner; from  spring test
 * 
 * @author user
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class }) // ָ��������
public class SpringbootTest {
	
	public static void main(String[] args) {
		
	}

	// @SpringApplicationConfiguration(classes = Application.class)// 1.4.0 ǰ�汾
	@Autowired
	 HelloController hc;

	@Test
	public void testOne() {
		System.out.println(""+hc.hello());
	}

}
