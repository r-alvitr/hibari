package org.sysken;

import java.math.*;
import java.util.*;

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

    /**
     * generateSmooth メソッド
     * 与えられた自然数kに対し，k-Smoothな自然数を返す．
     * @param k Smoothさの上限．
     * @return N k-Smoothな自然数．
     */
    public static BigInteger generateSmooth(int k){
        LinkedList<Integer> primes = Util.sieve(k);
        BigInteger N = new BigInteger("1");
        for(int prime : primes){
            int power = (int) Math.floor(Math.log(k) / Math.log(prime));
            N = N.multiply(BigInteger.valueOf((int) Math.pow(prime, power)));
        }
        return N;
    }

    /**
     * sieve メソッド
     * 与えられた自然数kに対し，k以下の素数を要素として持つリストをEratosthenesふるい法により計算し，返す．
     * @param k 素数をリストアップする上限．
     * @return primes 1以上k以下の素数をすべて要素に持つリスト．
     */
    public static LinkedList<Integer> sieve(int k){
        if(k < 2){
            return new LinkedList<Integer>();
        }

        LinkedList<Integer> primes = new LinkedList<Integer>();
        LinkedList<Integer> numbers = new LinkedList<Integer>();

        // TODO: 最適化する．
        for(int i = 2;i <= k;++i){
            numbers.add(i);
        }

        while(numbers.size() > 0){
            int nextPrime = numbers.remove();
            for(int i = nextPrime * nextPrime;i <= k;i += nextPrime){
                numbers.removeFirstOccurrence(i);
            }
            primes.add(nextPrime);
        }
        return primes;
    }
}
