package com.ifmo.lesson10;

import com.ifmo.lesson6.ArrayList;

import java.util.Iterator;
import java.util.List;

public class ListView<T extends Iterable> extends ArrayList {

    @Override
    public Iterator iterator() {
        Iterator iterator = super.iterator();

        return new Iterator() {
            Iterator iterator1 = ((T)iterator.next()).iterator();

            @Override
            public boolean hasNext() {
                if (!iterator.hasNext()){
                    if (!iterator1.hasNext()){
                        return false;
                    }
                }
                return true;
            }

            @Override
            public Object next() {
                if (!iterator1.hasNext()){
                    iterator1 = ((T)iterator.next()).iterator();
                }
                return iterator1.next();
            }
        };
    }
}
