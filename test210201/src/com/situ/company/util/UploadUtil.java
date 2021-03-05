package com.situ.company.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadUtil {

	public static Map<String, Object> upload(HttpServletRequest req) throws FileUploadException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = upload.parseRequest(req);
		Map<String, Object> result = new HashMap<>();
		List<String> files = new ArrayList<>();

		for (FileItem item : items) {
			if (item.isFormField()) {
				String val = item.getString();
				val = new String(val.getBytes("ISO-8859-1"), "UTF-8");
				result.put(item.getFieldName(), val);
			} else {
				String fn = getFilename(item);
				FileOutputStream fo = new FileOutputStream(getPath() + "/" + fn);
				InputStream is = item.getInputStream();
				byte[] b = new byte[1024 * 1024];
				int len = -1;
				while (-1 != (len = is.read(b))) {
					fo.write(b, 0, len);
				}
				fo.flush();
				fo.close();
				is.close();
				files.add(fn);
			}
		}
		result.put("list", files);
		return result;
	}

	private static String getFilename(FileItem item) {
		String fn = item.getName();
		String name = UUID.randomUUID().toString();
		return name + fn.substring(fn.indexOf("."));
	}

	private static File getPath() {// E:\思途学习记录\java
		File f = new File("C:/Users/TaoTao/Desktop/思途作业文件/网上商城项目/图片");
		if (!f.exists())
			f.mkdir();
		return f;
	}

	public static void delFile(String filename) {
		File file = new File(getPath() + "/" + filename);
		file.delete();
	}

	private UploadUtil() {

	}

}
