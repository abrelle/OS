package VM;

public class ByteWord {

    private Object value;


    public ByteWord(int value)
    {
        this.value = value;
    }


    public Object getValue()
    {
        return value;
    }
    public void setValue(Object value)
    {
        this.value = value;
    }
}
