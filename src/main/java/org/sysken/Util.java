package org.sysken;

import java.math.*;

public class Util {
    /**
     * LCM メソッド
     * 与えられた二つの自然数の最小公倍数を返す．
     * @param A 一方の自然数．
     * @param B 他方の自然数．
     * @return N AとBの最小公倍数．
     */
    public static BigInteger lcm(BigInteger A, BigInteger B){
        // LCM(A, B) = (A*B)/(GCD(A, B)) の関係が成りたつ．
        BigInteger N = A.multiply(B).divide(A.gcd(B));
        return N;
    }

    /**
     * kLCM メソッド
     * 与えられた自然数kに対し，1, 2, ……, k すべての最小公倍数を返す．
     * @param k kLCMを計算する上限．
     * @return N 計算されたkLCM．
     */
    public static BigInteger kLCM(int k){
        BigInteger N = new BigInteger("1");
        for(int i = 1;i <= k;++i){
            N = lcm(N, BigInteger.valueOf(i));
        }
        return N;
    }
}
