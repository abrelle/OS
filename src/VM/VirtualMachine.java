package VM;

import Memory.Memory;
import PageTables.VirtualMachineTable;
import RM.ChannelDevice;
import RM.RealCPU;

import java.util.ArrayList;

import static VM.Constants.BLOCK_LENGTH;
import static VM.Constants.BLOCK_NUMBER;

public class VirtualMachine {
    private VirtualCPU cpu = null;
    private ChannelDevice channelDevice;
    private Memory memory = null;
    private Commands interpreter = null;
    private RealCPU realCPU = null;
    private VirtualMachineTable virtualMachineTable;

    private int currentDSBlock = 0;
    private int currentSSBlock = 0;
    private int currentCSBlock = 0;

    //TODO: VirtualMachine(String sourceCode, RealCPU realCPU, int internalBlockBegin);
    public VirtualMachine() {
        try {
            memory = new Memory(BLOCK_NUMBER, BLOCK_LENGTH);

            cpu = new VirtualCPU(memory);
            interpreter = new Commands(cpu, memory);

            uploadCode();
            doYourMagic();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doYourMagic() {
        int i = 0;
        String command = "";
        while (true)
            try {

                command = memory.getCommand(cpu.getCS(new Word(0)), cpu.getIC());
                interpreter.execute(command);
                cpu.increaseIC();
                i++;
//                if(i == 3){
//                    System.exit(3);
//                }
                if (command.contains("HALT")) {
                    memory.printInfo();
                    System.out.println("Status registras " + String.format("%08d", Integer.valueOf(Integer.toBinaryString(cpu.getSR()))));
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void uploadCode() {
        try {
            Interpreter interpreter = new Interpreter("hard.txt");
            interpreter.read();
            interpreter.interpreter("programa1");
            ArrayList<String> dataSegment = interpreter.getDataSegment();
            for (int i = 0; i < dataSegment.size(); i++) {
                cpu.setDS(new Word(i), new Word(dataSegment.get(i), Word.WORD_TYPE.NUMERIC));
            }
            ArrayList<String> codeSegment = interpreter.getCodeSegment();

            StringBuilder code = new StringBuilder();
            for (String s : codeSegment) {
                code.append(s);
            }
            cpu.setCS(new Word(0), code.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
