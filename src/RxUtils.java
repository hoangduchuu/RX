/**
 * Created by hoang on 6/7/18 nhe.
 */
class RxUtils {


    public static void main(String[] args) {


        while (alive()) {
            eat();
            code();
            sleep();
        }


    }


    public static boolean alive() {
        return true;
    }

    public static String eat() {
        sleep();
        return "ăn như heo";
    }

    public static String code() {
        return "";
    }

    public static String sleep() {
        return "ngủ như heo";
    }
}
