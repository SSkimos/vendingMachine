package IFabrics;

import product.IProduct;
import product.lays;

public class laysFabric extends IFabric{
    @Override
    public IProduct create() {
        return new lays();
    }
}
