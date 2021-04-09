package VM;

import java.util.Arrays;

import static VM.Constants.*;

public class Memory{
    private int VIRTUAL_MEMORY_BLOCK_NUMBER = 0;
    private int BLOCK_LENGTH = 0;
    private int WORD_NUMBER = 0;
    private Word[][] vmMemory = null;

    Memory(int VIRTUAL_MEMORY_BLOCK_NUMBER, int BLOCK_LENGTH) {
        this.BLOCK_LENGTH = BLOCK_LENGTH;
        this.VIRTUAL_MEMORY_BLOCK_NUMBER = VIRTUAL_MEMORY_BLOCK_NUMBER;
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

    public boolean checkIfBlockEmpty(int block) throws Exception {
        if (block>=VIRTUAL_MEMORY_BLOCK_NUMBER) throw new Exception("Not existing block");
        for (int i=0;i<BLOCK_LENGTH;i++){
            if(vmMemory[block][i].getNumber()!=0) return false;
        }
        return true;
    }

    public void cleanBlock(int block) throws Exception {
        if (block>=VIRTUAL_MEMORY_BLOCK_NUMBER) throw new Exception("Not existing block");
        for (int i=0;i<BLOCK_LENGTH;i++){
            vmMemory[block][i].setWord(new Word(0));
        }
    }

    public Word[] getBlock(int block)throws Exception {
        if (block>=VIRTUAL_MEMORY_BLOCK_NUMBER) throw new Exception("Not existing block");
        return vmMemory[block];
    }

    public void setBlock(int block, Word[] data)throws Exception {
        if (block>=VIRTUAL_MEMORY_BLOCK_NUMBER) throw new Exception("Not existing block");
        if (data.length!=BLOCK_LENGTH) throw new Exception("BAD block length");
        vmMemory[block]= data;
    }

    public int getBlockBeginAddress(int block){
        return block*BLOCK_LENGTH;
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
     // Word[] splitWord = word.splitWord();
            getWord(virtualAddress + addrCounter).setWord(word);
            addrCounter++;
//            getWord(virtualAddress + addrCounter).setWord(splitWord[1]);
//            addrCounter++;
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

    public String getASCIIFormat(String stringValue) {
        int[] content = stringToIntArray(stringValue);
        String result = "";
        for (int A : content) {
            result += ((char) A);
        }
        return result;
    }

    public int[] stringToIntArray(String stringValue){
        int[] arr = new int [stringValue.length()/2];
        int counter = 0;
        for(int i = 0; i < stringValue.length(); i+=2){
            arr[counter] = Integer.parseInt(stringValue.substring(i, i+2));
            counter++;
        }
        return arr;
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
            for (int j = 0; j < BLOCK_LENGTH; j++){
                System.out.print(vmMemory[i][j].getHEXFormat() + " ");
            }
           System.out.println();
        }
    }

    public void printStack(){
        for (int i = 0; i < STACK_BLOCK_NUMBER; i++) {
            System.out.println(Arrays.toString(vmMemory[i]));
        }
    }

}
