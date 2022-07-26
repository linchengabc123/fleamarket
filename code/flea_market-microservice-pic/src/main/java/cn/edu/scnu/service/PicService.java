package cn.edu.scnu.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fleamarket.common.utils.UploadUtil;
import com.fleamarket.common.vo.PicUploadResult;

@Service
public class PicService {
	
	@Value("${pic.pathDirPrefix}")
	private String pathDirPrefix;
	
	@Value("${pic.urlPrefix}")
	private String urlPreparePrefix;
	
	
	public PicUploadResult picUpload(MultipartFile pic) {
		System.out.println("PicService");
		PicUploadResult result = new PicUploadResult();
		//判断后缀合法
		String originName = pic.getOriginalFilename();
		String extName = originName.substring(originName.lastIndexOf("."));
		boolean isok = extName.matches(".(jpg|png|gif|JPG|PNG|GIF)$");
		if(!isok){
			result.setError(1);
			return result;
		}
		//判断是不是木马
		try{
			BufferedImage bufImg = ImageIO.read(pic.getInputStream());
			bufImg.getWidth();
			bufImg.getHeight();
		}catch(IOException e){
			e.printStackTrace();
			result.setError(1);
			return result;
		}
		//创建以upload开始的路径
		String dir ="/"+UploadUtil.getUploadPath(originName, "upload")+"/";
		//创建nginx访问的静态目录，pathDir,pathDirPrefix
		System.out.println("dir:"+dir);
			String pathDir=pathDirPrefix+dir;
		System.out.println("pathDir"+pathDir);
			File file = new File(pathDir);
			if(!file.exists()){
				file.mkdirs();
			}
		//		5)创建urlPrefix （http://www.easymalll.com/upload/2/2/c/a/b/0/e/b/)		
			String urlPrefix = urlPreparePrefix + dir;
		System.out.println("urlPrefix"+urlPrefix);
		//		6)拼接图片名称,将图片重命名 uuid表示图片存储访问的名称
			String fileName = UUID.randomUUID().toString()+extName;
		System.out.println("fileName"+fileName);
		//		7上传文件到磁盘路径
			try{
				pic.transferTo(new File(pathDir+fileName));
			}catch(Exception e){
				e.printStackTrace();
				result.setError(1);
				return result;
			}
		//		8)返回urlPrefix+图片名称的路径
			result.setUrl(urlPrefix+fileName);
			return result;
	}

}