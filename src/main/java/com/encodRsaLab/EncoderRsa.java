package com.encodRsaLab;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class EncoderRsa {
    private long p;
    private long q;
    long d;
    long n;
    private String inputData;
    private final char[] characters = new char[]{ '#', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И',
            'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С',
            'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ы', 'Ъ',
            'Э', 'Ю', 'Я', ' ', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', '0' };

    public long getP() { return p; }

    public long getQ() { return q; }

    public void setQ(long q) { this.q = q; }

    public void setP(long p) { this.p = p; }

    public String getInputData() {
        inputData=RSAEncode();
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    private List<String> RSAEncodeData(String s, long e, long n) {
        List<String> result = new ArrayList<>();
        BigInteger bi;

        for (int i = 0; i < s.length(); i++)
        {
            int index = findIndex(characters, s.charAt(i));

            bi = new BigInteger(Integer.toString(index));

            bi=bi.pow((int)e);


            BigInteger N = new BigInteger(Long.toString(n));

            bi = bi.mod(N);

            result.add(bi.toString());
        }
        return result;
    }

    private int findIndex(char arr[], char t) {

        // если массив пуст возвращаем -1
        if (arr == null)
            return -1;

        // выполняем поиск элемента и возвращаем его индекс, если находим его в массиве
        for ( int i = 0; i < arr.length; i++)
            if (arr[i] == t)
                return i;

        //если не нашли, то возвращаем -1
        return -1;
    }
    private long calculateD(long m) {
        long d = m - 1;

        for (long i = 2; i <= m; i++)
            if ((m % i == 0) && (d % i == 0)) //если имеют общие делители
            {
                d--;
                i = 1;
            }

        return d;
    }
    private long calculateE(long d, long m) {
        long e = 10;

        while (true)
        {
            if ((e * d) % m == 1)
                break;
            else
                e++;
        }

        return e;
    }
    public String RSAEncode() {

        if (isTheNumberSimple(p) && isTheNumberSimple(q)) {
            String s = inputData;
            s = s.toUpperCase();

            long n = p * q;
            long m = (p - 1) * (q - 1);
            long d = calculateD(m);
            long e_ = calculateE(d, m);

            String result = "";

            this.d = d;
            this.n = n;
            for (String substring : RSAEncodeData(s, e_, n)){
                result+=substring;
            }
            return result;
        }

        return "Нет Результата";
    }
    private boolean isTheNumberSimple(long n) {
        if (n < 2)
            return false;

        if (n == 2)
            return true;

        for (long i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }
}
