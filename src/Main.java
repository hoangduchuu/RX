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

    public static void main(String[] args) {
        Observable.just(1, 3, 4, 32, 4, 32, 432)
                .filter(s -> s == 4)
                .take(2)
                .subscribe(myObserver());


        // take
        Observable.just("huu", "nam", "Bao", "khoa", "dien")
                .take(2)
                .takeLast(2)

                .subscribe(nameObserver());


    }

    // take 
    private static Observer<String> nameObserver() {
        return new Observer<>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println("name onSubscribe");
            }

            @Override
            public void onNext(String s) {
                System.out.println("name onNext " + s);

            }

            @Override
            public void onError(Throwable onError) {
                System.out.println("name onError " + onError.getMessage());

            }

            @Override
            public void onComplete() {
                System.out.println("name onComplete ");
            }
        };
    }

    private static Observer<Integer> myObserver() {
        return new Observer<>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println("onSubscribe");
            }


            @Override
            public void onNext(Integer s) {
                System.out.println("onNext: " + s);

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError");

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
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
