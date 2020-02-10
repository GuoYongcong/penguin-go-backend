package com.ryzin.penguin.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryzin.penguin.admin.model.SysLog;
import com.ryzin.penguin.admin.service.SysLogService;
import com.ryzin.penguin.core.page.PageRequest;
import com.ryzin.penguin.core.page.PageResult;

@RestController
@RequestMapping("log")
public class SysLogController {

	@Autowired
	private SysLogService sysLogService;
	
	@PostMapping(value="/save")
	public int save(SysLog record) {
		return sysLogService.save(record);
	}

	@PostMapping(value="/update")
	public int update(SysLog record) {
		return sysLogService.update(record);
	}

	@PostMapping(value="/delete")
	public int delete(SysLog record) {
		return sysLogService.delete(record);
	}

	@PostMapping(value="/delete")
	public int delete(List<SysLog> records) {
		return sysLogService.delete(records);
	}

	@GetMapping(value="/findById")
	public SysLog findById(Long id) {
		return sysLogService.findById(id);
	}

	@PostMapping(value="/update")
	public PageResult findPage(PageRequest pageRequest) {
		return sysLogService.findPage(pageRequest);
	}
}
