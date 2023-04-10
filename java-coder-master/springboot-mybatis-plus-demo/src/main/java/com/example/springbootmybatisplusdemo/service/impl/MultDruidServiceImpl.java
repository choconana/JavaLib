package com.example.springbootmybatisplusdemo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.springbootmybatisplusdemo.dal.model.MultDruid;
import com.example.springbootmybatisplusdemo.dal.mapper.MultDruidMapper;
import com.example.springbootmybatisplusdemo.service.IMultDruidService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chen
 * @since 2022-06-06
 */
@Service
public class MultDruidServiceImpl extends ServiceImpl<MultDruidMapper, MultDruid> implements IMultDruidService {

    @Override
    @DS("master")
    public MultDruid findInMaster() {
        return baseMapper.selectById(1);
    }

    @Override
    @DS("slave_1")
    public MultDruid findInSalve() {
        return baseMapper.selectById(1);
    }

}
