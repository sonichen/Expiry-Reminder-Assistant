package com.cyj.service.diary;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyj.pojo.Diary;
import com.cyj.utils.JsonObject;

public interface DiaryService extends IService<Diary> {


    JsonObject<Diary> queryDiaryList( );

    /**
     * 添加
     *
     * @param diary
     * @return int
     */
    int add(Diary diary);

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
     * @param diary
     * @return int
     */
    int updateData(Diary diary);

    /**
     * id查询数据
     *
     * @param id id
     * @return Diary
     */
    Diary findById(Long id);
}
