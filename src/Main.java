import org.reactivestreams.Subscriber;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1000);

        Observable
                .interval(1, 1, TimeUnit.SECONDS)
                .take(100)
                .subscribe(new Observer<Long>() {
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable disposable) {
                        this.disposable = disposable;

                        System.out.println("onSubscribe");

                    }

                    @Override
                    public void onNext(Long aLong) {
                        latch.countDown();
                        System.out.println("onNext along: " + aLong + "--cound: - " + latch.getCount());
                        if (aLong == 2){
                            disposable.dispose();
                        }

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onerrors");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("oncomplete");
                    }
                });

//        latch.await();
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
