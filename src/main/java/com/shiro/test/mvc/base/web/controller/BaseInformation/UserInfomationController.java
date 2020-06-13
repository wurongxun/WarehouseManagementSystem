package com.shiro.test.mvc.base.web.controller.BaseInformation;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiro.test.mvc.base.application.web.json.DatagridResult;
import com.shiro.test.mvc.base.application.web.json.WebJsonResult;
import com.shiro.test.mvc.base.tool.*;
import com.shiro.test.mvc.base.tool.Excel.UserInformationExcel;
import com.shiro.test.mvc.pojo.ActiveUser;
import com.shiro.test.mvc.pojo.RoleInformation;
import com.shiro.test.mvc.pojo.UserInformation;
import com.shiro.test.mvc.service.DetailedListInformationService;
import com.shiro.test.mvc.service.RoleInformationService;
import com.shiro.test.mvc.service.UserInformationService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description UserInfomationController
 * @Author Rongxun.Wu
 * @Date 2020/3/1 11:25
 * @Version 1.0
 */
@Controller
@RequestMapping(value = {"user.html", "userInformation.html"})
public class UserInfomationController {

    @Autowired
    DetailedListInformationService detailedListInformationService;

    @Autowired
    private UserInformationService userService;

    @Autowired
    private RoleInformationService roleService;


    @RequestMapping(params = "act=page")
    public String page() {
        return "BaseInformation/UserInformation_list";
    }

    @RequestMapping(params = "act=user_list")
    @ResponseBody
    public WebJsonResult list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "1") Integer rows, UserInformation userInformation, String find) {
        PageHelper.startPage(page, rows);
        List<UserInformation> userInformations;
        System.out.println("UserInformation:  " + userInformation.toString());
        System.out.println("find :" + find);
        if (StringUtil.isNotEmpty(find)) {
            userInformations = userService.findLikeUserInformation(userInformation);
        } else {

            userInformations = userService.getUserList();
        }
        PageInfo<UserInformation> pageInfo = new PageInfo<>(userInformations, 6);
        final DatagridResult creditDatagridResult = new DatagridResult();
        creditDatagridResult.setTotal(pageInfo.getTotal());
        creditDatagridResult.setRows(pageInfo.getList());
        for (UserInformation s : pageInfo.getList()
        ) {
            System.out.println(s.toString());
        }
        return WebJsonResult.newSuccess(creditDatagridResult);
    }

    @RequestMapping(params = "act=role_list")
    @ResponseBody
    public List<RoleInformation> roleList() {

        return roleService.getRoleList();
    }

    /*为分配角色*/
    @RequestMapping(params = "act=assign_role")
    @ResponseBody
    public WebJsonResult assignRole(String userId, String roleId) {
        System.out.println("userId" + userId + "roleId" + roleId);
        if (roleId != null || roleId != null) {
            int status =userService.addUserRole(userId, roleId);
            if (status!=0){
                return WebJsonResult.newSuccess("分配角色成功");
            }
           return WebJsonResult.newSuccess("更新失败");
        }
        return WebJsonResult.newFailure("失败 有空值");
    }


    /*返回角色ID*/
    @RequestMapping(params = "act=roleId_list")
    @ResponseBody
    public String userRole(String userId) {
        String u = userService.getUserRoleIds(userId);
        return u;
        /*return userService.getUserRole(userrole);*/
    }

    /*密码更新*/
    @RequestMapping(params = "act=up_password")
    @ResponseBody
    public Map UpPassword(String userid, String password) {
        System.out.println(userid + "     " + password);
        Integer status = userService.updataPassword(password, userid);
        if (status == 1) {
            return MessageUtil.geSuccessResult("修改成功");
        }
        return MessageUtil.geSuccessResult("修改失败");
    }

    //添加用户
    @RequestMapping(params = "act=go_adduser")
    @ResponseBody
    public WebJsonResult addUser(UserInformation userInformation) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        Date Create = new Date();
        if (StringUtil.isNotEmpty(userInformation.getPhone())
                && StringUtil.isNotEmpty(userInformation.getAddress())
                && userInformation.getAge() != null
                && userInformation.getSex() != null) {
            String md5_password = MD5Utils.encodePassword(userInformation.getPassword(), userInformation.getEmail());
            UserInformation user = new UserInformation();
            user = userInformation;
            user.setCreateBy(activeUser.getUserName());
            user.setCreateDate(Create);
            user.setJobNumber(RandomNumber.GetRandom());
            user.setUserCode("YHXX-" + RandomNumber.GetRandom2());
            user.setId(UID.getUID32());
            user.setPassword(md5_password);
            System.out.println(user.toString());
            Integer status = userService.insertSelective(user);
            if (status == 0) {
                return WebJsonResult.newFailure("添加失败");
            } else {
                //分配默认角色
                userService.addUserRole(user.getId(),"3");
                return WebJsonResult.newSuccess("添加用户成功");
            }
        } else {
            return WebJsonResult.newFailure("失败，含有未填项");
        }

    }

    /*删除用户*/

    @RequestMapping(params = "act=deleteUser")
    @ResponseBody
    public WebJsonResult deleteUser(UserInformation userInformation) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        if (StringUtil.isEquals(userInformation.getId(), activeUser.getId())) {
            return WebJsonResult.newFailure("不能对自己操作");
        }

        /*查看是否拥有权限*/
        if (StringUtil.isEquals(activeUser.getRole().getRoleName(), "admin")) {
            /*查看删除的用户是否还有订单*/
            HashMap<String, Object> map = new HashMap<>();
            map.put("applicantId", userInformation.getId());
            Integer number = detailedListInformationService.SelectDetailedListApplicantIdCount(map);
            System.out.println("number: "+number);
            if (number>0) {
                return WebJsonResult.newFailure("此用户出入库单未清完，不能删除");
            } else {
                Integer status = userService.deleteByPrimaryKey(userInformation.getId());
                if (status > 0) {
                    return WebJsonResult.newSuccess("删除成功");
                } else {
                    return WebJsonResult.newFailure("存在错误");
                }
            }
        } else {
            return WebJsonResult.newFailure("抱歉，您没有权限");
        }
    }
    @RequestMapping(params = "act=exportUserInformation")
   public void ExportWarehousingEntryInformation(HttpServletResponse response, HttpServletRequest request, UserInformation userInformation) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        List<UserInformation> listInformations = userService.findLikeUserInformation(userInformation);
        UserInformationExcel userInformationExcel=new UserInformationExcel();
        Date t = new Date();
        //生成错误的文档
        try {
            XSSFWorkbook workbook =userInformationExcel.CreateUserInformationListExcel(listInformations);
            OutputStream ouputStream = response.getOutputStream();
            String fileName = formatter.format(t);
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "UserInformationExcel" + ".xlsx");
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (Exception e) {
            System.out.println("导出错误的excel文件,UserInformationExcel 异常:");
        }
    }
}
