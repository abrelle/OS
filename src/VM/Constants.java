package VM;

public class Constants {
    // vartotojo atmintis
    public static final int WORD_LENGTH = 6;
    public static final int BLOCK_NUMBER = 256;
    public static final int BLOCK_LENGTH = 256;
    public static final int WORD_NUMBER = 65535; // 256*256

    // kur prasideda segmentai (kol kas i tris dalis parasyta)
//    public static final int STACK_SEGMENT = 0; //wordlenght*blocklenght*blocknumber
//    public static final int DATA_SEGMENT = 7169; //28*256=7168+1;
//    public static final int CODE_SEGMENT = 17410; //(28+40)*256=17408+2;
//    public static final long MAX_NUMBER = 262147; //(28+40+188)*256=65536+3;

    public static final int STACK_SEGMENT = 0; //wordlenght*blocklenght*blocknumber
    public static final int STACK_BLOCK_NUMBER = 6;
    public static final int DATA_SEGMENT = 1537; //6*256=1536+1;
    public static final int CODE_SEGMENT = 2562; //(6+4)*256=2560+2;
    public static final long MAX_NUMBER = 8192; // 32*256 = 8192

    public static final long MAX_WORD_SIZE_NUMBER = 65536;//256*256

    public static final int F_VALUE = 16;
    public static final int FF_VALUE = 256;
    public static final int FFF_VALUE = 4096;

    public static final int STATUS_FLAG_INDEX = 4;
    public static final int CARRY_FLAG_INDEX = 5;
    public static final int ZERO_FLAG_INDEX = 6;
    public static final int OVERFLOW_FLAG_INDEX = 7;

    public enum FILE_SEG
    {
        DATSEG,
        CODSEG,
        STACKSEG
    }


    public enum INTERRUPTION
    {
        HALT,
    }
}
