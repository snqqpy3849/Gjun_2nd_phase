import java.io.*;
public class PicCopy {

	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream("c:/tmp/img2.jpg");
		OutputStream out = new FileOutputStream("c:/tmp/img2-1.jpg");
		
		// �����W�@�ӫʥ]�O 8K�Acpu �B�z�̤p���O 4K�A�ҥH�o�� [4096] ���ӬO�̨Ϊ��g�k
		byte[] bt = new byte[4096];
		int size = 0;
		while((size=in.read(bt))>0) {
			out.write(bt);			
		}
		in.close();
		out.close();
		System.out.println("Saved");

	}

}
