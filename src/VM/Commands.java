package VM;

import java.util.Arrays;

import static VM.Constants.CARRY_FLAG_INDEX;
import static VM.Constants.ZERO_FLAG_INDEX;

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
            //PUSH();
        } else if (command.contains("LS")) {
            // pop();
        } else if (command.contains("PO")) {
            POP();
        } else if (command.contains("SD")) {
            // pop();
        } else if (command.contains("GD")) {
            // pop();
        } else if (command.contains("PD")) {
            // pop();
        } else if (command.contains("AND")) {
            // pop();
        } else if (command.contains("OR")) {
            // pop();
        } else if (command.contains("XOR")) {
            // pop();
        } else if (command.contains("NOT")) {
            // pop();
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
            if (result > Constants.MAX_WORD_SIZE_NUMBER ) {
                System.out.println("AD Overflow");
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
                System.out.println("ML Overflow");
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
            if (op2 == 0) throw new Exception("Division by zero");
            int div = op1 / op2;
            stack.push(new Word(div));
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
                System.out.println("INC overflow");
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
            Word w1 = stack.getPreviousElement(0);
            Word w2 = stack.getPreviousElement(-1);
            byte SR = cpu.getSR();
            if (w1.getNumber() == w2.getNumber()) {
                SR |= (1 << ZERO_FLAG_INDEX);
            } else if (w1.getNumber() < w2.getNumber()) {
                SR |= ~(1 << ZERO_FLAG_INDEX);
                SR |= ~(1 << CARRY_FLAG_INDEX);
            } else {
                SR |= ~(1 << ZERO_FLAG_INDEX);
                SR |= (1 << CARRY_FLAG_INDEX);
            }
            cpu.setSR(SR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void JH() {
        System.out.println("JH()");
        try {
            byte SR = cpu.getSR();
            if (((SR >> CARRY_FLAG_INDEX) & 1) == 0 && ((SR >> ZERO_FLAG_INDEX) & 1) == 0) {
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
            if (((SR >> ZERO_FLAG_INDEX) & 1) == 1) {
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
            if (((SR >> ZERO_FLAG_INDEX) & 1) == 0) {
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
            if (((SR >> CARRY_FLAG_INDEX) & 1) == 1 && ((SR >> ZERO_FLAG_INDEX) & 1) == 0) {
                JUMP();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void JUMP() {
        System.out.println("JUMP()");
        try {
            cpu.setIC(stack.pop());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void GIC() {
        System.out.println("GIC()");
        try {
            //  cpu.setRL(cpu.getIC());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void PUSH() {
//        System.out.println("PUSH()");
//        try {
//            stack.push();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void POP() {
        System.out.println("POP()");
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
            memory.printStack();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    private void SWAP() {
//        System.out.println("SWAP()");
//        try {
//            Word rh = cpu.getRH();
//            cpu.setRH(cpu.getRL());
//            cpu.setRL(rh);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    private void LOADB(Word virtualAddress) {
//        System.out.println("LOADB()");
//        try {
//            Word realVirtualAddress = cpu.getDS(virtualAddress);
//            cpu.setRL(memory.getWord(realVirtualAddress));
//            //  cpu.setC(Constants.C_VALUES.SYMBOLS);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void LOADW(Word virtualAddress) {
//        System.out.println("LOADW()");
//        try {
//            Word realVirtualAddress = cpu.getDS(virtualAddress);
//            cpu.setRL(memory.getWord(realVirtualAddress));
//            // cpu.setC(Constants.C_VALUES.NUMBERS);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    private void SAVE(Word virtualAddress) {
//        System.out.println("SAVE()");
//        try {
//            Word realVirtualAddress = cpu.getDS(virtualAddress);
//            memory.setWord(cpu.getRL(), realVirtualAddress);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void GET(Word virtualAddress) {
        System.out.println("GET()");
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
//
//    private void PRINTR() {
//        System.out.println("PRINTR()");
//        System.out.println("RL " + cpu.getRL().toString());
//    }


}
