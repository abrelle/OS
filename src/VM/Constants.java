package VM;

public class Constants {
    // vartotojo atmintis
    public static final int WORD_LENGTH = 4;
    public static final int COMMAND_LENGTH = 6;
    public static final int REAL_MEMORY_BLOCK_NUMBER = 256;
    public static final int BLOCK_LENGTH = 256;
    public static final int WORD_NUMBER = 65536; // 256*256

    //VIRTUALIOS MASINOS ATMINTIS
    public static final int BLOCK_NUMBER = 32;
    public static final int VIRTUAL_WORD_NUMBER = 8192; // 32*256
    public static final int VIRTUAL_MEMORY_WORD_NUMBER = 4096; // 16*256


    // kur prasideda segmentai (kol kas i tris dalis parasyta)
//    public static final int STACK_SEGMENT = 0; //wordlenght*blocklenght*blocknumber
//    public static final int DATA_SEGMENT = 7169; //28*256=7168+1;
//    public static final int CODE_SEGMENT = 17410; //(28+40)*256=17408+2;
//    public static final long MAX_NUMBER = 262147; //(28+40+188)*256=65536+3;

    public static final String PROGRAM_BEGIN = "$BGN";
    public static final String PROGRAM_END = "$END";
    public static final String PROGRAM_NAME = "$PRN";
    public static final String CODE_SEGMENT_NAME = "$BCS";
    public static final String DATA_SEGMENT_NAME = "$BDS";

    public static final int STACK_SEGMENT = 0; //wordlenght*blocklenght*blocknumber
    public static final int STACK_BLOCK_NUMBER = 6;
    public static final int DATA_SEGMENT = 1537; //6*256=1536+1;
    public static final int CODE_SEGMENT = 2562; //(6+4)*256=2560+2;
    public static final long MAX_NUMBER = 8192; // 32*256 = 8192

    public static final long MAX_WORD_SIZE_NUMBER = 65536;//256*256

    public static final int F_VALUE = 16;
    public static final int FF_VALUE = 256;
    public static final int FFF_VALUE = 4096;



    public enum INTERRUPTION {
        HALT,
        NONE,
    }


    public enum FLAGS {
        STATUS_FLAG_INDEX(3),
        CARRY_FLAG_INDEX(2),
        ZERO_FLAG_INDEX(1),
        OVERFLOW_FLAG_INDEX(0);

        private final int value;

        FLAGS(final int newValue) {
            value = newValue;
        }

        public int getValue() {
            return value;
        }
    }
}
