import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mutex {

    public static void main(String args[]){
  
     //Creating shared object
     BlockingQueue sharedQueue = new LinkedBlockingQueue(100);
 
     //Creating Producer and Consumer Thread
     Thread prodThread = new Thread(new Producer(sharedQueue));
     Thread consThread = new Thread(new Consumer(sharedQueue));

     //Starting producer and Consumer thread
     prodThread.start();
     consThread.start();
    }
 
}

//Producer Class in java
class Producer implements Runnable {

    private final BlockingQueue sharedQueue;

    public Producer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
       
            try {
 for(int i=0; i<10; i++){
                System.out.println("Produced: " + i);
                sharedQueue.put(i);
}
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }

        
finally{
System.out.println("Wake up Consumer");
}
    }

}

//Consumer Class in Java
class Consumer implements Runnable{

    private final BlockingQueue sharedQueue;

    public Consumer (BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }
  
    @Override
    public void run() {
         
         try{
 while(true){ 
             
                System.out.println("Consumed: "+ sharedQueue.take());
}
              }
//System.out.println("Wakeup Producer");
             catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
finally{
System.out.println("Wakeup Producer");
}

    
}

  }
