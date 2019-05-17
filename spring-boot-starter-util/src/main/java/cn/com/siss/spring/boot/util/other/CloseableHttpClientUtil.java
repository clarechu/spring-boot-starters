package cn.com.siss.spring.boot.util.other;

import cn.com.siss.spring.boot.util.base.BackResponseUtil;
import cn.com.siss.spring.boot.util.base.BaseResponse;
import cn.com.siss.spring.boot.util.base.ReturnCodeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CloseableHttpClientUtil {

    @Autowired
    private CloseableHttpClient closeableHttpClient;

    private static CloseableHttpClient client;

    private CloseableHttpClientUtil(){}

    @PostConstruct
    private void init(){
        client=closeableHttpClient;
    }

    /*public static <T> BaseResponse<T> get(String url,String token,Object param,Class<T> clz) throws Exception {
        HttpPost post=new HttpPost(url);
        post.addHeader("Authorization", token);
        StringEntity requestEntity = new StringEntity(JSON.toJSONString(param),"utf-8");
        requestEntity.setContentEncoding("UTF-8");
        post.setEntity(requestEntity);
        CloseableHttpResponse response = client.execute(post);
        String res = EntityUtils.toString(response.getEntity());
        BaseResponse baseResponse=JSON.parseObject(res, BaseResponse.class);
        Object data=baseResponse.getData();
        if (data==null){
            return baseResponse;
        }
        return null;
    }*/

    public static <T> BaseResponse<List<T>> postForList(String url, String token, Object param, Class<T> clz){
        HttpEntity httpEntity=null;
        try{
            HttpPost post=new HttpPost(url);
            post.addHeader("Authorization", token);
            post.addHeader("Content-Type","application/json");
            StringEntity requestEntity = new StringEntity(JSON.toJSONString(param),"utf-8");
            requestEntity.setContentEncoding("UTF-8");
            post.setEntity(requestEntity);
            CloseableHttpResponse response = client.execute(post);
            httpEntity=response.getEntity();
            String res = EntityUtils.toString(httpEntity);
            BaseResponse baseResponse=JSON.parseObject(res, BaseResponse.class);
            Object data=baseResponse.getData();
            if (!baseResponse.getCode().equals(ReturnCodeEnum.MESSAGE_COMMON_SUCCESS.getCode())){
                return baseResponse;
            }
            List<T> list=new ArrayList<>();
            JSONArray array=(JSONArray)data;
            for (int i=0;i<array.size();i++){
                //T t1=clz.newInstance();
                //BeanUtils.copyProperties(array.get(i),t1);
                T t1=BeanUtil.mapToBean((JSONObject)array.get(i),clz);
                list.add(t1);
            }
            baseResponse.setData(list);
            return baseResponse;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_COMMON_FAILED.getCode());
        }finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }

    }

    /*public static void main(String[] args) {
        RLottery lottery=new RLottery();
        lottery.setId(System.currentTimeMillis());
        List<RLottery> list=new ArrayList<>();
        list.add(lottery);

        BaseResponse<List<RLottery>> baseResponse=BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_COMMON_SUCCESS.getCode());
        baseResponse.setData(list);
        String json=JSON.toJSONString(baseResponse);

        BaseResponse<List<RLottery>> response= JSONObject.parseObject(json,BaseResponse.class);

        JSONArray array=(JSONArray)response.getData();
        List<RLottery> lotteryList=new ArrayList<>();
        for (int i = 0; i <array.size() ; i++) {
            RLottery l= new RLottery();
            BeanUtils.copyProperties(array.get(i),RLottery.class);
            lotteryList.add(l);
        }
        response.setData(lotteryList);
        System.out.println(response);

    }*/

}
