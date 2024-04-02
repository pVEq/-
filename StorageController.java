package com.wms.controller;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.RequestStorsge;
import com.wms.entity.Storage;
import com.wms.listener.StorageExcelListener;
import com.wms.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wms
 * @since 2022-10-15
 */
@CrossOrigin
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Storage storage) {
        return storageService.save(storage) ? Result.suc() : Result.fail();
    }

    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Storage storage) {
        return storageService.updateById(storage) ? Result.suc() : Result.fail();
    }

    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return storageService.removeById(id) ? Result.suc() : Result.fail();
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");

        Page<Storage> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(Storage::getName, name);
        }

        IPage result = storageService.pageCC(page, lambdaQueryWrapper);
        return Result.suc(result.getRecords(), result.getTotal());
    }

    @GetMapping("/list")
    public Result list() {
        List list = storageService.list();
        return Result.suc(list);
    }

    @GetMapping("/appList")
    public Result appList() {
        List<Storage> list = storageService.list();
        List<RequestStorsge> requestStorsgeList = new ArrayList<>();
        for (Storage storage : list) {
            RequestStorsge requestStorsge = new RequestStorsge();
            requestStorsge.setValue(storage.getId());
            requestStorsge.setLabel(storage.getName());
            requestStorsgeList.add(requestStorsge);
        }
        return Result.suc(requestStorsgeList);
    }

    @PostMapping("/import")
    public Result importData(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), Storage.class, new StorageExcelListener(storageService)).sheet().doRead();
        return Result.suc();
    }


}
