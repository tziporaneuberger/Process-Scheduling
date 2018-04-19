package AssignmentTwo;

public class SimProcessor implements IProcessor {
	IRandomValueGenerator rvg;
	int currInstruction;
	int[] registers;
	IProcess cp;

	public SimProcessor(IRandomValueGenerator rvg) {
		this.rvg = rvg;
		registers = new int[4];
	}

	public int getRegisterValue() {
		return rvg.getNextInt();
	}

	public IProcess getIProcess() {
		return cp;
	}

	public void setIProcess(IProcess cp) {
		this.cp = cp;
	}

	public int getCurrentInstruction() {
		return currInstruction;
	}

	public void setCurrentInstruction(int currInstruction) {
		this.currInstruction = currInstruction;
	}

	public void setRegisterValue(int i, int val) {
		registers[i] = val;
	}

	public int getRegisterValue(int i) {
		return rvg.getNextInt();
	}

	public ProcessState executeNextInstruction() {
		ProcessState ps = cp.execute(currInstruction++);
		return ps;

	}

}
