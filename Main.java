package AssignmentTwo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) {

		IRandomValueGenerator rvg = new RandomValueGenerator();
		IProcessor processor = new SimProcessor(rvg);
		final int QUANTUM = 5;
		Queue<ProcessControlBlock> ready = new LinkedList<ProcessControlBlock>();
		Queue<ProcessControlBlock> blocked = new LinkedList<ProcessControlBlock>();

		ProcessControlBlock pcb1 = new ProcessControlBlock(new SimProcess(rvg, 1, "Process One", 38));
		ready.add(pcb1);
		ProcessControlBlock pcb2 = new ProcessControlBlock(new SimProcess(rvg, 2, "Process Two", 33));
		ready.add(pcb2);
		ProcessControlBlock pcb3 = new ProcessControlBlock(new SimProcess(rvg, 3, "Process Three", 40));
		ready.add(pcb3);
		ProcessControlBlock pcb4 = new ProcessControlBlock(new SimProcess(rvg, 4, "Process Four", 33));
		ready.add(pcb4);
		ProcessControlBlock pcb5 = new ProcessControlBlock(new SimProcess(rvg, 5, "Process Five", 20));
		ready.add(pcb5);
		ProcessControlBlock pcb6 = new ProcessControlBlock(new SimProcess(rvg, 6, "Process Six", 32));
		ready.add(pcb6);
		ProcessControlBlock pcb7 = new ProcessControlBlock(new SimProcess(rvg, 7, "Process Seven", 30));
		ready.add(pcb7);
		ProcessControlBlock pcb8 = new ProcessControlBlock(new SimProcess(rvg, 8, "Process Eight", 30));
		ready.add(pcb8);
		ProcessControlBlock pcb9 = new ProcessControlBlock(new SimProcess(rvg, 9, "Process Nine", 31));
		ready.add(pcb9);
		ProcessControlBlock pcb10 = new ProcessControlBlock(new SimProcess(rvg, 10, "Process Ten", 30));
		ready.add(pcb10);

		ProcessControlBlock pcb = ready.remove();
		processor.setIProcess(pcb.getProc());
		boolean cSwitch;
		int quantum = 0;
		for (int i = 1; i < 3000; i++) {

			ProcessState ps = processor.executeNextInstruction();
			cSwitch = true;
			quantum++;
			System.out.println("Step " + i + " :");

			if (ps == ProcessState.FINISHED) {
				System.out.println("**finished**");
				ready.remove(pcb);

				if (ready.isEmpty() && blocked.isEmpty()) {
					System.out.println("Processes finished.");
					break;
				}

			} else if (ps == ProcessState.BLOCKED) {
				System.out.println("**process blocked**");
				ready.remove(pcb);
				blocked.add(pcb);
			} else if (quantum == QUANTUM) {
				System.out.println("**Quantum**");
				ready.remove(pcb);
				ready.add(pcb);
			} else if (ps == ProcessState.READY) {
				cSwitch = false;
			}
			if (cSwitch) {
				System.out.println("Context Switch: Saving process: " + processor.getIProcess().getPid());
				savePcb(processor, pcb, ready);
				readyFull(blocked, ready, rvg);
				pcb = setProcess(processor, ready);
				System.out.println(pcb.toString());
				quantum = 0;
			}
			wakeUpSleepingProcesses(blocked, ready, rvg);
		}
	}

	// save pcb 
	public static void savePcb(IProcessor processor, ProcessControlBlock pcb, Queue<ProcessControlBlock> ready) {

		pcb.setCurrInstruction(processor.getCurrentInstruction());
		pcb.setRegister1(processor.getRegisterValue(0));
		pcb.setRegister2(processor.getRegisterValue(1));
		pcb.setRegister3(processor.getRegisterValue(2));
		pcb.setRegister4(processor.getRegisterValue(3));

	}

	public static void readyFull(Queue<ProcessControlBlock> blocked, Queue<ProcessControlBlock> ready,
			IRandomValueGenerator rvg) {
		while (ready.isEmpty()) {
			System.out.println("**Processor Idling**");
			wakeUpSleepingProcesses(blocked, ready, rvg);
		}

	}

	public static ProcessControlBlock setProcess(IProcessor processor, Queue<ProcessControlBlock> ready) {

		ProcessControlBlock npbc = ready.remove();
		processor.setCurrentInstruction(npbc.getCurrInstruction());
		processor.setRegisterValue(0, npbc.getRegister1());
		processor.setRegisterValue(1, npbc.getRegister2());
		processor.setRegisterValue(2, npbc.getRegister3());
		processor.setRegisterValue(3, npbc.getRegister4());
		processor.setIProcess(npbc.getProc());
		return npbc;

	}

	public static void wakeUpSleepingProcesses(Queue<ProcessControlBlock> blocked, Queue<ProcessControlBlock> ready,
			IRandomValueGenerator rvg) {
		ArrayList<ProcessControlBlock> blockedProcess = new ArrayList<ProcessControlBlock>();
		for (ProcessControlBlock pcb : blocked) {
			if (rvg.getTrueWithProbability(.3)) {
				blockedProcess.add(pcb);
				ready.add(pcb);
			}
		}
		for (ProcessControlBlock pcb : blockedProcess) {
			if (blocked.contains(pcb)) {
				blocked.remove(pcb);
			}
		}
	}
}
