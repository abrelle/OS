package VM;

import java.util.Arrays;

import static VM.Constants.*;

public class Memory {
    private final Word[][] vmMemory;

    Memory() {
        vmMemory = new Word[VIRTUAL_MEMORY_BLOCK_NUMBER][BLOCK_LENGTH];
        for (int i = 0; i < VIRTUAL_MEMORY_BLOCK_NUMBER; i++) {
            for (int j = 0; j < BLOCK_LENGTH; j++) {
                try {
                    vmMemory[i][j] = new Word(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Word getWord(int virtualAddress) throws Exception {
        if (virtualAddress > Constants.VIRTUAL_WORD_NUMBER || virtualAddress < 0) {
            throw new Exception("Not existing address");
        }
        int block = virtualAddress / BLOCK_LENGTH;
        int word = virtualAddress % BLOCK_LENGTH;
        System.out.println("Pimpaliukas");
        return vmMemory[block][word];
    }

    public Word getWord(String virtualAddress) throws Exception {
        int decimalAddress = Integer.parseInt(virtualAddress, 16);
        return getWord(decimalAddress);
    }

    public Word getWord(Word virtualAddress) throws Exception {
        return getWord(virtualAddress.getNumber());
    }


    public void setWord(Word word, int virtualAddress) throws Exception {
        getWord(virtualAddress).setWord(word);
    }

    public void setWord(Word word, Word virtualAddress) throws Exception {
        getWord(virtualAddress.getNumber()).setWord(word);
    }

    public void setCommands(int virtualAddress, String value) throws Exception {
        int addrCounter = 0;
        while(value.length() % WORD_LENGTH != 0){
            value += '0';
        }
        for (int i = 0; i < value.length(); i += WORD_LENGTH){
            Word word = new Word(value.substring(i, i + WORD_LENGTH), Word.WORD_TYPE.SYMBOLIC);
            getWord(virtualAddress + addrCounter).setWord(word);
            addrCounter++;
        }
    }

    public String getCommand(Word virtualAddress, int shift) throws Exception {
        Word word1 = getWord(virtualAddress.getNumber());
        Word word2 = getWord(virtualAddress.getNumber() + 1);
        String words = word1.getASCIIFormat() + word2.getASCIIFormat();
        String command = "";

        if(shift < 2){
            command = words.substring(0, COMMAND_LENGTH);
        }
        else{
            command = words.substring(shift);
        }
        return command;
    }

    private String createWord(int val) {
        String hex = Integer.toHexString(val);
        for (int i = hex.length(); i < WORD_LENGTH; i++) {
            hex = "0" + hex;
        }
        return hex;
    }
/*
    public void print() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("the-file-name.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < numberOfBlocks; i++) {
            for (int j = 0; j < BLOCK_LENGTH; j++) {
                writer.print(vmMemory[i][j].toString());
                writer.print("   ");
            }
            writer.println("");
        }
        writer.close();
    }
*/
    public void printInfo(){
        for (int i = 0; i < VIRTUAL_MEMORY_BLOCK_NUMBER; i++) {
            System.out.println(Arrays.toString(vmMemory[i]));
        }
    }

    public void printStack(){
        for (int i = 0; i < STACK_BLOCK_NUMBER; i++) {
            System.out.println(Arrays.toString(vmMemory[i]));
        }
    }

}
