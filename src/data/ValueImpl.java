package data;

import edu.hws.jcm.data.Value;

/**
 *
 * @author luan
 */
public class ValueImpl implements Value {
    private double value;
    
    public ValueImpl(double value) {
        this.value = value;
    }
    
    @Override
    public double getVal() {
        return value;
    }
}
