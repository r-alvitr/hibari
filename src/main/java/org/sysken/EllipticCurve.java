package org.sysken;

import java.math.*;

/* 位数Nの剰余類環上で定義された楕円曲線 y^2 = x^3 + bx + c */
public class EllipticCurve {
    private BigInteger N;
    private BigInteger b, c;
    public Point start;
    public BigInteger discriminant;

    public EllipticCurve(BigInteger N, BigInteger b, Point p){
        this.N = N;
        this.b = b;
        this.start = p;
        this.c = p.y.pow(2).subtract(p.x.pow(3).add(p.x.multiply(b))).mod(N); // cの値を逆算的に決める．

        //判別式を計算する．
        this.discriminant = b.pow(3).multiply(BigInteger.valueOf(4)).add( c.pow(2).multiply(BigInteger.valueOf(27))).gcd(N); // det = GCD(N, (4 b^2 + 27 c^3))
    }
}
