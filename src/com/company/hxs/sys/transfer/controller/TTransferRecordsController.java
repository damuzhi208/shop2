package com.company.hxs.sys.transfer.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.hxs.common.Page;
import com.company.hxs.common.controller.BaseController;
import com.company.hxs.common.service.BaseService;
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
		return BaseService.List2Json4FullDate(list.getRows(), list.getTotal());
	}
	
	/**
	 * 新增、修改调动记录页面
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("modTransfer")
	public String modTransfer(HttpServletRequest request, Integer id){
		TTransferRecords record = tTransferRecordsService.getTransRecord(id);
		request.setAttribute("record", record);
		return "transfer/modTransfer";
	}
	
	/**
	 * 更新调动记录
	 * @return
	 */
	@RequestMapping("updateTransFer")
	@ResponseBody
	public String updateTransfer(TTransferRecords record){
		JSONObject js = new JSONObject();
		try{
			tTransferRecordsService.saveOrUpdate(record);
			js = createResult(true, null);
		}catch(Exception e){
			js = createResult(false, "操作失败：" + e.getMessage());
		}
		return js.toString();
	}
	
}
