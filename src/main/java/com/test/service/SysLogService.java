package com.test.service;

import com.test.beans.LogType;
import com.test.beans.PageQuery;
import com.test.beans.PageResult;
import com.test.common.RequestHolder;
import com.test.dao.SysLogMapper;
import com.test.exception.ParamException;
import com.test.model.dto.SearchLogDto;
import com.test.model.po.*;
import com.test.param.SearchLogParam;
import com.test.util.BeanValidator;
import com.test.util.IpUtil;
import com.test.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Created by:chenxu
 * @Created date:2021/2/7 15:41
 */
@Service
@Slf4j
public class SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    public PageResult<SysLogWithBLOBs> getList(SearchLogParam param, PageQuery query){
        BeanValidator.check(query);
        SearchLogDto dto = new SearchLogDto();
        dto.setType(param.getType());
        if (StringUtils.isNotBlank(param.getBeforeSeg())){
            dto.setBeforeSeg("%"+param.getBeforeSeg()+"%");
        }
        if (StringUtils.isNotBlank(param.getAfterSeg())){
            dto.setBeforeSeg("%"+param.getAfterSeg()+"%");
        }
        if (StringUtils.isNotBlank(param.getOperator())){
            dto.setOperator("%"+param.getOperator()+"%");
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            if (StringUtils.isNotBlank(param.getFromTime())){
                dto.setFromTime(sf.parse(param.getFromTime()));
            }
            if (StringUtils.isNotBlank(param.getToTime())){
                dto.setToTime(sf.parse(param.getToTime()));
            }
        }catch (Exception e){
            throw new ParamException("date format is wrong");
        }
        int count = sysLogMapper.countBySearchDto(dto);
        if (count>0){
            List<SysLogWithBLOBs> list = sysLogMapper.getPageListBySearchDto(dto,query);
            return PageResult.<SysLogWithBLOBs>builder().total(count).data(list).build();
        }
        return PageResult.<SysLogWithBLOBs>builder().build();
    }
    public void saveDeptLog(SysDept before,SysDept after)
    {
        SysLogWithBLOBs sysLog = new SysLogWithBLOBs();
        sysLog.setType(LogType.TYPE_DEPT);
        sysLog.setTargetId(after == null ? before.getId() : after.getId());
        sysLog.setOldValue(before == null ? "" : JsonMapper.obj2String(before));
        sysLog.setNewValue(after == null ? "" : JsonMapper.obj2String(after));
        sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLog.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysLog.setOperatorTime(new Date());
        sysLog.setStatus(1);
        sysLogMapper.insertSelective(sysLog);
    }

    public void saveUserLog(SysUser before,SysUser after){

    }

    public void saveAclModule(SysPermissionModule before,SysPermissionModule after){

    }

    public void saveAclLog(SysPermission bef,SysPermission aft){

    }

    public void saveRole(SysRole bef,SysRole aft){

    }

    public void saveRoleAcl(Long roleId, List<SysRolePermission> before, List<SysRolePermission> after){
        SysLogWithBLOBs sysLog = new SysLogWithBLOBs();
        sysLog.setType(LogType.TYPE_ROLE_ACL);
        sysLog.setTargetId(roleId);
        sysLog.setOldValue(before == null ? "" : JsonMapper.obj2String(before));
        sysLog.setNewValue(after == null ? "" : JsonMapper.obj2String(after));
        sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLog.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysLog.setOperatorTime(new Date());
        sysLog.setStatus(1);
        sysLogMapper.insertSelective(sysLog);
    }

}
