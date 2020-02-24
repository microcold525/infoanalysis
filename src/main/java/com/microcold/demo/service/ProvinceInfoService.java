package com.microcold.demo.service;

import com.microcold.demo.dao.ProvinceInfoMapper;
import com.microcold.demo.entity.ProvinceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceInfoService
{
    @Autowired
    private ProvinceInfoMapper provinceInfoMapper;

    public int add(String infoName,String typeName){
        return provinceInfoMapper.add(infoName,typeName);
    }
    public int update(int id,String infoName,String typeName){
        return provinceInfoMapper.update(id,infoName,typeName);
    }

    public int delete(int id){
        return provinceInfoMapper.delete(id);
    }
    public ProvinceInfo findProvinceInfo(int id){
        return provinceInfoMapper.findProvinceInfo(id);
    }

    public List<ProvinceInfo> findProvinceInfoList(){
        return provinceInfoMapper.findProvinceInfoList();
    }

}
