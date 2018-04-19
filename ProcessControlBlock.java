package AssignmentTwo;

public class ProcessControlBlock {
	IProcess proc;
	int currInstruction;
	int register1;
	int register2;
	int register3;
	int register4;
	public ProcessControlBlock( IProcess proc) {
		this.proc = proc;
	}
	
	public int getCurrInstruction() {
		return currInstruction;
	}
	

	public IProcess getProc() {
		return proc;
	}

	public int getRegister1() {
		return register1;
	}

	public int getRegister2() {
		return register2;
	}

	public int getRegister3() {
		return register3;
	}

	public int getRegister4() {
		return register4;
	}

	public void setRegister1(int register1) {
		this.register1 = register1;
	}

	public void setRegister2(int register2) {
		this.register2 = register2;
	}

	public void setRegister3(int register3) {
		this.register3 = register3;
	}

	public void setRegister4(int register4) {
		this.register4 = register4;
	}

	public void setCurrInstruction(int currInstruction) {
		this.currInstruction = currInstruction;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("current instruction: ");
		builder.append(currInstruction);
		builder.append(" R1 ");
		builder.append(register1);
		builder.append(" R2 ");
		builder.append(register2); 
		builder.append(" R3 ");
		builder.append(register3);
		builder.append(" R4 ");
		builder.append(register4);
		return builder.toString();}

}
