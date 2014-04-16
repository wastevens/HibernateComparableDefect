package com.dstevens.demonstration;

import java.util.*;
import java.util.function.Function;
import javax.persistence.*;

@Entity
@Table(name="Foo")
public class Foo implements Comparable<Foo> {

    @Id
    private final String id;
    
    @Column(name="name")
    private final String name;
    
    @Column(name="deleted_at")
    private final Date deletedAt;
    
    //Hibernate only
    @SuppressWarnings("unused")
    private Foo() {
        this(null, null, null);
    }
    
    public Foo(String id, String name) {
        this(id, name, null);
    }
    
    public Foo(String id, String name, Date deletedAt) {
        this.id = id;
        this.name = name;
        this.deletedAt = deletedAt;
    }
    
    public Foo delete(Date deletedAt) {
        return new Foo(id, name, deletedAt);
    }
    
    @Override
    public boolean equals(Object that) {
        if (that instanceof Foo) {
            return ((Foo)that).id.equals(this.id);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @Override
    public String toString() {
        return "Foo[id: " + id + ", name: " + name +", deletedAt: " + deletedAt +"]";
    }
    
    @Override
    public int compareTo(Foo that) {
        Function<Foo, Date> byDeletedTimestamp = new Function<Foo, Date>() {
            @Override
            public Date apply(Foo t) {
                return t.deletedAt;
            }
        };
        Function<Foo, String> byName = new Function<Foo, String>() {
            @Override
            public String apply(Foo t) {
                return t.name;
            }
        };
        return Comparator.comparing(byDeletedTimestamp, Comparator.nullsFirst(Comparator.naturalOrder())).
                      thenComparing(Comparator.comparing(byName)).
                      compare(this, that);
    }

}
