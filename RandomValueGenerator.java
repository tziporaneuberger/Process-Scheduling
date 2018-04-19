package AssignmentTwo;

import java.util.Random;

public class RandomValueGenerator implements IRandomValueGenerator {
	Random rand = new Random();
	public int getNextInt() {
	int  n = rand.nextInt();
	return n;
	}
	public boolean getTrueWithProbability(double p) {
		double r =rand.nextDouble();
		return (p <r? false:true);
	}
	

}
