package product;

public abstract class IProduct {
    protected String name;
    protected long cost;

    public long getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
