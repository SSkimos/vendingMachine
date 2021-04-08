package IFabrics;

import product.IProduct;
import product.borjomy;

public class borjomyFabric extends IFabric {
    @Override
    public IProduct create() {
        return new borjomy();
    }
}
