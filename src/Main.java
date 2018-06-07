import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Single;
import rx.functions.Func1;


public class Main {

    public static void main(String[] args) {
        Observable<String> source =
                Observable.just("Alpha", "Beta", "Gamma");
        source.first(s -> s.contains("Bzeta")) //returns a Single
                .subscribe(x -> System.out.println(x), throwable -> System.out.println("" + throwable.getMessage()));
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
