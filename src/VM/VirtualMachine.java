package VM;

import java.util.ArrayList;

import static VM.Constants.*;

public class VirtualMachine {
    private VM_CPU cpu = null;
    private Memory memory = null;
    private Commands interpreter = null;

    VirtualMachine() {
        try {
            memory = new Memory(VIRTUAL_MEMORY_BLOCK_NUMBER, BLOCK_LENGTH);
            cpu = new VM_CPU(memory);
            interpreter = new Commands(cpu, memory);
            uploadCode();

            doYourMagic();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doYourMagic() {
        int i = 0;
        int shift = 0;
        while (true)
            try {
                String command = memory.getCommand(cpu.getCS(cpu.getIC()), shift);
                interpreter.execute(command);
                cpu.increaseIC();
                shift = (shift + 2) % WORD_LENGTH;
                if(shift==0){
                    cpu.increaseIC();
                }
                i++;
//                if(i == 3){
//                    System.exit(5);
//                }
                if (command.contains("HALT")) {
                    memory.printInfo();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void uploadCode() {
        try {
            Interpreter interpreter = new Interpreter("prog.txt");
            interpreter.read();
            interpreter.interpreter();
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
