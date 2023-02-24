package site.itwill10.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

//파일 다운로드 기능을 제공하는 클래스 - Spring Bean으로 등록
//BeanNameViewResolver에 의해 실행되는 클래스는 반드시 AbstractView 클래스를 상속받아 작성
// => renderMergedOutputModel() 메소드 오버라이드 선언
public class FileDownload extends AbstractView {
	//클라이언트에 응답할 파일형식(MimeType) 변경 - 파일 다운로드 가능
	public FileDownload() {
		//AbstractView.setContentType(String mimeType) : 응답 파일형식을 변경하는 메소드
		setContentType("application/download; utf-8"); 
	}
	
	//BeanNameViewResolver에 의해 자동 호출되는 메소드
	// => Map 자료형의 매개변수에는 요청 처리 메소드에서 Model에 의해 제공되는 정보 저장
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//맵키를 이용하여 맵값을 반환받아 저장 - 다운로드 파일정보
		// => Object 타입으로 반환되므로 명시적 객체 형변환
		String uploadDir=(String)model.get("uploadDir");
		String uploadFilename=(String)model.get("uploadFilename");
		String originFilename=(String)model.get("originFilename");
		
		//다운로드 파일정보를 이용하여 File 객체 생성
		File downloadFile=new File(uploadDir, uploadFilename);
		
		//클라이언트에 파일을 전달하기 위한 응답정보 변경
		response.setContentType(getContentType());
		response.setContentLength((int)downloadFile.length());
		
		//클라이언트에게 다운로드 파일명 전달
		originFilename=URLEncoder.encode(originFilename,"utf-8");
		response.setHeader("Content-Disposition", "attachement;filename=\""+originFilename+"\";");
		
		//클라이언트에게 원시데이타를 전달하기 위한 출력스트림을 반환받아 저장
		OutputStream out=response.getOutputStream();
		
		FileInputStream in=null;
		try {
			//다운로드 파일을 원시데이타로 읽기 위한 입력스트림을 생성하여 저장
			in=new FileInputStream(downloadFile);
			
			/*
			//입력스트림에서 원시데이타를 읽어 출력스트임으로 전달 - 다운로드
			while(true) {
				int readByte=in.read();
				if(readByte==-1) break;
				out.write(readByte);
			}
			*/
			
			//Spring Framework에서 제공되는 FileCopyUtils 클래스의 copy() 메소드를 호출하여 파일 복사 - 다운로드
			FileCopyUtils.copy(in, out);	
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				in.close();
			} catch (Exception e) {}
		}
	}

}
