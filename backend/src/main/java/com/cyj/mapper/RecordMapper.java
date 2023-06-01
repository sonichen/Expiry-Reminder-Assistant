package com.cyj.mapper;

import com.cyj.pojo.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cyj
 * @since 2022-05-31
 */
@Repository
public interface RecordMapper extends BaseMapper<Record> {
    public List<Record> queryRecords(String userId,String state);
    public Long queryCountsByCategoriesId(Long categoriesId);
    public List<Record>queryRecordsByKeywords(String itemName,String userId);
    List<Record> queryAllRecord(String userId);
}
