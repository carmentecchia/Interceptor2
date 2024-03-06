package com.example.Interceptor2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Component
public class MonthInterceptor implements HandlerInterceptor {
    private final List<Month> finalList = MonthsList();
    public List<Month> MonthsList() {
        List<Month> fullList = new ArrayList<>();
        fullList.add(new Month(1, "January", "Gennaio", "Januar"));
        fullList.add(new Month(2, "February", "Febbraio", "Februar"));
        fullList.add(new Month(3, "March", "Marzo", "Marz"));
        fullList.add(new Month(4, "April", "Aprile", "April"));
        fullList.add(new Month(5, "May", "Maggio", "Mai"));
        fullList.add(new Month(6, "June", "Giugno", "Juni"));
        return fullList;
    }
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       String monthNumberString = request.getHeader("monthNumber");
       if (monthNumberString==null){
           response.sendError(HttpStatus.BAD_REQUEST.value());
           return false;
       }
       int monthNumber = Integer.parseInt(monthNumberString);
       Optional<Month> month = finalList.stream().filter(singleMonth->{
           return singleMonth.getMonthNumber()==monthNumber;
       }).findFirst();
       if (month.isPresent()) {
           response.setStatus(HttpStatus.OK.value());
           request.setAttribute("MonthInterceptor-Month", month.get());
       } else {
           Month emptyMonth = new Month(0,"nope", "nope", "nope");
           request.setAttribute("MonthInterceptor-Month",emptyMonth);
       }
       return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}


