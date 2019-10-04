package org.sysken;

import java.math.*;

public class Main {
    public static void main(String[] args){
        BigInteger N = new BigInteger("1715761513");
        FactorizationProcessor processor = new FactorizationProcessor();
        System.out.println(processor.factor(N));
    }
}
