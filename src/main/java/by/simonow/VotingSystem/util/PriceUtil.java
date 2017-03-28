package by.simonow.VotingSystem.util;


public class PriceUtil {


    public static String format(Integer price) {
        return (price / 100 + "," + price % 100);
    }
}
