package com.test.model.dto;

import com.google.common.collect.Lists;
import com.test.model.po.SysDept;
import com.test.model.po.SysPermissionModule;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Created by:chenxu
 * @Created date:2021/1/25 21:36
 */
@Data
public class PermissionModuleLevelDto extends SysPermissionModule{

    private List<PermissionModuleLevelDto> permissionModuleLevelDtoList = Lists.newArrayList();

    public static PermissionModuleLevelDto adapt(SysPermissionModule sysPermissionModule){
        PermissionModuleLevelDto dto = new PermissionModuleLevelDto();
        BeanUtils.copyProperties(sysPermissionModule,dto);
        return dto;
    }
}
