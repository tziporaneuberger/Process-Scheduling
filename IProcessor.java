package AssignmentTwo;

public interface IProcessor {
	public IProcess getIProcess();
	public void setIProcess(IProcess ps);
	public int getCurrentInstruction();
	public void setCurrentInstruction(int instruction);
	public ProcessState executeNextInstruction();
	public void setRegisterValue(int i, int val);
	public int getRegisterValue(int i);

}
