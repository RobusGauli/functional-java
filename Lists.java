import java.util.*;

public class Lists {
    public static <T> List<T> filter(List<T> src, Predicate<Boolean, T> predicate) {
        List<T> results = new ArrayList<T>();
        for(T t: src) {
            if(predicate.apply(t)) {
                results.add(t);
            }
        }
        return results;
    } 
}