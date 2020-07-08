package miniCodePrjPkg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class }) // 指定启动类
public class SpringbootTest {

	// @SpringApplicationConfiguration(classes = Application.class)// 1.4.0 前版本
	@Autowired
	Service1 svr1;

	@Test
	public void testOne() {
		System.out.println(svr1.m1());
	}

}
