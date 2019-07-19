package com.ego.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ego.service.UploadService;

@Controller
public class UploadController {

	@Autowired
	private UploadService uploadServiceImpl;
	
	/**
	 * 文件上传
	 * @throws IOException 
	 */
	@RequestMapping("/pic/upload")
	@ResponseBody
	public Map<String, Object> upload(MultipartFile uploadFile) throws IOException{
		
		Map<String,Object> map = null;
		try {
			map=uploadServiceImpl.upload(uploadFile);;
		} catch (IOException e) {
			e.printStackTrace();
			map.put("error", 1);
			map.put("message","上传图片时服务器异常");
		}
		return map;
		
	}
}
