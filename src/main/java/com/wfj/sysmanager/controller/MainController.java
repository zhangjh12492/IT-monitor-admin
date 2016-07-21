package com.wfj.sysmanager.controller;

import com.wfj.sysmanager.httpModel.EasyuiTreeNode;
import com.wfj.sysmanager.httpModel.User;
import com.wfj.sysmanager.service.MenuServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by MaYong on 2015/7/29.
 */
@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private MenuServiceI menuServiceI;

    @RequestMapping(params = "index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response,
                              ModelAndView model) {

        List<EasyuiTreeNode> list = menuServiceI.tree("0");

        for (EasyuiTreeNode node : list) {
            String pid = node.getId();
            List<EasyuiTreeNode> sub = menuServiceI.tree(pid);
            node.setChildren(sub);
        }
        model.addObject("mainMenu", list);
        model.setViewName("forward:index.jsp");
        return model;
    }
}
