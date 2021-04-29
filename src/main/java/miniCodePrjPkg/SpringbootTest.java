package miniCodePrjPkg;

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

	// @SpringApplicationConfiguration(classes = Application.class)// 1.4.0 ǰ�汾
	@Autowired
	Service1 svr1;

	@Test
	public void testOne() {
		System.out.println(svr1.m1());
	}

}
