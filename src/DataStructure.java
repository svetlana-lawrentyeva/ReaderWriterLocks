import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created with IntelliJ IDEA.
 * User: nau
 * Date: 1/17/15
 * Time: 11:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataStructure {
    static DataStructure dataStructure;
    private int length = 10;
    private ArrayList<String> data = new ArrayList<String>();
    ReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock initLock = new ReentrantLock();

    private DataStructure(){
        init();
    }

    public static DataStructure getInstance(){
        initLock.lock();
        try{
            if (dataStructure == null) dataStructure = new DataStructure();
        } finally {
            initLock.unlock();
        }
        return dataStructure;
    }
    private void init(){
        Random rnd = new Random();
        for(int i=0; i<length;++i){
            data.add(String.valueOf(rnd.nextInt(1000000)));
        }
    }

    public String read(int i, int name){
        lock.readLock().lock();
        try{
            String result = data.get(i);
            System.out.println("thread #" + name + " read from " + i + "::" + result);
            return result;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write(int i, String value, int name){
        lock.writeLock().lock();
        try{
            data.set(i,value);
            System.out.print("writing ");
            for(int j = 0; j<25;++j){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                System.out.print(".");

            }
            System.out.println(" thread #" + name + " wrote to " + i + "::" + value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void readWrite(int i, String value, int name){
        lock.writeLock().lock();
        try{
            System.out.println("===============<READ-WRITE>===============");
            read(i, name);
            write(i, value, name);
            System.out.println("===============</READ-WRITE>===============");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getLength() {
        return length;
    }
}
