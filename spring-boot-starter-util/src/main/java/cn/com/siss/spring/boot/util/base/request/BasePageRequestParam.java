package cn.com.siss.spring.boot.util.base.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BasePageRequestParam implements Serializable {

    private String shopId;

    private Integer pageSize;

    private Integer pageNum;




}
