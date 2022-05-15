package com.lzc.serviceRental.service;

import com.lzc.serviceRental.common.exception.JSONResponse;
import com.lzc.serviceRental.entity.dto.HireDTO;
import com.lzc.serviceRental.entity.model.Hire;

import java.util.List;

/**
 * @Description
 * @Author lzc
 * @Date 2022/5/4
 * @Version 1.0
 */
public interface HireService {

    JSONResponse insertHire(Hire hire,Integer type);

    List<HireDTO> queryUserHire(Integer userId, Integer type);

    JSONResponse deleteHire(Integer hireId, Integer type);
}
