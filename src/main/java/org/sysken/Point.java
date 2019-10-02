package org.sysken;

import java.math.*;

/* 楕円曲線上の点を表現するクラス */
public class Point {
    public BigInteger x, y;

    public Point(BigInteger x, BigInteger y){
        this.x = x;
        this.y = y;
    }
}
