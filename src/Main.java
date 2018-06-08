import org.reactivestreams.Subscriber;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class Main {
    private static final CompositeDisposable disposables
            = new CompositeDisposable();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1000);

        Observable<Long> seconds =
                Observable.interval(1, TimeUnit.SECONDS);
        Disposable disposable1 = seconds.subscribe(l -> System.out.println("Observer 1: " + l));
        Disposable disposable2 = seconds.subscribe(l -> System.out.println("Observer 2: " + l));
        sleep(5000);
        disposables.addAll(disposable1, disposable2);
        disposables.dispose();
        //sleep 5 seconds to prove
        //there are no more emissions
        sleep(5000);

//        latch.await();


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
