package com.harel.practise.threads;

public class OddEvenTwoThreads {

    public static void main(String[] args) {
        Printer printer = new Printer(true);
        Runnable oddPrinter = new OddEvenPrinter(printer, 10, true);
        Runnable evenPrinter = new OddEvenPrinter(printer, 10, false);

        Thread t1 = new Thread(oddPrinter, "Odd");
        Thread t2 = new Thread(evenPrinter, "Even");
        t2.start();

        t1.start();
    }

    static class OddEvenPrinter implements Runnable {

        private final Printer printer;
        private final int max;
        private final boolean isOdd;

        public OddEvenPrinter(Printer printer, int max, boolean isOdd) {
            this.printer = printer;
            this.max = max;
            this.isOdd = isOdd;
        }

        @Override
        public void run() {
            int number = isOdd ? 1 : 2;
            while (number < max) {
                try {
                    if (isOdd) {
                        printer.printOdd(number);
                    }
                    else {
                        printer.printEven(number);
                    }
                    number = number + 2;
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Printer {
        boolean isOdd;


        public Printer(boolean isOdd) {
            this.isOdd = isOdd;
        }

        synchronized void printOdd(int number) throws InterruptedException {
            while (!isOdd) {
                wait();
            }
            System.out.println(number + " Odd");
            isOdd = false;
            notify();

        }

        synchronized void printEven(int number) throws InterruptedException {
            while (isOdd) {
                wait();
            }
            System.out.println(number + " Even");
            isOdd = true;
            notify();

        }
    }
}
