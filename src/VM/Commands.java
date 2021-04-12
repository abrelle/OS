package VM;

import java.util.Arrays;

import static VM.Constants.FLAGS.*;

public class Commands {
    private final
    VM_CPU cpu;
    private final Memory memory;
    private final Stack stack;

    Commands(VM_CPU cpu, Memory memory) {
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
    private void AD() {
        System.out.println("AD()");
        try {
            int op1 = stack.pop().getNumber();
            int op2 = stack.pop().getNumber();
            int result = op1 + op2;
            if (result > Constants.MAX_WORD_SIZE_NUMBER) {
                cpu.setSR(OVERFLOW_FLAG_INDEX, 1);
            } else {
                stack.push(new Word(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SB() {
        System.out.println("SB()");
        try {
            int op1 = stack.pop().getNumber();
            int op2 = stack.pop().getNumber();
            int result = op1 - op2;
            stack.push(new Word(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ML() {
        System.out.println("ML()");
        try {
            Word p1 = stack.pop();
            Word p2 = stack.pop();
            int op1 = p1.getNumber();
            int op2 = p2.getNumber();
            long result = (long) op1 * op2;
            if (result > Constants.MAX_WORD_SIZE_NUMBER) {
                cpu.setSR(OVERFLOW_FLAG_INDEX, 1);
            } else {
                stack.push(new Word((int) result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void DV() {
        System.out.println("DV()");
        try {
            int op1 = stack.pop().getNumber();
            int op2 = stack.pop().getNumber();
            if (op2 == 0) {
                cpu.setSR(ZERO_FLAG_INDEX, 1);
            } else {
                int div = op1 / op2;
                stack.push(new Word(div));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void INC() {
        System.out.println("INC()");
        try {
            int op1 = stack.pop().getNumber();
            int result = op1 + 1;
            if (result > Constants.MAX_WORD_SIZE_NUMBER) {
                cpu.setSR(OVERFLOW_FLAG_INDEX, 1);
            } else {
                stack.push(new Word(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void DEC() {
        System.out.println("DEC()");
        try {
            int op1 = stack.pop().getNumber();
            int result = op1 - 1;
            stack.push(new Word(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CLR() {
        System.out.println("CLR()");
        try {
            stack.pop();
            stack.push(new Word(0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void MD() {
        System.out.println("MD()");
        try {
            int op1 = stack.pop().getNumber();
            int op2 = stack.pop().getNumber();
            int mod = op1 % op2;
            stack.push(new Word(mod));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CM() {
        System.out.println("CM()");
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void JH() {
        System.out.println("JH()");
        try {
            byte SR = cpu.getSR();
            if (((SR >> CARRY_FLAG_INDEX.getValue()) & 1) == 0 && ((SR >> ZERO_FLAG_INDEX.getValue()) & 1) == 0) {
                JUMP();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void JE() {
        System.out.println("JE()");
        try {
            byte SR = cpu.getSR();
            if (((SR >> ZERO_FLAG_INDEX.getValue()) & 1) == 1) {
                JUMP();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void JN() {
        System.out.println("JN()");
        try {
            byte SR = cpu.getSR();
            if (((SR >> ZERO_FLAG_INDEX.getValue()) & 1) == 0) {
                JUMP();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void JL() {
        System.out.println("JL()");
        try {
            byte SR = cpu.getSR();

            if (((SR >> CARRY_FLAG_INDEX.getValue()) & 1) == 1 && ((SR >> ZERO_FLAG_INDEX.getValue()) & 1) == 0) {
                JUMP();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void JUMP() { //******* REIKIA PADUOTI VIENU MAZESNI POSLINKI. PVZ.: JEI KOMANDA YRA POSLINKIU 0006, TAI
        //STEKO VIRSUI TURI BUT 0005
        System.out.println("JUMP()");
        try {
            cpu.setIC(stack.pop());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SP() {
        System.out.println("SP()");
        try {
            stack.push(cpu.getSP());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void PO() {
        System.out.println("PO()");
        try {
            stack.pop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SM(Word wordshift) {
        System.out.println("SM()");
        try {
            stack.push(memory.getWord(cpu.getDS(wordshift)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SV(Word word) {
        System.out.println("SV()");
        try {
            stack.push(word);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LS(Word address) {
        System.out.println("SL()");
        try {
            Word word = stack.getPreviousElement(-1);
            memory.setWord(word, address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void GIC() {
        System.out.println("GIC()");
        try {
            stack.push(cpu.getIC());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void XOR() {
        System.out.println("XOR()");
        try {
            int op1 = stack.pop().getNumber();
            int op2 = stack.pop().getNumber();
            stack.push(new Word(op1 ^ op2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void NOT() {
        System.out.println("NOT()");
        try {
            int op1 = stack.pop().getNumber();
            stack.push(new Word(~op1));

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void PD() {
        System.out.println("PD");
        try {
            Word bufferSize = stack.getPreviousElement(-1);
            Word fromAddress = stack.getPreviousElement(-2);
            Word toAddress = stack.getPreviousElement(-3);

            //TODO: realioj masinoj nustatyti SI
            for (int i = 0; i < bufferSize.getNumber(); ++i) {
                Word w = memory.getWord(fromAddress.add(i));
                memory.setWord(w, toAddress.add(i));
            }

            //stack.push();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void PUT(Word virtualAddress) {
        System.out.println("PUT()");
        try {
            Word realVirtualAddress = cpu.getDS(virtualAddress);
            System.out.println("PUTS values :");
            for (int i = 0; i < Constants.F_VALUE; i++) {
                System.out.println(Arrays.toString(memory.getWord(realVirtualAddress.add(i)).getContent()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void HALT() throws Exception {
        System.out.println("HALT()");
        // cpu.setSI(Constants.INTERRUPTION.HALT);
    }

}
