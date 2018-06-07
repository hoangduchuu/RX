import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Main {

    public static void main(String[] args) {
        Observable<Long> secondIntervals =
                Observable
                        .interval(1, TimeUnit.MILLISECONDS)
                .take(200)
                .map(x->x*2);

        secondIntervals
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .toBlocking()
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        System.out.print("abc " + "onCompleted");

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.print("abc " + "onError");

                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("abc " + aLong);
                    }
                });


    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
