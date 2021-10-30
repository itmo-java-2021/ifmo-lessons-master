package com.ifmo.lesson10;

import com.ifmo.lesson6.ArrayList;

import java.util.Iterator;
import java.util.List;

public class ListView<T extends Iterable, R> extends ArrayList {

    private Transformer transformer;

    public ListView(){}

    public ListView(Transformer transformer) {
        this.transformer = transformer;
    }

    @Override
    public Iterator<R> iterator() {
        Iterator<T> iterator = super.iterator();

        return new Iterator<R>() {
            Iterator iterator1 = iterator.next().iterator();

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
            public R next() {
                if (!iterator1.hasNext()){
                    iterator1 = iterator.next().iterator();
                }
                return transformer == null ? (R)iterator1.next() : (R)transformer.transform(iterator1.next());
            }
        };
    }
}
