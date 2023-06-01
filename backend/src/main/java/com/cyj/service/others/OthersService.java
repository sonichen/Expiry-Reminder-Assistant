package com.cyj.service.others;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyj.pojo.Others;
import com.cyj.utils.JsonObject;

import javax.print.attribute.standard.MediaSize;

public interface OthersService extends IService< Others> {

    /**
     * 添加
     *
     * @param others
     * @return int
     */
    int add(Others others);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param others
     * @return int
     */
    int updateData(Others others);

    /**
     * id查询数据
     *
     * @param id id
     * @return Others
     */
    Others findById(Long id);

    Others findByModuleName(String module);
}
