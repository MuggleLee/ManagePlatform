package com.hao.managebackend.dao;

import com.hao.managebackend.model.BlackIp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlackIPDao {

    @Insert("insert into black_ip(ip, createTime) values(#{ip}, #{createTime})")
    int save(BlackIp blackIp);

    @Delete("delete from black_ip where ip = #{ip}")
    int delete(String ip);

    @Select("select * from black_ip t where t.ip = #{ip}")
    BlackIp findByIp(String ip);

    int count(Map<String, Object> params);

    List<BlackIp> findData(Map<String, Object> params);
}
