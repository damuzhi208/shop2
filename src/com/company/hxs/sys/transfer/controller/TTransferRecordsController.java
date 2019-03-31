package com.company.hxs.sys.transfer.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.hxs.common.Page;
import com.company.hxs.common.controller.BaseController;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.ResponseTool;
import com.company.hxs.common.vo.SelectVO;
import com.company.hxs.sys.transfer.entity.TTransferRecords;
import com.company.hxs.sys.transfer.service.TTransferRecordsService;
import com.company.hxs.sys.transfer.vo.TTransferRecordsVO;

/**
 * 商品调动记录
 * @author luqi
 *
 */
@Controller
@RequestMapping("transfer")
public class TTransferRecordsController extends BaseController{
	
	@Resource private TTransferRecordsService tTransferRecordsService;
	
	/**
	 * 调动记录页面
	 * @return
	 */
	@RequestMapping("list")
	public String listRecord(){
		return "transfer/listRecord";
	}
	
	/**
	 * 调动记录数据
	 * @param request
	 * @param vo
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("listData")
	@ResponseBody
	public String listData(HttpServletRequest request,TTransferRecordsVO vo, Integer page, Integer rows){
		Page<TTransferRecordsVO> list = tTransferRecordsService.getPageResult(vo, page, rows);
		return BaseService.List2Json4FullDate(list.getRows(), list.getFooter(), list.getTotal());
	}
	
	/**
	 * 新增、修改调动记录页面
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("modTransfer")
	public String modTransfer(HttpServletRequest request, Integer id){
		if(id != null){
			TTransferRecords record = tTransferRecordsService.getTransRecord(id);
			request.setAttribute("record", record);
		}
		return "transfer/modTransfer";
	}
	
	/**
	 * 更新调动记录
	 * @return
	 */
	@RequestMapping("updateTransFer")
	@ResponseBody
	public void updateTransfer(HttpServletResponse response, @Validated TTransferRecords record,BindingResult result){
		JSONObject js = new JSONObject();
		try{
			tTransferRecordsService.saveOrUpdateRecord(record);
			js = createResult(true, "保存成功");
		}catch(Exception e){
			js = createResult(false, "操作失败：" + e.getMessage());
			e.printStackTrace();
		}
		ResponseTool.write(response, js);
	}
	
	@RequestMapping("getTransType")
	@ResponseBody
	public String getTransType(){
		List<SelectVO> list = tTransferRecordsService.getSelectList();
		return JSONArray.fromObject(list).toString();
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping("delTransRecord")
	@ResponseBody
	public void delTransRecord(HttpServletResponse response, Integer id){
		JSONObject js = new JSONObject();
		try{
			tTransferRecordsService.deleteRecord(id);
			js = createResult(true, "删除成功");
		}catch(Exception e){
			js = createResult(false, "删除失败：" + e.getMessage());
			e.printStackTrace();
		}
		ResponseTool.write(response, js);
	}
	
}
