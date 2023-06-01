package com.cyj.service.record;

import com.cyj.dto.RecordSearchDto;
import com.cyj.pojo.Diary;
import com.cyj.pojo.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cyj.utils.JsonObject;

import java.text.ParseException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyj
 * @since 2022-05-31
 */
public interface RecordService extends IService<Record> {
    JsonObject<Record> queryAllRecord();

    public JsonObject<Record> queryRecordListByKeyword(String keywords);
    JsonObject<Record> queryRecordList(Long state);

    /**
     * 添加
     *
     * @param record 
     * @return int
     */
    int add(Record record) throws ParseException;

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
     * @param record 
     * @return int
     */
    int updateData(Record record) throws ParseException;

    /**
     * id查询数据
     *
     * @param id id
     * @return Record
     */
    Record findById(Long id);
}
