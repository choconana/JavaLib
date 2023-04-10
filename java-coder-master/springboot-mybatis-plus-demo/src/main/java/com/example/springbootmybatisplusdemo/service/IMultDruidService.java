package com.example.springbootmybatisplusdemo.service;

import com.example.springbootmybatisplusdemo.dal.model.MultDruid;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chen
 * @since 2022-06-06
 */
public interface IMultDruidService extends IService<MultDruid> {

    MultDruid findInMaster();

    MultDruid findInSalve();

}
