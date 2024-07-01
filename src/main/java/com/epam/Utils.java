package com.epam;

import java.util.Collection;
import java.util.Map;

/*
    <p> Interface class. <p>
    Temporary this class contains the implementations of the method.
    In the future, this class will be the interface between the project and
    the external dependency like apache-commons-lang3
 */
public class Utils {

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}
