package com.hao.managebackend.Service;

import com.hao.commonmodel.common.Page;
import com.hao.commonmodel.mail.Mail;

import java.util.Map;

public interface MailService {

    void saveMail(Mail mail);

    void updateMail(Mail mail);

    void sendMail(Mail mail);

    Mail findById(Long id);

    Page<Mail> findMails(Map<String, Object> params);
}
