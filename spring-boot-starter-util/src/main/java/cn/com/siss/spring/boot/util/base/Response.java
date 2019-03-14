package cn.com.siss.spring.boot.util.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @ClassName Response
 * @Description TODO
 * @Author clare
 * @Date 2019/3/11 10:26
 * @Version 1.0
 */

@Slf4j
@Getter
@Setter
@ToString
public class Response  implements Serializable {

    private Integer code;

    private String message;

}
