import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: nau
 * Date: 1/17/15
 * Time: 11:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class User implements Runnable{
    private DataStructure dataStructure;
    private int name;

    public User(DataStructure dataStructure, int name){
        this.dataStructure = dataStructure;
        this.name = name;
    }

    public void readData(){
        Random rnd = new Random();
        int i = rnd.nextInt(dataStructure.getLength());
        dataStructure.read(i,name);
    }

    public void writeData(){
        Random rnd = new Random();
        String str = String.valueOf(rnd.nextInt(1000000) * -1);
        int i = rnd.nextInt(dataStructure.getLength());
        dataStructure.write(i, str, name);
    }

    public void readWriteData() {
        Random rnd = new Random();
        String str = String.valueOf(rnd.nextInt(1000000));
        int i = rnd.nextInt(dataStructure.getLength());
        dataStructure.readWrite(i, str, name);
    }

    public void run() {
        Random rnd = new Random();
        while(true){
            try {
                Thread.sleep(rnd.nextInt(5000)+1500);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            if(rnd.nextInt() % 3 == 0)
                writeData();
            else if(rnd.nextInt() % 4 == 0)
                readWriteData();
            else readData();
        }
    }
}
