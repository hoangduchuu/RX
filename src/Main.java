import org.reactivestreams.Subscriber;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.ResourceObserver;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        Observable<Long> source =
                Observable.interval(1, TimeUnit.SECONDS);
        ResourceObserver<Long> myObserver = new
                ResourceObserver<Long>() {
                    @Override
                    public void onNext(Long value) {
                        System.out.println(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }

                };
        //capture Disposable
        Disposable disposable = source.subscribeWith(myObserver);
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
