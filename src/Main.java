import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class Main {

    public static void main(String[] args) {
        Single<String> source =
                Single.just("Beta");
        source.subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println("onSubscribe 1");

            }

            @Override
            public void onSuccess(String s) {
                System.out.println("onSuccess : " + s);

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });


        //maybe
        Maybe<String> intSource = Maybe.just("z");
        intSource.subscribe(new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onSuccess(String integer) {
                System.out.println("onSuccess : " + integer);

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError : ");

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete : ");

            }
        });


        // compllete able
        Completable.complete()
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        System.out.println("complete:  onsubcribe");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("complete:  onComplete with no data");

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("complete:  onError with no data");

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
