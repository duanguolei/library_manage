package com.library.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.library.manage.entity.Punish;

public interface PunishMapper extends BaseMapper<Punish> {

    public void insertPuninsh(Punish punish);


}
