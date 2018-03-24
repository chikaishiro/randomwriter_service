package com.example.randomwriter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomwriterApplicationTests {

	Randomwriter randomWriter=new Randomwriter(1,"");
	@Test
	public void testConstruct(){
		Assert.assertNotNull(randomWriter);
	}

}
