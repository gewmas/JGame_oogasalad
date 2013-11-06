package temporary.buff;

public interface ISlowBuffable extends IBuffable {
    public void slowBuffOn(Buff buff,int offSet);
    public void slowBuffOff(Buff buff,int offSet);
}
