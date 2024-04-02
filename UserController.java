package com.wms.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.ExamineVo;
import com.wms.entity.Menu;
import com.wms.entity.Storage;
import com.wms.entity.User;
import com.wms.listener.UserExcelListener;
import com.wms.service.MenuService;
import com.wms.service.StorageService;
import com.wms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wms
 * @since 2022-07-21
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @Resource
    private StorageService storageService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no) {
        List list = userService.lambdaQuery().eq(User::getNo, no).list();
        return list.size() > 0 ? Result.suc(list) : Result.fail();
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        return userService.save(user) ? Result.suc() : Result.fail();
    }

    //更新
    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        return userService.updateById(user) ? Result.suc() : Result.fail();
    }

    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return userService.removeById(id) ? Result.suc() : Result.fail();
    }

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        List list = userService.lambdaQuery()
                .eq(User::getNo, user.getNo())
                .eq(User::getPassword, user.getPassword()).list();


        if (list.size() > 0) {
            User user1 = (User) list.get(0);
            List menuList = menuService.lambdaQuery().like(Menu::getMenuright, user1.getRoleId()).list();
            HashMap res = new HashMap();
            res.put("user", user1);
            res.put("menu", menuList);
            return Result.suc(res);
        }
        return Result.fail();
    }

    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody User user) {
        return userService.updateById(user);
    }

    //新增或修改
    @PostMapping("/saveOrMod")
    public Result saveOrMod(@RequestBody User user) {
        user.setScore(0);
        userService.saveOrUpdate(user);
        return Result.suc();
    }

    //删除
    @GetMapping("/delete")
    public boolean delete(Integer id) {
        return userService.removeById(id);
    }

    //查询（模糊、匹配）
    @PostMapping("/listP")
    public Result listP(@RequestBody User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(user.getName())) {
            lambdaQueryWrapper.like(User::getName, user.getName());
        }

        return Result.suc(userService.list(lambdaQueryWrapper));
    }

    @PostMapping("/listPage")
//    public List<User> listPage(@RequestBody HashMap map){
    public List<User> listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        System.out.println("name===" + (String) param.get("name"));
        /*System.out.println("no==="+(String)param.get("no"));*/
        /*LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(User::getName,user.getName());

        return userService.list(lambdaQueryWrapper);*/

        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getName, name);


        IPage result = userService.page(page, lambdaQueryWrapper);

        System.out.println("total==" + result.getTotal());

        return result.getRecords();
    }

    @PostMapping("/listPageC")
    public List<User> listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        System.out.println("name===" + (String) param.get("name"));


        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getName, name);


        //IPage result = userService.pageC(page);
        IPage result = userService.pageCC(page, lambdaQueryWrapper);

        System.out.println("total==" + result.getTotal());

        return result.getRecords();
    }

    @PostMapping("/listPageC1")
    public Result listPageC1(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        String sex = (String) param.get("sex");
        String roleId = (String) param.get("roleId");

        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(User::getName, name);
        }
        if (StringUtils.isNotBlank(sex)) {
            lambdaQueryWrapper.eq(User::getSex, sex);
        }
        if (StringUtils.isNotBlank(roleId)) {
            lambdaQueryWrapper.eq(User::getRoleId, roleId);
        }

        //IPage result = userService.pageC(page);
        IPage<User> result = userService.pageCC(page, lambdaQueryWrapper);

        result.getRecords().forEach(user -> {
            if (Objects.nonNull(user.getStorageId())) {
                Storage storage = storageService.getById(user.getStorageId());
                if (Objects.nonNull(storage)) {
                    user.setStorageName(storage.getName());
                }
            }
        });

        return Result.suc(result.getRecords(), result.getTotal());
    }

    @PostMapping("/getList")
    public Result getList(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        User user = new User();
        user.setName((String)param.get("name"));

//        user.setSex((Integer)param.get("sex"));
//        user.setRoleId((Integer)param.get("roleId"));
        ExamineVo examineVo = new ExamineVo();
        examineVo.setName((String)param.get("name"));
        examineVo.setSex((String)param.get("sex"));
        examineVo.setStorageId((Integer)param.get("storageId"));
        examineVo.setPageNum((Integer)param.get("pageNum"));
        examineVo.setPageSize((Integer)param.get("pageSize"));
        examineVo.setLevel((String)param.get("level"));

        Map<String, Object> map = userService.getList(examineVo);

        return Result.suc(map);
    }


    @PostMapping("/insert")
    public R insert(@RequestBody User user) {
        //app注册默认密码
        user.setPassword("appappappapp");
        boolean save = userService.save(user);
        return R.ok(save);

    }


    /**
     * 获取学生详情
     *
     * @param storageId
     * @return
     */
    @GetMapping("/getStudent/{storageId}")
    public Result getStudent(@PathVariable Integer storageId) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getStorageId, storageId);
        queryWrapper.eq(User::getRoleId, 3);
        List<User> list = userService.list(queryWrapper);
        list.stream().forEach(user -> {
            if (Objects.nonNull(user.getStorageId())) {
                Storage storage = storageService.getById(user.getStorageId());
                if (Objects.nonNull(storage)) {
                    user.setStorageName(storage.getName());
                }
            }
        });

        // 根据用户分数从高到低排序
        Collections.sort(list, (o1, o2) -> o2.getScore() - o1.getScore());
//        for (int i = 0; i < list.size(); i++) {
//            list.get(i).setRanking;
//        }


        double excellent = list.size() * 0.3;
        double good =  (list.size() * 0.6);
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            int j = i + 1;
            if (list.get(i).getScore() < 0) {
                user.setPerformance("不合格");
                user.setSort(j);
            } else if (i < excellent) {
                user.setPerformance("优");
                user.setSort(j);
            } else if (i < good) {
                user.setPerformance("良");
                user.setSort(j);
            } else {
                user.setPerformance("合格");
                user.setSort(j);
            }
        }
        log.info("list={}", JSONObject.toJSONString(list));
        return Result.suc(list);
    }


    @GetMapping("/getUserInfo/{userId}")
    public Result getUserInfo(@PathVariable Integer userId) {
        if (userId == null) {
            return Result.fail();
        }
        User user = userService.getById(userId);
        if (Objects.nonNull(user.getStorageId())) {
            Storage storage = storageService.getById(user.getStorageId());
            if (Objects.nonNull(storage)) {
                user.setStorageName(storage.getName());
            }
        }
        return Result.suc(user);
    }

    /**
     * 验证用户信息
     */
    @PostMapping("/verifyUser")
    public Result verifyUser(@RequestBody User user) {
        return userService.verifyUser(user);
    }

    @PostMapping("/import")
    public Result importUser(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), User.class, new UserExcelListener(userService)).sheet().doRead();
        return Result.suc();
    }
}
