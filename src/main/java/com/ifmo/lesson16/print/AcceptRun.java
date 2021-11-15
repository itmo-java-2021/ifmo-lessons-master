package com.ifmo.lesson16.print;

import java.io.IOException;

public interface AcceptRun {
    void run(Message message, String host) throws IOException;
}
