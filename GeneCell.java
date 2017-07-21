/**
 * Created by ctare on 2017/07/20.
 */
public class GeneCell implements Cloneable{
    private byte cell;

    public GeneCell init(){
        this.cell = Math.random() >= 0.5 ? 1 : (byte)0;
        return this;
    }

    public void mutation(){
        this.cell = this.cell == 0 ? 1 : (byte)0;
    }

    public byte val(){
        return cell;
    }

    @Override
    protected GeneCell clone() {
        try {
            return (GeneCell) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
