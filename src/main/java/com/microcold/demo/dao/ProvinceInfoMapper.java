package com.microcold.demo.dao;

import com.microcold.demo.entity.ProvinceInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProvinceInfoMapper
{
    @Insert("insert into provinceInfo(infoName,typeName)values(#{infoName},#{typeName})")
    int add(@Param("infoName") String infoName,@Param("typeName") String typeName);

    @Update("update provinceInfo set infoName=#{infoName},typeName=#{typeName} where id=#{id}")
    int update(@Param("id") int id,@Param("infoName") String infoName,@Param("typeName") String typeName);

    @Delete ("delete from provinceInfo where id= #{id}")
    int delete(int id);

    @Select("select id,infoName as infoName,typeName as typeName from provinceInfo")
    ProvinceInfo findProvinceInfo(@Param("id") int id);

    @Select("select * from provinceInfo")
    List<ProvinceInfo> findProvinceInfoList();
}
