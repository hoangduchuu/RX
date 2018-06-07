import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import rx.Single;


public class Main {

    public static void main(String[] args) {
        Single.just("huuhoang")
                .map(s -> s + "dep trai")
                .subscribe(s -> System.out.println(""+s));
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static final String TAG = Main.class.getSimpleName();

}
