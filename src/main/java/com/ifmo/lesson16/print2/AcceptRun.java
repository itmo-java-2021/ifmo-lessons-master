package com.ifmo.lesson16.print2;

import java.io.IOException;

public interface AcceptRun {
    void run(Message message, String host) throws IOException;
}
