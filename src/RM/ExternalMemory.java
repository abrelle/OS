package RM;

import Memory.Memory;

public class ExternalMemory extends Memory {
    static final int BLOCK_NUMBER = 512;
    static final int BLOCK_LENGTH = 256;
    private int WORD_NUMBER = BLOCK_NUMBER * BLOCK_LENGTH;

    ExternalMemory(){
        super(BLOCK_NUMBER,BLOCK_LENGTH);
    }
}
