import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Main {

    public static void main(String[] args) {
        // the onNext
        Observable<Integer> source =
                Observable.create(myEmmitter -> {
                    myEmmitter.onNext(1);
                    sleep(1000);
                    myEmmitter.onNext(2);
                    sleep(1000);
                    myEmmitter.onNext(3);
                    sleep(1000);
                    myEmmitter.onNext(4);
                    sleep(1000);
                    myEmmitter.onNext(5);
                    sleep(1000);
                    myEmmitter.onCompleted();
                });
        System.out.println("ok");
        source.subscribe(s -> System.out.println("RECEIVED: " + s));


        // the onError
        Observable<String> sourceErrors = Observable.create(emitter -> {
            try {
                emitter.onNext("Alpha");
                emitter.onNext("Beta");
                emitter.onNext("Gamma");
                emitter.onNext("Delta");
                emitter.onNext("Epsilon");
                emitter.onCompleted();
            } catch (Throwable e) {
                emitter.onError(e);
            }
        });
        sourceErrors
                .subscribe(s -> System.out.println("Mysourcez: " + s),
                        (Throwable throwable) -> {
                            throwable.printStackTrace();
                            System.out.println("Mysourcez error: " + throwable.getLocalizedMessage());

                        });


        ///source test filter
        Observable<String> sourceFillterz = Observable.create(emitter -> {

            try {
                emitter.onNext("Alpha");
                emitter.onNext("Beta");
                emitter.onNext("Gamma");
                emitter.onNext("Delta");
                emitter.onNext("Epsilon");
                emitter.onNext("a");
                emitter.onNext("v");
                emitter.onCompleted();
            } catch (Throwable e) {
                emitter.onError(e);
            }
        });
        Observable<Integer> myLength = sourceFillterz.map(String::length);
        Observable<Integer> filtered = myLength.filter(i -> i >= 2);
        filtered.subscribe(s -> System.out.println("testfillter : " + s));
    }


    public static void sleep(long millis) {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
