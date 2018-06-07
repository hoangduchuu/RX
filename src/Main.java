import java.util.concurrent.TimeUnit;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;



public class Main {

    public static void main(String[] args) {
        Observable<String> source =
                Observable.just("Alpha", "Beta", "Gamma");
        source.first("abc") //returns a Single
                .subscribe(x -> System.out.println(x), throwable -> System.out.println("" + throwable.getMessage()));

        Maybe<Integer> intSource = Maybe.just(1);
        intSource.subscribe(new MaybeObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onSuccess(Integer integer) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });

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
