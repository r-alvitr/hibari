package org.sysken;

import java.math.*;

public class Main {
    public static void main(String[] args){
        BigInteger N = new BigInteger("1");
        FactorizationProcessor processor = new FactorizationProcessor();
        processor.factor(N);
    }
}
