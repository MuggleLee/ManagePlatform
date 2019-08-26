package com.hao.notificationcenter.Service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hao.commonmodel.Common.Page;
import com.hao.commonunits.utils.PageUtil;
import com.hao.notificationcenter.Dao.SmsDao;
import com.hao.notificationcenter.Model.Sms;
import com.hao.notificationcenter.Service.SmsService;
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

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

	@Autowired
	private IAcsClient acsClient;
	@Value("${aliyun.sign.name1}")
	private String signName;
	@Value("${aliyun.template.code1}")
	private String templateCode;

	@Autowired
	private SmsDao smsDao;

	/**
	 * 异步发送阿里云短信
	 *
	 * @param sms
	 * @return
	 */
	@Async
	@Override
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
	@Override
	public void save(Sms sms, Map<String, String> params) {
		if (!CollectionUtils.isEmpty(params)) {
			sms.setParams(JSONObject.toJSONString(params));
		}

		sms.setCreateTime(new Date());
		sms.setUpdateTime(sms.getCreateTime());
		sms.setDay(sms.getCreateTime());

		smsDao.save(sms);
	}

	@Transactional
	@Override
	public void update(Sms sms) {
		sms.setUpdateTime(new Date());
		smsDao.update(sms);
	}

	@Override
	public Sms findById(Long id) {
		return smsDao.findById(id);
	}

	@Override
	public Page<Sms> findSms(Map<String, Object> params) {
		int total = smsDao.count(params);
		List<Sms> list = Collections.emptyList();
		if (total > 0) {
			PageUtil.pageParamConver(params, true);

			list = smsDao.findData(params);
		}
		return new Page<>(total, list);
	}

}
