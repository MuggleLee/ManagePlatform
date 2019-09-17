package com.hao.managebackend.consumer;

import com.hao.managebackend.config.RabbitmqConfig;
import com.hao.managebackend.dao.RoleMenuDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 删除角色时，处理消息
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitmqConfig.ROLE_DELETE_QUEUE)
public class RoleDeleteConsumer {

    @Autowired
    private RoleMenuDao roleMenuDao;

    /**
     * 接收到删除角色的消息<br>
     * 删除角色和菜单关系
     *
     * @param roleId
     */
    @RabbitHandler
    public void roleDeleteHandler(Long roleId) {
        log.info("接收到删除角色的消息,roleId:{}", roleId);
        try {
            roleMenuDao.delete(roleId, null);
        } catch (Exception e) {
            log.error("角色删除消息处理异常", e);
        }
    }
}
