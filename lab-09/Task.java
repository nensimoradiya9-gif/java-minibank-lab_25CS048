public class Task implements Runnable{
        int sum=0;
        int sum1=0;
        int sum2=0;
     public void run() {
        
        if(Thread.currentThread().getName().equals("one")){
            for(int i=1;i<100;i+=2){
                try{ 
                 Thread.sleep(100);
                }   
                catch(Exception e){

                } 
                sum1+=i;
            }
              //System.out.println("Sum1: " +sum1);
        }
        else if(Thread.currentThread().getName().equals("two")){
            
            for(int i=0;i<100;i+=2){
                 try{ 
                 Thread.sleep(100);
                }   
                catch(Exception e){

                } 
                sum2+=i;
            }
            //System.out.println("Sum2: " +sum2);
        }
        else{
         sum=sum1+sum2;
         System.out.println("Sum: " +sum);
        }
        
    }
    public static void main(String[] args) {
        Task task1 = new Task();
        Task2 task2 = new Task2();
       Thread t1 = new Thread(task1,"one");
       t1.start();
       Thread t2 = new Thread(task1,"two");
       t2.start();
       Thread t3 = new Thread(task1,"three");
       try{
       t1.join();
       t2.join();
       }
       catch(Exception e){
        System.out.println(e);
       }
       t3.start();
    }
}

class Task2 implements Runnable{
    public void run() { 
        for (int i = 0; i < 5; i++) {
             System.out.println(Thread.currentThread().getName());
            System.out.println(i);
            try {
                Thread.sleep(1000); 
            } catch(Exception e){
                System.out.println(e);
            }
        }
    }
}