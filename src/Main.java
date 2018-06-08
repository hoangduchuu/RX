import org.reactivestreams.Subscriber;

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
import io.reactivex.observers.ResourceObserver;


public class Main {
    private static final CompositeDisposable disposables
            = new CompositeDisposable();

    public static void main(String[] args) throws InterruptedException {

        Observable<Integer> source = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> triger) throws Exception {
                try {
                    for (int i = 0; i < 2; i++) {
                        triger.onNext(i);
                        System.out.println(i);

                        if (triger.isDisposed())
                            return;
                    }
                    triger.onComplete();
                } catch (Throwable e) {
                    triger.onError(e);
                }
            }
        });


        sleep(500);
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
