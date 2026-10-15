class MessageBuffer{
    private String message;
    private boolean hasData=false;

    synchronized public void produce(String msg){
        if(hasData){
            try{
                wait();
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        this.message=msg;
        this.hasData=true;
        notify();
        System.out.println("[Producer] Produced: "+msg);
    }

     synchronized public void consume(){
        if(!hasData){
            try{
                wait();
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
       
        this.hasData=false;
        notify();
        System.out.println("[Consumer] Consumed: "+message);
    }
}

class Producer implements Runnable{
    MessageBuffer buffer;
    public Producer(MessageBuffer buffer){
        this.buffer=buffer;
    }
    public void run(){
        String[] messages={"one","two","three","four","five"};
        for(String msg:messages){
            buffer.produce(msg);
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable{
    MessageBuffer buffer;
    public Consumer(MessageBuffer buffer){
        this.buffer=buffer;
    }
    public void run(){
       for(int i=0;i<5;i++){
            buffer.consume();
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}

class BufferDemo {
    public static void main(String[] args) 
        {
    MessageBuffer sharedBuffer=new MessageBuffer();
    Thread producerThread=new Thread(new Producer(sharedBuffer),"Producer");
    Thread consumerThread=new Thread(new Consumer(sharedBuffer),"Consumer");
    producerThread.start();
    consumerThread.start();
    }
    }
