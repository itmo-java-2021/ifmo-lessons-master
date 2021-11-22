package com.ifmo.lesson19;

import java.util.List;

public class ToStringObject {
    @Exclude
    private final String val1;
    private final int val2;
    private final List<String> val3;

    private final ToStringObject another;

    public  ToStringObject()
    {
        this.val1 = null;
        this.val2 = 0;
        this.val3 = null;
        this.another = null;
    }


    public ToStringObject(String val1, int val2, List<String> val3, ToStringObject another) {
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
        this.another = another;
    }
}
