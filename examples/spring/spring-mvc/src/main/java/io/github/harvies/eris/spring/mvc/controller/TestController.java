package io.github.harvies.eris.spring.mvc.controller;

import io.github.harvies.eris.spring.mvc.model.EmployeeVO;
import io.github.harvies.eris.spring.mvc.service.EmployeeManager;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author harvies
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private EmployeeManager manager;

    /**
     * Content-Type: text/html;charset=ISO-8859-1
     * @return
     */
    @GetMapping(value = "/text")
    @ResponseBody
    public String plainText() {
        return "success";
    }

    /**
     * 不使用spring的视图
     *
     * @param request
     * @param httpServletResponse
     * @throws IOException
     */
    @RequestMapping(value = "/text2", method = RequestMethod.GET)
    public void text2(HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setHeader("Content-Type", "text/plain");
        httpServletResponse.setCharacterEncoding("UTF-8");
        @Cleanup PrintWriter writer = httpServletResponse.getWriter();
        writer.write("success");
    }

    /**
     * Can not run.
     *
     * @return
     */
    @GetMapping(value = "/json")
    public List<EmployeeVO> json() {
        return manager.getAllEmployees();
    }
}
