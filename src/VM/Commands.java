package VM;

import Memory.Memory;

import java.util.Arrays;

import static VM.Constants.FLAGS.*;

public class Commands {
    private final VirtualCPU cpu;
    private final Memory memory;
    private final Stack stack;

    Commands(VirtualCPU cpu, Memory memory) {
        this.cpu = cpu;
        this.memory = memory;
        stack = new Stack(memory, cpu);
    }

    public void execute(String command) throws Exception {
        if (command.contains("AD")) {
            AD();
        } else if (command.contains("SB")) {
            SB();
        } else if (command.contains("ML")) {
            ML();
        } else if (command.contains("DV")) {
            DV();
        } else if (command.contains("INC")) {
            INC();
        } else if (command.contains("DEC")) {
            DEC();
        } else if (command.contains("CLR")) {
            CLR();
        } else if (command.contains("MD")) {
            MD();
        } else if (command.contains("CM")) {
            CM();
        } else if (command.contains("JH")) {
            JH();
        } else if (command.contains("JE")) {
            JE();
        } else if (command.contains("JN")) {
            JN();
        } else if (command.contains("JL")) {
            JL();
        } else if (command.contains("JM")) {
            JUMP();
        } else if (command.contains("GIC")) {
            GIC();
        } else if (command.contains("HALT")) {
            HALT();
        } else if (command.contains("SM")) {
            Word word = new Word(command.substring(2), Word.WORD_TYPE.NUMERIC);
            SM(word);
        } else if (command.contains("SV")) {
            Word word = new Word(command.substring(2), Word.WORD_TYPE.NUMERIC);
            SV(word);
        } else if (command.contains("LS")) {
            Word word = new Word(command.substring(2), Word.WORD_TYPE.NUMERIC);
            LS(word);
        } else if (command.contains("PO")) {
            PO();
        } else if (command.contains("SP")) {
            SP();
        } else if (command.contains("GD")) {
            GD();
        } else if (command.contains("PD")) {
            PD();
        } else if (command.contains("AND")) {
            AND();
        } else if (command.contains("OR")) {
            OR();
        } else if (command.contains("XOR")) {
            XOR();
        } else if (command.contains("NOT")) {
            NOT();
        } else {
            System.out.print("Not found ");
        }
    }

    // komandos
    private void AD() throws Exception {
        System.out.println("AD()");
        int op1 = stack.pop().getNumber();
        int op2 = stack.pop().getNumber();
        int result = op1 + op2;
        if (result > Constants.MAX_WORD_SIZE_NUMBER) {
            cpu.setSR(OVERFLOW_FLAG_INDEX, 1);
        } else {
            stack.push(new Word(result));
        }
    }

    private void SB() throws Exception {
        System.out.println("SB()");
        int op1 = stack.pop().getNumber();
        int op2 = stack.pop().getNumber();
        int result = op1 - op2;
        stack.push(new Word(result));

    }

