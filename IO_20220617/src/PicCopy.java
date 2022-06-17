import java.io.*;
public class PicCopy {

	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream("c:/tmp/img2.jpg");
		OutputStream out = new FileOutputStream("c:/tmp/img2-1.jpg");
		
		// 網路上一個封包是 8K，cpu 處理最小單位是 4K，所以這個 [4096] 應該是最佳的寫法
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
