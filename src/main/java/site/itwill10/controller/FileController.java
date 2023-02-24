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

//���� ���ε� ���
//1.commons-fileupload ���̺귯�� ���� ó�� - pom.xml
//2.���� ���ε� ����� �����ϴ� Ŭ������ Spring Bean���� ��� - servlet-context.xml
//3.������ ���޹޾� ���ε� ó��

@Controller
public class FileController {
	//�ʵ忡 WebApplicationContext ��ü(Spring Container)�� ������ ó��
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private FileBoardService fileBoardService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload() {
		return "file/upload";
	}
	
	/*
	//MultipartHttpServletRequest : [multipart/form-data]�� ���޵Ǵ� ���� �Ǵ� ���� ó���ϱ� ���� ��ü
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest request) throws IOException {
		String uploader=request.getParameter("uploader");
		
		//MultipartHttpServletRequest.getFile(String name) : ���� ������ MultipartFile ��ü ��ȯ�ϴ� �޼ҵ�
		//MultipartFile : ���޵� ������ ������ �����ϴ� ��ü
		MultipartFile uploadFile=request.getFile("uploadFile");
		
		//�������Ͽ� ���� ��ȿ�� �˻� : ���� ���� ����, ���� ����, ���� ũ�� ��
		//MultipartFile.isEmpty() : ���������� ���� ��� true�� ��ȯ�ϴ� �޼ҵ�
		if(uploadFile.isEmpty()) {
			return "file/upload_fail";
		}
		
		//MultipartFile.getContentType() : ���������� ����(MimeType)�� ��ȯ�ϴ� �޼ҵ�
		System.out.println(uploadFile.getContentType());
		//MultipartFile.getBytes() : ���������� byte �迭�� ��ȯ�Ͽ� ��ȯ�ϴ� �޼ҵ�
		System.out.println(uploadFile.getBytes().length);
		
		//���������� �����ϱ� ���� ���ε� ���͸��� �ý��� ��θ� ��ȯ�޾� ����
		String uploadDir=request.getServletContext().getRealPath("/resources/images");
		
		//���������� ������ �����ϱ� ���� File ��ü ����
		//MultipartFile.getOriginalFilename() : �������ϸ��� ��ȯ�ϴ� �޼ҵ�
		File file=new File(uploadDir, uploadFile.getOriginalFilename());

		//MultipartFile.transferTo(File file) : ���������� ������ �����ϴ� �޼ҵ�
		uploadFile.transferTo(file);//���ε� ó��
		
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
		
		//WebApplicationContext ��ü�� �̿��Ͽ� ������ ���ε� ���丮�� �ý��� ��θ� ��ȯ�޾� ����
		String uploadDir=context.getServletContext().getRealPath("/resources/images");
		System.out.println("uploadDir = "+uploadDir);

		File file=new File(uploadDir, uploadFile.getOriginalFilename());

		uploadFile.transferTo(file);//���ε� ó��
		
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
		//�������Ͽ� ���� ��ȿ�� �˻�
		if(fileBoard.getFile().isEmpty()) {
			return "file/file_upload";
		}
		
		String uploadDir=context.getServletContext().getRealPath("/WEB-INF/upload");
		
		//�������ϸ��� ��ȯ�޾� ����
		String origin=fileBoard.getFile().getOriginalFilename();
		
		//���ε� ���ϸ��� ������(TimeStamp)���� �����Ͽ� ����
		// => ���ε� ������ �ߺ����� �ʵ��� �ۼ�
		String upload=System.currentTimeMillis()+"";
		
		//���� ���ε�
		fileBoard.getFile().transferTo(new File(uploadDir, upload));
		
		//DTO Ŭ������ �ʵ尪 ����
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
		//���� ���丮�� ����� ���� ����
		FileBoard fileBoard=fileBoardService.getFileBoard(num);
		String uploadDir=context.getServletContext().getRealPath("/WEB-INF/upload");
		new File(uploadDir, fileBoard.getUpload());
		
		//FILEBOARD ���̺� ����� �Խñ� ����
		fileBoardService.removeFileBoard(num);
		
		return "redirect:/file_list"; 
	}
	*/
	
	@RequestMapping("/file_delete/{num}")
	public String fileDelete(@PathVariable int num) {
		//���� ���丮�� ����� ���� ����
		FileBoard fileBoard=fileBoardService.getFileBoard(num);
		String uploadDir=context.getServletContext().getRealPath("/WEB-INF/upload");
		new File(uploadDir, fileBoard.getUpload()).delete();
		
		//FILEBOARD ���̺� ����� �Խñ� ����
		fileBoardService.removeFileBoard(num);
		
		return "redirect:/file_list";
	}
	
	//��û ó�� �޼ҵ忡�� ��û ó�� �� �ٿ�δ� ���α׷��� �����Ͽ� ���� 
	// => ��ȯ�Ǵ� ���ڿ��� �̿��Ͽ� ���α׷��� ����ǵ��� ViewResolver ���� - servlet-context.xml
	@RequestMapping("/file_download/{num}")
	public String fileDownload(@PathVariable int num, Model model) {
		FileBoard fileBoard=fileBoardService.getFileBoard(num);
		
		//�ٿ�ε� ���������� �ٿ�δ� ���α׷�(Spring Bean)���� ����
		model.addAttribute("uploadDir", context.getServletContext().getRealPath("/WEB-INF/upload"));
		model.addAttribute("uploadFilename", fileBoard.getUpload());
		model.addAttribute("originFilename", fileBoard.getOrigin());
		
		//���α׷�(Spring Bean)�� beanName ��ȯ
		return "fileDownload";
	}
}











