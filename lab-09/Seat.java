class Booking {
private int seatsLeft = 5;
 public void book() {

        if (seatsLeft > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            seatsLeft--;

            System.out.println(Thread.currentThread().getName()
                    + ": Booking successful. Seats left: " + seatsLeft);
        }
        else {
            System.out.println(Thread.currentThread().getName()
                    + ": Booking failed. No seats left.");
        }
    }

    synchronized public void bookS() {

        if (seatsLeft > 0) {
            seatsLeft--;

            System.out.println(Thread.currentThread().getName()
                    + ": Synched Booking successful. Seats left: " + seatsLeft);
        }
        else {
            System.out.println(Thread.currentThread().getName()
                    + ": Synched Booking failed. No seats left.");
        }
    }
}

class SeatBooking implements Runnable {

    Booking booking;
    boolean sync;

    SeatBooking(Booking booking, boolean sync) {
        this.booking = booking;
        this.sync = sync;
    }

    public void run() {

        if (sync) {
            booking.bookS();
        }
        else {
            booking.book();
        }
    }
}

class Seat {

    public static void main(String[] a) throws InterruptedException {

        System.out.println("WITHOUT SYNCHRONIZATION");

        Booking booking1 = new Booking();

        Thread[] t = new Thread[10];

        for (int i = 0; i < 10; i++) {
            t[i] = new Thread(
                    new SeatBooking(booking1, false),
                    "Thread " + (i + 1)
            );
            t[i].start();
        }

        System.out.println("\nWITH SYNCHRONIZATION");

        Booking booking2 = new Booking();

        for (int i = 0; i < 10; i++) {
            t[i] = new Thread(
                    new SeatBooking(booking2, true),
                    "Thread " + (i + 1)
            );
            t[i].start();
        }

    }
}