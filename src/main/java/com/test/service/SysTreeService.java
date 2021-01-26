package com.test.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.test.dao.SysDeptMapper;
import com.test.dao.SysPermissionModuleMapper;
import com.test.model.dto.DeptLevelDto;
import com.test.model.dto.PermissionModuleLevelDto;
import com.test.model.po.SysDept;
import com.test.model.po.SysPermissionModule;
import com.test.util.LevelUtil;
import org.apache.hadoop.hbase.util.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Created by:chenxu
 * @Created date:2021/1/7 12:51
 */
@Service
public class SysTreeService {

    @Resource
    private SysDeptMapper sysDeptMapper;
    @Resource
    private SysPermissionModuleMapper sysPermissionModuleMapper;

    public List<PermissionModuleLevelDto> aclModuleTree() {
        List<SysPermissionModule> aclModuleList = sysPermissionModuleMapper.getAllAclModule();
        List<PermissionModuleLevelDto> dtoList = Lists.newArrayList();
        for (SysPermissionModule aclModule : aclModuleList) {
            dtoList.add(PermissionModuleLevelDto.adapt(aclModule));
        }
        return aclModuleListToTree(dtoList);
    }

    public List<PermissionModuleLevelDto> aclModuleListToTree(List<PermissionModuleLevelDto> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Lists.newArrayList();
        }
        // level -> [aclmodule1, aclmodule2, ...] Map<String, List<Object>>
        Multimap<String, PermissionModuleLevelDto> levelAclModuleMap = ArrayListMultimap.create();
        List<PermissionModuleLevelDto> rootList = Lists.newArrayList();

        for (PermissionModuleLevelDto dto : dtoList) {
            levelAclModuleMap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }
        Collections.sort(rootList, aclModuleSeqComparator);
        transformAclModuleTree(rootList, LevelUtil.ROOT, levelAclModuleMap);
        return rootList;
    }

    public void transformAclModuleTree(List<PermissionModuleLevelDto> dtoList, String level, Multimap<String, PermissionModuleLevelDto> levelAclModuleMap) {
        for (int i = 0; i < dtoList.size(); i++) {
            PermissionModuleLevelDto dto = dtoList.get(i);
            String nextLevel = LevelUtil.calculatorLevel(level, dto.getId());
            List<PermissionModuleLevelDto> tempList = (List<PermissionModuleLevelDto>) levelAclModuleMap.get(nextLevel);
            if (CollectionUtils.notEmpty(tempList)) {
                Collections.sort(tempList, aclModuleSeqComparator);
                dto.setPermissionModuleLevelDtoList(tempList);
                transformAclModuleTree(tempList, nextLevel, levelAclModuleMap);
            }
        }
    }


    //get dept level tree
    public List<DeptLevelDto> levelTree(){
        List<SysDept> depts = sysDeptMapper.getAllDept();

        List<DeptLevelDto> dtos = Lists.newArrayList();

        for (SysDept dept:depts){
            DeptLevelDto dto = DeptLevelDto.adapt(dept);
            dtos.add(dto);
        }
        return deptListToDeptTree(dtos);
    }

    public List<DeptLevelDto> deptListToDeptTree(List<DeptLevelDto> deptLevelDtoList){
        if (CollectionUtils.isEmpty(deptLevelDtoList)){
            return Lists.newArrayList();
        }

        //key level value level response name(dept list)
        //if map value is list, choose multiMap
        Multimap<String,DeptLevelDto> levelDtoMultimap = ArrayListMultimap.create();
        List<DeptLevelDto> rootList = Lists.newArrayList();

        for (DeptLevelDto dto:deptLevelDtoList){
            levelDtoMultimap.put(dto.getLevel(),dto);
            //deal root
            if (LevelUtil.ROOT.equals(dto.getLevel())){
                rootList.add(dto);
            }
        }

        Collections.sort(rootList, new Comparator<DeptLevelDto>() {
            @Override
            public int compare(DeptLevelDto o1, DeptLevelDto o2) {
                return o1.getSeq()-o2.getSeq();
            }
        });
        transformDeptTree(rootList,LevelUtil.ROOT,levelDtoMultimap);
        return rootList;
    }

    //current level include dept
    public void transformDeptTree(List<DeptLevelDto> deptLevelDtoList,String level,Multimap<String,DeptLevelDto> map){
        for (int i = 0; i<deptLevelDtoList.size(); i++){

            DeptLevelDto deptLevelDto = deptLevelDtoList.get(i);
            //the next level
            String nextLevel = LevelUtil.calculatorLevel(level,deptLevelDto.getId());
            //deal next level
            List<DeptLevelDto> tempDeptList = (List<DeptLevelDto>) map.get(nextLevel);

            if (CollectionUtils.notEmpty(tempDeptList)){
                //sort next level
                Collections.sort(tempDeptList, deptSeqComparator);
                //set dept for next level
                deptLevelDto.setDeptList(tempDeptList);
                //go to next level deal
                transformDeptTree(tempDeptList,nextLevel,map);
            }
        }
    }

    public Comparator<DeptLevelDto> deptSeqComparator = new Comparator<DeptLevelDto>() {
        @Override
        public int compare(DeptLevelDto o1, DeptLevelDto o2) {
            return o1.getSeq()-o2.getSeq();
        }
    };

    public Comparator<PermissionModuleLevelDto> aclModuleSeqComparator = new Comparator<PermissionModuleLevelDto>() {
        @Override
        public int compare(PermissionModuleLevelDto o1, PermissionModuleLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

}
