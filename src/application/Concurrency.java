package application;

import java.util.Random;

public class Concurrency {
	
	class Adder extends Thread {
		
		private int[] data;
		private int start, end, total;
		
		public Adder(int[] data, int start, int end) {
			this.data = data;
			this.start = start;
			this.end = Math.min(end, data.length);
		}
		
		public void run() {
			total = sum(data, start, end);
		}
		
		public int sum(int[] data, int start, int end) {
			int sumt = 0;
			for (int i = start; i < end; i++) {
				sumt += data[i];
			}
			
			return sumt;
		}
		
		public int getTotal() {
			return this.total;
		}
	}
	private int[] data = new int[200000000];
	
	private int sumMulti = 0;
	private int index = 0;
	private int sumSingle = 0;

	public void concurrentAdd(int numberOfThreads) {
		Random random = new Random();
		for(int i = 0; i < 200000000; i++) {
			data[i] = random.nextInt(11) + 0;
		}
		
		System.out.println("=======================\n     Single Thread\n=======================\n");
		long startTime = System.currentTimeMillis();
		for(int add : data) {
			sumSingle += add;
		}
		
		long endTime = System.currentTimeMillis() - startTime;
		System.out.println("Sum: " + sumSingle + "\nTime: " + endTime + " ms\n");
		
		System.out.println("=======================\n     Multi Thread\n=======================\n");
		
		startTime = System.currentTimeMillis();
		Adder[] threads = new Adder[numberOfThreads];
		int range = (int) Math.ceil(data.length * 1.0 / numberOfThreads);
		for(int i = 0; i < numberOfThreads; i++) {
			threads[i] = new Adder(data, i * range, (i + 1) * range);
			threads[i].start();
		}
		try {
			for(Adder t : threads) {
				t.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
		
		for(Adder t : threads) {
			sumMulti += t.getTotal();
		}
		
		endTime = System.currentTimeMillis() - startTime;
		System.out.println("Sum: " + sumMulti + "\nTime: " + endTime + " ms\n");
	}

	public int getSumMulti() {
		return sumMulti;
	}

	public void setSumMulti(int sum) {
		sumMulti = sum;
	}
	
	public int getSumSingle() {
		return sumSingle;
	}

	public void setSumSingle(int sum) {
		sumSingle = sum;
	}

}
