package com.pizzeria;

public class Pizzeria {

    public static void main(String[] args) throws InterruptedException {
        Chef chef = new Chef();
        Waiter waiter = new Waiter(chef);
        chef.start();
        waiter.start();

        Thread.sleep(1000);

//        final Order order = new Order("pizza1");
//        System.out.println("Клиент оформляет заказ " + order.getOrderDesc());
//        waiter.newOrder(order);
//        waiter.newOrder(new Order("pizza2"));
//        waiter.newOrder(new Order("pizza3"));

        Client client1 = new Client(waiter, "pizza1");
        Client client2 = new Client(waiter, "pizza2");
        Client client3 = new Client(waiter, "pizza3");

        client1.start();
        client2.start();
        client3.start();

    }

    private static class Client extends Thread{
        private final Object mon = new Object();
        private final Waiter waiter;
        private final Order order;

        private Client(Waiter waiter, String order) {
            this.waiter = waiter;
            this.order = new Order(order);
        }

        public void run() {
            waiter.newOrder(order);
        }
    }

    private static class Waiter extends Thread{
        private final Object mon = new Object();
        private final Chef chef;
        private Order order;

        private Waiter(Chef chef) {
            this.chef = chef;
        }


        public void run() {
            while (!isInterrupted()){
                try {
                    synchronized (mon){
                        final Order order = this.order;
                        if (order != null){
                            this.order = null;
                            System.out.println("Waiter: Получил заказ, иду к повару - " + order.getOrderDesc());
                            chef.newOrder(order);
                            System.out.println("Waiter: Передал заказ повару - " + order.getOrderDesc());
                        }
                        mon.wait();
                    }
                } catch (InterruptedException e) {
                    interrupt();
                }
            }
        }

        public void newOrder(Order order){
            synchronized (mon){
                this.order = order;
                mon.notify();
            }
        }
    }

    private static class Chef extends Thread{
        private final Object mon = new Object();
        private Order order;

        @Override
        public void run() {
            while (!isInterrupted()){
                try {
                    synchronized (mon){
                        final Order order = this.order;
                        if (order != null){
                            this.order = null;
                            System.out.println("Chef: Готовим " + order.getOrderDesc());
                            sleep(1000);
                            System.out.println("Chef: Готово " + order.getOrderDesc());
                        }
                        mon.wait();
                    }
                } catch (InterruptedException e) {
                    interrupt();
                }
            }
        }

        public void newOrder(Order order){
            synchronized (mon){
                this.order = order;
                mon.notify();
            }
        }
    }

    private static class Order{
        private String orderDesc;

        public Order(String orderDesc) {
            this.orderDesc = orderDesc;
        }

        public String getOrderDesc() {
            return orderDesc;
        }

        public void setOrderDesc(String orderDesc) {
            this.orderDesc = orderDesc;
        }
    }
}
