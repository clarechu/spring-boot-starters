package cn.com.siss.spring.boot.util.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @ClassName BaseResponse
 * @Description 基类 baseResponse
 * @Author clare
 * @Date 2019/2/19 13:41
 * @Version 1.0
 */

@Slf4j
@Getter
@Setter
@ToString
public class BaseResponse implements Serializable {

    private Integer code;

    private String message;

    private Object data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof BaseResponse) {
            BaseResponse baseResponse = (BaseResponse) o;
            if (baseResponse.getCode() .equals(this.code) && baseResponse.getMessage().equals(this.message) ) return true;
            return false;
        }
        return false;
    }

}
