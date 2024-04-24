package ro.vilauweb;

@FunctionalInterface
public interface MyCustomPredicate<T, U> {

    boolean test(T arg1, U arg2);

}

