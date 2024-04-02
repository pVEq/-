package com.wms.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wms.common.Result;
import com.wms.common.util.StringUtils;
import com.wms.entity.Examine;
import com.wms.entity.ExportData;
import com.wms.entity.Request;
import com.wms.entity.User;
import com.wms.service.ExamineService;
import com.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/examine")
public class ExamineController {

    @Autowired
    ExamineService examineService;
    @Autowired
    UserService userService;

    @GetMapping("/list/{roleId}")
    public Result list(@PathVariable Integer roleId) {
        LambdaQueryWrapper<Examine> queryWrapper = new LambdaQueryWrapper();
        if (roleId == 1) {
            queryWrapper.eq(Examine::getState, 2);
        }
        if (roleId == 2) {
            queryWrapper.eq(Examine::getState, 0);

        }
        List<Examine> list = examineService.list(queryWrapper);
        return Result.suc(list);
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody Examine examine) {
        if (StringUtils.isEmpty(examine.getReason())) {
            return Result.result(400, "请输入获奖原因", 0L, null);
        }
        List<Object> imgList = examine.getImgList();
        String jsonString = JSON.toJSONString(imgList);
        examine.setImg(jsonString);
        examine.setState(0);
        examineService.save(examine);
        return Result.suc();
    }

    /**
     * 审批
     */
    @PostMapping("/check")
    public Result check(@RequestBody Request request) {
        Examine examine = examineService.getById(request.getId());
        switch (request.getStatus()) {
            case 1:
                if (examine.getState() == 2) {
                    if (Objects.isNull(examine)) {
                        return Result.fail();
                    }
                    examine.setState(4);
                    User user = userService.getById(examine.getUserId());
                    switch (examine.getType()) {
                        case "院级":
                            switch (examine.getLevel()) {
                                case "思想引领":
                                    user.setScore(user.getScore() + 1);
                                    examine.setScore(1);
                                    break;
                                case "专业技能":
                                    user.setScore(user.getScore() + 1);
                                    examine.setScore(1);
                                    break;
                                case "文体活动":
                                    user.setScore(user.getScore() + 1);
                                    examine.setScore(1);
                                    break;
                                case "社会工作和志愿服务":
                                    user.setScore(user.getScore() + 1);
                                    examine.setScore(1);
                                    break;
                                case "日常表现综合评价":
                                    user.setScore(user.getScore() + 1);
                                    examine.setScore(1);
                                    break;
                                case "劳动教育":
                                    user.setScore(user.getScore() + 1);
                                    examine.setScore(1);
                                    break;
                                case "扣分项目":
                                    user.setScore(user.getScore() - 1);
                                    examine.setScore(-1);
                                    break;
                            }
                            break;
                        case "校级":
                            switch (examine.getLevel()) {
                                case "思想引领":
                                    user.setScore(user.getScore() + 2);
                                    examine.setScore(2);
                                    break;
                                case "专业技能":
                                    user.setScore(user.getScore() + 2);
                                    examine.setScore(2);
                                    break;
                                case "文体活动":
                                    user.setScore(user.getScore() + 2);
                                    examine.setScore(2);
                                    break;
                                case "社会工作和志愿服务":
                                    user.setScore(user.getScore() + 2);
                                    examine.setScore(2);
                                    break;
                                case "日常表现综合评价":
                                    user.setScore(user.getScore() + 2);
                                    examine.setScore(2);
                                    break;
                                case "劳动教育":
                                    user.setScore(user.getScore() + 2);
                                    examine.setScore(2);
                                    break;
                                case "扣分项目":
                                    user.setScore(user.getScore() - 2);
                                    examine.setScore(-2);
                                    break;
                            }
                            break;
                        case "省级":
                            switch (examine.getLevel()) {
                                case "思想引领":
                                    user.setScore(user.getScore() + 3);
                                    examine.setScore(3);
                                    break;
                                case "专业技能":
                                    user.setScore(user.getScore() + 3);
                                    examine.setScore(3);
                                    break;
                                case "文体活动":
                                    user.setScore(user.getScore() + 3);
                                    examine.setScore(3);
                                    break;
                                case "社会工作和志愿服务":
                                    user.setScore(user.getScore() + 3);
                                    examine.setScore(3);
                                    break;
                                case "日常表现综合评价":
                                    user.setScore(user.getScore() + 3);
                                    examine.setScore(3);
                                    break;
                                case "劳动教育":
                                    user.setScore(user.getScore() + 3);
                                    examine.setScore(3);
                                    break;
                                case "扣分项目":
                                    user.setScore(user.getScore() - 3);
                                    examine.setScore(-3);
                                    break;
                            }
                            break;
                        case "国家级":
                            switch (examine.getLevel()) {
                                case "思想引领":
                                    user.setScore(user.getScore() + 4);
                                    examine.setScore(4);
                                    break;
                                case "专业技能":
                                    user.setScore(user.getScore() + 4);
                                    examine.setScore(4);
                                    break;
                                case "文体活动":
                                    user.setScore(user.getScore() + 4);
                                    examine.setScore(4);
                                    break;
                                case "社会工作和志愿服务":
                                    user.setScore(user.getScore() + 4);
                                    examine.setScore(4);
                                    break;
                                case "日常表现综合评价":
                                    user.setScore(user.getScore() + 4);
                                    examine.setScore(4);
                                    break;
                                case "劳动教育":
                                    user.setScore(user.getScore() + 4);
                                    examine.setScore(4);
                                    break;
                                case "扣分项目":
                                    user.setScore(user.getScore() - 4);
                                    examine.setScore(-4);
                                    break;
                            }
                            break;
                    }
                    userService.updateById(user);
                    examineService.updateById(examine);
                }

                if (examine.getState() == 0) {
                    examine.setState(2);
                }
                examineService.updateById(examine);
                break;
            case 2:
                examine.setState(1);
                if (org.apache.commons.lang3.StringUtils.isNotBlank(request.getMessage())) {
                    examine.setMessage(request.getMessage());
                }
                examineService.updateById(examine);
                break;
        }
        return Result.suc();
    }

    /**
     * 获取得分详情
     */
    @GetMapping("/getInfo/{userId}")
    public Result getInfo(@PathVariable Integer userId) {
        if (Objects.isNull(userId)) {
            return Result.fail();
        }
        return Result.suc(examineService.list(Wrappers.<Examine>lambdaQuery().eq(Examine::getUserId, userId).eq(Examine::getState, 4)));
    }

    @GetMapping("/getdaishen/{userId}")
    public Result getdaishen(@PathVariable Integer userId){
        if (Objects.isNull(userId)){
            return  Result.fail();
        }
        return Result.suc(examineService.list(Wrappers.<Examine>lambdaQuery()
                .eq(Examine::getUserId, userId) // 用户ID条件
                .and(wrapper -> wrapper.eq(Examine::getState, 0).or().eq(Examine::getState, 2)) // 状态为0或1的条件
        ));
    }



    /**
     * 获取待审批列表
     */
    @GetMapping("/getPendingApproval")
    public Result getPendingApproval(@RequestParam Integer userId) {

        return Result.suc(examineService.getPendingApproval(userId));
    }

    /**
     * 获取学生未通过审批列表
     */
    @GetMapping("/getNotPass")
    public Result getNotPass(@RequestParam Integer userId) {
        return Result.suc(examineService.getNotPass(userId));
    }

    @PostMapping("export")
    public void export(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("数据", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        List<ExportData> exportDataList = examineService.exportData();
        EasyExcel.write(response.getOutputStream(), ExportData.class).sheet("模板").doWrite(exportDataList);
    }

}
