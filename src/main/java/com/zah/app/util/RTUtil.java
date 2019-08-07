package com.zah.app.util;

import com.zah.app.comm.err.Err;
import org.springframework.ui.ModelMap;


public class RTUtil {
	public static ModelMap getMap(Err error, String msg) {
		ModelMap result = new ModelMap();
		result.addAttribute("code", error.getValue());
		result.addAttribute("msg", msg);
		return result;
	}

	public static ModelMap getMap(Err error) {
		ModelMap result = new ModelMap();
		result.addAttribute("code", error.getValue());
		result.addAttribute("msg", error.getErrorMsg());
		return result;
	}

	public static ModelMap getOKMap(String msg) {
		ModelMap result = new ModelMap();
		result.addAttribute("code", Err.ERR_NO_ERR.getValue());
		result.addAttribute("msg", msg);
		return result;
	}

	public static ModelMap getOKMap() {
		ModelMap result = new ModelMap();
		result.addAttribute("code", Err.ERR_NO_ERR.getValue());
		result.addAttribute("msg", Err.ERR_NO_ERR.getErrorMsg());
		return result;
	}

}
