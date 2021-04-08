package IFabrics;

import product.IProduct;
import product.croissant;

public class croissantFabric extends IFabric{
    @Override
    public IProduct create() {
        return new croissant();
    }
}

