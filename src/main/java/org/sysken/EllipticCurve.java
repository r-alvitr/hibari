package org.sysken;

import java.math.*;

/* 位数Nの剰余類環上で定義された楕円曲線 y^2 = x^3 + bx + c */
public class EllipticCurve {
    private BigInteger N;
    private BigInteger b, c;
    private Point start;

    public EllipticCurve(BigInteger N, BigInteger b, Point p){
        this.N = N;
        this.b = b;
        this.start = p;
        this.c = this.start.y.pow(2).subtract(start.x.pow(3).add(this.start.x.multiply(this.b))).mod(this.N); // cの値を逆算的に決める．
    }
}
