package org.qingshan.swagger.controller;

import io.swagger.annotations.*;
import org.qingshan.swagger.pojo.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "用户模块", description = "用户模块相关接口")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    //模拟数据库
    public static List<User> users = new ArrayList<>();

    static {
        users.add(new User("张三", "123456"));
        users.add(new User("李四", "111111"));
    }

    @ApiOperation(value = "获取用户列表", notes = "获取所有的用户信息", httpMethod = "POST")
    @PostMapping(value = "/list")
    public Object listUser() {
        Map map = new HashMap<>();
        map.put("users", users);
        return map;
    }

    @ApiOperation(value = "获取单个用户", notes = "根据id获取单个用户", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "int", paramType = "path")
    @GetMapping(value = "/{id}")
    public Object findUser(
            @PathVariable("id") int id
    ) {
        return users.get(id);
    }

    @ApiOperation(value = "添加用户", notes = "根据用户信息添加用户", httpMethod = "POST")
    @ApiImplicitParam(name = "user", value = "用户对象", dataType = "User", paramType = "body")
    @PostMapping(value = "/add")
    public Object addUser(
            @RequestBody User user
    ) {
        return users.add(user);
    }

    @ApiOperation(value = "删除用户", notes = "根据id删除用户", httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "用户id", paramType = "query", dataType = "int")
    @PostMapping(value = "/delete")
    public Object deleteUser(
            @RequestParam(value = "id") int id
    ) {
        return users.remove(id);
    }


//    paramType:
//    header-->放在请求头。请求参数的获取：@RequestHeader(代码中接收注解)
//    query-->用于get请求的参数拼接。请求参数的获取：@RequestParam(代码中接收注解)
//    path（用于restful接口）-->请求参数的获取：@PathVariable(代码中接收注解)
//    body-->放在请求体。请求参数的获取：@RequestBody(代码中接收注解)

//    dataType:
//    dataType="int" 代表请求参数类型为int类型，当然也可以是Map、User、String

}
