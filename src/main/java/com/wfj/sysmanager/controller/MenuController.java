package com.wfj.sysmanager.controller;

import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.MsgReturnDto;
import com.wfj.mq.dto.DataTableDto;
import com.wfj.sysmanager.cond.MenuCond;
import com.wfj.sysmanager.entity.PcdsSymenu2;
import com.wfj.sysmanager.httpModel.*;
import com.wfj.sysmanager.service.MenuServiceI;
import com.wfj.sysmanager.util.ResourceUtil;
import com.wfj.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * 菜单控制器
 *
 * @author 孙宇
 */
@Controller
@RequestMapping("/menuController")
public class MenuController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    private MenuServiceI menuService;

    public MenuServiceI getMenuService() {
        return menuService;
    }


    @RequestMapping(params = "getMenuList")
    @ResponseBody
    public DataTableJson getMenuList(@ModelAttribute MenuCond cond, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        cond.setOrderBy(" seq ");
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(cond);
        try {
            json = menuService.getMenuList(page);
            json.setsEcho(dataTableDto.getsEcho());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping(params = "getMenuById")
    @ResponseBody
    public PcdsSymenu2 getMenuById(@ModelAttribute MenuCond cond, HttpServletRequest request) {
        PcdsSymenu2 pcdsSymenu2 = null;
        try {
            pcdsSymenu2 = menuService.getMenuById(cond.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pcdsSymenu2;
    }

    @RequestMapping(params = "saveOrUpdateMenu")
    @ResponseBody
    public MsgReturnDto saveOrUpdateMenu(@ModelAttribute PcdsSymenu2 menu, HttpServletRequest request) {
        String msg = null;
        try {
            menuService.saveOrUpdateMenu(menu);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);
    }

    @RequestMapping(params = "delMenuById")
    @ResponseBody
    public MsgReturnDto delMenuById(@RequestParam String id, HttpServletRequest request){
        String msg = null;
        try {
            menuService.delMenuById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return new MsgReturnDto("", msg);

    }


    @Autowired
    public void setMenuService(MenuServiceI menuService) {
        this.menuService = menuService;
    }

    /**
     * 跳转到菜单管理页面
     *
     * @return
     */
    @RequestMapping(params = "menu")
    public String menu() {
//        return "/admin/menu";
        return "/admin/menu_2";
    }

/*
    @RequestMapping(params = "getUserGroupList")
    @ResponseBody
    public DataTableJson getUserGroupList(@ModelAttribute MenuCond menuCond, HttpServletRequest request) {
        DataTableJson json = new DataTableJson();
        Page page = new Page();
        DataTableDto dataTableDto = new DataTableDto(request);
        page.setStart(dataTableDto.getiDisplayStart());
        page.setRows(dataTableDto.getiDisplayLength());
        page.setCond(menuCond);
        try {
            json = userGroupService.getUsergroupList(page);
            json.setsEcho(dataTableDto.getsEcho());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
*/


    /**
     * 获取一级菜单树
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "accordion")
    @ResponseBody
    public List<EasyuiTreeNode> accordion(String id) {
        System.out.println("--------accordion----------");
        return menuService.tree(id);
    }

    /**
     * 获取菜单树
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "tree")
    @ResponseBody
    public List<EasyuiTreeNode> tree(HttpServletRequest request, HttpServletResponse response, String id) {
        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
        assert sessionInfo != null;
        User user = sessionInfo.getUser();
        if (user.getId() == 0) {// 超级管理员全部菜单
            return menuService.tree(id);

        } else {
            return menuService.tree(id, user.getId());
        }
    }

    /**
     * 获取菜单treegrid
     *
     * @param id
     * @return
     */
    @RequestMapping(params = "treegrid")
    @ResponseBody
    public List<Menu> treegrid(HttpServletRequest request, HttpServletResponse response, String id) {

        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
        assert sessionInfo != null;
        User user = sessionInfo.getUser();
        if (user.getId() == 0) {// 超级管理员全部菜单
            return menuService.treegrid(id);

        } else {
            return menuService.treegrid(id, user.getId());
        }

    }

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    @RequestMapping(params = "add")
    @ResponseBody
    public Json add(Menu menu) {
        Json j = new Json();
        Menu m = menuService.add(menu);
        j.setSuccess(true);
        j.setMsg("添加成功!");
        return j;
    }

    /**
     * 删除菜单
     *
     * @param menu
     * @return
     */
    @RequestMapping(params = "del")
    @ResponseBody
    public Json del(Menu menu) {
        Json j = new Json();
        menuService.del(menu);
        j.setSuccess(true);
        j.setMsg("删除成功!");
        return j;
    }

    /**
     * 编辑菜单
     *
     * @param menu
     * @return
     */
    @RequestMapping(params = "edit")
    @ResponseBody
    public Json edit(Menu menu) {
        Json j = new Json();
        Menu m = menuService.edit(menu);
        j.setSuccess(true);
        j.setMsg("编辑成功!");
        return j;
    }

}
