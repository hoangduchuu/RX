import rx.Observable;
import rx.observables.ConnectableObservable;

;

public class Main {

    public static void main(String[] args) {
        ConnectableObservable<String> source =
                Observable.just("Alpha","Beta","Gamma","Delta","Epsilon")
                       .publish();
//Set up observer 1
        source.subscribe(s -> System.out.println("Observer 1: " + s));
//Set up observer 2
        source.map(String::length)
                .subscribe(i -> System.out.println("Observer 2: " + i));

        // need call connect function to open coonect
        source.connect();

//Fire!
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
