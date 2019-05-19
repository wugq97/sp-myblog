package com.wugq.blog.annotation;

import com.wugq.blog.entity.*;
import com.wugq.blog.service.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SysLogAspect {

    @Autowired
    ActionLogService actionLogService;
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    TagService tagService;
    @Autowired
    UserService userService;
    @Autowired
    private HttpServletRequest request;

    @Around(value = "@annotation(mylog)")
    public Object around(ProceedingJoinPoint pjp, MyLog mylog) throws Throwable {
        String name = mylog.value();
        String kind = mylog.kind();
        String operator = mylog.operator();
        boolean flag = false;
        Object[] params = pjp.getArgs(); // 方法参数
        Object result = null;

        ActionLog actionLog = new ActionLog();
        actionLog.setAction(name);

        User user = userService.getLoginUser(request);
        actionLog.setUser(user.getUsername());

        switch (kind) {
            case "article":
                if("add".equals(operator)){
                    actionLog.setObject(((Article)params[0]).getTitle());
                }else if ("update".equals(operator)) {
                    flag = true;
                    Article oldA = articleService.selectById(((Article)params[0]).getId());
                    result  = pjp.proceed();
                    Article newA = articleService.selectById(((Article)params[0]).getId());
                    if (oldA.getTitle().equals(newA.getTitle())) {
                        actionLog.setObject(oldA.getTitle());
                    } else {
                        actionLog.setObject(oldA.getTitle() + "-->" + newA.getTitle());
                    }
                } else if ("delete".equals(operator)) {
                    Article article = articleService.selectById(((Integer)params[0]));
                    actionLog.setObject(article.getTitle());
                }
                break;
            case "category":
                if("add".equals(operator)){
                    actionLog.setObject(((Category)params[0]).getName());
                }else if ("update".equals(operator)) {
                    Category oldA = categoryService.selectById(((Category)params[0]).getId());
                    result  = pjp.proceed();
                    Category newA = categoryService.selectById(((Category)params[0]).getId());
                    if (oldA.getName().equals(newA.getName())) {
                        actionLog.setObject(oldA.getName());
                    } else {
                        actionLog.setObject(oldA.getName() + "-->" + newA.getName());
                    }
                } else if ("delete".equals(operator)) {
                    Category category = categoryService.selectById(((Integer)params[0]));
                    actionLog.setObject(category.getName());
                }
                break;
            case "tag":
                if("add".equals(operator)){
                    actionLog.setObject(((Tag)params[0]).getName());
                }else if ("update".equals(operator)) {
                    Tag oldA = tagService.selectById(((Tag)params[0]).getId());
                    result  = pjp.proceed();
                    Tag newA = tagService.selectById(((Tag)params[0]).getId());
                    if (oldA.getName().equals(newA.getName())) {
                        actionLog.setObject(oldA.getName());
                    } else {
                        actionLog.setObject(oldA.getName() + "-->" + newA.getName());
                    }
                } else if ("delete".equals(operator)) {
                    Tag tag = tagService.selectById(((Integer)params[0]));
                    actionLog.setObject(tag.getName());
                }
                break;
            default: break;
        }
        actionLogService.insert(actionLog);
        if (!flag) {
            result = pjp.proceed();
        }
        return result;
    }

}
