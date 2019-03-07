package cn.com.siss.spring.boot.util;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @ClassName RsaTest
 * @Description TODO
 * @Author clare
 * @Date 2019/3/5 09:04
 * @Version 1.0
 */
public class RsaTest {

    @Test
    public void decode() throws FileNotFoundException {
        System.out.println(System.getProperty("~/.ssh/id_rsa"));
        FileInputStream fis = new FileInputStream("/Users/clare/.ssh/id_rsa");
    }
}
