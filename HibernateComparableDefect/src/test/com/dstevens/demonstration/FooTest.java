package com.dstevens.demonstration;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;

public class FooTest {

    @Test
    public void testOrdersByName() {
        Foo p1 = foo("Alice");
        Foo p2 = foo("Betty");
        Foo p3 = foo("Chris");
        Foo p4 = foo("David");
        
        List<Foo> initialList = list(p2, p4, p1, p3);
        List<Foo> expectedList = list(p1, p2, p3, p4);
        assertEquals(expectedList, sort(initialList));
    }
    
    @Test
    public void testOrdersByDeletedTimestamp() {
        Foo p1 = foo("David").delete(new Date(1001L));
        Foo p2 = foo("Chris").delete(new Date(1002L));;
        Foo p3 = foo("Betty").delete(new Date(1003L));;
        Foo p4 = foo("Alice").delete(new Date(1004L));;
        
        List<Foo> initialList = list(p2, p4, p1, p3);
        List<Foo> expectedList = list(p1, p2, p3, p4);
        assertEquals(expectedList, sort(initialList));
    }
    
    @Test
    public void testOrdersByNameWhenDeletedTimestampsEqual() {
        Foo p1 = foo("Alice").delete(new Date(1000L));
        Foo p2 = foo("Betty").delete(new Date(1000L));;
        Foo p3 = foo("Chris").delete(new Date(1000L));;
        Foo p4 = foo("David").delete(new Date(1000L));;
        
        List<Foo> initialList = list(p2, p4, p1, p3);
        List<Foo> expectedList = list(p1, p2, p3, p4);
        assertEquals(expectedList, sort(initialList));
    }
    
    @Test
    public void testOrdersByFoosWithDeletedTimestampsToTheBack() {
        Foo p1 = foo("Eugene");
        Foo p2 = foo("Fred");
        Foo p3 = foo("George");
        Foo p4 = foo("Harry");
        Foo deletedP1 = foo("Alice").delete(new Date(1001L));
        Foo deletedP2 = foo("Betty").delete(new Date(1002L));
        Foo deletedP3 = foo("Chris").delete(new Date(1003L));
        Foo deletedP4 = foo("David").delete(new Date(1004L));
        
        List<Foo> initialList = list(deletedP2, deletedP4, deletedP1, deletedP3, p2, p4, p1, p3);
        List<Foo> expectedList = list(p1, p2, p3, p4, deletedP1, deletedP2, deletedP3, deletedP4);
        assertEquals(expectedList, sort(initialList));
    }
    
    private Foo foo(String name) {
        return new Foo(name, name, null);
    }
    
    private <E> List<E> list(@SuppressWarnings("unchecked") E... elements) {
        List<E> list = new ArrayList<>();
        for (E e : elements) {
            list.add(e);
        }
        
        return list;
    }
    
    private <E extends Comparable<E>> List<E> sort(List<E> elements) {
        List<E> sortedList = new ArrayList<>(elements);
        Collections.sort(sortedList);
        return sortedList;
    }
    
}
