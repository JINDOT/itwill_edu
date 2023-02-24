package site.itwill10.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import site.itwill10.dto.FileBoard;
import site.itwill10.service.FileBoardService;

//파일 업로드 방법
//1.commons-fileupload 라이브러리 빌드 처리 - pom.xml
//2.파일 업로드 기능을 제공하는 클래스를 Spring Bean으로 등록 - servlet-context.xml
//3.파일을 전달받아 업로드 처리

@Controller
public class FileController {
	//필드에 WebApplicationContext 객체(Spring Container)를 인젝션 처리
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private FileBoardService fileBoardService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload() {
		return "file/upload";
	}
	
	/*
	//MultipartHttpServletRequest : [multipart/form-data]로 전달되는 파일 또는 값을 처리하기 위한 객체
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest request) throws IOException {
		String uploader=request.getParameter("uploader");
		
		//MultipartHttpServletRequest.getFile(String name) : 전달 파일을 MultipartFile 객체 반환하는 메소드
		//MultipartFile : 전달된 파일의 정보를 저장하는 객체
		MultipartFile uploadFile=request.getFile("uploadFile");
		
		//전달파일에 대한 유효성 검사 : 파일 존재 유무, 파일 형식, 파일 크기 등
		//MultipartFile.isEmpty() : 전달파일이 없는 경우 true를 반환하는 메소드
		if(uploadFile.isEmpty()) {
			return "file/upload_fail";
		}
		
		//MultipartFile.getContentType() : 전달파일의 형식(MimeType)을 반환하는 메소드
		System.out.println(uploadFile.getContentType());
		//MultipartFile.getBytes() : 전달파일을 byte 배열로 변환하여 반환하는 메소드
		System.out.println(uploadFile.getBytes().length);
		
		//전달파일을 저장하기 위한 업로드 디렉터리의 시스템 경로를 반환받아 저장
		String uploadDir=request.getServletContext().getRealPath("/resources/images");
		
		//전달파일을 서버에 저장하기 위한 File 객체 생성
		//MultipartFile.getOriginalFilename() : 전달파일명을 반환하는 메소드
		File file=new File(uploadDir, uploadFile.getOriginalFilename());

		//MultipartFile.transferTo(File file) : 전달파일을 서버에 저장하는 메소드
		uploadFile.transferTo(file);//업로드 처리
		
		request.setAttribute("uploader", uploader);
		request.setAttribute("originalFilename", uploadFile.getOriginalFilename());
		
		return "file/upload_ok";
	}
	*/
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam String uploader
		,@RequestParam MultipartFile uploadFile, Model model) throws IOException {
		if(uploadFile.isEmpty()) {
			return "file/upload_fail";
		}
		
		//WebApplicationContext 객체를 이용하여 서버의 업로드 디렉토리의 시스템 경로를 반환받아 저장
		String uploadDir=context.getServletContext().getRealPath("/resources/images");
		System.out.println("uploadDir = "+uploadDir);

		File file=new File(uploadDir, uploadFile.getOriginalFilename());

		uploadFile.transferTo(file);//업로드 처리
		
		model.addAttribute("uploader", uploader);
		model.addAttribute("originalFilename", uploadFile.getOriginalFilename());
		
		return "file/upload_ok";
	}
	
	@RequestMapping(value = "/file_upload", method = RequestMethod.GET)
	public String fileUpload() {
		return "file/file_upload";
	}
	
	@RequestMapping(value = "/file_upload", method = RequestMethod.POST)
	public String fileUpload(@ModelAttribute FileBoard fileBoard) throws IllegalStateException, IOException {
		//전달파일에 대한 유효성 검사
		if(fileBoard.getFile().isEmpty()) {
			return "file/file_upload";
		}
		
		String uploadDir=context.getServletContext().getRealPath("/WEB-INF/upload");
		
		//전달파일명을 반환받아 저장
		String origin=fileBoard.getFile().getOriginalFilename();
		
		//업로드 파일명을 고유값(TimeStamp)으로 설정하여 저장
		// => 업로드 파일이 중복되지 않도록 작성
		String upload=System.currentTimeMillis()+"";
		
		//파일 업로드
		fileBoard.getFile().transferTo(new File(uploadDir, upload));
		
		//DTO 클래스의 필드값 변경
		fileBoard.setOrigin(origin);
		fileBoard.setUpload(upload);
		
		fileBoardService.addFileBoard(fileBoard);
		
		return "redirect:/file_list";
	}
	
	@RequestMapping("/file_list")
	public String fileList(Model model) {
		model.addAttribute("fileBoardList", fileBoardService.getFileBoardList());
		return "file/file_list";
	}
	
	/*
	@RequestMapping("/file_delete")
	public String fileDelete(@RequestParam int num) {
		//서버 디렉토리에 저장된 파일 삭제
		FileBoard fileBoard=fileBoardService.getFileBoard(num);
		String uploadDir=context.getServletContext().getRealPath("/WEB-INF/upload");
		new File(uploadDir, fileBoard.getUpload());
		
		//FILEBOARD 테이블에 저장된 게시글 삭제
		fileBoardService.removeFileBoard(num);
		
		return "redirect:/file_list"; 
	}
	*/
	
	@RequestMapping("/file_delete/{num}")
	public String fileDelete(@PathVariable int num) {
		//서버 디렉토리에 저장된 파일 삭제
		FileBoard fileBoard=fileBoardService.getFileBoard(num);
		String uploadDir=context.getServletContext().getRealPath("/WEB-INF/upload");
		new File(uploadDir, fileBoard.getUpload()).delete();
		
		//FILEBOARD 테이블에 저장된 게시글 삭제
		fileBoardService.removeFileBoard(num);
		
		return "redirect:/file_list";
	}
	
	//요청 처리 메소드에서 요청 처리 후 다운로더 프로그램을 실행하여 응답 
	// => 반환되는 문자열을 이용하여 프로그램이 실행되도록 ViewResolver 설정 - servlet-context.xml
	@RequestMapping("/file_download/{num}")
	public String fileDownload(@PathVariable int num, Model model) {
		FileBoard fileBoard=fileBoardService.getFileBoard(num);
		
		//다운로드 파일정보를 다운로더 프로그램(Spring Bean)에게 제공
		model.addAttribute("uploadDir", context.getServletContext().getRealPath("/WEB-INF/upload"));
		model.addAttribute("uploadFilename", fileBoard.getUpload());
		model.addAttribute("originFilename", fileBoard.getOrigin());
		
		//프로그램(Spring Bean)의 beanName 반환
		return "fileDownload";
	}
}











