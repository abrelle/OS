package VM;

import java.util.Arrays;

public class Commands {
    private final CPU cpu;
    private final Memory memory;
    private final Stack stack;

    Commands(CPU cpu, Memory memory)
    {
        this.cpu = cpu;
        this.memory = memory;
        stack = new Stack(memory, cpu);
    }

    public void execute(String command) throws Exception {
        if(command.contains("AD"))
        {
            AD();
        }else if(command.contains("SB")){
            SB();
        }else if(command.contains("ML")){
            ML();
        }else if(command.contains("DV")){
            DV();
        }else if(command.contains("INC")){
           // CM();
        }else if(command.contains("DEC")){
           // CM();
        }else if(command.contains("CLR")){
            //CM();
        }else if(command.contains("MD")){
           // CM();
        }else if(command.contains("CM")){
           // CM();
        }else if(command.contains("JH")){
           // CMR();
        }else if(command.contains("JE")){
           // JUMP();
        }else if(command.contains("JN")){
           // JUMPR();
        }else if(command.contains("JL")){
           // JA();
        }else if(command.contains("JM")){
           // JA();
        }else if(command.contains("GIC")){
           // JA();
        }else if(command.contains("HALT")){
            //JA();
        }else if(command.contains("SM")){
            //PUSH();
        }else if(command.contains("SV")){
            //PUSH();
        }else if(command.contains("LS")){
            // POP();
        }else if(command.contains("PO")){
           // POP();
        }else if(command.contains("SD")){
            // POP();
        }else if(command.contains("GD")){
            // POP();
        }else if(command.contains("PD")){
            // POP();
        }else if(command.contains("AND")){
            // POP();
        }else if(command.contains("OR")){
            // POP();
        }else if(command.contains("XOR")){
            // POP();
        }else if(command.contains("NOT")){
            // POP();
        }else {
            System.out.print("Not found");
        }
    }

    // komandos
    private void AD()
    {
        System.out.println("AD()");
        try {
            stack.Pop();
            int op1 = cpu.getRL().getNumber();
            stack.Pop();
            int op2 = cpu.getRL().getNumber();
            long result = op1 + op2;
            if (result > Constants.MAX_NUMBER) {
                //ADD to RX
            } else {
                cpu.setRL(new Word((int) result));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void SB()
    {
        System.out.println("SB()");
        try {
            stack.Pop();
            int op1 = cpu.getRL().getNumber();
            stack.Pop();
            int op2 = cpu.getRL().getNumber();
            int result = op1 - op2;
            cpu.setRL(new Word(result));
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void ML()
    {
        System.out.println("ML()");
        try {
            stack.Pop();
            int op1 = cpu.getRL().getNumber();
            stack.Pop();
            int op2 = cpu.getRL().getNumber();
            long result = op1 * op2;
            if (result > Constants.MAX_NUMBER) {
                //result to RX
            } else {
                cpu.setRL(new Word((int) result));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void DV()
    {
        System.out.println("DV()");
        try {
            stack.Pop();
            int op1 = cpu.getRL().getNumber();
            stack.Pop();
            int op2 = cpu.getRL().getNumber();
            if(op2 == 0)throw new Exception("Division by zero");
            int div = op1 / op2;
            int mod = op1 % op2;
            cpu.setRL(new Word(div));
            cpu.setRH(new Word(div));
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void CM()
    {
        System.out.println("CM()");
        try {
            Word w1 = stack.getNthElement(0);
            Word w2 = stack.getNthElement(1);
            if(w1.getNumber() == w2.getNumber())
            {
                cpu.setC(Constants.C_VALUES.EQUAL);
            } else{
                if(w1.getNumber() < w2.getNumber())
                {
                    cpu.setC(Constants.C_VALUES.LESS);
                }else {
                    cpu.setC(Constants.C_VALUES.MORE);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void CMR()
    {
        System.out.println("CMR()");
        try {
            Word w1 = cpu.getRL();
            Word w2 =  cpu.getRH();
            if(w1.getNumber() == w2.getNumber())
            {
                cpu.setC(Constants.C_VALUES.EQUAL);
            } else{
                if(w1.getNumber() < w2.getNumber())
                {
                    cpu.setC(Constants.C_VALUES.LESS);
                }else {
                    cpu.setC(Constants.C_VALUES.MORE);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void JUMP()
    {
        System.out.println("JUMP()");
        try {
            stack.Pop();
            cpu.setIC(cpu.getRL());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void JUMPR()
    {
        System.out.println("JUMPR()");
        try {
            cpu.setIC(cpu.getRL());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void JA()
    {
        System.out.println("JA()");
        try {
            if(cpu.getC() == Constants.C_VALUES.MORE)
            {
                JUMP();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void JAR()
    {
        System.out.println("JAR()");
        try {
            if(cpu.getC() == Constants.C_VALUES.MORE)
            {
                JUMPR();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void JB()
    {
        System.out.println("JB()");
        try {
            if(cpu.getC() == Constants.C_VALUES.LESS)
            {
                JUMP();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void JBR()
    {
        System.out.println("JBR()");
        try {
            if(cpu.getC() == Constants.C_VALUES.LESS)
            {
                JUMPR();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void JE()
    {
        System.out.println("JE()");
        try {
            if(cpu.getC() == Constants.C_VALUES.EQUAL)
            {
                JUMP();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void JER()
    {
        System.out.println("JER");
        try {
            if(cpu.getC() == Constants.C_VALUES.EQUAL)
            {
                JUMPR();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void PUSH()
    {
        System.out.println("PUSH()");
        try {
            stack.Push();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void POP()
    {
        System.out.println("POP()");
        try {
            stack.Pop();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void PUSHALL()
    {
        System.out.println("PUSHALL()");
    }
    private void POPALL()
    {
        System.out.println("POPALL()");
    }
    private void SWAP()
    {
        System.out.println("SWAP()");
        try {
            Word rh = cpu.getRH();
            cpu.setRH(cpu.getRL());
            cpu.setRL(rh);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void LOADB(Word virtualAddress)
    {
        System.out.println("LOADB()");
        try {
            Word realVirtualAddress = cpu.getDS(virtualAddress);
            cpu.setRL(memory.getWord(realVirtualAddress));
            cpu.setC(Constants.C_VALUES.SYMBOLS);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void LOADW(Word virtualAddress)
    {
        System.out.println("LOADW()");
        try {
            Word realVirtualAddress = cpu.getDS(virtualAddress);
            cpu.setRL(memory.getWord(realVirtualAddress));
            cpu.setC(Constants.C_VALUES.NUMBERS);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void SAVE(Word virtualAddress)
    {
        System.out.println("SAVE()");
        try {
            Word realVirtualAddress = cpu.getDS(virtualAddress);
            memory.setWord(cpu.getRL(), realVirtualAddress);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void GET(Word virtualAddress)
    {
        System.out.println("GET()");
    }
    private void PUT(Word virtualAddress)
    {
        System.out.println("PUT()");
        try {
            Word realVirtualAddress = cpu.getDS(virtualAddress);
            System.out.println("PUTS values :");
            for (int i =0; i<Constants.F_VALUE; i++)
            {
                System.out.println(Arrays.toString(memory.getWord(realVirtualAddress.add(i)).getContent()));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void HALT() throws Exception {
        System.out.println("HALT()");
        cpu.setSI(Constants.INTERRUPTION.HALT);
    }

    private void PRINTR(){
        System.out.println("PRINTR()");
        System.out.println("RL " + cpu.getRL().toString());
    }


}
