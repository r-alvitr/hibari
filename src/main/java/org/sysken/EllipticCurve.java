package org.sysken;

import java.math.*;

/* 位数Nの剰余類環上で定義された楕円曲線 y^2 = x^3 + bx + c */
public class EllipticCurve {
    private BigInteger N;
    private int b, c;

    public EllipticCurve(BigInteger N, int b, int c){
        this.N = N;
        this.b = b;
        this.c = c;
    }
}
