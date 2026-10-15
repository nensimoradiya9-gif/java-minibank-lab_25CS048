class OutOfStockException extends Exception {
    private int shortFall;

    OutOfStockException(String msg, int shortFall) {
        super(msg);
        this.shortFall = shortFall;
    }

    public String toString() {
        return super.getMessage() + " Shortfall: " + shortFall;
    }
}

class InvalidQuantityException extends Exception {
    InvalidQuantityException(String msg) {
        super(msg);
    }
}

public class WareHouse {

    String[] item = {"Apple", "Orange", "Banana"};
    int[] quan = {5, 4, 3};

    void issue(String item, int qty)
            throws InvalidQuantityException, OutOfStockException {

        int index = -1;

        if (qty <= 0) {
            throw new InvalidQuantityException("InvalidQuantityException");
        }

        for (int i = 0; i < this.item.length; i++) {
            if (item.equalsIgnoreCase(this.item[i])) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new InvalidQuantityException("Item not found");
        }

        if (quan[index] < qty) {
            throw new OutOfStockException(
                item, qty - quan[index]
            );
        }

        quan[index] -= qty;

        System.out.println("Order Successful");
    }

    public static void main(String[] a) {

        WareHouse w = new WareHouse();

        try {
            w.issue("Apple", -2);
        }
        catch (OutOfStockException o) {
            System.out.println(o.toString());
        }
        catch (InvalidQuantityException i) {
            System.out.println(i.toString());
        }

        try {
            w.issue("Orange", 5);
        }
        catch (OutOfStockException o) {
            System.out.println(o.toString());
        }
        catch (InvalidQuantityException i) {
            System.out.println(i.toString());
        }

        try {
            w.issue("Banana", 2);
        }
        catch (OutOfStockException o) {
            System.out.println(o.toString());
        }
        catch (InvalidQuantityException i) {
            System.out.println(i.toString());
        }
    }
}