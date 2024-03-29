package RM;

import Memory.Memory;

public class InternalMemory extends Memory {
    private static final int BLOCK_NUMBER = 256;// F
    private static final int BLOCK_LENGTH = 256;// FF
    private int WORD_NUMBER = BLOCK_NUMBER*BLOCK_LENGTH ;// FFF

    InternalMemory(){
        super(BLOCK_NUMBER,BLOCK_LENGTH);
    }

    public int findEmptyBlock() throws Exception {
        for(int i = 0; i<BLOCK_NUMBER; i++){
            if(checkIfBlockEmpty(i))return i;
        }
        return -1;
    }
}
