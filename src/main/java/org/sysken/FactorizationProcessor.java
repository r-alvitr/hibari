package org.sysken;

import java.math.*;
import java.util.*;

public class FactorizationProcessor {
    final int RANDOM_LIMIT_BITS = 32;
    final int RANDOM_SEED = 1470487;
    final int LCM_MAX = 10;

    public FactorizationProcessor(){

    }

    /**
     * factor メソッド
     * 与えられた自然数の非自明な約数を返す．
     * @param N 分解したい合成数
     * @return p Nの非自明な約数
     */
    public BigInteger factor(BigInteger N){
        Random random = new Random(RANDOM_SEED);
        while(true){
            BigInteger b = new BigInteger(RANDOM_LIMIT_BITS, random);
            Point p = new Point(new BigInteger(RANDOM_LIMIT_BITS, random), new BigInteger(RANDOM_LIMIT_BITS, random));
            BigInteger k = Util.generateSmooth(Util.kLCM(LCM_MAX).intValue());
            EllipticCurve curve = new EllipticCurve(N, b, p);

            // 判別式discに対し，disc > 1 かつ disc < N であればdiscはNの非自明な約数である．
            if(curve.discriminant.compareTo(BigInteger.ONE) == 1 && curve.discriminant.compareTo(N) == -1){
                return curve.discriminant;
            }

            BigInteger ret = this.calculateMultiplication(N, curve, k);
            if(!ret.equals(BigInteger.ZERO)){
                return ret;
            }
        }
    }

    /**
     * calculateMultiplication メソッド
     * Z/NZにおける楕円曲線のk倍演算を行う
     * @param N 楕円曲線が乗っている剰余類環の位数
     * @param curve 利用する楕円曲線
     * @param k 倍算の回数
     * @return p （みつかれば）Nの非自明な約数．（みつからなければ）0
     */
    public BigInteger calculateMultiplication(BigInteger N, EllipticCurve curve, BigInteger k){
        BigInteger b = curve.b;
        BigInteger c = curve.c;
        // 繰り返し二乗法により計算する．もっといい実装がある気もする（項別の剰余演算を省けるかもしれない，など）．
        int bitLength = k.bitLength();
        LinkedList<Point> points = new LinkedList<Point>();

        // 2^n * Pの計算を行う．
        Point currentPoint = curve.start;
        for(int i = 0;i < bitLength;++i){
            BigInteger x = currentPoint.x;
            BigInteger y = currentPoint.y;

            // ビットが立っているときの点情報だけ持っておけばよい．
            if(k.and(BigInteger.ONE).equals(BigInteger.ONE)){
                points.add(currentPoint);
            }
            k = k.shiftRight(1);

            // 2yとNとのGCD．これが(1, N)にあればNの非自明な約数である．Nならばこの楕円曲線での計算は放棄．
            BigInteger gcdOf2Y = y.multiply(BigInteger.valueOf(2)).gcd(N).mod(N);
            if(gcdOf2Y.compareTo(BigInteger.ONE) == 1 && gcdOf2Y.compareTo(N) == -1){
                return gcdOf2Y;
            }else if(gcdOf2Y.compareTo(N) == 0){
                return BigInteger.ZERO;
            }

            // Don't think, feel.
            BigInteger newX = ( x.pow(4).subtract( b.multiply(BigInteger.valueOf(2)).multiply(x.pow(2)) ).subtract( c.multiply(BigInteger.valueOf(8)).multiply(x) ).add( b.pow(2) ) ).multiply( y.pow(2).multiply(BigInteger.valueOf(4)).modInverse(N) ).mod(N);
            BigInteger newY = ( x.pow(6).add( b.multiply(BigInteger.valueOf(5)).multiply(x.pow(4)) ).add( c.multiply(BigInteger.valueOf(20)).multiply(x.pow(3)) ).subtract( b.pow(2).multiply(BigInteger.valueOf(5)).multiply(x.pow(2)) ).subtract( b.multiply(c).multiply(BigInteger.valueOf(4)).multiply(x) ).subtract( c.pow(2).multiply(BigInteger.valueOf(8)).add(b.pow(3))) ).multiply( y.pow(3).multiply(BigInteger.valueOf(8)).modInverse(N) ).mod(N);

            currentPoint = new Point(newX, newY);
        }

        // 2^n * Pの情報を元にkPを計算する．
        currentPoint = points.get(0);
        for(int i = 1;i < points.size();++i){
            Point newPoint = points.get(i);

            BigInteger dx = newPoint.x.subtract(currentPoint.x);
            BigInteger dy = newPoint.y.subtract(currentPoint.y);

            // dxとNとのGCD．これが(1, N)にあればNの非自明な約数である．Nならばこの楕円曲線での計算は放棄．
            BigInteger gcdOfDx = dx.gcd(N);
            if(gcdOfDx.compareTo(BigInteger.ONE) == 1 && gcdOfDx.compareTo(N) == -1){
                return gcdOfDx;
            }else if(gcdOfDx.compareTo(N) == 0){
                return BigInteger.ZERO;
            }

            // 点の加法に用いる．
            BigInteger lambda = dy.multiply( dx.modInverse(N) ).mod(N);

            BigInteger X = lambda.pow(2).subtract(currentPoint.x).subtract(newPoint.x).mod(N);
            BigInteger Y = lambda.multiply(X).multiply(BigInteger.valueOf(-1)).subtract(currentPoint.y).add(lambda.multiply(currentPoint.x));
            currentPoint = new Point(X, Y);
        }

        return BigInteger.ZERO;
    }
}
