package com.hao.managebackend.Service;


import com.hao.commonmodel.Common.Page;
import com.hao.managebackend.Model.BlackIP;

import java.util.Map;

public interface BlackIPService {

    void save(BlackIP blackIP);

    void delete(String ip);

    Page<BlackIP> findBlackIPs(Map<String, Object> params);

}
