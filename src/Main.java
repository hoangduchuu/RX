import java.util.concurrent.TimeUnit;


;import rx.Observable;

public class Main {

    public static void main(String[] args) {
        Observable.interval(1, 1, TimeUnit.SECONDS)
                .map(x -> x * 2)
                .toBlocking()
                .subscribe(s -> System.out.println(s+ " Mississippi"));
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
