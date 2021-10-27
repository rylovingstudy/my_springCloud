package com.atguigu.myrule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.ShowDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ren
 * @version 1.0
 * @description: TODO
 * @date 2021/10/27 14:25
 */
@Slf4j
public class FanXing {

    /**
     * 泛型类
     */
    public class Generic<T> {

        // key这个成员变量的类型为T,T的类型由外部指定
        private T key;

        public Generic(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
            this.key = key;
        }

        public Generic() {

        }

        public T getKey() { //泛型方法getKey的返回值类型为T，T的类型由外部指定
            return key;
        }

        // 上界通配符 < ? extends E>
        private <K extends StringBuilder, E extends StringBuilder> E test(K arg1, E arg2) {
            E result = arg2;
            arg2.compareTo(arg1);
            //.....
            return result;
        }
    }

    /**
     * 泛型接口
     */
    public interface Generator<T> {
        public T next();
    }

    public class FruitGenerator<T> implements Generator<T> {

        @Override
        public T next() {
            return null;
        }
    }

    public class FruitGenerator2 implements Generator {

        @Override
        public Object next() {
            return null;
        }
    }

    //指定泛型的类型
    public class FruitGenerator3 implements Generator<String> {

        @Override
        public String next() {
            return null;
        }
    }

    public void showKeyValue1(Generic<?> obj) {
        log.info("泛型测试" + "key value is " + obj.getKey());
    }

    /**
     * 泛型方法
     *
     * @param <T>
     */
    public <T> T genericMethod(Class<T> tClass) throws InstantiationException, IllegalAccessException {
        T t = tClass.newInstance();
        return t;
    }


    public void test() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Generic generic = new Generic(1213);
        Generic<String> stringGeneric = new Generic<>("123");
        Generic<Object> e = new Generic<>();

        StringBuilder test = generic.test(new StringBuilder(), new StringBuilder());

        Class.forName("com.atguigu.myrule.FanXing").newInstance();

        Generic<Number> num = new Generic<>(123);
        Generic<Integer> num2 = new Generic<>(123);
        showKeyValue1(num2);

        Object o = genericMethod(Class.forName("com.atguigu.myrule.FanXing"));


    }
}
