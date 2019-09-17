package com.hao.notificationcenter.service;

import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hao.notificationcenter.mapper.SmsMapper;
import com.hao.notificationcenter.model.Sms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Muggle Lee
 * @Date: 2019/7/30 18:54
 */
@Slf4j
@Service
public class SmsService {

    @Autowired
    private IAcsClient acsClient;

    @Value("${aliyun.sign.name1}")
    private String signName;

    @Value("${aliyun.template.code1}")
    private String templateCode;

    @Autowired
    private SmsMapper smsMapper;

    /**
     * 异步发送阿里云短信
     *
     * @param sms
     * @return
     */
    @Async
    public SendSmsResponse sendSmsMsg(Sms sms) {
        if (sms.getSignName() == null) {
            sms.setSignName(this.signName);
        }
        if (sms.getTemplateCode() == null) {
            sms.setTemplateCode(this.templateCode);
        }
        // 阿里云短信官网demo代码
        SendSmsRequest request = new SendSmsRequest();
        request.setMethod(MethodType.POST);
        request.setPhoneNumbers(sms.getPhone());
        request.setSignName(sms.getSignName());
        request.setTemplateCode(sms.getTemplateCode());
        request.setTemplateParam(sms.getParams());
        request.setOutId(sms.getId().toString());
        SendSmsResponse response = null;
        try {
            response = acsClient.getAcsResponse(request);
            if (response != null) {
                log.info("发送短信结果：code:{}，message:{}，requestId:{}，bizId:{}", response.getCode(), response.getMessage(),
                        response.getRequestId(), response.getBizId());
                sms.setCode(response.getCode());
                sms.setMessage(response.getMessage());
                sms.setBizId(response.getBizId());
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        update(sms);
        return response;
    }

    /**
     * 保存短信记录
     *
     * @param sms
     * @param params
     */
    @Transactional
    public void save(Sms sms, Map<String, String> params) {
        if (!CollectionUtils.isEmpty(params)) {
            sms.setParams(JSONObject.toJSONString(params));
        }
        sms.setCreateTime(new Date());
        sms.setUpdateTime(sms.getCreateTime());
        sms.setDay(sms.getCreateTime());
        sms.insert();
    }

    @Transactional
    public void update(Sms sms) {
        sms.setUpdateTime(new Date());
        sms.updateById();
    }

    public Sms findById(Long id) {
        return smsMapper.selectOne(new QueryWrapper<Sms>().eq("id", id));
    }

    public IPage<Sms> findSms(Map<String, Object> params) {
        Sms log = new Sms();
        long pageNum = null == params.get("draw") ? 1 : Long.valueOf(params.get("draw").toString());
        Page<Sms> page = new Page(pageNum, 10);
        IPage<Sms> iPage = log.selectPage(page, null);
        return iPage;
    }

}
