package com.exampreparations.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import redis.clients.jedis.Jedis;

import com.exampreparations.utility.JedisFactory;

@Controller
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String openhomePage() {
		logger.debug("Inside Instruction page");
		return "home";
	}
	
	@RequestMapping(value = "/allexams", method = RequestMethod.GET)
	public String openallexamsPage() {
		logger.debug("Inside Instruction page");
		return "allexams";
	}
	
	@RequestMapping(value = "/instructions", method = RequestMethod.GET)
	public String openinstructionsPage(HttpServletRequest request) {
		List<Integer> s=new ArrayList<Integer>();
		Map<Integer,Integer> questionNumberList=new HashMap<Integer,Integer>();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
	        s.add(i + 1);
	    }
		
			Collections.shuffle(s, rand);
			Iterator<Integer> it=s.iterator();
			
			for(int j=1;j<6;j++){
				questionNumberList.put(j,(Integer) it.next());
			}
		
			HttpSession httpSession=request.getSession();
			
			httpSession.setAttribute("questionNumberList", questionNumberList);
			
			httpSession.setAttribute("timeleft", 5);
		logger.debug("Inside Instruction page");
		return "instructions";
	}

	@RequestMapping(value = "/aptitude", method = RequestMethod.GET)
	public ModelAndView openaptitudePage(HttpServletRequest request) {
		
		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();

		
			HttpSession httpSession=request.getSession();
			
			Map<Integer,Integer> questionNumberList=  (Map<Integer, Integer>) httpSession.getAttribute("questionNumberList");
			
			
			Map<String,String> questionMap= jedis.hgetAll("QUESTION_ALL:"+questionNumberList.get(1));
			
			ModelAndView modelAndView =new ModelAndView("aptitude");
			modelAndView.addObject("questionMap", questionMap);
			
			modelAndView.addObject("questionNumberList", questionNumberList);

			
		logger.debug("Inside Instruction page");
		return modelAndView;
	}
	
	@RequestMapping(value = "/aquestions", method = RequestMethod.GET)
	public ModelAndView openaptitudeQuestions(HttpServletRequest request,@RequestParam("originalQno") String originalQno) {
		
		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();
		
		ModelAndView modelAndView =new ModelAndView("aptitude");

		Map<String,String> questionMap= jedis.hgetAll("QUESTION_ALL:"+originalQno);
		
		HttpSession httpSession=request.getSession();
		
		Map<Integer,Integer> questionNumberList=  (Map<Integer, Integer>) httpSession.getAttribute("questionNumberList");
		
		
		modelAndView.addObject("questionMap", questionMap);
		
		modelAndView.addObject("questionNumberList", questionNumberList);

			
		logger.debug("Inside Instruction page");
		return modelAndView;
	}
	
	@RequestMapping(value = "/reasoning", method = RequestMethod.GET)
	public ModelAndView openreasoningPage(HttpServletRequest request) {
		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();

		HttpSession httpSession=request.getSession();
		
		List<Integer> questionNumberList= (List<Integer>) httpSession.getAttribute("questionNumberList");
		
		// In future this will be changed to QUESTION_REASONING
		Map<String,String> questionMap= jedis.hgetAll("QUESTION_ALL:"+questionNumberList.get(0));
		
		ModelAndView modelAndView =new ModelAndView("reasoning");
		modelAndView.addObject("questionMap", questionMap);
		logger.debug("Inside Instruction page");
		return modelAndView;
	}
	
	@RequestMapping(value = "/english", method = RequestMethod.GET)
	public ModelAndView openenglishPage(HttpServletRequest request) {
		
		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();

		HttpSession httpSession=request.getSession();
		
		List<Integer> questionNumberList= (List<Integer>) httpSession.getAttribute("questionNumberList");
		
		// In future this will be changed to QUESTION_REASONING
		Map<String,String> questionMap= jedis.hgetAll("QUESTION_ALL:"+questionNumberList.get(0));
		
		ModelAndView modelAndView =new ModelAndView("english");
		modelAndView.addObject("questionMap", questionMap);
		
		logger.debug("Inside Instruction page");
		return modelAndView;
	}
	
	@RequestMapping(value = "/gk", method = RequestMethod.GET)
	public ModelAndView opengkPage(HttpServletRequest request) {
		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();

		HttpSession httpSession=request.getSession();
		
		List<Integer> questionNumberList= (List<Integer>) httpSession.getAttribute("questionNumberList");
		
		// In future this will be changed to QUESTION_REASONING
		Map<String,String> questionMap= jedis.hgetAll("QUESTION_ALL:"+questionNumberList.get(0));
		
		ModelAndView modelAndView =new ModelAndView("gk");
		modelAndView.addObject("questionMap", questionMap);
		logger.debug("Inside Instruction page");
		return modelAndView;
	}
	
	@RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
    public @ResponseBody
    String getTimeLeft(HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		int timeLeft=(int) session.getAttribute("timeleft");
 
        timeLeft=timeLeft-1;
        session.setAttribute("timeleft",timeLeft);
        return Integer.toString(timeLeft);
    }
}