    private void ML() {
        System.out.println("ML()");
        try {
            Word p1 = stack.pop();
            Word p2 = stack.pop();
            int op1 = p1.getNumber();
            int op2 = p2.getNumber();
            int result = op1 * op2;
            if (result > Constants.MAX_WORD_SIZE_NUMBER) {
                cpu.setSR(OVERFLOW_FLAG_INDEX, 1);
            } else {
                stack.push(new Word((int) result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void DV() throws Exception {
        System.out.println("DV()");
        int op1 = stack.pop().getNumber();
        int op2 = stack.pop().getNumber();
        if (op2 == 0) {
            cpu.setSR(ZERO_FLAG_INDEX, 1);
        } else {
            int div = op1 / op2;
            stack.push(new Word(div));
        }
    }

    private void INC() throws Exception {
        System.out.println("INC()");
        int op1 = stack.pop().getNumber();
        int result = op1 + 1;
        if (result > Constants.MAX_WORD_SIZE_NUMBER) {
            cpu.setSR(OVERFLOW_FLAG_INDEX, 1);
        } else {
            stack.push(new Word(result));
        }
    }

    private void DEC() throws Exception {
        System.out.println("DEC()");
        int op1 = stack.pop().getNumber();
        int result = op1 - 1;
        stack.push(new Word(result));
    }

    private void CLR() throws Exception {
        System.out.println("CLR()");
        stack.pop();
        stack.push(new Word(0));
    }

    private void MD() throws Exception {
        System.out.println("MD()");
        int op1 = stack.pop().getNumber();
        int op2 = stack.pop().getNumber();
        int mod = op1 % op2;
        stack.push(new Word(mod));
    }

    private void CM() throws Exception {
        System.out.println("CM()");
        Word w1 = stack.getPreviousElement(-1);
        Word w2 = stack.getPreviousElement(-2);

        if (w1.getNumber() == w2.getNumber()) {
            cpu.setSR(ZERO_FLAG_INDEX, 1);
        } else if (w1.getNumber() > w2.getNumber()) {
            cpu.setSR(ZERO_FLAG_INDEX, 0);
            cpu.setSR(CARRY_FLAG_INDEX, 0);
        } else {
            cpu.setSR(ZERO_FLAG_INDEX, 0);
            cpu.setSR(CARRY_FLAG_INDEX, 1);
        }
    }

    private void JH() {
        System.out.println("JH()");
        byte SR = cpu.getSR();
        if (((SR >> CARRY_FLAG_INDEX.getValue()) & 1) == 0 && ((SR >> ZERO_FLAG_INDEX.getValue()) & 1) == 0) {
            JUMP();
        }
    }

    private void JE() {
        System.out.println("JE()");
        byte SR = cpu.getSR();
        if (((SR >> ZERO_FLAG_INDEX.getValue()) & 1) == 1) {
            JUMP();
        }
    }

    private void JN() {
        System.out.println("JN()");
        byte SR = cpu.getSR();
        if (((SR >> ZERO_FLAG_INDEX.getValue()) & 1) == 0) {
            JUMP();
        }
    }

    private void JL() {
        System.out.println("JL()");
        try {
            byte SR = cpu.getSR();

        if (((SR >> CARRY_FLAG_INDEX.getValue()) & 1) == 1 && ((SR >> ZERO_FLAG_INDEX.getValue()) & 1) == 0) {
            JUMP();
        }
    }

    private void JUMP() throws Exception { //******* REIKIA PADUOTI VIENU MAZESNI POSLINKI. PVZ.: JEI KOMANDA YRA POSLINKIU 0006, TAI
        //STEKO VIRSUI TURI BUT 0005
        System.out.println("JUMP()");
        cpu.setIC(stack.pop());
    }

    private void SP() throws Exception {
        System.out.println("SP()");
        stack.push(cpu.getSP());
    }

    private void PO() throws Exception {
        System.out.println("PO()");
        stack.pop();
    }

    private void SM(Word wordshift) throws Exception {
        System.out.println("SM()");
        stack.push(memory.getWord(cpu.getDS(wordshift)));
    }

    private void SV(Word word) throws Exception {
        System.out.println("SV()");
        stack.push(word);
    }

    private void LS(Word address) {
        System.out.println("SL()");
        Word word = stack.getPreviousElement(-1);
        memory.setWord(word, address);
    }

    private void GIC() throws Exception {
        System.out.println("GIC()");
        stack.push(cpu.getIC());
    }

    private void AND() {
        System.out.println("AND()");
        try {
            int op1 = stack.pop().getNumber();
            int op2 = stack.pop().getNumber();
            stack.push(new Word(op1 & op2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void OR() {
        System.out.println("OR()");
        try {
            int op1 = stack.pop().getNumber();
            int op2 = stack.pop().getNumber();
            stack.push(new Word(op1 | op2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void XOR() throws Exception {
        System.out.println("XOR()");

        int op1 = stack.pop().getNumber();
        int op2 = stack.pop().getNumber();
        stack.push(new Word(op1 ^ op2));
    }

    private void NOT() throws Exception {
        System.out.println("NOT()");

        int op1 = stack.pop().getNumber();
        stack.push(new Word(~op1));
    }

    private void GD() {
        System.out.println("GD");
        try {
            Word bufferSize = stack.getPreviousElement(-1);
            Word startAddress = stack.getPreviousElement(-2);
            //TODO: realioj masinoj nustatyti SI
            for (int i = 0; i < bufferSize.getNumber(); ++i) {
                System.out.println(memory.getWord(startAddress.add(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void PD() throws Exception {
        System.out.println("PD");

        Word bufferSize = stack.getPreviousElement(-1);
        Word fromAddress = stack.getPreviousElement(-2);
        Word toAddress = stack.getPreviousElement(-3);

        //TODO: realioj masinoj nustatyti SI
        for (int i = 0; i < bufferSize.getNumber(); ++i) {
            Word w = memory.getWord(fromAddress.add(i));
            memory.setWord(w, toAddress.add(i));
        }

        //stack.push();

    }

    private void PUT(Word virtualAddress) throws Exception {
        System.out.println("PUT()");

        Word realVirtualAddress = cpu.getDS(virtualAddress);
        System.out.println("PUTS values :");
        for (int i = 0; i < Constants.F_VALUE; i++) {
            System.out.println(Arrays.toString(memory.getWord(realVirtualAddress.add(i)).getContent()));
        }

    }

    private void HALT() throws Exception {
        System.out.println("HALT()");
        //cpu.setSI(Constants.INTERRUPTION.HALT);
    }

}
