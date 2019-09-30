package org.sysken;

import java.math.*;

public class Main {
    public static void main(String[] args){
        byte magnitude[] = {0, 1, 0};
        BigInteger N = new BigInteger(1, magnitude);
        FactorizationProcessor processor = new FactorizationProcessor();
        processor.factor(N);
    }
}
