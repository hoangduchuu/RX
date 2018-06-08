import org.reactivestreams.Subscriber;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.ResourceObserver;


public class Main {
    private static final CompositeDisposable disposables
            = new CompositeDisposable();

    public static void main(String[] args) {


        // startWith is adding the first item to the dataReturned
        Observable.just("1", "22", "33", "33")
                .startWith("init to the first")
                .subscribe(s -> System.out.println("startWith " + s));
        //

        // startWith Arrays is adding the first item to the dataReturned
        Observable.just("1", "22", "33", "33")
                .startWithArray("init to the first", "init to seconds")
                .subscribe(s -> System.out.println("startWithArrays " + s));


        // switchIfEmpty : we can put new observable stream value if condition is empty !!
        Observable.just("1", "22", "33", "33")
                .startWithArray("init to the first", "init to seconds")
                .filter(x -> x.length() > 1000)
                .switchIfEmpty(Observable.just("Zeta", "Eta", "Theta"))
                .defaultIfEmpty("khong co")
                .subscribe(s -> System.out.println("defaultIfEmpty " + s));

        Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                .sorted(Comparator.reverseOrder()).toList()
                .subscribe(s -> System.out.println("sorted() " + s));
        // repeat will run again
        Observable.just("Alpha", "Beta", "Gamma", "Delta",
                "Epsilon","-------------")
//                .delay(3, TimeUnit.SECONDS)
                .repeat(3)
                .delay(1,TimeUnit.SECONDS)
                .subscribe(s -> System.out.println("repeat(): " + s));

        sleep(5000);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static final String TAG = Main.class.getSimpleName();

}
