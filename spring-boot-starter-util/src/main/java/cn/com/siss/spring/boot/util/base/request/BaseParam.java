package cn.com.siss.spring.boot.util.base.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
public class BaseParam implements Serializable {

    private String openId;

    private String cardId;

    private String shopId;

}
