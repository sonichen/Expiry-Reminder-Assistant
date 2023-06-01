package com.cyj.service.email.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyj.dto.RecordDto;
import com.cyj.mapper.CategoriesMapper;
import com.cyj.mapper.RecordMapper;
import com.cyj.mapper.UserMapper;
import com.cyj.pojo.Email;
import com.cyj.pojo.Record;
import com.cyj.pojo.User;
import com.cyj.service.email.EmailServer;
import com.cyj.service.record.RecordService;
import com.cyj.service.user.UserService;
import com.cyj.utils.SendMail;
import com.cyj.utils.TokenUtil;
import com.cyj.utils.excel.EasyExcelTools;
import com.cyj.utils.file.FileTools;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailServerImpl implements EmailServer {

    @Autowired
    UserService userService;
    @Autowired
    RecordMapper recordMapper;

    @Autowired
    CategoriesMapper categoriesMapper;
    @Override
    public void sendEmail(Long categoriesId) {
        User user= userService.findUser(Long.valueOf(TokenUtil.getTokenUserId()));
        String filePath=getFilePath(categoriesId);
        Email email=new Email("Re数据报表","来自Re的数据报表，请查收。","data.xlsx",filePath);
        SendMail sendMail=new SendMail(user,email);
        sendMail.start();
        //发送成功后删除文件

    }
    public String getFilePath(Long categoriesId){
        QueryWrapper wrapper =new QueryWrapper();
        wrapper.eq("userId",TokenUtil.getTokenUserId());
        wrapper.eq("categoriesId",categoriesId);
        List<Record> records=recordMapper.selectList(wrapper);
        List<RecordDto> recordDtos=new ArrayList<>();
        String categoriesName=categoriesMapper.selectById(categoriesId).getName();
        for(int i=0;i<records.size();i++){
            Record temp= records.get(i);
            String state=null;
            if(temp.getState()==0){
                state="未到期";
            }else if (temp.getState()==1){
                state="已过期";
            }else{
                state="临近过期";
            }
                recordDtos.add(new RecordDto(
                        categoriesName,temp.getItemName(),temp.getCloseTime(),temp.getExpireTime(),state,temp.getDescription(),temp.getCreateTime(),temp.getUpdateTime()
                ));
        }
        EasyExcelTools easyExcelTools=new EasyExcelTools(recordDtos);
        String filePath=easyExcelTools.simpleWrite();
        return filePath;
    }

}
