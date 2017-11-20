interface MappingPredicate<T1, T2> {
    //takes in type of T1 and return type of T2
    //person to string or watervare
    public T2 apply(T1 t);
}