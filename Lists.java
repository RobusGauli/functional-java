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

    public static <T1, T2> List<T2> map(List<T1> src, MappingPredicate<T1, T2> predicate) {
        List<T2> results = new ArrayList<T2>();
        for(T1 t: src) {
            T2 n = predicate.apply(t);
            results.add(n);
        }
        return results;
    }
    //accumulator A
    public static <A, T> A reduce(A seed, List<T> src, ReducePredicate<A, T> reduce) {
        A accum = seed;
        for(T t: src) {
            accum  = reduce.apply(accum, t);

        }
        return accum;
    } 
}
