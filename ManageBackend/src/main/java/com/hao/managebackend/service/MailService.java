package com.hao.managebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hao.commonmodel.mail.Mail;
import com.hao.commonmodel.mail.constants.MailStatus;
import com.hao.commonmodel.user.AppUser;
import com.hao.commonunits.utils.AppUserUtils;
import com.hao.managebackend.mapper.MailMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class MailService{

//    @Autowired
//    private MailDao mailDao;
    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private MailMapper mailMapper;

    /**
     * 保存邮件
     *
     * @param mail
     */
    @Transactional
    public void saveMail(Mail mail) {
        if (mail.getUserId() == null || StringUtils.isBlank(mail.getUsername())) {
            AppUser appUser = AppUserUtils.getLoginAppUser();
            if (appUser != null) {
                mail.setUserId(appUser.getId());
                mail.setUsername(appUser.getUsername());
            }
        }
        if (mail.getUserId() == null) {
            mail.setUserId(0L);
            mail.setUsername("系统邮件");
        }
        if (mail.getCreateTime() == null) {
            mail.setCreateTime(new Date());
        }
        mail.setUpdateTime(mail.getCreateTime());
        mail.setStatus(MailStatus.DRAFT);
        mail.insert();
        log.info("保存邮件：{}", mail);
    }

    /**
     * 修改未发送邮件
     *
     * @param mail
     */
    @Transactional
    public void updateMail(Mail mail) {
        Mail oldMail = mail.selectById();
        if (oldMail.getStatus() == MailStatus.SUCCESS) {
            throw new IllegalArgumentException("已发送的邮件不能编辑");
        }
        mail.setUpdateTime(new Date());
        mail.updateById();
        log.info("修改邮件：{}", mail);
    }

    /**
     * 异步发送邮件
     *
     * @param mail
     */
    @Async
    public void sendMail(Mail mail) {
        boolean flag = sendMailService.sendMail(mail.getToEmail(), mail.getSubject(), mail.getContent());
        mail.setSendTime(new Date());
        mail.setStatus(flag ? MailStatus.SUCCESS : MailStatus.ERROR); // 邮件发送结果
        mail.updateById();
    }

    public Mail findById(Long id) {
        return mailMapper.selectOne(new QueryWrapper<Mail>().eq("id",id));
    }

    public IPage<Mail> findMails(Map<String, Object> params) {
        // 获取页面传来的页数
        long pageNum = null == params.get("draw") ? 1 : Long.valueOf(params.get("draw").toString());
        Page<Mail> page = new Page<>(pageNum, 10);
        IPage<Mail> iPage = new Mail().selectPage(page, null);
        return iPage;
    }
}
