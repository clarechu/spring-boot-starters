package cn.com.siss.spring.boot.util.base.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
public class BaseParam implements Serializable {


    private Long createdBy  = 888888L;

    private Long updatedBy  =   888888L;

    private Long createdTime;

    private Long updatedTime;

    private Integer deleted ;

}
