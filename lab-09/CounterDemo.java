class Counter implements Runnable {
    int count=0;
   
     synchronized public void increment(){
            count++;
     }
      public void run(){
        for(int i=0;i<50000;i++){
            increment();
        }
    }
    
}

class CounterDemo {
    public static void main(String []a){
    Counter c1=new Counter();
     Thread t1 = new Thread(c1,"one");
       t1.start();
       Thread t2 = new Thread(c1,"two");
     t2.start();
       try{
       t1.join();
       t2.join();
       }
       catch(Exception e){
        System.out.println(e);
       }
    //c1.increment();
    System.out.println(c1.count); 
   }

}