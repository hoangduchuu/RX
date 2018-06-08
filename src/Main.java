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

        // map();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");
        Observable.just("1/3/2016", "5/9/2016", "10/12/2016")
                .map(s -> LocalDate.parse(s, dtf))
                .subscribe(i -> System.out.println("RECEIVED: " + i));


        // cast()
        Observable<Object> items =
                Observable.just("Alpha", "Beta", "Gamma")
                        .map(s -> (Object) s);

        items.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object s) throws Exception {
                System.out.println("acept " + s);
            }
        });

        // cast string to int
        Observable<Object> myList = Observable.just("1", "22", "33", "33");
        myList.cast(Object.class)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        System.out.println("castIn onSubscribe");
                    }

                    @Override
                    public void onNext(Object value) {
                        if (value.getClass() == Integer.class) {
                            System.out.println("This is an Integer");
                        } else if (value.getClass() == String.class) {
                            System.out.println("This is a String");
                        } else if (value.getClass() == Float.class) {
                            System.out.println("This is a Float");
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("castIn onerrors " + throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("castIn onComplete");
                    }
                });


        //

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
