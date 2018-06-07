import io.reactivex.disposables.Disposable;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

public class Main {

    public static void main(String[] args) {
        Observable<String> source =
                Observable.just("Alpha", "Beta", "Gamma", "Delta",
                        "Epsilon");
        Observer<Integer> myObserver = new Observer<>() {
            @Override
            public void onCompleted() {
                System.out.println("done");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("my : " + integer);
            }
        };
        source.map(s -> s.length())
                .filter(i -> i >= 5)
                .subscribe(myObserver);

    }


    public static void sleep(long millis) {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
