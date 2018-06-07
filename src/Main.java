import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Main {

    public static void main(String[] args) {
        List<String> companyMems = Arrays.asList("huu", "Dien", "Nam", "Bao", "Khoa");

        Observable<String> source = Observable.from(companyMems);
        source.map(String::length) // when map --> source is a list of inteter
                .filter(x -> x > 3)
                .subscribe(s -> System.out.println("haveMap: " + s));
        source // when map --> source is a list of inteter
                .filter(x -> x.length() > 3)
                .subscribe(s -> System.out.println("noMap:  " + s));

    }


    public static void sleep(long millis) {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
