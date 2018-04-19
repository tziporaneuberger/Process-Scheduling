package AssignmentTwo;

public class SimProcess implements IProcess {
	IRandomValueGenerator rvg;
	int pid;
	String procName;
	int totalInstructions;

	public SimProcess(IRandomValueGenerator rvg, int pid, String procName, int totalInstructions) {
		this.rvg = rvg;
		this.pid = pid;
		this.procName = procName;
		this.totalInstructions = totalInstructions;
	}

	public int getPid() {
		return pid;
	}

	public String getProcName() {
		return procName;
	}

	public ProcessState execute(int i) {
		ProcessState ps;
		System.out.println("pid: " + pid + " process " + procName + " Executing Instruction Number: " + i );
		if (i >= totalInstructions) {
			ps = ProcessState.FINISHED;
		} else if (rvg.getTrueWithProbability(.15)) {
			ps = ProcessState.BLOCKED;
		} else {
			ps = ProcessState.READY;
		}
		return ps;

	}

}
