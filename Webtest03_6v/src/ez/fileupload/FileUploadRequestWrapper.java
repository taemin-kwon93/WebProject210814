package ez.fileupload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import java.util.Map;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Iterator;
//������������ ���ٷ� ó���� �ȴ�.
//�� Ŭ������ �̿��ؾ� ���ε� ó���� �ȴ�.
public class FileUploadRequestWrapper extends HttpServletRequestWrapper{
	//FileUploadRequestWrapper�� HttpServletRequestWrapper�� ��ӹ޴´�.
	private boolean multipart = false;
	
	private HashMap parameterMap;//�Ϲ� �� �����͸� ������� HashMap��ü
	private HashMap fileItemMap;//���ε�� ������ ������� HashMap��ü
	
	//ù��° FileUploadRequestWrapper
	public FileUploadRequestWrapper(HttpServletRequest request) throws FileUploadException, UnsupportedEncodingException{
		this(request, -1, -1, null);
	}
	
	//�ι�° FileUploadRequestWrapper(���ε�� ����request, threshold���ڰ� ,max=���ε� �ִ�ũ��, ����� ���)
	public FileUploadRequestWrapper(HttpServletRequest request, int threshold, int max, String repositoryPath) throws FileUploadException, UnsupportedEncodingException{
		super(request);
		parsing(request, threshold, max, repositoryPath);//threshold : �Ѱ���
	}

	private void parsing(HttpServletRequest request, int threshold, int max, String repositoryPath)throws FileUploadException, UnsupportedEncodingException {
		if(FileUpload.isMultipartContent(request)) {//boolean isMultipartContent�� true�̸� ���ε�
			multipart = true;
			
			parameterMap = new java.util.HashMap();//HashMap��ü�� ����
			fileItemMap = new java.util.HashMap();//HashMap��ü�� ����
			
			DiskFileUpload diskFileUpload = new DiskFileUpload();//DiskFileUpload��ü�� ����
			if(threshold != -1) {//threshold�� -1�� �ƴϸ�
				diskFileUpload.setSizeThreshold(threshold);//diskFileUpload�� Threshold���� �������ش�.
			}
			diskFileUpload.setSizeMax(max);
			if(repositoryPath != null) {
				diskFileUpload.setRepositoryPath(repositoryPath);//���ε� ��Ѹ� �����Ѵ�.
			}
			java.util.List list = diskFileUpload.parseRequest(request);//���ε带 ó���Ѵ�.
			for(int i=0; i<list.size(); i++) {
				FileItem fileItem = (FileItem) list.get(i);//list�� ����� FileItem��ü�� �����´�.
				String name = fileItem.getFieldName();//�ʵ��� �̸��� �����´�.
				
				if(fileItem.isFormField()) {//fileItem�� ���������� ��� �Ʒ� ���� ����.
					String value = fileItem.getString("EUC-KR");
					String[] values = (String[])parameterMap.get(name);
					if(values == null) {
						values = new String[] {value};
					}else {
						String[] tempValues = new String[values.length +1];
						System.arraycopy(values, 0, tempValues, 0, 1);
						tempValues[tempValues.length -1]= value;
						values = tempValues;
					}
					parameterMap.put(name, values);
				}else {//fileItem�� �Ϲ� �������� ��� �Ʒ� ���� ����
					fileItemMap.put(name, fileItem);
				}
			}
			addTo();
		}
	}

	public boolean isMultipartContent() {
		return multipart;
	}

	public String getParameter(String name) {
		if (multipart) {
			String[] values = (String[]) parameterMap.get(name);
			if (values == null)
				return null;
			return values[0];
		} else
			return super.getParameter(name);
	}

	public String[] getParameterValues(String name) {
		if (multipart)
			return (String[]) parameterMap.get(name);
		else
			return super.getParameterValues(name);
	}

	public Enumeration getParameterNames() {
		if (multipart) {
			return new Enumeration() {
				Iterator iter = parameterMap.keySet().iterator();

				public boolean hasMoreElements() {
					return iter.hasNext();
				}

				public Object nextElement() {
					return iter.next();
				}
			};
		} else {
			return super.getParameterNames();
		}
	}

	public Map getParameterMap() {
		if (multipart)
			return parameterMap;
		else
			return super.getParameterMap();
	}

	public FileItem getFileItem(String name) {
		if (multipart)
			return (FileItem) fileItemMap.get(name);
		else
			return null;
	}

	/**
	 * ���õ� FileItem ���� delete() �޼ҵ带 ȣ���Ѵ�.
	 */
	public void delete() {//�� delete�� ��� ����?
		if (multipart) {
			Iterator fileItemIter = fileItemMap.values().iterator();
			while (fileItemIter.hasNext()) {
				FileItem fileItem = (FileItem) fileItemIter.next();
				fileItem.delete();
			}
		}
	}

	public void addTo() {
		System.out.println(FileUploadRequestWrapper.class.getName());
		super.setAttribute(FileUploadRequestWrapper.class.getName(), this);
	}

	public static FileUploadRequestWrapper getFrom(HttpServletRequest request) {
		return (FileUploadRequestWrapper) request.getAttribute(FileUploadRequestWrapper.class.getName());
	}

	public static boolean hasWrapper(HttpServletRequest request) {
		if (FileUploadRequestWrapper.getFrom(request) == null) {
			return false;
		} else {
			return true;
		}
	}
}
