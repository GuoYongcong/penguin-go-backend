package com.ryzin.penguin.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryzin.penguin.core.http.HttpResult;
import com.ryzin.penguin.core.page.PageRequest;

import com.ryzin.penguin.admin.model.FunRate;
import com.ryzin.penguin.admin.service.FunRateService;


@RestController
@RequestMapping("funRate")
public class FunRateController {

	@Autowired
	private FunRateService funRateService;

	/**
	 * 保存
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody FunRate record) {
		return HttpResult.ok(funRateService.save(record));
	}

    /**
     * 删除
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<FunRate> records) {
		return HttpResult.ok(funRateService.delete(records));
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(funRateService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param id
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam Long id) {
		return HttpResult.ok(funRateService.findById(id));
	}
	
	/**
     * 根据实验ID和被评价者ID查询
     * @param
     * @return
     */ 	
	@GetMapping(value="/findRateByExpIdAndRatedId")
	public HttpResult findRateByExpIdAndRatedId(@RequestParam Long expId, @RequestParam Long ratedId) {
		return HttpResult.ok(funRateService.findRateByExpIdAndRatedId(expId, ratedId));
	}
	
	/**
     * 根据实验ID和被评价者ID查询
     * @param
     * @return
     */ 	
	@GetMapping(value="/findAllRateByRatedId")
	public HttpResult findAllRateByRatedId(@RequestParam Long ratedId) {
		return HttpResult.ok(funRateService.findAllRateByRatedId(ratedId));
	}
}
