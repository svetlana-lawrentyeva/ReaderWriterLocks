import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: nau
 * Date: 1/18/15
 * Time: 12:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        DataStructure dataStructure = DataStructure.getInstance();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<30;++i){
            executorService.submit(new User(dataStructure, i));
        }
    }
}
