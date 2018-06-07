import rx.Observable;

;

public class Main {

    public static void main(String[] args) {
        Observable<String> source =
                Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
//first observer
        source.subscribe(s -> System.out.println("Observer 1 Received: " + s));
        System.out.println("------------------------ \n\n");
//second observer
        source.map(s -> s.length())
                .filter(x->x>5)
                .subscribe(s -> System.out.println("Observer 2 Received: " + s));

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
