//package com.test.service;
//
//import com.google.common.base.Preconditions;
//import com.test.common.RequestHolder;
//import com.test.dao.SysPermissionMapper;
//import com.test.dao.SysPermissionModuleMapper;
//import com.test.exception.ParamException;
//import com.test.model.po.SysPermissionModule;
//import com.test.param.AclModuleParam;
//import com.test.util.BeanValidator;
//import com.test.util.IpUtil;
//import com.test.util.LevelUtil;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.Date;
//
///**
// * @Created by:chenxu
// * @Created date:2021/1/21 12:40
// */
//@Service
//public class SysAclModuleService {
//
//    @Resource
//    private SysPermissionModuleMapper sysPermissionModuleMapper;
//
//    @Resource
//    private SysPermissionMapper sysPermissionMapper;
//
//    public void save(AclModuleParam param){
//        BeanValidator.check(param);
//        if(checkExist(param.getParentId(), param.getName(), param.getId())) {
//            throw new ParamException("同一层级下存在相同名称的权限模块");
//        }
//        SysPermissionModule aclModule = SysPermissionModule.builder()
//                .name(param.getName())
//                .parentId(param.getParentId())
//                .seq(param.getSeq())
//                .status(param.getStatus())
//                .remark(param.getRemark()).build();
//        aclModule.setLevel(LevelUtil.calculatorLevel(getLevel(param.getParentId()), param.getParentId()));
//        aclModule.setOperator(RequestHolder.getCurrentUser().getUsername());
//        aclModule.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
//        aclModule.setOperatorTime(new Date());
//        sysPermissionModuleMapper.insertSelective(aclModule);
//    }
//
//    private boolean checkExist(Long parentId, String aclModuleName, Long deptId) {
//        return sysPermissionModuleMapper.countByNameAndParentId(parentId, aclModuleName, deptId) > 0;
//    }
//
//    private String getLevel(Long aclModuleId) {
//        SysPermissionModule aclModule = SysPermissionModuleMapper.selectByPrimaryKey(aclModuleId);
//        if (aclModule == null) {
//            return null;
//        }
//        return aclModule.getLevel();
//    }
//
////    public void delete(Long aclModuleId) {
////        SysPermissionModule aclModule = sysPermissionModuleMapper.selectByPrimaryKey(aclModuleId);
////        Preconditions.checkNotNull(aclModule, "待删除的权限模块不存在，无法删除");
////        if(sysPermissionModuleMapper.countByParentId(aclModule.getId()) > 0) {
////            throw new ParamException("当前模块下面有子模块，无法删除");
////        }
////        if (sysPermissionMapper.countByAclModuleId(aclModule.getId()) > 0) {
////            throw new ParamException("当前模块下面有用户，无法删除");
////        }
////        sysPermissionModuleMapper.deleteByPrimaryKey(aclModuleId);
////    }
//
//}
