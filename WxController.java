package com.wms.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.wms.entity.AjaxResult;
import com.wms.entity.Storage;
import com.wms.entity.User;
import com.wms.service.StorageService;
import com.wms.service.UserService;
import com.wms.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
public class WxController {

    @Autowired
    private WxService wxService;

    @Autowired
    private UserService userService;

    @Resource
    private StorageService storageService;

    /**
     * 获取openid
     *
     * @param code
     * @return
     */
    @GetMapping("/getOpenId/{code}")
    public R getOpenId(@PathVariable String code) {
        String wxOpenId = wxService.getWxOpenId(code);
        return R.ok(wxOpenId);
    }

    @GetMapping("/appLogin/{openId}")
    public AjaxResult appLogin(@PathVariable String openId) {

        AjaxResult ajax = AjaxResult.success();
        User user = userService.getuid(openId);
        if (user == null) {
            return new AjaxResult(201, "成功", openId);
        } else {
            Storage storage = storageService.getOne(Wrappers.<Storage>lambdaQuery().eq(Storage::getId, user.getStorageId()));
            if (Objects.nonNull(storage)) {
                user.setStorageName(storage.getName());
            }
            ajax.put("user", user);
        }
        return ajax;
    }

}
