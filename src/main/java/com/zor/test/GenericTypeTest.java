package com.zor.test;

/**
 * JAVA为什么不支持泛型数组
 * https://www.cnblogs.com/scutwang/articles/3735219.html
 *
 * @date 2020/5/6
 */
public class GenericTypeTest {

    public static void main(String[] args) {
        String[] strArray = new String[20];
        Object[] objArray = strArray;
        objArray[0] = new String(""); // throws ArrayStoreException at runtime

    }

    private void test() {
        /*
        //下面的代码使用了泛型的数组，是无法通过编译的
        GenTest<String> genArr[] = new GenTest<String>[2];
        Object[] test = genArr;
        GenTest<StringBuffer> strBuf = new GenTest<StringBuffer>();
        strBuf.setValue(new StringBuffer());
        test[0] = strBuf;
        GenTest<String> ref = genArr[0]; //上面两行相当于使用数组移花接木，让Java编译器把GenTest<StringBuffer>当作了GenTest<String>
        String value = ref.getValue();// 这里是重点！
         */
    }

    public class GenTest<T> {
        T value;

        public T getValue() {
            return value;
        }

        public void setValue(T t) {
            value = t;
        }
    }
}
