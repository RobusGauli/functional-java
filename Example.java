import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

//finding the subset of the list
//{4,5} => {}, {4}, {5}, {4, 5}


/*


IN javascript
let re = clist.reduce((acc, item) => acc.concat(acc.map(alist => alist.concat(item))), [[]])



*/

//in java this is serious shit going on

public class Example {
    public static void main(String... args) {
        List<Integer> alist = new ArrayList<>();
        alist.add(4);
        alist.add(6);
        

        List<List<Integer>> results = Lists.reduce(new ArrayList<List<Integer>>(), alist,
        new ReducePredicate<ArrayList<List<Integer>>, Integer>(){
            public ArrayList<List<Integer>> apply(ArrayList<List<Integer>> acc, Integer item) {
                return concat(acc, Lists.map(acc, new MappingPredicate<List<Integer>, List<Integer>>() {
                    public List<Integer> apply(List<Integer> alist) {
                        return immutableappend(alist, item);
                    }
                }));
            } 
        });
        System.out.println(results.size());
        for (List<Integer> a: results) {
            for(Integer i: a) {
                System.out.print(i + ' ');
            }
            System.out.println("break");
        }
    }


    public static ArrayList<List<Integer>> concat(List<List<Integer>> a, List<List<Integer>> b) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        result.addAll(a);
        result.addAll(b);
        return result;

    }

    public static List<Integer> immutableappend(List<Integer> alist, Integer i) {
        List<Integer> newList = new ArrayList<Integer>();
        newList.addAll(alist);
        newList.add(i);
        return newList;
    }
}