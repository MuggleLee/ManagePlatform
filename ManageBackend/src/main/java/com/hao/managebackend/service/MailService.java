package com.hao.managebackend.Service;

import com.hao.commonmodel.Common.Page;
import com.hao.commonmodel.Mail.Mail;

import java.util.Map;

public interface MailService {

    void saveMail(Mail mail);

    void updateMail(Mail mail);

    void sendMail(Mail mail);

    Mail findById(Long id);

    Page<Mail> findMails(Map<String, Object> params);
}
