package application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.Assert;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

class TestCase {

	@Test
	@RepeatedTest(10)
	void test() {
		Concurrency main = new Concurrency();
		Random random = new Random();
		int numOfThreads = random.nextInt(8) + 1;
		main.concurrentAdd(numOfThreads);
		System.out.println(main.getSumMulti());
		System.out.println(main.getSumSingle());
		Assert.assertEquals(main.getSumMulti(), main.getSumSingle());
	}

}
