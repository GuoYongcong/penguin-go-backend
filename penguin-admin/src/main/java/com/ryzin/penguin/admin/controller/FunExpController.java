package com.ryzin.penguin.admin.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

import net.sf.json.JSONObject;

import com.ryzin.penguin.admin.model.FunExp;
import com.ryzin.penguin.admin.model.FunExpUser;
import com.ryzin.penguin.admin.model.FunUserExp;
import com.ryzin.penguin.admin.model.SysUser;
import com.ryzin.penguin.admin.service.FunExpService;
import com.ryzin.penguin.admin.service.FunExpUserService;
import com.ryzin.penguin.admin.service.FunUserExpService;
import com.ryzin.penguin.admin.service.SysUserService;


@RestController
@RequestMapping("funExp")
public class FunExpController {

	@Autowired
	private FunExpService funExpService;
	
	@Autowired
	private FunExpUserService funExpUserService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private FunUserExpService funUserExpService;
	

	/**
	 * 保存
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody FunExp record) {
		return HttpResult.ok(funExpService.save(record));
	}

    /**
     * 删除
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<FunExp> records) {
		return HttpResult.ok(funExpService.delete(records));
	}
	
	/**
     * 删除
     * @param records
     * @return
     */
	@PostMapping(value="/deletePeo")
	public HttpResult deletePeo(@RequestBody List<FunExpUser> records) {
		return HttpResult.ok(funExpService.deletePeo(records));
	}
	
	/**
     * 删除
     * @param records
     * @return
     */
	@PostMapping(value="/deletePeoByExpIdAndUserName")
	public HttpResult deletePeoByExpIdAndUserName(@RequestBody FunExpUser record) {
		return HttpResult.ok(funExpService.deletePeoByExpIdAndUserName(record));
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(funExpService.findPage(pageRequest));
	}
	
	/**
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findSubjectPageByUserName")
	public HttpResult findPageByUserId(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(funExpService.findSubjectPageByUserName(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param id
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam Long id) {
		return HttpResult.ok(funExpService.findById(id));
	}
	
    /**
     * 加载统计数据，包括总实验次数、总报酬、总时长
     * @param id
     * @return
     */ 	
	@PreAuthorize("hasAuthority('fun:exp:view')")
	@GetMapping(value="/getExpStatData")
	
	public HttpResult getExpStatData(@RequestParam Long id) {
		SysUser user = sysUserService.findById(id);
		user.setUserRoles(sysUserService.findUserRoles(id));
		Map<String,Long> resultMap = new HashMap<String, Long>(); 
		if(user.getUserRoles().get(0).getRoleId() == 9) {
			//角色为被试
			List<FunExpUser> attendRecords = new LinkedList<>();
			List<FunExp> expRecords = new LinkedList<>();
			//查询被试的实验
			attendRecords = funExpUserService.findExpUserByUserId(id);
			//实验总次数
			long expCount = attendRecords.size();
			if(expCount <= 0) {
				resultMap.put("expCount", (long)0);
				resultMap.put("timeCount", (long)0);
				resultMap.put("paymentCount", (long)0);
				return HttpResult.ok(resultMap);
			}
			//所有实验总时间
			long timeCount = 0;
			
			long paymentCount = 0;
			for(FunExpUser record : attendRecords) {
				FunExp tempExp = funExpService.findById(record.getExpId());
				expRecords.add(tempExp);
				timeCount += tempExp.getDuration();
			}
		           
			for(FunExp exp : expRecords) {
				String rawJson = exp.getPayment().replaceAll("\\\\", "");
				JSONObject strJson = JSONObject.fromObject(rawJson);
				paymentCount += Long.valueOf(String.valueOf(strJson.get("paymentMax"))).longValue();
			}
			
			resultMap.put("expCount", expCount);
			resultMap.put("timeCount", timeCount);
			resultMap.put("paymentCount", paymentCount);
			
		}else if(user.getUserRoles().get(0).getRoleId() == 10) {
			//角色为主试
			List<FunUserExp> attendRecords = new LinkedList<>();
			List<FunExp> expRecords = new LinkedList<>();
			//查询主试的实验
			attendRecords = funUserExpService.findUserExpByUserId(id);
			//实验总次数
			long expCount = attendRecords.size();
			if(expCount <= 0) {
				resultMap.put("expCount", (long)0);
				resultMap.put("timeCount", (long)0);
				resultMap.put("paymentCount", (long)0);
				return HttpResult.ok(resultMap);
			}
			//所有实验总时间
			long timeCount = 0;
			
			long paymentCount = 0;
			for(FunUserExp record : attendRecords) {
				FunExp tempExp = funExpService.findById(record.getExpId());
				expRecords.add(tempExp);
				timeCount += tempExp.getDuration();
			}
		           
			for(FunExp exp : expRecords) {
				String rawJson = exp.getPayment().replaceAll("\\\\", "");
				JSONObject strJson = JSONObject.fromObject(rawJson);
				paymentCount += Long.valueOf(String.valueOf(strJson.get("paymentMax"))).longValue();
			}
			
			resultMap.put("expCount", expCount);
			resultMap.put("timeCount", timeCount);
			resultMap.put("paymentCount", paymentCount);
		}
		return HttpResult.ok(resultMap);
	}
	
	/**
	 * 查询实验用户集合
     * @param expId
	 * @return
	 */
	@GetMapping(value="/findExpUsers")
	public HttpResult findExpUsers(@RequestParam Long expId) {
		return HttpResult.ok(funExpService.findExpUsers(expId));
	}
	
	/**
	 * 查询实验用户
     * @param expId
	 * @return
	 */
	@GetMapping(value="/findExpUserById")
	public HttpResult findExpUserByUserId(@RequestParam Long id) {
		return HttpResult.ok(funExpUserService.findById(id));
	}
	
	/**
	 * 查询实验用户
     * @param 
	 * @return
	 */
	@PostMapping(value="/findExpUserByExpIdAndUserName")
	public HttpResult findExpUserByExpIdAndUserName(@RequestParam Long expId, @RequestParam String userName) {
		return HttpResult.ok(funExpUserService.findByExpIdAndUserName(expId, userName));
	}
	
	/**
	 * 查询实验用户集合
     * @param
	 * @return
	 */
	@PostMapping(value="/findExpUsersPage")
	public HttpResult findExpUsersPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(funExpService.findExpUsersPage(pageRequest));
	}
	
	/**
	 * 保存实验用户
	 * @param records
	 * @return
	 */
	@PostMapping(value="/saveExpUser")
	public HttpResult saveExpUser(@RequestBody FunExpUser record) {
		System.out.println("ExpUserId：" + record.getId());
		return HttpResult.ok(funExpService.saveExpUser(record));
	}
	
    /**
     * 查询实验的报名人数
     * @param expId
     * @return
     */
    @GetMapping(value="/getExpUserCount")
    public HttpResult getExpUserCount(@RequestParam Long expId) {
        return HttpResult.ok(funExpService.getExpUserCount(expId));
    }
    
    /**
     * 查询实验主试
     * @param expId
     * @return
     */
    @GetMapping(value="/findUserExpByExpId")
    public HttpResult findUserExpByExpId(@RequestParam Long expId) {
        return HttpResult.ok(funUserExpService.findUserExpByExpId(expId));
    }
}
