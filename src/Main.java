import org.reactivestreams.Subscriber;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


        // defaultIfEmpty help your item when your stream is Empty
        Observable.just("1", "22", "33", "33")
                .startWithArray("init to the first", "init to seconds")
                .filter(x->x.length() >1000)
                .defaultIfEmpty("khong co")
                .subscribe(s -> System.out.println("defaultIfEmpty " + s));

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
