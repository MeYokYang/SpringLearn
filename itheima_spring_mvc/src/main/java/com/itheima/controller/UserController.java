package com.itheima.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.User;
import com.itheima.domain.VO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    // 请求地址 http://localhost:8080/user/quick
    @RequestMapping(value = "/quick", method = RequestMethod.GET, params = {"username"})
    public String save(){
        System.out.println("Controller save running...");
        return "success";
    }

    @RequestMapping(value = "/quick2")
    public ModelAndView save2(){
        /*
            Model：模型 作用封装数据
            View：视图 作用展示数据
         */
        ModelAndView modelAndView = new ModelAndView();
        //设置模型数据
        modelAndView.addObject("username", "itcast");
        //设置视图名称
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping(value = "/quick3")
    public ModelAndView save3(ModelAndView modelAndView){ //SpringMVC会自动注入参数
        modelAndView.addObject("username", "itheima");
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping(value = "/quick4")
    public String save4(Model model){
        model.addAttribute("username", "博学谷");
        return "success";
    }

    @RequestMapping(value = "/quick5")
    public String save5(HttpServletRequest req){
        req.setAttribute("username", "酷丁鱼");
        return "success";
    }

    @RequestMapping(value = "/quick6")
    public void save6(HttpServletResponse res) throws IOException {
        res.getWriter().println("Hello itcast");
    }

    @ResponseBody //告诉SpringMVC框架 不进行视图跳转 直接进行数据响应
    @RequestMapping(value = "/quick7")
    public String save7(){
        return "Hello itheima";
    }

    @ResponseBody
    @RequestMapping(value = "/quick8")
    public String save8(){
        return "{\"username\": \"zhangsan\", \"age\": 18}";
    }

    @ResponseBody
    @RequestMapping(value = "/quick9")
    public String save9() throws Exception {
        User user = new User();
        user.setUsername("lisi");
        user.setAge(30);
        //使用json的转换工具将对象转换成json格式字符串再返回
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        return json;
    }

    //期望SpringMVC自动将User转换成json格式的字符串
    @ResponseBody
    @RequestMapping(value = "/quick10")
    public User save10(){
        User user = new User();
        user.setUsername("lisi2");
        user.setAge(32);
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "/quick11")
    public void save11(String username, int age){
        System.out.println(username);
        System.out.println(age);
    }

    @ResponseBody
    @RequestMapping(value = "/quick12")
    public void save12(User user){
        System.out.println(user);
    }

    @ResponseBody
    @RequestMapping(value = "/quick13")
    public void save13(String[] strs){
        System.out.println(Arrays.asList(strs));
    }

    @ResponseBody
    @RequestMapping(value = "/quick14")
    public void save14(VO vo){
        System.out.println(vo);
    }

    @ResponseBody
    @RequestMapping(value = "/quick15")
    public void save15(@RequestBody List<User> userList){
        System.out.println(userList);
    }

    @ResponseBody
    @RequestMapping(value = "/quick16")
    public void save16(@RequestParam(value = "name", required = false, defaultValue = "itcast") String username){
        System.out.println(username);
    }

    @ResponseBody
    @RequestMapping(value = "/quick17/{username}")
    public void save17(@PathVariable(value = "username") String username){
        System.out.println(username);
    }

    @ResponseBody
    @RequestMapping(value = "/quick18")
    public void save18(Date date){
        System.out.println(date);
    }

    @ResponseBody
    @RequestMapping(value = "/quick19")
    public void save19(HttpServletRequest req, HttpServletResponse res, HttpSession session){
        System.out.println(req);
        System.out.println(res);
        System.out.println(session);
    }

    @ResponseBody
    @RequestMapping(value = "/quick20")
    public void save20(@RequestHeader(value = "User-Agent", required = false) String user_agent){
        System.out.println(user_agent);
    }

    @ResponseBody
    @RequestMapping(value = "/quick21")
    public void save21(@CookieValue(value = "JSESSIONID") String jsessionID){
        System.out.println(jsessionID);
    }

    @ResponseBody
    @RequestMapping(value = "/quick22")
    public void save22(String username, MultipartFile uploadFile) throws IOException {
        System.out.println(username);
//        System.out.println(uploadFile);
        //获得上传文件的名称
        String originalFilename = uploadFile.getOriginalFilename();
        uploadFile.transferTo(new File("D:\\"+originalFilename));
    }

    @ResponseBody
    @RequestMapping(value = "/quick23")
    public void save23(String username, MultipartFile[] uploadFile) throws IOException {
        System.out.println(username);
        for (MultipartFile multipartFile : uploadFile) {
            String originalFilename = multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File("D:\\"+originalFilename));
        }
    }

}
