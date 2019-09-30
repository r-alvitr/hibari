package org.sysken;

import java.math.*;

public class Util {
    /**
     * LCM メソッド
     * 与えられた二つの自然数の最小公倍数．
     * @param A 一方の自然数．
     * @param B 他方の自然数．
     * @return N AとBの最小公倍数．
     */
    public static BigInteger LCM(BigInteger A, BigInteger B){
        // LCM(A, B) = (A*B)/(GCD(A, B)) の関係が成りたつ．
        BigInteger N = A.multiply(B).divide(A.gcd(B));
        return N;
    }
}
